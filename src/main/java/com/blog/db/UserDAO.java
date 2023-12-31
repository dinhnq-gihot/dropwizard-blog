package com.blog.db;

import org.hibernate.SessionFactory;
import com.blog.entity.UserEntity;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;

public class UserDAO extends AbstractDAO<UserEntity> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public UserEntity findById(Long id) {
        return this.get(id);
    }

    public UserEntity save(UserEntity user) {
        return this.persist(user);
    }

    public List<UserEntity> findAll() {
        return this.list(namedTypedQuery("com.blog.entity.UserEntity.findAll"));
    }

    public UserEntity findByEmail(String email) {
        return this.uniqueResult(
                namedTypedQuery("com.blog.entity.UserEntity.findByEmail").setParameter("email", email));
    }

    public UserEntity findByUsername(String username) {
        return this.uniqueResult(
                namedTypedQuery("com.blog.entity.UserEntity.findByUsername").setParameter("username", username));
    }
}
