package com.petcaremax.service;

import com.petcaremax.dao.TreatmentMedicationDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.TreatmentMedication;

import java.util.List;

public class TreatmentMedicationService {

    private final TreatmentMedicationDAO treatmentMedicationDAO;

    public TreatmentMedicationService() {
    this.treatmentMedicationDAO =
            DAOFactory.createTreatmentMedicationDAO();
}

    public boolean addTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        validateTreatmentMedication(treatmentMedication);

        return treatmentMedicationDAO
                .addTreatmentMedication(treatmentMedication);
    }

    public boolean updateTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        validateTreatmentMedication(treatmentMedication);

        if (treatmentMedication.getTreatmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid treatment ID."
            );
        }

        if (treatmentMedication.getMedicationId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid medication ID."
            );
        }

        return treatmentMedicationDAO
                .updateTreatmentMedication(treatmentMedication);
    }

    public boolean deleteTreatmentMedication(
            int treatmentId,
            int medicationId) {

        if (treatmentId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid treatment ID."
            );
        }

        if (medicationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid medication ID."
            );
        }

        return treatmentMedicationDAO
                .deleteTreatmentMedication(
                        treatmentId,
                        medicationId
                );
    }

    public List<TreatmentMedication> getAllTreatmentMedications() {

        return treatmentMedicationDAO
                .getAllTreatmentMedications();
    }

    private void validateTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        if (treatmentMedication == null) {
            throw new IllegalArgumentException(
                    "Treatment medication cannot be null."
            );
        }

        if (treatmentMedication.getTreatmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a valid treatment."
            );
        }

        if (treatmentMedication.getMedicationId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a valid medication."
            );
        }

        if (treatmentMedication.getDosage() == null
                || treatmentMedication.getDosage().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Dosage is required."
            );
        }

        if (treatmentMedication.getFrequency() == null
                || treatmentMedication.getFrequency().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Frequency is required."
            );
        }

        if (treatmentMedication.getDuration() == null
                || treatmentMedication.getDuration().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Duration is required."
            );
        }
    }
}