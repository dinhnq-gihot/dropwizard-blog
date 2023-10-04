package com.blog.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.blog.db.RoleDAO;
import com.blog.db.UserDAO;
import com.blog.service.IUserService;
import com.blog.service.impl.UserServiceImpl;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(RoleDAO.class).to(RoleDAO.class);
        bind(UserDAO.class).to(UserDAO.class);
        bind(UserServiceImpl.class).to(UserServiceImpl.class);
    }
}
