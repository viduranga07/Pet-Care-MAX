// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.ServiceDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Service;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class ServiceService {

    private final ServiceDAO serviceDAO;

   public ServiceService() {
    this.serviceDAO = DAOFactory.createServiceDAO();
}

    public boolean addService(Service service) {

        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null.");
        }

        if (service.getServiceName() == null
                || service.getServiceName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Service name is required."
            );
        }

        if (service.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than zero."
            );
        }

        if (service.getStatus() == null
                || service.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Service status is required."
            );
        }

        return serviceDAO.addService(service);
    }

    public boolean updateService(Service service) {

        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null.");
        }

        if (service.getServiceId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid service ID."
            );
        }

        if (service.getServiceName() == null
                || service.getServiceName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Service name is required."
            );
        }

        if (service.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than zero."
            );
        }

        if (service.getStatus() == null
                || service.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Service status is required."
            );
        }

        return serviceDAO.updateService(service);
    }

    public boolean deleteService(int serviceId) {

        if (serviceId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid service ID."
            );
        }

        return serviceDAO.deleteService(serviceId);
    }

    public List<Service> getAllServices() {
        return serviceDAO.getAllServices();
    }
}
