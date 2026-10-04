// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.exception.CustomerValidationException;
import com.petcaremax.model.Customer;

// This controller receives user actions and passes the work to the service layer.
public class CustomerControllerTest {

    public static void main(String[] args)
            throws CustomerValidationException {

        CustomerController controller =
                new CustomerController();

        boolean result = controller.addCustomer(
                "Test Customer",
                "0711111111",
                "testcustomer@gmail.com",
                "Colombo"
        );

        System.out.println(
                "Customer added: " + result
        );

        System.out.println("\nAll Customers:");

        for (Customer customer :
                controller.getAllCustomers()) {

            System.out.println(
                    customer.getCustomerId()
                    + " | "
                    + customer.getFullName()
                    + " | "
                    + customer.getPhone()
                    + " | "
                    + customer.getEmail()
            );
        }
    }
}
