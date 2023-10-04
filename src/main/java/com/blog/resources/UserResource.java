package com.blog.resources;

import java.util.List;
import java.util.OptionalLong;

import com.blog.auth.principals.AuthUser;
import com.blog.dto.response.ErrorResponseDTO;
import com.blog.dto.response.SuccessResponseDTO;
import com.blog.dto.user.ResponseUserDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;
import com.blog.service.IUserService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response getAllUser(@Auth AuthUser user) {
        final List<ResponseUserDTO> allUsers = service.getAllUser();
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", allUsers);
        return Response.ok(res).build();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Response getUserById(@PathParam("id") OptionalLong id) {
        ResponseUserDTO responseUserDTO = service.getUserById(id.getAsLong());
        if (responseUserDTO == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(404, "ERROR", "User id not found");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", responseUserDTO);
        return Response.ok(res).build();
    }

    @PATCH
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Response updateUser(@PathParam("id") OptionalLong id, @NotNull @Valid UpdateUserDTO user) {
        ResponseUserDTO responseUserDTO = service.updateUser(id.getAsLong(), user);
        if (responseUserDTO == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(404, "ERROR", "User id not found");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", responseUserDTO);
        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Response deleteUser(@PathParam("id") OptionalLong id) {
        ResponseUserDTO responseUserDTO = service.deleteUser(id.getAsLong());
        if (responseUserDTO == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(404, "ERROR", "User id not found");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", responseUserDTO);
        return Response.ok(res).build();
    }

    @GET
    @Path("/{id}/role")
    @Timed
    @UnitOfWork
    public Response getRoleUserById(@PathParam("id") OptionalLong id) {
        RoleEntity role = service.getRoleUserById(id.getAsLong());
        return Response.ok(role).build();
    }
}
