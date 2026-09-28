package com.petcaremax.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetReceptionPassword {

    public static void main(String[] args) {

        String password = "reception123";

        try {

            String hash =
                    Password.hashPassword(password);

            String sql =
                    "UPDATE users "
                    + "SET password_hash = ? "
                    + "WHERE username = 'reception'";

            try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setString(1, hash);

                int rows =
                        statement.executeUpdate();

                System.out.println(
                        "Rows updated: " + rows
                );

                System.out.println(
                        "Hash length: " + hash.length()
                );

                System.out.println(
                        "Password verification: "
                        + Password.verifyPassword(
                                password,
                                hash
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}