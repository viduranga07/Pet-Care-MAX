// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Medication;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface MedicationDAO {

    boolean addMedication(Medication medication);

    boolean updateMedication(Medication medication);

    boolean deleteMedication(int medicationId);

    List<Medication> getAllMedications();
}
