package com.petcaremax.service;

import com.petcaremax.dao.UserDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.User;
import com.petcaremax.util.Password;

public class LoginService {

    private final UserDAO userDAO;

    public LoginService() {

        userDAO =
                DAOFactory.createUserDAO();
    }

    public User authenticate(
            String username,
            String password
    ) throws Exception {

        if (username == null
                || username.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (password == null
                || password.isEmpty()) {

            throw new IllegalArgumentException(
                    "Password is required."
            );
        }

        User user =
                userDAO.findByUsername(
                        username.trim()
                );

        if (user == null) {

            throw new IllegalArgumentException(
                    "Invalid username or password."
            );
        }

        if (!"Active".equalsIgnoreCase(
                user.getStatus()
        )) {

            throw new IllegalArgumentException(
                    "This user account is inactive."
            );
        }

        boolean valid =
                Password.verifyPassword(
                        password,
                        user.getPasswordHash()
                );

        if (!valid) {

            throw new IllegalArgumentException(
                    "Invalid username or password."
            );
        }

        return user;
    }
}