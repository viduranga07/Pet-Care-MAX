// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Payment;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface PaymentDAO {

    boolean addPayment(Payment payment);

    boolean updatePayment(Payment payment);

    boolean deletePayment(int paymentId);

    List<Payment> getAllPayments();
}
