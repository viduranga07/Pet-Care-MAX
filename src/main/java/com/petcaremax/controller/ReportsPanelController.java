// This class handles one part of the PetCareMAX application.
package com.petcaremax.controller;

import com.petcaremax.service.ReportService;
import com.petcaremax.view.ReportsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

// This controller handles report button actions for the report screen.
public class ReportsPanelController implements ActionListener {

    private final ReportsPanel view;
    private final ReportService reportService;

    public ReportsPanelController(ReportsPanel view) {
        this.view = view;
        this.reportService = new ReportService();
        view.getReportsButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == view.getReportsButton()) {
            try {
                reportService.showAppointmentReport();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        view,
                        "Unable to generate appointment report.\n\n" + ex.getMessage(),
                        "Report Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
