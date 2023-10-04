package com.blog.auth;

import io.jsonwebtoken.Claims;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.ResourceModel;

import com.blog.auth.annotations.RolesAllowed;

@Priority(Priorities.AUTHORIZATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String requestPath = requestContext.getUriInfo().getPath();

        System.out.println(requestPath);
        // Skip JWT validation for the login path
        if (requestPath.equals("auth/login")) {
            // Allow login requests to proceed without JWT validation
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer".length()).trim();

            try {
                Claims claims = jwtUtil.parseToken(token);

                // RolesAllowed rolesAnnotation = getRolesAnnotation(requestContext);
                // Perform additional checks if needed
                if (claims.get("exp", Integer.class) - claims.get("iat", Integer.class) < 0) {
                    throw new Exception();
                }
                // String userRole = claims.get("role", String.class);
                // RolesAllowed allowedRoles =
                // requestContext.getRequest().getResourceMethod().getAnnotation(RolesAllowed.class);
                // String[] requiredRoles = allowedRoles.value();

                System.out.println(claims.get("iat"));
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    // private RolesAllowed getRolesAnnotation(ContainerRequestContext
    // requestContext) {
    // // Check if the @RequiresRoles annotation is present on the requested
    // resource
    // // or method
    // RolesAllowed rolesAnnotation =
    // requestContext.getResourceClass().getAnnotation(RolesAllowed.class);

    // if (rolesAnnotation == null) {
    // rolesAnnotation =
    // requestContext.getResourceClass().getAnnotation(RequiresRoles.class);
    // }

    // return rolesAnnotation;
    // }
}
