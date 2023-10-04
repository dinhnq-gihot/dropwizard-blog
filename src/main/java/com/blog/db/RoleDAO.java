package com.blog.db;

import org.hibernate.SessionFactory;

import com.blog.entity.RoleEntity;

import io.dropwizard.hibernate.AbstractDAO;

public class RoleDAO extends AbstractDAO<RoleEntity> {
    public RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public RoleEntity findById(Long id) {
        return this.get(id);
    }

    public RoleEntity findByName(String name) {
        return this.uniqueResult(
                namedTypedQuery("com.blog.entity.RoleEntity.findByName").setParameter("name", name));
    }
}
