// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try {

            Connection con = DBConnection.getInstance().getConnection();

            System.out.println(
                    "Database connection successful!"
            );

            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Database connection failed!"
            );

            e.printStackTrace();
        }
    }
}
