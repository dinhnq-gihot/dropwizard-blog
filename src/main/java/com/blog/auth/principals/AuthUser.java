package com.blog.auth.principals;

import io.dropwizard.auth.PrincipalImpl;

public class AuthUser extends PrincipalImpl {

    private String email;
    private String role; // A set of roles associated with the user

    public AuthUser(String name, String email, String role) {
        super(name);
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
