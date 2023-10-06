package com.blog.service.impl;

import java.util.List;

import com.blog.converter.BlogConverter;
import com.blog.db.BlogDAO;
import com.blog.db.UserDAO;
import com.blog.dto.blog.CreateBlogDTO;
import com.blog.dto.blog.UpdateBlogDTO;
import com.blog.entity.BlogEntity;
import com.blog.entity.UserEntity;
import com.blog.service.IBlogService;

import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;

public class BlogServiceImpl implements IBlogService {
    private BlogDAO blogDao;
    private UserDAO userDao;

    public BlogServiceImpl(BlogDAO blogDao, UserDAO userDao) {
        this.blogDao = blogDao;
        this.userDao = userDao;
    }

    private boolean isAuthor(Long authorId, Long blogId) {
        BlogEntity blogEntity = blogDao.findById(blogId);
        UserEntity userEntity = userDao.findById(authorId);

        if (userEntity == null || blogEntity == null) {
            throw new NotFoundException();
        }
        if (blogEntity.getUser().getId() != userEntity.getId()) {
            throw new NotAcceptableException();
        }
        return true;
    }

    private boolean isAdmin(Long authorId) {
        UserEntity userEntity = userDao.findById(authorId);
        return userEntity.getRole().getName() == "admin" ? true : false;
    }

    public BlogEntity getBlogById(Long id) {
        return this.blogDao.findById(id);
    }

    @Override
    public BlogEntity updateBlog(Long authorId, Long blogId, UpdateBlogDTO updateDTO) {
        if (!isAuthor(authorId, blogId)) {
            return null;
        }
        BlogEntity entity = this.blogDao.findById(blogId);
        entity = BlogConverter.toUpdateEntity(updateDTO, entity);
        return blogDao.save(entity);
    }

    @Override
    public BlogEntity deleteBlog(Long authorId, Long blogId) {
        if (isAuthor(authorId, blogId) || isAdmin(authorId)) {
            BlogEntity entity = this.blogDao.findById(blogId);
            blogDao.delete(entity);
            return entity;
        }
        return null;
    }

    @Override
    public BlogEntity createBlog(Long authorId, CreateBlogDTO dto) {
        BlogEntity blogEntity = BlogConverter.toEntity(dto);
        UserEntity userEntity = userDao.findById(authorId);

        if (userEntity == null) {
            return null;
        }
        blogEntity.setUser(userEntity);
        return blogDao.save(blogEntity);
    }

    @Override
    public List<BlogEntity> getAllBlogs() {
        return blogDao.findAll();
    }
}
