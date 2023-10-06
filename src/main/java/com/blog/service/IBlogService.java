package com.blog.service;

import java.util.List;

import com.blog.dto.blog.CreateBlogDTO;
import com.blog.dto.blog.UpdateBlogDTO;
import com.blog.entity.BlogEntity;

public interface IBlogService {
    public BlogEntity createBlog(Long authorId, CreateBlogDTO dto);

    public BlogEntity getBlogById(Long id);

    public BlogEntity updateBlog(Long authorId, Long blogId, UpdateBlogDTO blogDTO);

    public BlogEntity deleteBlog(Long authorId, Long blogId);

    public List<BlogEntity> getAllBlogs();
}
