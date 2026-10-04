// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.view.PaymentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles button actions for PaymentForm.
 * The view stays focused on displaying data and collecting input.
 */
// This controller receives user actions and passes the work to the service layer.
public class PaymentFormController implements ActionListener {
    private final PaymentForm view;

    public PaymentFormController(PaymentForm view) {
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
            view.savePayment();
        }
        else if (event.getSource() == view.getBtnUpdate()) {
            view.updatePayment();
        }
        else if (event.getSource() == view.getBtnDelete()) {
            view.deletePayment();
        }
        else if (event.getSource() == view.getBtnClear()) {
            view.clearPaymentFields();
        }
        else if (event.getSource() == view.getBtnSearch()) {
            view.searchPayments();
        }
    }
}
