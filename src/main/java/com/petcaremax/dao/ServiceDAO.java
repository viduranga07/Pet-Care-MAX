// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Service;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface ServiceDAO {

    boolean addService(Service service);

    boolean updateService(Service service);

    boolean deleteService(int serviceId);

    List<Service> getAllServices();
}
