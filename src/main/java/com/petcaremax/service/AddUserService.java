// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.AddUserDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.User;

import java.util.List;

/**
 * Business/service layer for user management.
 */
// This service keeps the business rules separate from the user interface.
public class AddUserService {

    private final AddUserDAO userDAO;

    public AddUserService() {
        this.userDAO = DAOFactory.createUserDAO();
    }

    public boolean createUser(User user) {
        validateUser(user);

        if (usernameExists(user.getUsername())) {
            return false;
        }

        return userDAO.createUser(user);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        validateUser(user);
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        if (userId <= 0) {
            return false;
        }
        return userDAO.deleteUser(userId);
    }

    public boolean usernameExists(String username) {
        return username != null
                && !username.trim().isEmpty()
                && userDAO.usernameExists(username.trim());
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (isBlank(user.getFullName())) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (isBlank(user.getUsername())) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (isBlank(user.getPasswordHash())) {
            throw new IllegalArgumentException("Password hash is required.");
        }

        if (isBlank(user.getRole())) {
            throw new IllegalArgumentException("Role is required.");
        }

        if (isBlank(user.getStatus())) {
            throw new IllegalArgumentException("Status is required.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
