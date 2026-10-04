// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Appointment;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments "
                + "(pet_id, veterinarian_id, service_id, "
                + "appointment_date, appointment_time, reason, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPetId());
            statement.setInt(2, appointment.getVeterinarianId());
            statement.setInt(3, appointment.getServiceId());

            statement.setDate(
                    4,
                    Date.valueOf(appointment.getAppointmentDate())
            );

            statement.setTime(
                    5,
                    Time.valueOf(appointment.getAppointmentTime())
            );

            statement.setString(6, appointment.getReason());
            statement.setString(7, appointment.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {

        String sql = "UPDATE appointments SET "
                + "pet_id = ?, "
                + "veterinarian_id = ?, "
                + "service_id = ?, "
                + "appointment_date = ?, "
                + "appointment_time = ?, "
                + "reason = ?, "
                + "status = ? "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPetId());
            statement.setInt(2, appointment.getVeterinarianId());
            statement.setInt(3, appointment.getServiceId());

            statement.setDate(
                    4,
                    Date.valueOf(appointment.getAppointmentDate())
            );

            statement.setTime(
                    5,
                    Time.valueOf(appointment.getAppointmentTime())
            );

            statement.setString(6, appointment.getReason());
            statement.setString(7, appointment.getStatus());
            statement.setInt(8, appointment.getAppointmentId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {

        String sql =
                "DELETE FROM appointments "
                + "WHERE appointment_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT appointment_id, pet_id, veterinarian_id, "
                + "service_id, appointment_date, appointment_time, "
                + "reason, status "
                + "FROM appointments "
                + "ORDER BY appointment_id DESC";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Appointment appointment =
                        new Appointment(
                                resultSet.getInt("appointment_id"),
                                resultSet.getInt("pet_id"),
                                resultSet.getInt("veterinarian_id"),
                                resultSet.getInt("service_id"),
                                resultSet.getDate("appointment_date")
                                        .toLocalDate(),
                                resultSet.getTime("appointment_time")
                                        .toLocalTime(),
                                resultSet.getString("reason"),
                                resultSet.getString("status")
                        );

                appointments.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
