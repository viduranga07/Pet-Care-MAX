// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Appointment;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface AppointmentDAO {

    boolean addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);

    List<Appointment> getAllAppointments();
}
