package com.jpa.step.repository;

import com.jpa.step.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long userId) {
        return em.find(User.class,userId);
    }

}
