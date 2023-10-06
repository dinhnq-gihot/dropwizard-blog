package com.blog.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.eclipse.jetty.server.Authentication.User;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.blog.converter.UserConverter;
import com.blog.db.UserDAO;
import com.blog.dto.response.ResponseUserDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IImageService;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

public class ImageServiceImpl implements IImageService {
    private UserDAO userDAO;

    public ImageServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private String createUUIDFilename(String filename) {
        int lastIndex = filename.lastIndexOf('.');
        String extension = "";

        if (lastIndex != -1) {
            extension = filename.substring(lastIndex);
            filename = filename.substring(0, lastIndex);
        }

        UUID uuid = UUID.randomUUID();

        return filename + "_" + uuid.toString() + extension;
    }

    @Override
    public ResponseUserDTO upload(InputStream fileInputStream, FormDataContentDisposition contentDispositionHeader,
            Long userId) {
        final String uploadDirectory = "./src/main/resources/uploads/";

        String uploadedFileName = this.createUUIDFilename(contentDispositionHeader.getFileName());

        try {
            // Create the upload directory if it doesn't exist
            if (!Files.exists(Paths.get(uploadDirectory))) {
                Files.createDirectories(Paths.get(uploadDirectory));
            }

            // Save the uploaded file to the specified directory
            OutputStream outputStream = new FileOutputStream(new File(uploadDirectory, uploadedFileName));
            int read;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();

            UserEntity userEntity = userDAO.findById(userId);
            if (userEntity == null) {
                throw new NotFoundException();
            }
            userEntity.setProfileImage(uploadedFileName);
            UserEntity newEntity = userDAO.save(userEntity);
            ResponseUserDTO userDTO = UserConverter.toResponseDTO(newEntity);

            return userDTO;
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            e.printStackTrace();
            return null;
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Response download(String imageName) {
        final String imageDirectory = "./src/main/resources/uploads/";

        try {
            File imageFile = new File(imageDirectory, imageName);

            if (!imageFile.exists() || !imageFile.isFile()) {
                // return null;
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

            // return imageBytes;
            return Response.ok(imageBytes)
                    .header("Content-Disposition", "attachment; filename=" + imageName)
                    .build();
        } catch (Exception e) {
            // return null;
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error downloading image").build();
        }
    }

}
