// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Veterinarian;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class VeterinarianDAOImpl implements VeterinarianDAO {

    @Override
    public boolean addVeterinarian(Veterinarian veterinarian) {

        String sql = """
                INSERT INTO veterinarians
                (full_name, specialization, phone, email, availability, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, veterinarian.getFullName());
            statement.setString(2, veterinarian.getSpecialization());
            statement.setString(3, veterinarian.getPhone());
            statement.setString(4, veterinarian.getEmail());
            statement.setString(5, veterinarian.getAvailability());
            statement.setString(6, veterinarian.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateVeterinarian(Veterinarian veterinarian) {

        String sql = """
                UPDATE veterinarians
                SET full_name = ?,
                    specialization = ?,
                    phone = ?,
                    email = ?,
                    availability = ?,
                    status = ?
                WHERE veterinarian_id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, veterinarian.getFullName());
            statement.setString(2, veterinarian.getSpecialization());
            statement.setString(3, veterinarian.getPhone());
            statement.setString(4, veterinarian.getEmail());
            statement.setString(5, veterinarian.getAvailability());
            statement.setString(6, veterinarian.getStatus());
            statement.setInt(7, veterinarian.getVeterinarianId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteVeterinarian(int veterinarianId) {

        String sql = """
                DELETE FROM veterinarians
                WHERE veterinarian_id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, veterinarianId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Veterinarian> getAllVeterinarians() {

        List<Veterinarian> veterinarians = new ArrayList<>();

        String sql = """
                SELECT veterinarian_id,
                       full_name,
                       specialization,
                       phone,
                       email,
                       availability,
                       status
                FROM veterinarians
                ORDER BY veterinarian_id DESC
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Veterinarian veterinarian = new Veterinarian(
                        resultSet.getInt("veterinarian_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("specialization"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("availability"),
                        resultSet.getString("status")
                );

                veterinarians.add(veterinarian);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return veterinarians;
    }
}
