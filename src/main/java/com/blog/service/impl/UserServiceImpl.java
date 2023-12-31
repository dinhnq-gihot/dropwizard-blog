package com.blog.service.impl;

import java.util.List;

import com.blog.converter.UserConverter;
import com.blog.db.UserDAO;
import com.blog.dto.user.SignupDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IUserService;

import jakarta.ws.rs.*;

public class UserServiceImpl implements IUserService {
    private UserDAO dao;

    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<UserEntity> getAllUser() {
        return dao.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity entity = dao.findById(id);
        if (entity == null) {
            throw new NotFoundException("user id not found");
        }
        return entity;
    }

    @Override
    public UserEntity updateUser(Long id, UpdateUserDTO dto) {
        UserEntity entity = getUserById(id);

        if (dto.getEmail() != null && dao.findByEmail(dto.getEmail()) == null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null && dao.findByUsername(dto.getUsername()) == null) {
            entity.setUsername(dto.getEmail());
        }
        return dao.save(entity);
    }

    // @Override
    // public UserEntity deleteUser(Long id) {
    //     UserEntity entity = getUserById(id);
    //     entity.setDeleted(1);

    //     return dao.save(entity);
    // }

    @Override
    public UserEntity signup(SignupDTO dto) {
        UserEntity existedEmail = dao.findByEmail(dto.getEmail());
        UserEntity existedUsername = dao.findByUsername(dto.getUsername());

        if (existedEmail != null || existedUsername != null) {
            throw new NotAcceptableException("email or username existed");
        }

        UserEntity entity = UserConverter.toEntity(dto);
        return dao.save(entity);
    }

}
