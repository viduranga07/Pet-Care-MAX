// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.AddUserDAO;
import com.petcaremax.exception.UserValidationException;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.User;

import java.util.List;

/**
 * Business/service layer for user management.
 *
 * This service keeps business rules separate from
 * the user interface and controller layers.
 */
public class AddUserService {

    private final AddUserDAO userDAO;

    public AddUserService() {
        this.userDAO = DAOFactory.createUserDAO();
    }

    /**
     * Creates a new user after validating the data.
     *
     * @param user user object to create
     * @return true if the user was created successfully
     * @throws UserValidationException if validation fails
     */
    public boolean createUser(User user) throws UserValidationException {

        validateUser(user);

        if (usernameExists(user.getUsername())) {
            return false;
        }

        return userDAO.createUser(user);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId user ID
     * @return User object or null if not found
     */
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username username
     * @return User object or null if not found
     */
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    /**
     * Retrieves all users.
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Updates an existing user after validating the data.
     *
     * @param user user object to update
     * @return true if the user was updated successfully
     * @throws UserValidationException if validation fails
     */
    public boolean updateUser(User user) throws UserValidationException {

        validateUser(user);

        return userDAO.updateUser(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId user ID
     * @return true if the user was deleted successfully
     */
    public boolean deleteUser(int userId) {

        if (userId <= 0) {
            return false;
        }

        return userDAO.deleteUser(userId);
    }

    /**
     * Checks whether a username already exists.
     *
     * @param username username to check
     * @return true if the username exists
     */
    public boolean usernameExists(String username) {

        return username != null
                && !username.trim().isEmpty()
                && userDAO.usernameExists(username.trim());
    }

    /**
     * Validates user information.
     *
     * @param user user object to validate
     * @throws UserValidationException if any required field is invalid
     */
    private void validateUser(User user) throws UserValidationException {

        if (user == null) {
            throw new UserValidationException(
                    "User cannot be null."
            );
        }

        if (isBlank(user.getFullName())) {
            throw new UserValidationException(
                    "Full name is required."
            );
        }

        if (isBlank(user.getUsername())) {
            throw new UserValidationException(
                    "Username is required."
            );
        }

        if (isBlank(user.getPasswordHash())) {
            throw new UserValidationException(
                    "Password hash is required."
            );
        }

        if (isBlank(user.getRole())) {
            throw new UserValidationException(
                    "Role is required."
            );
        }

        if (isBlank(user.getStatus())) {
            throw new UserValidationException(
                    "Status is required."
            );
        }
    }

    /**
     * Checks whether a String is null or empty.
     *
     * @param value value to check
     * @return true if the value is blank
     */
    private boolean isBlank(String value) {

        return value == null
                || value.trim().isEmpty();
    }
}