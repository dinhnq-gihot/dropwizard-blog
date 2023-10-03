package com.blog.service;

import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.entity.UserEntity;

public interface IAuthService {
    public UserEntity signup(SignupDTO dto);

    public String login(LoginDTO dto);
}
