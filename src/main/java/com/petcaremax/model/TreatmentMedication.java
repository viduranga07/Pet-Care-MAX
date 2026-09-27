package com.petcaremax.model;

public class TreatmentMedication {

    private int treatmentId;
    private int medicationId;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;

    public TreatmentMedication() {
    }

    public TreatmentMedication(int treatmentId,
                               int medicationId,
                               String dosage,
                               String frequency,
                               String duration,
                               String instructions) {

        this.treatmentId = treatmentId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.instructions = instructions;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}