package com.petcaremax.dao;

import com.petcaremax.model.Payment;
import java.util.List;

public interface PaymentDAO {

    boolean addPayment(Payment payment);

    boolean updatePayment(Payment payment);

    boolean deletePayment(int paymentId);

    List<Payment> getAllPayments();
}