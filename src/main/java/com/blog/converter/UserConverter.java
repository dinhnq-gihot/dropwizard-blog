package com.blog.converter;

import com.blog.dto.auth.SignupDTO;
import com.blog.dto.user.ResponseUserDTO;
import com.blog.entity.UserEntity;

public class UserConverter {
    public static UserEntity toEntity(SignupDTO dto) {
        UserEntity entity = new UserEntity();

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public static ResponseUserDTO toResponseDTO(UserEntity entity) {
        return new ResponseUserDTO(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getRole().getName());
    }
}
