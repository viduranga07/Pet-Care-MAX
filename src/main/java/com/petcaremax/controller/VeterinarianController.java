// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Veterinarian;
import com.petcaremax.service.VeterinarianService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    public VeterinarianController() {
        veterinarianService = new VeterinarianService();
    }

    public boolean addVeterinarian(Veterinarian veterinarian) {

        return veterinarianService.addVeterinarian(veterinarian);
    }

    public boolean updateVeterinarian(Veterinarian veterinarian) {

        return veterinarianService.updateVeterinarian(veterinarian);
    }

    public boolean deleteVeterinarian(int veterinarianId) {

        return veterinarianService.deleteVeterinarian(veterinarianId);
    }

    public List<Veterinarian> getAllVeterinarians() {

        return veterinarianService.getAllVeterinarians();
    }
}
