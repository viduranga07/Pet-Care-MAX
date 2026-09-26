package com.petcaremax.service;

import com.petcaremax.dao.DashboardDAO;

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

    public double getRevenue() throws Exception {
        return dashboardDAO.getRevenue();
    }
}