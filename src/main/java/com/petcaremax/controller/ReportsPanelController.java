package com.petcaremax.controller;

import com.petcaremax.service.ReportService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReportsPanelController implements ActionListener {

    private final JButton appointmentReportButton;
    private final JButton paymentReportButton;
    private final JFrame parentView;

    private final ReportService reportService;

    public ReportsPanelController(
            JButton appointmentReportButton,
            JButton paymentReportButton,
            JFrame parentView
    ) {

        this.appointmentReportButton =
                appointmentReportButton;

        this.paymentReportButton =
                paymentReportButton;

        this.parentView =
                parentView;

        this.reportService =
                new ReportService();

        appointmentReportButton.addActionListener(this);
        paymentReportButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(
            ActionEvent event
    ) {

        // APPOINTMENT REPORT

        if (
                event.getSource() ==
                appointmentReportButton
        ) {

            try {

                reportService.showAppointmentReport();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        parentView,
                        "Unable to generate appointment report.\n\n"
                        + ex.getMessage(),
                        "Report Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }

        // PAYMENT REPORT

        else if (
                event.getSource() ==
                paymentReportButton
        ) {

            try {

                reportService.showPaymentReport();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        parentView,
                        "Unable to generate payment report.\n\n"
                        + ex.getMessage(),
                        "Report Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}