// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Treatment;
import com.petcaremax.service.TreatmentService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController() {
        treatmentService = new TreatmentService();
    }

    public boolean addTreatment(Treatment treatment) {
        return treatmentService.addTreatment(treatment);
    }

    public boolean updateTreatment(Treatment treatment) {
        return treatmentService.updateTreatment(treatment);
    }

    public boolean deleteTreatment(int treatmentId) {
        return treatmentService.deleteTreatment(treatmentId);
    }

    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }
}
