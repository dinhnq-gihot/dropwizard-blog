package com.blog.service.impl;

import com.blog.auth.JwtUtil;
import com.blog.converter.UserConverter;
import com.blog.db.RoleDAO;
import com.blog.db.UserDAO;
import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;
import com.blog.service.IAuthService;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotAuthorizedException;

public class AuthServiceImpl implements IAuthService {
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private final JwtUtil jwtUtil;

    /**
     * @param userDAOdao
     * @param jwtUtil
     */
    public AuthServiceImpl(UserDAO userDAO, RoleDAO roleDAO, JwtUtil jwtUtil) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserEntity signup(SignupDTO dto) {
        UserEntity existedEmail = userDAO.findByEmail(dto.getEmail());
        UserEntity existedUsername = userDAO.findByUsername(dto.getUsername());

        if (existedEmail != null || existedUsername != null) {
            throw new NotAcceptableException("email or username existed");
        }

        dto.setEmail(dto.getEmail().toLowerCase());
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashedPassword);

        UserEntity userEntity = UserConverter.toEntity(dto);
        if (dto.getRole() != null) {
            RoleEntity roleEntity = roleDAO.findByName(dto.getRole());
            userEntity.setRole(roleEntity);
        }

        return userDAO.save(userEntity);
    }

    @Override
    public String login(LoginDTO dto) {
        UserEntity entity = userDAO.findByEmail(dto.getEmail());

        if (entity == null) {
            throw new NotAuthorizedException("email or password wrong");
        }

        boolean passwordMatch = BCrypt.checkpw(dto.getPassword(), entity.getPassword());
        if (!passwordMatch) {
            throw new NotAuthorizedException("email or password wrong");
        }

        RoleEntity role = entity.getRole();
        String token = jwtUtil.createToken(entity.getId(), entity.getEmail(), role.getName());

        return token;
    }

}
