package com.blog.resources;

import com.codahale.metrics.annotation.Timed;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloworldResource {
    @GET
    @Timed
    public String sayHello() {
        return "Hello World";
    }
}
