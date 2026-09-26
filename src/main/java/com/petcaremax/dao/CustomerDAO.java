package com.petcaremax.dao;

import com.petcaremax.model.Customer;
import java.util.List;

public interface CustomerDAO {

    boolean addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);
}