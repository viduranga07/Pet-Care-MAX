package com.petcaremax.dao;

import com.petcaremax.model.Medication;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAOImpl implements MedicationDAO {

    @Override
    public boolean addMedication(Medication medication) {

        String sql = "INSERT INTO medications "
                + "(medication_name, description, unit_price, "
                + "stock_quantity, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, medication.getMedicationName());
            statement.setString(2, medication.getDescription());
            statement.setDouble(3, medication.getUnitPrice());
            statement.setInt(4, medication.getStockQuantity());
            statement.setString(5, medication.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMedication(Medication medication) {

        String sql = "UPDATE medications SET "
                + "medication_name = ?, "
                + "description = ?, "
                + "unit_price = ?, "
                + "stock_quantity = ?, "
                + "status = ? "
                + "WHERE medication_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, medication.getMedicationName());
            statement.setString(2, medication.getDescription());
            statement.setDouble(3, medication.getUnitPrice());
            statement.setInt(4, medication.getStockQuantity());
            statement.setString(5, medication.getStatus());
            statement.setInt(6, medication.getMedicationId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMedication(int medicationId) {

        String sql =
                "DELETE FROM medications WHERE medication_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, medicationId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Medication> getAllMedications() {

        List<Medication> medications = new ArrayList<>();

        String sql = "SELECT medication_id, medication_name, "
                + "description, unit_price, stock_quantity, status "
                + "FROM medications "
                + "ORDER BY medication_id DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Medication medication = new Medication();

                medication.setMedicationId(
                        resultSet.getInt("medication_id"));

                medication.setMedicationName(
                        resultSet.getString("medication_name"));

                medication.setDescription(
                        resultSet.getString("description"));

                medication.setUnitPrice(
                        resultSet.getDouble("unit_price"));

                medication.setStockQuantity(
                        resultSet.getInt("stock_quantity"));

                medication.setStatus(
                        resultSet.getString("status"));

                medications.add(medication);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return medications;
    }
}