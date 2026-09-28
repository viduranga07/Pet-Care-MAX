package com.petcaremax.service;

import com.petcaremax.dao.AppointmentDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    public AppointmentService() {
    this.appointmentDAO = DAOFactory.createAppointmentDAO();
}

    public boolean addAppointment(Appointment appointment) {

        validateAppointment(appointment);

        return appointmentDAO.addAppointment(appointment);
    }

    public boolean updateAppointment(Appointment appointment) {

        if (appointment == null) {
            throw new IllegalArgumentException(
                    "Appointment cannot be null."
            );
        }

        if (appointment.getAppointmentId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid appointment ID."
            );
        }

        validateAppointment(appointment);

        return appointmentDAO.updateAppointment(appointment);
    }

    public boolean deleteAppointment(int appointmentId) {

        if (appointmentId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid appointment ID."
            );
        }

        return appointmentDAO.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    private void validateAppointment(Appointment appointment) {

        if (appointment == null) {
            throw new IllegalArgumentException(
                    "Appointment cannot be null."
            );
        }

        if (appointment.getPetId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a pet."
            );
        }

        if (appointment.getVeterinarianId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a veterinarian."
            );
        }

        if (appointment.getServiceId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a service."
            );
        }

        LocalDate appointmentDate =
                appointment.getAppointmentDate();

        if (appointmentDate == null) {
            throw new IllegalArgumentException(
                    "Appointment date is required."
            );
        }

        LocalTime appointmentTime =
                appointment.getAppointmentTime();

        if (appointmentTime == null) {
            throw new IllegalArgumentException(
                    "Appointment time is required."
            );
        }

        if (appointment.getStatus() == null
                || appointment.getStatus().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment status is required."
            );
        }
    }
}