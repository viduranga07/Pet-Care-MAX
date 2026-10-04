// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.DashboardDAO;
import java.util.List;

// This service keeps the business rules separate from the user interface.
public class DashboardService {

    private final DashboardDAO dashboardDAO;

    public DashboardService() {
        dashboardDAO = new DashboardDAO();
    }

    public int getCustomerCount() throws Exception {
        return dashboardDAO.getCustomerCount();
    }

    public int getPetCount() throws Exception {
        return dashboardDAO.getPetCount();
    }

    public int getAppointmentCount() throws Exception {
        return dashboardDAO.getAppointmentCount();
    }
    
    public List<Object[]> getRecentAppointments() throws Exception {
    return dashboardDAO.getRecentAppointments();
}

    public double getRevenue() throws Exception {
        return dashboardDAO.getRevenue();
    }
}
