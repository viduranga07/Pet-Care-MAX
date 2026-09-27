package com.petcaremax.model;

import java.time.LocalDate;

public class Treatment {

    private int treatmentId;
    private int appointmentId;
    private String diagnosis;
    private String treatmentDescription;
    private LocalDate treatmentDate;
    private String notes;

    public Treatment() {
    }

    public Treatment(int treatmentId, int appointmentId,
                     String diagnosis, String treatmentDescription,
                     LocalDate treatmentDate, String notes) {

        this.treatmentId = treatmentId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.treatmentDescription = treatmentDescription;
        this.treatmentDate = treatmentDate;
        this.notes = notes;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}