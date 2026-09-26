package com.petcaremax.service;

import com.petcaremax.dao.CustomerDAO;
import com.petcaremax.dao.CustomerDAOImpl;
import com.petcaremax.model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
    }

    public boolean addCustomer(Customer customer) {

        validateCustomer(customer);

        return customerDAO.addCustomer(customer);
    }

    public Customer getCustomerById(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        return customerDAO.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {

        return customerDAO.getAllCustomers();
    }

    public boolean updateCustomer(Customer customer) {

        if (customer.getCustomerId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        validateCustomer(customer);

        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        return customerDAO.deleteCustomer(customerId);
    }

    private void validateCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        if (customer.getFullName() == null ||
            customer.getFullName().isBlank()) {

            throw new IllegalArgumentException(
                    "Customer name is required."
            );
        }

        if (customer.getPhone() == null ||
            customer.getPhone().isBlank()) {

            throw new IllegalArgumentException(
                    "Phone number is required."
            );
        }

        if (customer.getEmail() != null &&
            !customer.getEmail().isBlank() &&
            !customer.getEmail().contains("@")) {

            throw new IllegalArgumentException(
                    "Please enter a valid email address."
            );
        }
    }
}