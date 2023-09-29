package com.blog.converter;

import com.blog.dto.user.SignupDTO;
import com.blog.entity.UserEntity;

public class UserConverter {
    public static UserEntity toEntity(SignupDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
