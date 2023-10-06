package com.blog.db;

import java.util.List;

import org.hibernate.SessionFactory;
import com.blog.entity.BlogEntity;

import io.dropwizard.hibernate.AbstractDAO;

public class BlogDAO extends AbstractDAO<BlogEntity> {

    public BlogDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public BlogEntity findById(Long id) {
        return this.get(id);
    }

    public BlogEntity save(BlogEntity blog) {
        return this.persist(blog);
    }

    public List<BlogEntity> findAll() {
        return this.list(namedTypedQuery("com.blog.entity.BlogEntity.findAll"));
    }

    public void delete(BlogEntity blog) {
        super.currentSession().remove(blog);
    }
}
