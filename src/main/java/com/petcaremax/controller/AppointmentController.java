package com.petcaremax.controller;

import com.petcaremax.model.Appointment;
import com.petcaremax.service.AppointmentService;

import java.util.List;

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