package com.petcaremax.dao;

import com.petcaremax.model.Pet;
import java.util.List;

public interface PetDAO {

    boolean addPet(Pet pet);

    boolean updatePet(Pet pet);

    boolean deletePet(int petId);

    List<Pet> getAllPets();
}