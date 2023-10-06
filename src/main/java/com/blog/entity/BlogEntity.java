package com.blog.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "blogs")
@NamedQuery(name = "com.blog.entity.BlogEntity.findAll", query = "SELECT b FROM BlogEntity b")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @Column()
    private String slug;

    @Column(columnDefinition = "TEXT DEFAULT ''")
    private String description = "";

    @Column(columnDefinition = "TEXT DEFAULT ''")
    private String body = "";

    @Column(nullable = false, name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    @Column(nullable = false, name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updateAt = new Date();

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer likes = 0;

    @Column()
    private Date publishedDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id") // Defines the foreign key column
    private UserEntity user; // Many-to-one relationship with User

    public BlogEntity() {
    }

    public BlogEntity(Long id, String title, String slug, String description, String body, Date createdAt,
            Date updateAt, Integer likes, Date publishedDate, UserEntity user) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.likes = likes;
        this.publishedDate = publishedDate;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Date getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
