// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.view.VeterinarianForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles button actions for VeterinarianForm.
 * The view stays focused on displaying data and collecting input.
 */
// This controller receives user actions and passes the work to the service layer.
public class VeterinarianFormController implements ActionListener {
    private final VeterinarianForm view;

    public VeterinarianFormController(VeterinarianForm view) {
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
            view.saveVeterinarian();
        }
        else if (event.getSource() == view.getBtnUpdate()) {
            view.updateVeterinarian();
        }
        else if (event.getSource() == view.getBtnDelete()) {
            view.deleteVeterinarian();
        }
        else if (event.getSource() == view.getBtnClear()) {
            view.clearVeterinarian();
        }
        else if (event.getSource() == view.getBtnSearch()) {
            view.searchVeterinarians();
        }
    }
}
