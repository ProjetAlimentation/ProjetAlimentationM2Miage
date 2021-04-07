package com.miage.backendspring.service;

import com.miage.backendspring.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserService {

    private final UserDAO userDAO;

    public void updateLogin(String username, String password){
        userDAO.updateLogin(username,password);
    }

    public boolean login(String login, String password) {
        return userDAO.login(login, password);
    }

}
