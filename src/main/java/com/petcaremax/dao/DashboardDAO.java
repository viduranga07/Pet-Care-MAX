package com.petcaremax.dao;

import com.petcaremax.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public double getRevenue() throws Exception {

        String sql = "SELECT COALESCE(SUM(amount), 0) FROM payments";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }

        return 0.0;
    }

    private int getCount(String sql) throws Exception {

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }
}