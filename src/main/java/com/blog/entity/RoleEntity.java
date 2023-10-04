package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
@NamedQuery(name = "com.blog.entity.RoleEntity.findAll", query = "SELECT r FROM RoleEntity r")
@NamedQuery(name = "com.blog.entity.RoleEntity.findByName", query = "SELECT r FROM RoleEntity r WHERE r.name = :name")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();

    // Constructors, getters, setters, etc.
    public RoleEntity() {
    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}