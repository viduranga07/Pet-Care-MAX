// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.TreatmentMedication;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class TreatmentMedicationDAOImpl implements TreatmentMedicationDAO {

    @Override
    public boolean addTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        String sql = "INSERT INTO treatment_medications "
                + "(treatment_id, medication_id, dosage, frequency, "
                + "duration, instructions) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentMedication.getTreatmentId());
            statement.setInt(2, treatmentMedication.getMedicationId());
            statement.setString(3, treatmentMedication.getDosage());
            statement.setString(4, treatmentMedication.getFrequency());
            statement.setString(5, treatmentMedication.getDuration());
            statement.setString(6, treatmentMedication.getInstructions());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTreatmentMedication(
            TreatmentMedication treatmentMedication) {

        String sql = "UPDATE treatment_medications SET "
                + "dosage = ?, frequency = ?, duration = ?, "
                + "instructions = ? "
                + "WHERE treatment_id = ? AND medication_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, treatmentMedication.getDosage());
            statement.setString(2, treatmentMedication.getFrequency());
            statement.setString(3, treatmentMedication.getDuration());
            statement.setString(4, treatmentMedication.getInstructions());
            statement.setInt(5, treatmentMedication.getTreatmentId());
            statement.setInt(6, treatmentMedication.getMedicationId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTreatmentMedication(
            int treatmentId,
            int medicationId) {

        String sql = "DELETE FROM treatment_medications "
                + "WHERE treatment_id = ? AND medication_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, treatmentId);
            statement.setInt(2, medicationId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TreatmentMedication> getAllTreatmentMedications() {

        List<TreatmentMedication> treatmentMedications =
                new ArrayList<>();

        String sql = "SELECT treatment_id, medication_id, "
                + "dosage, frequency, duration, instructions "
                + "FROM treatment_medications "
                + "ORDER BY treatment_id DESC";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                TreatmentMedication treatmentMedication =
                        new TreatmentMedication(
                                resultSet.getInt("treatment_id"),
                                resultSet.getInt("medication_id"),
                                resultSet.getString("dosage"),
                                resultSet.getString("frequency"),
                                resultSet.getString("duration"),
                                resultSet.getString("instructions")
                        );

                treatmentMedications.add(treatmentMedication);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return treatmentMedications;
    }
}
