// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    private static final String URL =
            "jdbc:mysql://localhost:3306/petcare_db";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    // Private constructor
    private DBConnection() {
    }

    // Singleton instance
    public static DBConnection getInstance() {

        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    // Get database connection
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
