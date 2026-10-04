// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.exception.CustomerValidationException;
import com.petcaremax.model.Customer;
import com.petcaremax.service.CustomerService;

import java.util.List;

// This controller receives user actions and passes the work to the service layer.
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    // ADD CUSTOMER

    public boolean addCustomer(
            String fullName,
            String phone,
            String email,
            String address)
            throws CustomerValidationException {

        Customer customer = new Customer(
                fullName,
                phone,
                email,
                address
        );

        return customerService.addCustomer(customer);
    }

    // GET CUSTOMER BY ID

    public Customer getCustomerById(int customerId) {

        return customerService.getCustomerById(customerId);
    }

    // GET ALL CUSTOMERS

    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    // UPDATE CUSTOMER

    public boolean updateCustomer(
            int customerId,
            String fullName,
            String phone,
            String email,
            String address)
            throws CustomerValidationException {

        Customer customer = new Customer(
                customerId,
                fullName,
                phone,
                email,
                address,
                null
        );

        return customerService.updateCustomer(customer);
    }

    // DELETE CUSTOMER

    public boolean deleteCustomer(int customerId) {

        return customerService.deleteCustomer(customerId);
    }
}
