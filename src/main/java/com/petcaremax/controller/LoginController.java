package com.petcaremax.controller;

import com.petcaremax.model.User;
import com.petcaremax.service.LoginService;

public class LoginController {

    private final LoginService loginService;

    public LoginController() {

        loginService =
                new LoginService();
    }

    public User login(
            String username,
            String password
    ) throws Exception {

        return loginService.authenticate(
                username,
                password
        );
    }
}