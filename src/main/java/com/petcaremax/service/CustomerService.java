// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.dao.CustomerDAO;
import com.petcaremax.factory.DAOFactory;
import com.petcaremax.model.Customer;
import com.petcaremax.exception.CustomerValidationException;

import java.util.List;

// This service keeps the business rules separate from the user interface.
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = DAOFactory.createCustomerDAO();
    }

    // ADD CUSTOMER

    public boolean addCustomer(Customer customer)
            throws CustomerValidationException {

        validateCustomer(customer);

        return customerDAO.addCustomer(customer);
    }

    // GET CUSTOMER BY ID

    public Customer getCustomerById(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        return customerDAO.getCustomerById(customerId);
    }

    // GET ALL CUSTOMERS

    public List<Customer> getAllCustomers() {

        return customerDAO.getAllCustomers();
    }

    // UPDATE CUSTOMER

    public boolean updateCustomer(Customer customer)
            throws CustomerValidationException {

        if (customer == null) {
            throw new CustomerValidationException(
                    "Customer cannot be null."
            );
        }

        if (customer.getCustomerId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        validateCustomer(customer);

        return customerDAO.updateCustomer(customer);
    }

    // DELETE CUSTOMER

    public boolean deleteCustomer(int customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid customer ID."
            );
        }

        return customerDAO.deleteCustomer(customerId);
    }

    // VALIDATE CUSTOMER
    // USER-DEFINED EXCEPTION USED HERE

    private void validateCustomer(Customer customer)
            throws CustomerValidationException {

        if (customer == null) {
            throw new CustomerValidationException(
                    "Customer cannot be null."
            );
        }

        if (customer.getFullName() == null ||
            customer.getFullName().isBlank()) {

            throw new CustomerValidationException(
                    "Customer name is required."
            );
        }

        if (customer.getPhone() == null ||
            customer.getPhone().isBlank()) {

            throw new CustomerValidationException(
                    "Phone number is required."
            );
        }

        if (customer.getEmail() != null &&
            !customer.getEmail().isBlank() &&
            !customer.getEmail().contains("@")) {

            throw new CustomerValidationException(
                    "Please enter a valid email address."
            );
        }
    }
}
