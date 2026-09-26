package com.petcaremax.controller;

import com.petcaremax.model.Pet;
import com.petcaremax.service.PetService;

import java.util.List;

public class PetController {

    private final PetService petService;

    public PetController() {
        petService = new PetService();
    }

    public boolean addPet(Pet pet) {

        return petService.addPet(pet);
    }

    public boolean updatePet(Pet pet) {

        return petService.updatePet(pet);
    }

    public boolean deletePet(int petId) {

        return petService.deletePet(petId);
    }

    public List<Pet> getAllPets() {

        return petService.getAllPets();
    }
}