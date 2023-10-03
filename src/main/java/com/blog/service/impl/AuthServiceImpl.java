package com.blog.service.impl;

import com.blog.auth.JwtUtil;
import com.blog.converter.UserConverter;
import com.blog.db.UserDAO;
import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.entity.UserEntity;
import com.blog.service.IAuthService;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAuthorizedException;

public class AuthServiceImpl implements IAuthService {
    private UserDAO dao;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserDAO dao, JwtUtil jwtUtil) {
        this.dao = dao;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserEntity signup(SignupDTO dto) {
        UserEntity existedEmail = dao.findByEmail(dto.getEmail());
        UserEntity existedUsername = dao.findByUsername(dto.getUsername());

        if (existedEmail != null || existedUsername != null) {
            throw new NotAcceptableException("email or username existed");
        }

        dto.setEmail(dto.getEmail().toLowerCase());
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashedPassword);

        UserEntity entity = UserConverter.toEntity(dto);
        return dao.save(entity);
    }

    @Override
    public String login(LoginDTO dto) {
        // TODO Auto-generated method stub
        UserEntity entity = dao.findByEmail(dto.getEmail());

        if (entity == null) {
            throw new NotAuthorizedException("email or password wrong");
        }

        boolean passwordMatch = BCrypt.checkpw(dto.getPassword(), entity.getPassword());
        if (!passwordMatch) {
            throw new NotAuthorizedException("email or password wrong");
        }

        String token = jwtUtil.createToken(entity.getUsername());

        return token;
    }

}
