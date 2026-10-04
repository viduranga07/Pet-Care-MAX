// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.view.TreatmentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles button actions for TreatmentForm.
 * The view stays focused on displaying data and collecting input.
 */
// This controller receives user actions and passes the work to the service layer.
public class TreatmentFormController implements ActionListener {
    private final TreatmentForm view;

    public TreatmentFormController(TreatmentForm view) {
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
            view.saveTreatment();
        }
        else if (event.getSource() == view.getBtnClear()) {
            view.clearTreatmentFields();
        }
        else if (event.getSource() == view.getBtnUpdate()) {
            view.updateTreatment();
        }
        else if (event.getSource() == view.getBtnDelete()) {
            view.deleteTreatment();
        }
        else if (event.getSource() == view.getBtnSearch()) {
            view.searchTreatments();
        }
    }
}
