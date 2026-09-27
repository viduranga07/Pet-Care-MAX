package com.petcaremax.dao;

import com.petcaremax.model.Medication;
import java.util.List;

public interface MedicationDAO {

    boolean addMedication(Medication medication);

    boolean updateMedication(Medication medication);

    boolean deleteMedication(int medicationId);

    List<Medication> getAllMedications();
}