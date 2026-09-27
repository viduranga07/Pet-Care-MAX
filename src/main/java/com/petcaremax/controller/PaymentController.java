package com.petcaremax.controller;

import com.petcaremax.model.Payment;
import com.petcaremax.service.PaymentService;

import java.util.List;

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