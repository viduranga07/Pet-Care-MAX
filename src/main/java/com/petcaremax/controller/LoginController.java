// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.User;
import com.petcaremax.service.LoginService;

// This controller receives user actions and passes the work to the service layer.
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
