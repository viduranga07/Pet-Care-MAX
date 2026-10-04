// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Pet;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface PetDAO {

    boolean addPet(Pet pet);

    boolean updatePet(Pet pet);

    boolean deletePet(int petId);

    List<Pet> getAllPets();
}
