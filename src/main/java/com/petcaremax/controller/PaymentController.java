// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Payment;
import com.petcaremax.service.PaymentService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController() {
        paymentService = new PaymentService();
    }

    public boolean addPayment(Payment payment) {

        return paymentService.addPayment(payment);
    }

    public boolean updatePayment(Payment payment) {

        return paymentService.updatePayment(payment);
    }

    public boolean deletePayment(int paymentId) {

        return paymentService.deletePayment(paymentId);
    }

    public List<Payment> getAllPayments() {

        return paymentService.getAllPayments();
    }
}
