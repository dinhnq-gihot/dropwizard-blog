package com.blog.service;

import java.util.List;

import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;

public interface IUserService {
    public List<UserEntity> getAllUser();

    public UserEntity getUserById(Long id);

    public UserEntity updateUser(Long id, UpdateUserDTO dto);

    public UserEntity deleteUser(Long id);

    public RoleEntity getRoleUserById(Long id);

    // public UserEntity signup(SignupDTO dto);
}
