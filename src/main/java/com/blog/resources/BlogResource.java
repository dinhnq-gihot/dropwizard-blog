package com.blog.resources;

import java.util.List;

import org.hibernate.annotations.Parameter;

import com.blog.auth.annotations.RolesAllowed;
import com.blog.auth.principals.AuthUser;
import com.blog.dto.blog.CreateBlogDTO;
import com.blog.dto.blog.UpdateBlogDTO;
import com.blog.entity.BlogEntity;
import com.blog.service.IBlogService;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("blog")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource {
    private IBlogService blogService;

    public BlogResource(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GET
    @Path("")
    @Timed
    @UnitOfWork
    public List<BlogEntity> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @POST
    @Path("")
    @Timed
    @UnitOfWork
    @RolesAllowed("admin, user")
    public BlogEntity createBlog(@Auth AuthUser currentUser, @NotNull @Valid CreateBlogDTO dto) {
        return this.blogService.createBlog(Long.parseLong(currentUser.getName()), dto);
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public BlogEntity getBlogById(@PathParam("id") Long id) {
        return this.blogService.getBlogById(id);
    }

    @PATCH
    @Path("/{id}")
    @Timed
    @UnitOfWork
    @RolesAllowed("admin, user")
    public BlogEntity updateBlog(@Auth AuthUser currentUser, @PathParam("id") Long blogId, UpdateBlogDTO dto) {
        return this.blogService.updateBlog(Long.parseLong(currentUser.getName()), blogId, dto);
    }

    @DELETE
    @Path("/{id}")
    @Timed
    @UnitOfWork
    @RolesAllowed("admin, user")
    public BlogEntity deleteBlog(@Auth AuthUser currentUser, @PathParam("id") Long blogId) {
        return blogService.deleteBlog(Long.parseLong(currentUser.getName()), blogId);
    }
}
