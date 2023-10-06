package com.blog.resources;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

import com.blog.auth.principals.AuthUser;
import com.blog.dto.response.ErrorResponseDTO;
import com.blog.dto.response.ResponsePaginationDTO;
import com.blog.dto.response.ResponseUserDTO;
import com.blog.dto.response.SuccessResponseDTO;
import com.blog.dto.user.UpdateRoleOfUserDTO;
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
    // @RolesAllowed("admin, user")
    public Response getAllUser(@Auth AuthUser user,
            @QueryParam("page") OptionalInt page,
            @QueryParam("limit") OptionalInt limit,
            @QueryParam("username") Optional<String> username) {
        final ResponsePaginationDTO allUsers = service.getAllUser(username.orElse(""), page.orElse(1), limit.orElse(5));
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
    @Path("")
    @Timed
    @UnitOfWork
    public Response updateUser(@Auth AuthUser currentUser, @NotNull @Valid UpdateUserDTO user) {
        ResponseUserDTO responseUserDTO = service.updateUser(Long.parseLong(currentUser.getName()), user);
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
    @RolesAllowed("admin")
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
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", role);
        return Response.ok(res).build();
    }

    @PATCH
    @Path("/{id}/role")
    @Timed
    @UnitOfWork
    @RolesAllowed("admin")
    public Response updateRoleOfUser(@PathParam("id") OptionalLong id, @NotNull @Valid UpdateRoleOfUserDTO dto) {
        ResponseUserDTO user = service.updateRoleOfUser(id.getAsLong(), dto.getRole());
        if (user == null) {
            final ErrorResponseDTO res = new ErrorResponseDTO(404, "ERROR", "User id or role invalid");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
        final SuccessResponseDTO res = new SuccessResponseDTO(200, "OK", user);
        return Response.ok(res).build();
    }
}
