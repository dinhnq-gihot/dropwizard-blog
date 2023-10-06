package com.blog.service;

import com.blog.dto.response.ResponsePaginationDTO;
import com.blog.dto.response.ResponseUserDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;

public interface IUserService {
    public ResponsePaginationDTO getAllUser(String username, Integer page, Integer limit);

    public ResponseUserDTO getUserById(Long id);

    public ResponseUserDTO updateUser(Long id, UpdateUserDTO dto);

    public ResponseUserDTO deleteUser(Long id);

    public RoleEntity getRoleUserById(Long id);

    public ResponseUserDTO updateRoleOfUser(Long userId, String roleName);

    // public UserEntity signup(SignupDTO dto);
}
