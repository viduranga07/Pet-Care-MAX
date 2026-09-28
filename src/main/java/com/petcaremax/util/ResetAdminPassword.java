package com.petcaremax.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetAdminPassword {

    public static void main(String[] args) {

        String password = "admin123";

        try {

            // Generate hash
            String hash = Password.hashPassword(password);

            System.out.println("Generated hash:");
            System.out.println(hash);

            System.out.println("Generated length: " + hash.length());

            // Save directly to database
            String sql =
                    "UPDATE users SET password_hash = ? "
                    + "WHERE username = 'admin'";

            try (Connection connection =
                         DBConnection.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, hash);

                int rows = statement.executeUpdate();

                System.out.println("Rows updated: " + rows);
            }

            // Verify the same generated hash
            boolean verified =
                    Password.verifyPassword(
                            password,
                            hash
                    );

            System.out.println(
                    "Password verification: " + verified
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}