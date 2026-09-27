/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.MedicationController;
import com.petcaremax.model.Medication;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bimsara
 */
public class MedicationForm extends javax.swing.JFrame {
    
    private final MedicationController medicationController;
private List<Medication> medications;
private int selectedMedicationId = -1;

    private static final java.util.logging.Logger logger = 
            java.util.logging.Logger.getLogger(MedicationForm.class.getName());

    /**
     * Creates new form MedicationForm
     */
    public MedicationForm() {
    initComponents();

    medicationController = new MedicationController();

    cmbStatus.removeAllItems();
    cmbStatus.addItem("Active");
    cmbStatus.addItem("Inactive");

    btnSave.addActionListener(this::btnSaveActionPerformed);
    btnUpdate.addActionListener(this::btnUpdateActionPerformed);
    btnDelete.addActionListener(this::btnDeleteActionPerformed);
    btnClear.addActionListener(this::btnClearActionPerformed);
    btnSearch.addActionListener(this::btnSearchActionPerformed);

    tblMedications.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblMedicationsMouseClicked(evt);
        }
    });

    loadMedications();

    setLocationRelativeTo(null);
}
    private void loadMedications() {

    medications = medicationController.getAllMedications();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblMedications.getModel();

    model.setRowCount(0);

    for (Medication medication : medications) {

        model.addRow(new Object[]{
            medication.getMedicationId(),
            medication.getMedicationName(),
            medication.getDescription(),
            String.format("%.2f", medication.getUnitPrice()),
            medication.getStockQuantity(),
            medication.getStatus()
        });
    }
}
    private void clearMedicationFields() {

    txtMedicationName.setText("");
    txtDescription.setText("");
    txtUnitPrice.setText("");
    txtStockQuantity.setText("");

    if (cmbStatus.getItemCount() > 0) {
        cmbStatus.setSelectedIndex(0);
    }

    txtSearch.setText("");

    selectedMedicationId = -1;

    tblMedications.clearSelection();

    loadMedications();

    txtMedicationName.requestFocus();
}
    private void saveMedication() {

    try {

        String name = txtMedicationName.getText().trim();
        String description = txtDescription.getText().trim();
        String priceText = txtUnitPrice.getText().trim();
        String stockText = txtStockQuantity.getText().trim();
        String status = cmbStatus.getSelectedItem().toString();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter medication name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtMedicationName.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter unit price.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtUnitPrice.requestFocus();
            return;
        }

        if (stockText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter stock quantity.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtStockQuantity.requestFocus();
            return;
        }

        double price = Double.parseDouble(priceText);
        int stock = Integer.parseInt(stockText);

        Medication medication = new Medication(
                0,
                name,
                description,
                price,
                stock,
                status
        );

        boolean success = medicationController.addMedication(medication);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medication saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadMedications();
            clearMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save medication.\n"
                    + "The medication name may already exist.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Unit Price must be a valid number and Stock Quantity must be a whole number.",
                "Invalid Input",
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
                "An unexpected error occurred:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void deleteMedication() {

    if (selectedMedicationId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a medication from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this medication?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        boolean success =
                medicationController.deleteMedication(selectedMedicationId);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medication deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadMedications();
            clearMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete medication.",
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to delete medication.\n"
                + e.getMessage(),
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    private void searchMedications() {

    String keyword = txtSearch.getText().trim().toLowerCase();

    if (keyword.isEmpty()) {
        loadMedications();
        return;
    }

    List<Medication> filteredMedications = medications.stream()
            .filter(medication ->
                    String.valueOf(medication.getMedicationId())
                            .contains(keyword)
                    || medication.getMedicationName()
                            .toLowerCase()
                            .contains(keyword)
                    || (medication.getDescription() != null
                            && medication.getDescription()
                                    .toLowerCase()
                                    .contains(keyword))
                    || String.valueOf(medication.getUnitPrice())
                            .contains(keyword)
                    || String.valueOf(medication.getStockQuantity())
                            .contains(keyword)
                    || medication.getStatus()
                            .toLowerCase()
                            .contains(keyword)
            )
            .toList();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblMedications.getModel();

    model.setRowCount(0);

    for (Medication medication : filteredMedications) {

        model.addRow(new Object[]{
            medication.getMedicationId(),
            medication.getMedicationName(),
            medication.getDescription(),
            String.format("%.2f", medication.getUnitPrice()),
            medication.getStockQuantity(),
            medication.getStatus()
        });
    }
}
    private void tblMedicationsMouseClicked(java.awt.event.MouseEvent evt) {

    int selectedRow = tblMedications.getSelectedRow();

    if (selectedRow == -1) {
        return;
    }

    try {

        selectedMedicationId =
                Integer.parseInt(
                        tblMedications.getValueAt(selectedRow, 0)
                                .toString()
                );

        txtMedicationName.setText(
                tblMedications.getValueAt(selectedRow, 1)
                        .toString()
        );

        Object description =
                tblMedications.getValueAt(selectedRow, 2);

        txtDescription.setText(
                description == null ? "" : description.toString()
        );

        txtUnitPrice.setText(
                tblMedications.getValueAt(selectedRow, 3)
                        .toString()
        );

        txtStockQuantity.setText(
                tblMedications.getValueAt(selectedRow, 4)
                        .toString()
        );

        cmbStatus.setSelectedItem(
                tblMedications.getValueAt(selectedRow, 5)
                        .toString()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load selected medication.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    private void updateMedication() {

    if (selectedMedicationId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a medication from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    try {

        String name = txtMedicationName.getText().trim();
        String description = txtDescription.getText().trim();
        String priceText = txtUnitPrice.getText().trim();
        String stockText = txtStockQuantity.getText().trim();
        String status = cmbStatus.getSelectedItem().toString();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter medication name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter unit price.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (stockText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter stock quantity.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double price = Double.parseDouble(priceText);
        int stock = Integer.parseInt(stockText);

        Medication medication = new Medication(
                selectedMedicationId,
                name,
                description,
                price,
                stock,
                status
        );

        boolean success =
                medicationController.updateMedication(medication);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medication updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadMedications();
            clearMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update medication.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Unit Price must be a valid number and Stock Quantity must be a whole number.",
                "Invalid Input",
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
                "An unexpected error occurred:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
    saveMedication();
}

private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
    updateMedication();
}

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
    deleteMedication();
}

private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
    clearMedicationFields();
}

private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
    searchMedications();
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
        txtMedicationName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStockQuantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedications = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("MEDICATION MANAGEMENT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 6, 160, -1));

        jLabel2.setText("Medication Name ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        jPanel1.add(txtMedicationName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 170, -1));

        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, -1));

        jLabel4.setText("Unit Price ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        jPanel1.add(txtUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 170, -1));

        jLabel5.setText("Stock Quantity ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));
        jPanel1.add(txtStockQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 170, -1));

        jLabel6.setText("Status ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        jPanel1.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 170, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        btnDelete.setText("Delete");
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, -1));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, -1, -1));

        tblMedications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name ", " Description ", "Price ", "Stock", "Status│"
            }
        ));
        jScrollPane1.setViewportView(tblMedications);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 180));

        jLabel7.setText(" Search Medication");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, -1));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 170, -1));

        btnSearch.setText("Search");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MedicationForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
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
    private javax.swing.JTable tblMedications;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtMedicationName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStockQuantity;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
