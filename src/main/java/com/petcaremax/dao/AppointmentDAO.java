package com.petcaremax.dao;

import com.petcaremax.model.Appointment;
import java.util.List;

public interface AppointmentDAO {

    boolean addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);

    List<Appointment> getAllAppointments();
}