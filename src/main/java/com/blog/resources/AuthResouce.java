package com.blog.resources;

import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IAuthService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    public UserEntity signup(@NotNull @Valid SignupDTO user) {
        System.out.println(user);
        return service.signup(user);
    }

    @POST
    @Path("login")
    @Timed
    @UnitOfWork
    public String login(@NotNull @Valid LoginDTO user) {
        System.out.println(user);
        return service.login(user);
    }
}
