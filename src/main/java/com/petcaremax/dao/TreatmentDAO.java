package com.petcaremax.dao;

import com.petcaremax.model.Treatment;
import java.util.List;

public interface TreatmentDAO {

    boolean addTreatment(Treatment treatment);

    boolean updateTreatment(Treatment treatment);

    boolean deleteTreatment(int treatmentId);

    List<Treatment> getAllTreatments();
}