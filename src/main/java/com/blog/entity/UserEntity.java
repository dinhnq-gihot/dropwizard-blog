package com.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@NamedQuery(name = "com.blog.entity.UserEntity.findAll", query = "SELECT u FROM UserEntity u WHERE u.deleted = 0")
@NamedQuery(name = "com.blog.entity.UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email =:email")
@NamedQuery(name = "com.blog.entity.UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username =:username")
// @NamedQuery(name = "com.blog.entity.UserEntity.findWithPagination", query =
// "SELECT u FROM UserEntity u WHERE u.deleted = 0 LIMIT :limit OFFSET :offset")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer deleted;

    @ManyToOne()
    @JoinColumn(name = "role_id", columnDefinition = "INT DEFAULT 1")
    private RoleEntity role;

    @PrePersist
    private void prePersist() {
        if (role == null) {
            this.role = new RoleEntity();
            this.role.setId(1L); // Assuming "user" role has ID 1, adjust as needed
            this.role.setName("user"); // Set the role name
        }
        if (deleted == null) {
            deleted = 0;
        }
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public RoleEntity getRole() {
        return this.role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
