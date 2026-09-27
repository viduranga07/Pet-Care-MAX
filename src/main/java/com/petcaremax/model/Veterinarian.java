package com.petcaremax.model;

public class Veterinarian {

    private int veterinarianId;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;
    private String availability;
    private String status;

    public Veterinarian() {
    }

    public Veterinarian(
            int veterinarianId,
            String fullName,
            String specialization,
            String phone,
            String email,
            String availability,
            String status) {

        this.veterinarianId = veterinarianId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.availability = availability;
        this.status = status;
    }

    public int getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(int veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}