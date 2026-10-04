// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Payment;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean addPayment(Payment payment) {

        String sql = "INSERT INTO payments "
                + "(appointment_id, amount, payment_method, payment_date, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getAppointmentId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());

            if (payment.getPaymentDate() != null) {
                statement.setTimestamp(
                        4,
                        Timestamp.valueOf(payment.getPaymentDate())
                );
            } else {
                statement.setTimestamp(
                        4,
                        new Timestamp(System.currentTimeMillis())
                );
            }

            statement.setString(5, payment.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {

        String sql = "UPDATE payments SET "
                + "appointment_id = ?, "
                + "amount = ?, "
                + "payment_method = ?, "
                + "payment_date = ?, "
                + "status = ? "
                + "WHERE payment_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getAppointmentId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());

            if (payment.getPaymentDate() != null) {
                statement.setTimestamp(
                        4,
                        Timestamp.valueOf(payment.getPaymentDate())
                );
            } else {
                statement.setTimestamp(
                        4,
                        new Timestamp(System.currentTimeMillis())
                );
            }

            statement.setString(5, payment.getStatus());
            statement.setInt(6, payment.getPaymentId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePayment(int paymentId) {

        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, paymentId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT payment_id, appointment_id, amount, "
                + "payment_method, payment_date, status "
                + "FROM payments "
                + "ORDER BY payment_id DESC";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Timestamp timestamp =
                        resultSet.getTimestamp("payment_date");

                Payment payment = new Payment(
                        resultSet.getInt("payment_id"),
                        resultSet.getInt("appointment_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_method"),
                        timestamp != null
                                ? timestamp.toLocalDateTime()
                                : null,
                        resultSet.getString("status")
                );

                payments.add(payment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }
}
