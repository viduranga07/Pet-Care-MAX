package com.petcaremax.service;

import com.petcaremax.dao.VeterinarianDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Veterinarian;

import java.util.List;

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

        if (veterinarian.getFullName() == null
                || veterinarian.getFullName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Veterinarian name is required."
            );
        }

        if (veterinarian.getPhone() == null
                || veterinarian.getPhone().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Phone number is required."
            );
        }

        if (veterinarian.getStatus() == null
                || veterinarian.getStatus().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Status is required."
            );
        }
    }
}