package com.blog.converter;

import com.blog.dto.blog.CreateBlogDTO;
import com.blog.dto.blog.UpdateBlogDTO;
import com.blog.entity.BlogEntity;

public class BlogConverter {
    public static BlogEntity toEntity(CreateBlogDTO dto) {
        BlogEntity entity = new BlogEntity();
        entity.setTitle(dto.getTitle());

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getBody() != null) {
            entity.setBody(dto.getBody());
        }

        return entity;
    }

    public static BlogEntity toUpdateEntity(UpdateBlogDTO dto, BlogEntity entity) {
        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getBody() != null) {
            entity.setBody(dto.getBody());
        }

        return entity;
    }
}
