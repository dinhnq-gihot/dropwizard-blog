package com.blog.resources;

import java.util.List;
import java.util.OptionalLong;

import com.blog.dto.user.SignupDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IUserService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private IUserService service;

    public UserResource(IUserService service) {
        this.service = service;
    }

    @GET
    @Path("")
    @Timed
    @UnitOfWork
    public List<UserEntity> getAllUser() {
        return service.getAllUser();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public UserEntity getUserById(@PathParam("id") OptionalLong id) {
        return service.getUserById(id.getAsLong());
    }

    @PATCH
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public UserEntity updateUser(@PathParam("id") OptionalLong id, @NotNull @Valid UpdateUserDTO user) {
        return service.updateUser(id.getAsLong(), user);
    }

    // @DELETE
    // @Path("/{id}")
    // @Timed
    // @UnitOfWork
    // public UserEntity deleteUser(@PathParam("id") OptionalLong id) {
    // return service.deleteUser(id.getAsLong());
    // }

    @POST
    @Path("sign-up")
    @Timed
    @UnitOfWork
    public UserEntity signup(@NotNull @Valid SignupDTO user) {
        System.out.println(user);
        return service.signup(user);
    }
}
