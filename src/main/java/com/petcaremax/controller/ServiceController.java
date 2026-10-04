// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.model.Service;
import com.petcaremax.service.ServiceService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
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
