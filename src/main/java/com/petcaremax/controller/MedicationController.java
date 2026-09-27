package com.petcaremax.controller;

import com.petcaremax.model.Medication;
import com.petcaremax.service.MedicationService;

import java.util.List;

public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController() {
        medicationService = new MedicationService();
    }

    public boolean addMedication(Medication medication) {
        return medicationService.addMedication(medication);
    }

    public boolean updateMedication(Medication medication) {
        return medicationService.updateMedication(medication);
    }

    public boolean deleteMedication(int medicationId) {
        return medicationService.deleteMedication(medicationId);
    }

    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }
}