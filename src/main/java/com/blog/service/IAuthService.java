package com.blog.service;

import com.blog.dto.auth.LoginDTO;
import com.blog.dto.auth.SignupDTO;
import com.blog.dto.user.ResponseUserDTO;

public interface IAuthService {
    public ResponseUserDTO signup(SignupDTO dto);

    public String login(LoginDTO dto);
}
