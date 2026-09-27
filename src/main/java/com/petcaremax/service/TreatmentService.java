package com.petcaremax.service;

import com.petcaremax.dao.TreatmentDAO;
import com.petcaremax.dao.TreatmentDAOImpl;
import com.petcaremax.model.Treatment;

import java.util.List;

public class TreatmentService {

    private final TreatmentDAO treatmentDAO;

    public TreatmentService() {
        treatmentDAO = new TreatmentDAOImpl();
    }

    public boolean addTreatment(Treatment treatment) {

        validateTreatment(treatment);

        return treatmentDAO.addTreatment(treatment);
    }

    public boolean updateTreatment(Treatment treatment) {

        if (treatment == null || treatment.getTreatmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid treatment."
            );
        }

        validateTreatment(treatment);

        return treatmentDAO.updateTreatment(treatment);
    }

    public boolean deleteTreatment(int treatmentId) {

        if (treatmentId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid treatment ID."
            );
        }

        return treatmentDAO.deleteTreatment(treatmentId);
    }

    public List<Treatment> getAllTreatments() {

        return treatmentDAO.getAllTreatments();
    }

    private void validateTreatment(Treatment treatment) {

        if (treatment == null) {
            throw new IllegalArgumentException(
                    "Treatment cannot be null."
            );
        }

        if (treatment.getAppointmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a valid appointment."
            );
        }

        if (treatment.getDiagnosis() == null
                || treatment.getDiagnosis().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Diagnosis is required."
            );
        }

        if (treatment.getTreatmentDescription() == null
                || treatment.getTreatmentDescription().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment description is required."
            );
        }

        if (treatment.getTreatmentDate() == null) {

            throw new IllegalArgumentException(
                    "Treatment date is required."
            );
        }
    }
}