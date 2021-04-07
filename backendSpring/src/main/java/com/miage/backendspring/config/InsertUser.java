package com.miage.backendspring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class InsertUser {

    private final EntityManager em;

    @Transactional
    public void insertUser(){
        Query nativeQuery = em.createNativeQuery("INSERT INTO USERS (id, username, password) values (1,'admin','password')");
        nativeQuery.executeUpdate();
    }

}
