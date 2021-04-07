package com.miage.backendspring.controller;

import com.miage.backendspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public boolean login(@RequestParam("login") String login,  @RequestParam("password") String password) {
        return userService.login(login, password);
    }


    @PostMapping(value = "/updateLogin")
    public void updateLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        userService.updateLogin(username,password);
    }

}