package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.blog.converter.UserConverter;
import com.blog.db.RoleDAO;
import com.blog.db.UserDAO;
import com.blog.dto.response.ResponsePaginationDTO;
import com.blog.dto.response.ResponseUserDTO;
import com.blog.dto.user.UpdateUserDTO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;
import com.blog.service.IUserService;

public class UserServiceImpl implements IUserService {
    private UserDAO userDao;
    private RoleDAO roleDao;

    // @Inject
    public UserServiceImpl(UserDAO userDao, RoleDAO roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public ResponsePaginationDTO getAllUser(String username, Integer page, Integer limit) {
        int offset = (page - 1) * limit;

        List<UserEntity> allUserEntities = userDao.findAllWithUsername(username);
        List<UserEntity> paginationAllUserEntities = userDao.findAllWithPaginatedUsername(username, limit, offset);

        List<ResponseUserDTO> allUserDTOs = new ArrayList<>();

        for (UserEntity entity : paginationAllUserEntities) {
            allUserDTOs.add(UserConverter.toResponseDTO(entity));
        }

        ResponsePaginationDTO pagination = new ResponsePaginationDTO(allUserDTOs, allUserEntities.size(),
                (int) Math.ceil((double) allUserEntities.size() / limit), page);

        return pagination;
    }

    @Override
    public ResponseUserDTO getUserById(Long id) {
        UserEntity entity = userDao.findById(id);
        if (entity == null) {
            return null;
        }
        ResponseUserDTO dto = UserConverter.toResponseDTO(entity);
        return dto;
    }

    @Override
    public ResponseUserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        UserEntity entity = userDao.findById(id);
        if (entity == null) {
            return null;
        }
        if (updateUserDTO.getEmail() != null && userDao.findByEmail(updateUserDTO.getEmail()) == null) {
            entity.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getUsername() != null && userDao.findByUsername(updateUserDTO.getUsername()) == null) {
            entity.setUsername(updateUserDTO.getEmail());
        }
        userDao.save(entity);
        ResponseUserDTO responseUserDTO = UserConverter.toResponseDTO(entity);

        return responseUserDTO;
    }

    @Override
    public ResponseUserDTO deleteUser(Long id) {
        UserEntity entity = userDao.findById(id);
        if (entity == null) {
            return null;
        }
        entity.setDeleted(1);
        userDao.save(entity);

        ResponseUserDTO responseUserDTO = UserConverter.toResponseDTO(entity);
        return responseUserDTO;
    }

    @Override
    public RoleEntity getRoleUserById(Long id) {
        UserEntity entity = userDao.findById(id);
        if (entity == null) {
            return null;
        }
        return entity.getRole();
    }

    @Override
    public ResponseUserDTO updateRoleOfUser(Long userId, String roleName) {
        RoleEntity role = this.roleDao.findByName(roleName);
        UserEntity user = this.userDao.findById(userId);

        if (role == null || user == null) {
            return null;
        }
        user.setRole(role);
        userDao.save(user);

        return UserConverter.toResponseDTO(user);
    }
}
