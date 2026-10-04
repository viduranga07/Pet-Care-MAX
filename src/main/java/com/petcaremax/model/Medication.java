// This class handles one part of the PetCareMAX application.
package com.petcaremax.model;

// This model stores the data used by this part of the application.
public class Medication {

    private int medicationId;
    private String medicationName;
    private String description;
    private double unitPrice;
    private int stockQuantity;
    private String status;

    public Medication() {
    }

    public Medication(int medicationId,
                      String medicationName,
                      String description,
                      double unitPrice,
                      int stockQuantity,
                      String status) {

        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
