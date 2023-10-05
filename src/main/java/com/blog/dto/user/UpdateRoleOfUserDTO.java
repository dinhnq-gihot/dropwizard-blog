package com.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class UpdateRoleOfUserDTO {
    @NotEmpty
    private String role;

    @JsonCreator
    public UpdateRoleOfUserDTO(@JsonProperty("role") String role) {
        this.role = role;
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
