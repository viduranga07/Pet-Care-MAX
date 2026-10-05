// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.PetDAO;
import com.petcaremax.exception.PetValidationException;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Pet;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class PetService {

    private final PetDAO petDAO;

    public PetService() {
        this.petDAO = DAOFactory.createPetDAO();
    }

    public boolean addPet(Pet pet) throws PetValidationException {

        validatePet(pet);

        return petDAO.addPet(pet);
    }

    public boolean updatePet(Pet pet) throws PetValidationException {

        if (pet == null) {
            throw new PetValidationException(
                    "Pet information cannot be empty."
            );
        }

        if (pet.getPetId() <= 0) {
            throw new PetValidationException(
                    "Invalid pet ID."
            );
        }

        validatePet(pet);

        return petDAO.updatePet(pet);
    }

    public boolean deletePet(int petId) throws PetValidationException {

        if (petId <= 0) {
            throw new PetValidationException(
                    "Invalid pet ID."
            );
        }

        return petDAO.deletePet(petId);
    }

    public List<Pet> getAllPets() {

        return petDAO.getAllPets();
    }

    private void validatePet(Pet pet) throws PetValidationException {

        if (pet == null) {
            throw new PetValidationException(
                    "Pet information cannot be empty."
            );
        }

        if (pet.getCustomerId() <= 0) {
            throw new PetValidationException(
                    "Please select an owner."
            );
        }

        if (pet.getPetName() == null
                || pet.getPetName().trim().isEmpty()) {

            throw new PetValidationException(
                    "Pet name is required."
            );
        }

        if (pet.getSpecies() == null
                || pet.getSpecies().trim().isEmpty()) {

            throw new PetValidationException(
                    "Species is required."
            );
        }

        if (pet.getGender() == null
                || pet.getGender().trim().isEmpty()) {

            throw new PetValidationException(
                    "Gender is required."
            );
        }

        if (pet.getWeight() != null
                && pet.getWeight() <= 0) {

            throw new PetValidationException(
                    "Weight must be greater than zero."
            );
        }
    }
}