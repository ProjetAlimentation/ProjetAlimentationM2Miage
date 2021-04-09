package com.miage.backendspring.dao;

import com.miage.backendspring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserDAO {

    private final EntityManager em;

    @Transactional
    public void updateLogin(String username, String password) {
        User user = em.find(User.class, 1);
        user.setUsername(username);
        user.setPassword(password);
        em.merge(user);
    }


    @Transactional
    public boolean login(String login, String password) {
        Query query = em.createNamedQuery("User.login", Long.class);

        query.setParameter("username", login);
        query.setParameter("password", password);
        long size = (long) query.getSingleResult();
        return size == 1;
    }

}
