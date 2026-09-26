package com.petcaremax.controller;

import com.petcaremax.model.Customer;
import com.petcaremax.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    // Add Customer
    public boolean addCustomer(
            String fullName,
            String phone,
            String email,
            String address) {

        Customer customer = new Customer(
                fullName,
                phone,
                email,
                address
        );

        return customerService.addCustomer(customer);
    }

    // Get Customer by ID
    public Customer getCustomerById(int customerId) {

        return customerService.getCustomerById(customerId);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    // Update Customer
    public boolean updateCustomer(
            int customerId,
            String fullName,
            String phone,
            String email,
            String address) {

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

    // Delete Customer
    public boolean deleteCustomer(int customerId) {

        return customerService.deleteCustomer(customerId);
    }
}