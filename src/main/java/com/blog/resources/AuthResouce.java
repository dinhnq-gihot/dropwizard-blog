package com.blog.resources;

import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.dto.response.ErrorResponseDTO;
import com.blog.dto.response.SuccessResponseDTO;
import com.blog.dto.user.ResponseUserDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IAuthService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResouce {
    private IAuthService service;

    public AuthResouce(IAuthService service) {
        this.service = service;
    }

    @POST
    @Path("sign-up")
    @Timed
    @UnitOfWork
    public Response signup(@NotNull @Valid SignupDTO user) {
        final ResponseUserDTO responseUserDTO = service.signup(user);
        if (responseUserDTO == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(406, "ERROR", "Email or username existed");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", responseUserDTO);
        return Response.ok(res).build();
    }

    @POST
    @Path("login")
    @Timed
    @UnitOfWork
    public Response login(@NotNull @Valid LoginDTO user) {
        String token = service.login(user);
        if (token == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(401, "ERROR", "Email or password wrong");
            return Response.status(Response.Status.UNAUTHORIZED).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", token);
        return Response.ok(res).build();
    }
}
