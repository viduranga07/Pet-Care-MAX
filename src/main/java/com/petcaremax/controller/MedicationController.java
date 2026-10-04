// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Medication;
import com.petcaremax.service.MedicationService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
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
