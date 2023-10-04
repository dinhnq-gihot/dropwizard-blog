package com.blog.auth;

import java.util.Optional;

import org.eclipse.jetty.server.Authentication.User;

import com.blog.auth.principals.AuthUser;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;

public class JwtAuthenticator implements Authenticator<String, AuthUser> {
    private final JwtUtil jwtUtil;

    public JwtAuthenticator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<AuthUser> authenticate(String credentials) throws AuthenticationException {
        System.out.println(credentials);
        Claims claims = jwtUtil.parseToken(credentials);

        // RolesAllowed rolesAnnotation = getRolesAnnotation(requestContext);
        // Perform additional checks if needed
        String sub = claims.get("sub", String.class);
        String email = claims.get("email", String.class);
        String role = claims.get("role", String.class);

        return Optional.of(new AuthUser(sub, email, role));
    }
}
