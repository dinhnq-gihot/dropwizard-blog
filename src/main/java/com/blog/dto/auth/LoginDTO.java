package com.blog.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

    @JsonCreator
    public LoginDTO(@JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}
