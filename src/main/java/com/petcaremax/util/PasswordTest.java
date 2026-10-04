// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

public class PasswordTest {

    public static void main(String[] args) {

        String password = "admin123";

       String hash = Password.hashPassword(password);

System.out.println("Generated Hash:");
System.out.println(hash);

System.out.println("Hash length: " + hash.length());

        System.out.println("Password Verification:");

        System.out.println(
                Password.verifyPassword(
                        password,
                        hash
                )
        );
    }
}
