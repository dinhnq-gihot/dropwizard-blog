package com.blog.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class SignupDTO {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @Nullable
    private String role;

    @JsonCreator
    public SignupDTO(@JsonProperty("email") String email,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("role") String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("username")
    public String getUsername() {
        return this.username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("role")
    public String getRole() {
        return this.role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

}
