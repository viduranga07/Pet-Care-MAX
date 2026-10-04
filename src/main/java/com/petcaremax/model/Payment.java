// This class handles one part of the PetCareMAX application.
package com.petcaremax.model;

import java.time.LocalDateTime;

// This model stores the data used by this part of the application.
public class Payment {

    private int paymentId;
    private int appointmentId;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String status;

    public Payment() {
    }

    public Payment(int paymentId,
                   int appointmentId,
                   double amount,
                   String paymentMethod,
                   LocalDateTime paymentDate,
                   String status) {

        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
