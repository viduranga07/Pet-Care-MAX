package com.petcaremax.dao;

import com.petcaremax.model.Treatment;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAOImpl implements TreatmentDAO {

    @Override
    public boolean addTreatment(Treatment treatment) {

        String sql = "INSERT INTO treatments "
                + "(appointment_id, diagnosis, treatment_description, "
                + "treatment_date, notes) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getAppointmentId());
            statement.setString(2, treatment.getDiagnosis());
            statement.setString(3, treatment.getTreatmentDescription());
            statement.setDate(4,
                    java.sql.Date.valueOf(treatment.getTreatmentDate()));
            statement.setString(5, treatment.getNotes());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTreatment(Treatment treatment) {

        String sql = "UPDATE treatments SET "
                + "appointment_id = ?, "
                + "diagnosis = ?, "
                + "treatment_description = ?, "
                + "treatment_date = ?, "
                + "notes = ? "
                + "WHERE treatment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getAppointmentId());
            statement.setString(2, treatment.getDiagnosis());
            statement.setString(3, treatment.getTreatmentDescription());
            statement.setDate(4,
                    java.sql.Date.valueOf(treatment.getTreatmentDate()));
            statement.setString(5, treatment.getNotes());
            statement.setInt(6, treatment.getTreatmentId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTreatment(int treatmentId) {

        String sql = "DELETE FROM treatments WHERE treatment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT treatment_id, appointment_id, diagnosis, "
                + "treatment_description, treatment_date, notes "
                + "FROM treatments "
                + "ORDER BY treatment_id DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Treatment treatment = new Treatment();

                treatment.setTreatmentId(
                        resultSet.getInt("treatment_id"));

                treatment.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                treatment.setDiagnosis(
                        resultSet.getString("diagnosis"));

                treatment.setTreatmentDescription(
                        resultSet.getString("treatment_description"));

                treatment.setTreatmentDate(
                        resultSet.getDate("treatment_date").toLocalDate());

                treatment.setNotes(
                        resultSet.getString("notes"));

                treatments.add(treatment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return treatments;
    }
}