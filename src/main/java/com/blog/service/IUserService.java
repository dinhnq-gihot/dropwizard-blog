package com.blog.service;

import java.util.List;

import com.blog.dto.auth.SignupDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.UserEntity;

public interface IUserService {
    public List<UserEntity> getAllUser();

    public UserEntity getUserById(Long id);

    public UserEntity updateUser(Long id, UpdateUserDTO dto);

    public UserEntity deleteUser(Long id);

    // public UserEntity signup(SignupDTO dto);
}
