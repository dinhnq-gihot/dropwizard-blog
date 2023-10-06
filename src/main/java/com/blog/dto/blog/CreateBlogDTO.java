package com.blog.dto.blog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class CreateBlogDTO {
    @NotEmpty
    private String title;
    private String description;
    private String body;

    @JsonCreator
    public CreateBlogDTO(@JsonProperty("title") String title, @JsonProperty("description") String description,
            @JsonProperty("body") String body) {
        this.title = title;
        this.description = description;
        this.body = body;
    }

    @JsonProperty("title")
    public String getTitle() {
        return this.title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("body")
    public String getBody() {
        return this.body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }
}
