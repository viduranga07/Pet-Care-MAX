/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.PaymentFormController;

import com.petcaremax.controller.PaymentController;
import com.petcaremax.controller.AppointmentController;
import com.petcaremax.util.PetCareTheme;
import com.petcaremax.model.Payment;
import com.petcaremax.model.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author Bimsara
 */
public class PaymentForm extends javax.swing.JFrame {
    private final PaymentFormController formController;

    
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(PaymentForm.class.getName());
    
private final PaymentController paymentController;
private final AppointmentController appointmentController;

private List<Appointment> appointments;
private List<Payment> payments;

private int selectedPaymentId = -1;
    /**
     * Creates new form PaymentForm
     */
   public PaymentForm() {

    initComponents();

    formController = new PaymentFormController(this);

    paymentController = new PaymentController();
    appointmentController = new AppointmentController();

    cmbPaymentMethod.removeAllItems();
    cmbPaymentMethod.addItem("Cash");
    cmbPaymentMethod.addItem("Card");
    cmbPaymentMethod.addItem("Bank Transfer");

    cmbStatus.removeAllItems();
    cmbStatus.addItem("Paid");
    cmbStatus.addItem("Pending");
    cmbStatus.addItem("Refunded");

    PetCareTheme.apply(this);
    loadAppointments();
    loadPayments();
    clearPaymentFields();
    tblPayments.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPaymentsMouseClicked(evt);
        }
    });

    setLocationRelativeTo(null);
}
private void tblPaymentsMouseClicked(java.awt.event.MouseEvent evt) {

    int selectedRow = tblPayments.getSelectedRow();

    if (selectedRow == -1) {
        return;
    }

    try {

        selectedPaymentId =
                Integer.parseInt(
                        tblPayments
                                .getValueAt(selectedRow, 0)
                                .toString()
                );

        String appointmentValue =
                tblPayments
                        .getValueAt(selectedRow, 1)
                        .toString();

        int appointmentId =
                Integer.parseInt(
                        appointmentValue.split(" - ")[0]
                );

        for (int i = 0; i < appointments.size(); i++) {

            if (appointments.get(i).getAppointmentId()
                    == appointmentId) {

                cmbAppointment.setSelectedIndex(i);
                break;
            }
        }

        txtAmount.setText(
                tblPayments
                        .getValueAt(selectedRow, 2)
                        .toString()
        );

        cmbPaymentMethod.setSelectedItem(
                tblPayments
                        .getValueAt(selectedRow, 3)
                        .toString()
        );

        txtPaymentDate.setText(
                tblPayments
                        .getValueAt(selectedRow, 4)
                        .toString()
        );

        cmbStatus.setSelectedItem(
                tblPayments
                        .getValueAt(selectedRow, 5)
                        .toString()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load selected payment.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   private void loadAppointments() {

    appointments =
            appointmentController.getAllAppointments();

    cmbAppointment.removeAllItems();

    for (Appointment appointment : appointments) {

        String displayText =
                appointment.getAppointmentId()
                + " - "
                + appointment.getAppointmentDate()
                + " "
                + appointment.getAppointmentTime();

        cmbAppointment.addItem(displayText);
    }
}
   private void loadPayments() {

    payments =
            paymentController.getAllPayments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblPayments.getModel();

    model.setRowCount(0);

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    for (Payment payment : payments) {

        String appointmentText =
                appointments.stream()
                        .filter(a ->
                                a.getAppointmentId()
                                        == payment.getAppointmentId())
                        .map(a ->
                                a.getAppointmentId()
                                + " - "
                                + a.getAppointmentDate()
                                + " "
                                + a.getAppointmentTime())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        payment.getAppointmentId())
                        );

        String paymentDate =
                payment.getPaymentDate() == null
                        ? ""
                        : payment.getPaymentDate()
                                .format(formatter);

        model.addRow(new Object[]{
            payment.getPaymentId(),
            appointmentText,
            String.format("%.2f", payment.getAmount()),
            payment.getPaymentMethod(),
            paymentDate,
            payment.getStatus()
        });
    }
}
   public void clearPaymentFields() {

    if (cmbAppointment.getItemCount() > 0) {
        cmbAppointment.setSelectedIndex(0);
    }

    txtAmount.setText("");

    if (cmbPaymentMethod.getItemCount() > 0) {
        cmbPaymentMethod.setSelectedIndex(0);
    }

    txtPaymentDate.setText(
            LocalDateTime.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "yyyy-MM-dd HH:mm:ss"
                            )
                    )
    );

    if (cmbStatus.getItemCount() > 0) {
        cmbStatus.setSelectedIndex(0);
    }

    txtSearch.setText("");

    selectedPaymentId = -1;

    tblPayments.clearSelection();

    loadPayments();
}
   public void savePayment() {

    try {

        int appointmentIndex = cmbAppointment.getSelectedIndex();

        if (appointmentIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String amountText = txtAmount.getText().trim();

        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter payment amount.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtAmount.requestFocus();
            return;
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Payment amount must be greater than zero.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Appointment selectedAppointment =
                appointments.get(appointmentIndex);

        String paymentMethod =
                cmbPaymentMethod.getSelectedItem().toString();

        String status =
                cmbStatus.getSelectedItem().toString();

        LocalDateTime paymentDate;

        String dateText =
                txtPaymentDate.getText().trim();

        if (dateText.isEmpty()) {

            paymentDate = LocalDateTime.now();

        } else {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm:ss"
                    );

            paymentDate =
                    LocalDateTime.parse(
                            dateText,
                            formatter
                    );
        }

        Payment payment = new Payment(
                0,
                selectedAppointment.getAppointmentId(),
                amount,
                paymentMethod,
                paymentDate,
                status
        );

        boolean success =
                paymentController.addPayment(payment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPayments();
            clearPaymentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save payment.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Amount must be a valid number.",
                "Invalid Amount",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid payment date/time.\n\n"
                + "Use this format:\n"
                + "yyyy-MM-dd HH:mm:ss\n\n"
                + "Example:\n"
                + "2026-09-27 22:30:00",
                "Invalid Date/Time",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (IllegalArgumentException e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "An unexpected error occurred:\n"
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   public void updatePayment() {

    if (selectedPaymentId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a payment from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    try {

        int appointmentIndex = cmbAppointment.getSelectedIndex();

        if (appointmentIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String amountText = txtAmount.getText().trim();

        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter payment amount.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Payment amount must be greater than zero.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Appointment selectedAppointment =
                appointments.get(appointmentIndex);

        String paymentMethod =
                cmbPaymentMethod.getSelectedItem().toString();

        String status =
                cmbStatus.getSelectedItem().toString();

        String dateText =
                txtPaymentDate.getText().trim();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm:ss"
                );

        LocalDateTime paymentDate;

        if (dateText.isEmpty()) {
            paymentDate = LocalDateTime.now();
        } else {
            paymentDate =
                    LocalDateTime.parse(
                            dateText,
                            formatter
                    );
        }

        Payment payment = new Payment(
                selectedPaymentId,
                selectedAppointment.getAppointmentId(),
                amount,
                paymentMethod,
                paymentDate,
                status
        );

        boolean success =
                paymentController.updatePayment(payment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPayments();
            clearPaymentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update payment.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Amount must be a valid number.",
                "Invalid Amount",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid payment date/time.\n\n"
                + "Use:\n"
                + "yyyy-MM-dd HH:mm:ss",
                "Invalid Date/Time",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (IllegalArgumentException e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "An unexpected error occurred:\n"
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void deletePayment() {

    if (selectedPaymentId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a payment from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this payment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        boolean success =
                paymentController.deletePayment(
                        selectedPaymentId
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPayments();
            clearPaymentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete payment.",
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to delete payment:\n"
                + e.getMessage(),
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void searchPayments() {

    String keyword =
            txtSearch.getText().trim().toLowerCase();

    if (keyword.isEmpty()) {

        loadPayments();
        return;
    }

    List<Payment> filteredPayments =
            payments.stream()
                    .filter(payment -> {

                        String appointmentText =
                                String.valueOf(
                                        payment.getAppointmentId()
                                );

                        String amountText =
                                String.valueOf(
                                        payment.getAmount()
                                );

                        String method =
                                payment.getPaymentMethod() == null
                                        ? ""
                                        : payment.getPaymentMethod();

                        String status =
                                payment.getStatus() == null
                                        ? ""
                                        : payment.getStatus();

                        return String.valueOf(
                                    payment.getPaymentId())
                                .contains(keyword)

                                || appointmentText
                                        .contains(keyword)

                                || amountText
                                        .contains(keyword)

                                || method
                                        .toLowerCase()
                                        .contains(keyword)

                                || status
                                        .toLowerCase()
                                        .contains(keyword);
                    })
                    .toList();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblPayments.getModel();

    model.setRowCount(0);

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss"
            );

    for (Payment payment : filteredPayments) {

        String appointmentText =
                appointments.stream()
                        .filter(a ->
                                a.getAppointmentId()
                                        == payment.getAppointmentId())
                        .map(a ->
                                a.getAppointmentId()
                                + " - "
                                + a.getAppointmentDate()
                                + " "
                                + a.getAppointmentTime())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        payment.getAppointmentId())
                        );

        String paymentDate =
                payment.getPaymentDate() == null
                        ? ""
                        : payment.getPaymentDate()
                                .format(formatter);

        model.addRow(new Object[]{
            payment.getPaymentId(),
            appointmentText,
            String.format(
                    "%.2f",
                    payment.getAmount()
            ),
            payment.getPaymentMethod(),
            paymentDate,
            payment.getStatus()
        });
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbAppointment = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbPaymentMethod = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPaymentDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPayments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PetCareMAX - Payment Management");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 247, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Payment Management");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, 500, 42));

        jLabel2.setText("Appointment");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 115, 150, 24));
        jPanel1.add(cmbAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 142, 430, 40));

        jLabel3.setText("Amount");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 200, 150, 24));
        jPanel1.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 227, 430, 40));

        jLabel4.setText("Payment Method");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 285, 180, 24));
        jPanel1.add(cmbPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 312, 430, 40));

        jLabel5.setText("Payment Date");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 115, 180, 24));
        jPanel1.add(txtPaymentDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 142, 300, 40));

        jLabel6.setText("Status");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 200, 180, 24));
        jPanel1.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 227, 300, 40));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 312, 105, 40));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 312, 105, 40));

        btnDelete.setText("Delete");
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 312, 105, 40));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 312, 105, 40));

        jLabel7.setText("Search Payment");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 395, 150, 30));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 395, 330, 38));

        btnSearch.setText("Search");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 395, 110, 38));

        tblPayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Appointment", "Amount", "Method", "Payment Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPayments.setRowHeight(36);
        jScrollPane1.setViewportView(tblPayments);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 1160, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stylePrimaryButton(javax.swing.JButton button) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        button.setForeground(new java.awt.Color(35, 40, 50));
        button.setBackground(new java.awt.Color(245, 190, 55));
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleSecondaryButton(javax.swing.JButton button) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        button.setForeground(new java.awt.Color(35, 40, 50));
        button.setBackground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 228, 235)),
                javax.swing.BorderFactory.createEmptyBorder(7, 15, 7, 15)));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleDangerButton(javax.swing.JButton button) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        button.setForeground(java.awt.Color.WHITE);
        button.setBackground(new java.awt.Color(210, 70, 70));
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAppointment;
    private javax.swing.JComboBox<String> cmbPaymentMethod;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPayments;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtPaymentDate;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    // Button accessors are used by the controller.
    public javax.swing.JButton getBtnSave() {
        return btnSave;
    }
    public javax.swing.JButton getBtnUpdate() {
        return btnUpdate;
    }
    public javax.swing.JButton getBtnDelete() {
        return btnDelete;
    }
    public javax.swing.JButton getBtnClear() {
        return btnClear;
    }
    public javax.swing.JButton getBtnSearch() {
        return btnSearch;
    }

}
