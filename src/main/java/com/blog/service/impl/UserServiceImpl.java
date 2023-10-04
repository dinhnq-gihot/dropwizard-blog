package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.blog.converter.UserConverter;
import com.blog.db.UserDAO;
import com.blog.dto.user.ResponseUserDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;
import com.blog.service.IUserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

public class UserServiceImpl implements IUserService {
    private UserDAO dao;

    // @Inject
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }
    

    @Override
    public List<ResponseUserDTO> getAllUser() {
        List<UserEntity> allUserEntities = dao.findAll();
        List<ResponseUserDTO> allUserDTOs = new ArrayList<>();

        for (UserEntity entity : allUserEntities) {
            allUserDTOs.add(UserConverter.toResponseDTO(entity));
        }

        return allUserDTOs;
    }

    @Override
    public ResponseUserDTO getUserById(Long id) {
        UserEntity entity = dao.findById(id);
        if (entity == null) {
            return null;
        }
        ResponseUserDTO dto = UserConverter.toResponseDTO(entity);
        return dto;
    }

    @Override
    public ResponseUserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        UserEntity entity = dao.findById(id);
        if (entity == null) {
            return null;
        }
        if (updateUserDTO.getEmail() != null && dao.findByEmail(updateUserDTO.getEmail()) == null) {
            entity.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getUsername() != null && dao.findByUsername(updateUserDTO.getUsername()) == null) {
            entity.setUsername(updateUserDTO.getEmail());
        }
        dao.save(entity);
        ResponseUserDTO responseUserDTO = UserConverter.toResponseDTO(entity);

        return responseUserDTO;
    }

    @Override
    public ResponseUserDTO deleteUser(Long id) {
        UserEntity entity = dao.findById(id);
        if (entity == null) {
            return null;
        }
        entity.setDeleted(1);
        dao.save(entity);

        ResponseUserDTO responseUserDTO = UserConverter.toResponseDTO(entity);
        return responseUserDTO;
    }

    @Override
    public RoleEntity getRoleUserById(Long id) {
        UserEntity entity = dao.findById(id);
        if (entity == null) {
            return null;
        }
        return entity.getRole();
    }
}
