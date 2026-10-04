// This class handles one part of the PetCareMAX application.
package com.petcaremax.factory;

import com.petcaremax.dao.AddUserDAO;
import com.petcaremax.dao.AddUserDAOImpl;

import com.petcaremax.dao.AppointmentDAO;
import com.petcaremax.dao.AppointmentDAOImpl;

import com.petcaremax.dao.CustomerDAO;
import com.petcaremax.dao.CustomerDAOImpl;

import com.petcaremax.dao.MedicationDAO;
import com.petcaremax.dao.MedicationDAOImpl;

import com.petcaremax.dao.PaymentDAO;
import com.petcaremax.dao.PaymentDAOImpl;

import com.petcaremax.dao.PetDAO;
import com.petcaremax.dao.PetDAOImpl;

import com.petcaremax.dao.ServiceDAO;
import com.petcaremax.dao.ServiceDAOImpl;

import com.petcaremax.dao.TreatmentDAO;
import com.petcaremax.dao.TreatmentDAOImpl;

import com.petcaremax.dao.TreatmentMedicationDAO;
import com.petcaremax.dao.TreatmentMedicationDAOImpl;

import com.petcaremax.dao.VeterinarianDAO;
import com.petcaremax.dao.VeterinarianDAOImpl;


// This factory creates the DAO objects used by the application.
public class DAOFactory {

    private DAOFactory() {
        // Prevent creating objects from this class
    }

    public static CustomerDAO createCustomerDAO() {
        return new CustomerDAOImpl();
    }

    public static PetDAO createPetDAO() {
        return new PetDAOImpl();
    }

    public static VeterinarianDAO createVeterinarianDAO() {
        return new VeterinarianDAOImpl();
    }

    public static ServiceDAO createServiceDAO() {
        return new ServiceDAOImpl();
    }

    public static AppointmentDAO createAppointmentDAO() {
        return new AppointmentDAOImpl();
    }

    public static TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAOImpl();
    }

    public static MedicationDAO createMedicationDAO() {
        return new MedicationDAOImpl();
    }

    public static TreatmentMedicationDAO createTreatmentMedicationDAO() {
        return new TreatmentMedicationDAOImpl();
    }

    public static PaymentDAO createPaymentDAO() {
        return new PaymentDAOImpl();
    }

    public static AddUserDAO createUserDAO() {
        return new AddUserDAOImpl();
    }
}
