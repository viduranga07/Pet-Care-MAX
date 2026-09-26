package com.petcaremax.model;

import java.time.LocalDate;

public class Pet {

    private int petId;
    private int customerId;
    private String petName;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private Double weight;
    private String notes;

    public Pet() {
    }

    public Pet(
            int petId,
            int customerId,
            String petName,
            String species,
            String breed,
            String gender,
            LocalDate dateOfBirth,
            Double weight,
            String notes
    ) {
        this.petId = petId;
        this.customerId = customerId;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.notes = notes;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}