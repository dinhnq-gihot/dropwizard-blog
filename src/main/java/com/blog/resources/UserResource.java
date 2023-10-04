package com.blog.resources;

import java.util.List;
import java.util.OptionalLong;

import com.blog.auth.principals.AuthUser;
import com.blog.dto.auth.SignupDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;
import com.blog.service.IUserService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private IUserService service;

    public UserResource(IUserService service) {
        this.service = service;
    }

    @GET
    @Path("")
    @Timed
    @UnitOfWork
    @RolesAllowed("admin, user")
    public List<UserEntity> getAllUser(@Auth AuthUser user) {
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

    @DELETE
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public UserEntity deleteUser(@PathParam("id") OptionalLong id) {
        return service.deleteUser(id.getAsLong());
    }

    @GET
    @Path("/{id}/role")
    @Timed
    @UnitOfWork
    public RoleEntity getRoleUserById(@PathParam("id") OptionalLong id) {
        return service.getRoleUserById(id.getAsLong());
    }
}
