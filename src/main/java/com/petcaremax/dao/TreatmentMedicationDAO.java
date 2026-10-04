// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.TreatmentMedication;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface TreatmentMedicationDAO {

    boolean addTreatmentMedication(TreatmentMedication treatmentMedication);

    boolean updateTreatmentMedication(TreatmentMedication treatmentMedication);

    boolean deleteTreatmentMedication(int treatmentId, int medicationId);

    List<TreatmentMedication> getAllTreatmentMedications();
}
