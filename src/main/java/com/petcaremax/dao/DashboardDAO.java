// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class DashboardDAO {

    public int getCustomerCount() throws Exception {
        return getCount("SELECT COUNT(*) FROM customers");
    }

    public int getPetCount() throws Exception {
        return getCount("SELECT COUNT(*) FROM pets");
    }

    public int getAppointmentCount() throws Exception {
        return getCount("SELECT COUNT(*) FROM appointments");
    }

    public List<Object[]> getRecentAppointments() throws Exception {

        List<Object[]> appointments = new ArrayList<>();

        String sql = """
            SELECT
                c.full_name AS customer_name,
                p.pet_name,
                v.full_name AS veterinarian_name,
                a.appointment_date,
                a.status
            FROM appointments a
            INNER JOIN pets p
                ON a.pet_id = p.pet_id
            INNER JOIN customers c
                ON p.customer_id = c.customer_id
            INNER JOIN veterinarians v
                ON a.veterinarian_id = v.veterinarian_id
            ORDER BY a.appointment_date DESC, a.appointment_time DESC
            LIMIT 3
            """;

        try (
            Connection connection =
                    DBConnection.getInstance().getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            while (resultSet.next()) {

                appointments.add(new Object[]{
                    resultSet.getString("customer_name"),
                    resultSet.getString("pet_name"),
                    resultSet.getString("veterinarian_name"),
                    resultSet.getDate("appointment_date"),
                    resultSet.getString("status")
                });
            }
        }

        return appointments;
    }

    public double getRevenue() throws Exception {

        String sql =
                "SELECT COALESCE(SUM(amount), 0) " +
                "FROM payments WHERE status = 'Paid'";

        try (
            Connection connection =
                    DBConnection.getInstance().getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }

        return 0.0;
    }

    private int getCount(String sql) throws Exception {

        try (
            Connection connection =
                    DBConnection.getInstance().getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }
}
