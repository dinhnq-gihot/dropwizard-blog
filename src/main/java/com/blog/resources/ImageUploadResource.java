package com.blog.resources;

import java.io.InputStream;
import java.util.Optional;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.blog.auth.principals.AuthUser;
import com.blog.dto.response.ErrorResponseDTO;
import com.blog.dto.response.ResponseUserDTO;
import com.blog.dto.response.SuccessResponseDTO;
import com.blog.service.IImageService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/image")
@Produces(MediaType.APPLICATION_JSON)
public class ImageUploadResource {
    private IImageService imageService;

    public ImageUploadResource(IImageService imageService) {
        this.imageService = imageService;
    }

    @POST
    @Path("upload")
    @Timed
    @UnitOfWork
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
            @Auth AuthUser user) {
        ResponseUserDTO userDTO = this.imageService.upload(fileInputStream, contentDispositionHeader,
                Long.parseLong(user.getName()));
        if (userDTO != null) {
            final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", userDTO);
            return Response.ok(res).build();
        }
        final ErrorResponseDTO res = new ErrorResponseDTO(400, "ERROR", "Upload failed");
        return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
    }

    @GET
    @Path("/download/{imageName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("imageName") Optional<String> imageName) {
        return imageService.download(imageName.orElse(""));
    }
}
