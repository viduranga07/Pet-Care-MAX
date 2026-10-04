// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.VeterinarianDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Veterinarian;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class VeterinarianService {

    private final VeterinarianDAO veterinarianDAO;

    public VeterinarianService() {
    this.veterinarianDAO = DAOFactory.createVeterinarianDAO();
}

    public boolean addVeterinarian(Veterinarian veterinarian) {

        validateVeterinarian(veterinarian);

        return veterinarianDAO.addVeterinarian(veterinarian);
    }

    public boolean updateVeterinarian(Veterinarian veterinarian) {

        if (veterinarian.getVeterinarianId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid veterinarian ID."
            );
        }

        validateVeterinarian(veterinarian);

        return veterinarianDAO.updateVeterinarian(veterinarian);
    }

    public boolean deleteVeterinarian(int veterinarianId) {

        if (veterinarianId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid veterinarian ID."
            );
        }

        return veterinarianDAO.deleteVeterinarian(veterinarianId);
    }

    public List<Veterinarian> getAllVeterinarians() {

        return veterinarianDAO.getAllVeterinarians();
    }

   private void validateVeterinarian(Veterinarian veterinarian) {

    if (veterinarian == null) {
        throw new IllegalArgumentException(
                "Veterinarian information cannot be empty."
        );
    }

    // Full Name
    if (veterinarian.getFullName() == null
            || veterinarian.getFullName().trim().isEmpty()) {

        throw new IllegalArgumentException(
                "Veterinarian name is required."
        );
    }

    // Phone Number
    if (veterinarian.getPhone() == null
            || veterinarian.getPhone().trim().isEmpty()) {

        throw new IllegalArgumentException(
                "Phone number is required."
        );
    }

    String phone = veterinarian.getPhone().trim();

    if (!phone.matches("^(0\\d{9}|\\+94\\d{9})$")) {

        throw new IllegalArgumentException(
                "Please enter a valid Sri Lankan phone number.\n"
                + "Example: 0712345678 or +94712345678"
        );
    }

    // Email
    if (veterinarian.getEmail() != null
            && !veterinarian.getEmail().trim().isEmpty()) {

        String email = veterinarian.getEmail().trim();

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                    "Please enter a valid email address."
            );
        }
    }

    // Status
    if (veterinarian.getStatus() == null
            || veterinarian.getStatus().trim().isEmpty()) {

        throw new IllegalArgumentException(
                "Status is required."
        );
    }
}
}
