// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.PaymentDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Payment;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class PaymentService {

    private final PaymentDAO paymentDAO;

   public PaymentService() {
    this.paymentDAO = DAOFactory.createPaymentDAO();
}

    public boolean addPayment(Payment payment) {

        validatePayment(payment);

        return paymentDAO.addPayment(payment);
    }

    public boolean updatePayment(Payment payment) {

        validatePayment(payment);

        if (payment.getPaymentId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid payment ID."
            );
        }

        return paymentDAO.updatePayment(payment);
    }

    public boolean deletePayment(int paymentId) {

        if (paymentId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid payment ID."
            );
        }

        return paymentDAO.deletePayment(paymentId);
    }

    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();
    }

    private void validatePayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment cannot be null."
            );
        }

        if (payment.getAppointmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a valid appointment."
            );
        }

        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero."
            );
        }

        if (payment.getPaymentMethod() == null
                || payment.getPaymentMethod().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Payment method is required."
            );
        }

        if (payment.getStatus() == null
                || payment.getStatus().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Payment status is required."
            );
        }
    }
}
