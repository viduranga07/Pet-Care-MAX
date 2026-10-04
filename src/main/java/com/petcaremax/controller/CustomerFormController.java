// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.view.CustomerForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles button actions for CustomerForm.
 * The view stays focused on displaying data and collecting input.
 */
// This controller receives user actions and passes the work to the service layer.
public class CustomerFormController implements ActionListener {
    private final CustomerForm view;

    public CustomerFormController(CustomerForm view) {
        this.view = view;
        bindEvents();
    }

    // Connect the form buttons to this controller.
    private void bindEvents() {
        view.getBtnSave().addActionListener(this);
        view.getBtnUpdate().addActionListener(this);
        view.getBtnDelete().addActionListener(this);
        view.getBtnClear().addActionListener(this);
        view.getBtnSearch().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Send the selected action to the form.
        if (event.getSource() == view.getBtnSave()) {
            view.saveCustomer();
        }
        else if (event.getSource() == view.getBtnUpdate()) {
            view.updateCustomer();
        }
        else if (event.getSource() == view.getBtnDelete()) {
            view.deleteCustomer();
        }
        else if (event.getSource() == view.getBtnClear()) {
            view.clearCustomer();
        }
        else if (event.getSource() == view.getBtnSearch()) {
            view.searchCustomers();
        }
    }
}
