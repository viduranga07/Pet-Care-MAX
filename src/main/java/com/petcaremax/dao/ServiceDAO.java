package com.petcaremax.dao;

import com.petcaremax.model.Service;
import java.util.List;

public interface ServiceDAO {

    boolean addService(Service service);

    boolean updateService(Service service);

    boolean deleteService(int serviceId);

    List<Service> getAllServices();
}