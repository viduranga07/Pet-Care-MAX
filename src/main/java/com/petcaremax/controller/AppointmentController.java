// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Appointment;
import com.petcaremax.service.AppointmentService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController() {
        appointmentService = new AppointmentService();
    }

    public boolean addAppointment(Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    public boolean updateAppointment(Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }

    public boolean deleteAppointment(int appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
}
