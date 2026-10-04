// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.TreatmentMedication;
import com.petcaremax.service.TreatmentMedicationService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class TreatmentMedicationController {

    private final TreatmentMedicationService treatmentMedicationService;

    public TreatmentMedicationController() {
        treatmentMedicationService =
                new TreatmentMedicationService();
    }

    public boolean addTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        return treatmentMedicationService
                .addTreatmentMedication(treatmentMedication);
    }

    public boolean updateTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        return treatmentMedicationService
                .updateTreatmentMedication(treatmentMedication);
    }

    public boolean deleteTreatmentMedication(
            int treatmentId,
            int medicationId) {

        return treatmentMedicationService
                .deleteTreatmentMedication(
                        treatmentId,
                        medicationId
                );
    }

    public List<TreatmentMedication> getAllTreatmentMedications() {

        return treatmentMedicationService
                .getAllTreatmentMedications();
    }
}
