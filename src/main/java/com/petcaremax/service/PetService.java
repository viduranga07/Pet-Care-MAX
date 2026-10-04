// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.PetDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Pet;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class PetService {

    private final PetDAO petDAO;

  public PetService() {
    this.petDAO = DAOFactory.createPetDAO();
}
    public boolean addPet(Pet pet) {

        validatePet(pet);

        return petDAO.addPet(pet);
    }

    public boolean updatePet(Pet pet) {

        if (pet.getPetId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pet ID."
            );
        }

        validatePet(pet);

        return petDAO.updatePet(pet);
    }

    public boolean deletePet(int petId) {

        if (petId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pet ID."
            );
        }

        return petDAO.deletePet(petId);
    }

    public List<Pet> getAllPets() {

        return petDAO.getAllPets();
    }

    private void validatePet(Pet pet) {

        if (pet == null) {
            throw new IllegalArgumentException(
                    "Pet information cannot be empty."
            );
        }

        if (pet.getCustomerId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select an owner."
            );
        }

        if (pet.getPetName() == null
                || pet.getPetName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Pet name is required."
            );
        }

        if (pet.getSpecies() == null
                || pet.getSpecies().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Species is required."
            );
        }

        if (pet.getGender() == null
                || pet.getGender().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Gender is required."
            );
        }

        if (pet.getWeight() != null
                && pet.getWeight() <= 0) {

            throw new IllegalArgumentException(
                    "Weight must be greater than zero."
            );
        }
    }
}
