// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.User;
import com.petcaremax.service.AddUserService;
import com.petcaremax.util.Password;
import com.petcaremax.view.AddUserForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Controller for the Add User screen.
 *
 * The controller handles button events and delegates persistence/business
 * operations to AddUserService. The view contains no SQL or database logic.
 */
// This controller receives user actions and passes the work to the service layer.
public class AddUserController implements ActionListener {

    private final AddUserForm view;
    private final AddUserService addUserService;

    public AddUserController(AddUserForm view) {
        this.view = view;
        this.addUserService = new AddUserService();
        bindEvents();
    }

    private void bindEvents() {
        view.getBtnCreateUser().addActionListener(this);
        view.getBtnClear().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == view.getBtnCreateUser()) {
            createUser();
        } else if (event.getSource() == view.getBtnClear()) {
            view.clearForm();
        }
    }

    private void createUser() {
        String fullName = view.getTxtFullName().getText().trim();
        String username = view.getTxtUsername().getText().trim();
        char[] passwordChars = view.getTxtPassword().getPassword();
        String role = String.valueOf(view.getCmbRole().getSelectedItem());
        String status = String.valueOf(view.getCmbStatus().getSelectedItem());

        try {
            validateInput(fullName, username, passwordChars);

            if (addUserService.usernameExists(username)) {
                showWarning(
                        "This username already exists.\nPlease choose another username.",
                        "Duplicate Username"
                );
                view.getTxtUsername().requestFocus();
                return;
            }

            String passwordHash = Password.hashPassword(
                    new String(passwordChars)
            );

            User user = new User();
            user.setFullName(fullName);
            user.setUsername(username);
            user.setPasswordHash(passwordHash);
            user.setRole(role);
            user.setStatus(status);

            boolean created = addUserService.createUser(user);

            if (created) {
                JOptionPane.showMessageDialog(
                        view,
                        "User account created successfully.",
                        "User Created",
                        JOptionPane.INFORMATION_MESSAGE
                );
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(
                        view,
                        "The user could not be created. Please check the database connection and try again.",
                        "Create User Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IllegalArgumentException ex) {
            showWarning(ex.getMessage(), "Validation Error");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    view,
                    "An unexpected error occurred while creating the user.\n"
                    + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            Arrays.fill(passwordChars, '\0');
        }
    }

    private void validateInput(
            String fullName,
            String username,
            char[] password
    ) {
        if (fullName.isEmpty()) {
            view.getTxtFullName().requestFocus();
            throw new IllegalArgumentException("Full name is required.");
        }

        if (username.isEmpty()) {
            view.getTxtUsername().requestFocus();
            throw new IllegalArgumentException("Username is required.");
        }

        if (username.length() < 3) {
            view.getTxtUsername().requestFocus();
            throw new IllegalArgumentException(
                    "Username must contain at least 3 characters."
            );
        }

        if (password.length == 0) {
            view.getTxtPassword().requestFocus();
            throw new IllegalArgumentException("Password is required.");
        }

        if (password.length < 6) {
            view.getTxtPassword().requestFocus();
            throw new IllegalArgumentException(
                    "Password must contain at least 6 characters."
            );
        }
    }

    private void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(
                view,
                message,
                title,
                JOptionPane.WARNING_MESSAGE
        );
    }
}
