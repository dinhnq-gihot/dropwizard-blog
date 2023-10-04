package com.blog.auth;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.blog.auth.principals.AuthUser;
import com.blog.db.UserDAO;
import com.blog.entity.UserEntity;

import io.dropwizard.auth.Authorizer;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.container.ContainerRequestContext;

public class JwtAuthorizer implements Authorizer<AuthUser> {
    // private final UserDAO userDAO;

    // public JwtAuthorizer(UserDAO userDAO) {
    //     this.userDAO = userDAO;
    // }

    @Override
    public boolean authorize(AuthUser principal, String role, @Nullable ContainerRequestContext requestContext) {
        if (role.contains(principal.getRole())) {
            return true;
        }
        return false;
    }
}
