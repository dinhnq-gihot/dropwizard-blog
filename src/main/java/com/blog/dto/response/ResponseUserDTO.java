package com.blog.dto.response;

public class ResponseUserDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String image;

    public ResponseUserDTO(Long id, String username, String email, String role, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.image = image;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
