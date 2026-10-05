package com.petcaremax.view;

import javax.swing.JFrame;

public class ReportsPanel extends JFrame {

    public ReportsPanel() {

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setTitle(
                "PetCareMAX - Reports"
        );

        setSize(
                600,
                400
        );

        setLocationRelativeTo(null);
    }
}