package com.petcaremax.controller;

import com.petcaremax.model.Service;
import com.petcaremax.service.ServiceService;

import java.util.List;

public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController() {
        serviceService = new ServiceService();
    }

    public boolean addService(Service service) {
        return serviceService.addService(service);
    }

    public boolean updateService(Service service) {
        return serviceService.updateService(service);
    }

    public boolean deleteService(int serviceId) {
        return serviceService.deleteService(serviceId);
    }

    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }
}