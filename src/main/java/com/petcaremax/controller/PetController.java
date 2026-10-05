// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.exception.PetValidationException;
import com.petcaremax.model.Pet;
import com.petcaremax.service.PetService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class PetController {

    private final PetService petService;

    public PetController() {
        petService = new PetService();
    }

    public boolean addPet(Pet pet) throws PetValidationException {

        return petService.addPet(pet);
    }

    public boolean updatePet(Pet pet) throws PetValidationException {

        return petService.updatePet(pet);
    }

    public boolean deletePet(int petId) throws PetValidationException {

        return petService.deletePet(petId);
    }

    public List<Pet> getAllPets() {

        return petService.getAllPets();
    }
}