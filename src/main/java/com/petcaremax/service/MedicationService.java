package com.petcaremax.service;

import com.petcaremax.dao.MedicationDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Medication;

import java.util.List;

public class MedicationService {

    private final MedicationDAO medicationDAO;

  public MedicationService() {
    this.medicationDAO = DAOFactory.createMedicationDAO();
}

    public boolean addMedication(Medication medication) {

        validateMedication(medication);

        return medicationDAO.addMedication(medication);
    }

    public boolean updateMedication(Medication medication) {

        if (medication == null
                || medication.getMedicationId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid medication."
            );
        }

        validateMedication(medication);

        return medicationDAO.updateMedication(medication);
    }

    public boolean deleteMedication(int medicationId) {

        if (medicationId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid medication ID."
            );
        }

        return medicationDAO.deleteMedication(medicationId);
    }

    public List<Medication> getAllMedications() {

        return medicationDAO.getAllMedications();
    }

    private void validateMedication(Medication medication) {

        if (medication == null) {

            throw new IllegalArgumentException(
                    "Medication cannot be null."
            );
        }

        if (medication.getMedicationName() == null
                || medication.getMedicationName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Medication name is required."
            );
        }

        if (medication.getUnitPrice() < 0) {

            throw new IllegalArgumentException(
                    "Unit price cannot be negative."
            );
        }

        if (medication.getStockQuantity() < 0) {

            throw new IllegalArgumentException(
                    "Stock quantity cannot be negative."
            );
        }

        if (medication.getStatus() == null
                || medication.getStatus().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Medication status is required."
            );
        }
    }
}