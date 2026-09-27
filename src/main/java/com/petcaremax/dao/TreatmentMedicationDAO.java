package com.petcaremax.dao;

import com.petcaremax.model.TreatmentMedication;
import java.util.List;

public interface TreatmentMedicationDAO {

    boolean addTreatmentMedication(TreatmentMedication treatmentMedication);

    boolean updateTreatmentMedication(TreatmentMedication treatmentMedication);

    boolean deleteTreatmentMedication(int treatmentId, int medicationId);

    List<TreatmentMedication> getAllTreatmentMedications();
}