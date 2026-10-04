// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.view.AppointmentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles button actions for AppointmentForm.
 * The view stays focused on displaying data and collecting input.
 */
// This controller receives user actions and passes the work to the service layer.
public class AppointmentFormController implements ActionListener {
    private final AppointmentForm view;

    public AppointmentFormController(AppointmentForm view) {
        this.view = view;
        bindEvents();
    }

    // Connect the form buttons to this controller.
    private void bindEvents() {
        view.getBtnSave().addActionListener(this);
        view.getBtnClear().addActionListener(this);
        view.getBtnUpdate().addActionListener(this);
        view.getBtnDelete().addActionListener(this);
        view.getBtnSearch().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Send the selected action to the form.
        if (event.getSource() == view.getBtnSave()) {
            view.saveAppointment();
        }
        else if (event.getSource() == view.getBtnClear()) {
            view.clearAppointmentFields();
        }
        else if (event.getSource() == view.getBtnUpdate()) {
            view.updateAppointment();
        }
        else if (event.getSource() == view.getBtnDelete()) {
            view.deleteAppointment();
        }
        else if (event.getSource() == view.getBtnSearch()) {
            view.searchAppointments();
        }
    }
}
