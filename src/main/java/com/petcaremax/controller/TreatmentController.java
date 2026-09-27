package com.petcaremax.controller;

import com.petcaremax.model.Treatment;
import com.petcaremax.service.TreatmentService;

import java.util.List;

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