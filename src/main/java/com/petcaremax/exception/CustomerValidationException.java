// This class handles one part of the PetCareMAX application.
package com.petcaremax.exception;

// This exception gives the application a clear validation error.
public class CustomerValidationException extends Exception {

    public CustomerValidationException(String message) {
        super(message);
    }
}
