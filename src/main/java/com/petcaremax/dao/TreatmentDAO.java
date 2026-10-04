// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Treatment;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface TreatmentDAO {

    boolean addTreatment(Treatment treatment);

    boolean updateTreatment(Treatment treatment);

    boolean deleteTreatment(int treatmentId);

    List<Treatment> getAllTreatments();
}
