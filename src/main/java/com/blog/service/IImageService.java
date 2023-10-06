package com.blog.service;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.blog.dto.response.ResponseUserDTO;

import jakarta.ws.rs.core.Response;

public interface IImageService {
    public ResponseUserDTO upload(InputStream fileInputStream, FormDataContentDisposition contentDispositionHeader,
            Long userId);

    public Response download(String imageName);
}
