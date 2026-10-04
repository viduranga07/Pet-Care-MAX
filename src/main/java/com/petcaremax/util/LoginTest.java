// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

import com.petcaremax.dao.AddUserDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.User;

public class LoginTest {

    public static void main(String[] args) {

        try {

            AddUserDAO userDAO = DAOFactory.createUserDAO();

            User user = userDAO.findByUsername("admin");

            if (user == null) {
                System.out.println("USER NOT FOUND");
                return;
            }

            String storedHash = user.getPasswordHash();

            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
            System.out.println("Status: " + user.getStatus());

            System.out.println();
            System.out.println("HASH STORED IN DATABASE:");
            System.out.println(storedHash);

            System.out.println();
            System.out.println("HASH LENGTH:");
            System.out.println(storedHash.length());

            System.out.println();
            System.out.println("VERIFYING admin123:");

            boolean correct = Password.verifyPassword(
                    "admin123",
                    storedHash
            );

            System.out.println(
                    "Password correct: " + correct
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
