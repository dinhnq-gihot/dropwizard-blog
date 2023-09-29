package com.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import jakarta.validation.constraints.Email;

public class UpdateUserDTO {
    @Nullable
    private String username;

    @Nullable
    @Email
    private String email;

    @JsonCreator
    public UpdateUserDTO(@JsonProperty("username") String username, @JsonProperty("email") String email) {
        this.username = username;
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

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }
}
