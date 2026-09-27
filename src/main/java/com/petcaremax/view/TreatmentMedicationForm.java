/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.TreatmentMedicationController;
import com.petcaremax.controller.TreatmentController;
import com.petcaremax.controller.MedicationController;

import com.petcaremax.model.TreatmentMedication;
import com.petcaremax.model.Treatment;
import com.petcaremax.model.Medication;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bimsara
 */
public class TreatmentMedicationForm extends javax.swing.JFrame {
    private final TreatmentMedicationController treatmentMedicationController;
private final TreatmentController treatmentController;
private final MedicationController medicationController;

private List<Treatment> treatments;
private List<Medication> medications;
private List<TreatmentMedication> treatmentMedications;

private int selectedTreatmentId = -1;
private int selectedMedicationId = -1;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TreatmentMedicationForm.class.getName());

    /**
     * Creates new form TreatmentMedicationForm
     */
   public TreatmentMedicationForm() {
    initComponents();

    treatmentMedicationController =
            new TreatmentMedicationController();

    treatmentController =
            new TreatmentController();

    medicationController =
            new MedicationController();
    
    btnSave.addActionListener(this::btnSaveActionPerformed);
    btnUpdate.addActionListener(this::btnUpdateActionPerformed);
    btnDelete.addActionListener(this::btnDeleteActionPerformed);
    btnClear.addActionListener(this::btnClearActionPerformed);
    btnSearch.addActionListener(this::btnSearchActionPerformed);

    loadTreatments();
    loadMedications();
    loadTreatmentMedications();

    

    tblTreatmentMedications.addMouseListener(
            new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblTreatmentMedicationsMouseClicked(evt);
        }
    });

    setLocationRelativeTo(null);
}
   private void saveTreatmentMedication() {

    try {

        int treatmentIndex = cmbTreatment.getSelectedIndex();
        int medicationIndex = cmbMedication.getSelectedIndex();

        if (treatmentIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a treatment.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (medicationIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a medication.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String dosage = txtDosage.getText().trim();
        String frequency = txtFrequency.getText().trim();
        String duration = txtDuration.getText().trim();
        String instructions = txtInstructions.getText().trim();

        if (dosage.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter dosage.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtDosage.requestFocus();
            return;
        }

        if (frequency.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter frequency.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtFrequency.requestFocus();
            return;
        }

        if (duration.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter duration.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            txtDuration.requestFocus();
            return;
        }

        Treatment selectedTreatment =
                treatments.get(treatmentIndex);

        Medication selectedMedication =
                medications.get(medicationIndex);

        TreatmentMedication treatmentMedication =
                new TreatmentMedication(
                        selectedTreatment.getTreatmentId(),
                        selectedMedication.getMedicationId(),
                        dosage,
                        frequency,
                        duration,
                        instructions
                );

        boolean success =
                treatmentMedicationController
                        .addTreatmentMedication(
                                treatmentMedication
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTreatmentMedications();
            clearTreatmentMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save prescription.\n"
                    + "This treatment may already have this medication.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

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
   private void clearTreatmentMedicationFields() {

    if (cmbTreatment.getItemCount() > 0) {
        cmbTreatment.setSelectedIndex(0);
    }

    if (cmbMedication.getItemCount() > 0) {
        cmbMedication.setSelectedIndex(0);
    }

    txtDosage.setText("");
    txtFrequency.setText("");
    txtDuration.setText("");
    txtInstructions.setText("");
    txtSearch.setText("");

    selectedTreatmentId = -1;
    selectedMedicationId = -1;

    tblTreatmentMedications.clearSelection();

    loadTreatmentMedications();
}
   private void tblTreatmentMedicationsMouseClicked(
        java.awt.event.MouseEvent evt) {

    int selectedRow =
            tblTreatmentMedications.getSelectedRow();

    if (selectedRow == -1) {
        return;
    }

    try {

        String treatmentValue =
                tblTreatmentMedications
                        .getValueAt(selectedRow, 0)
                        .toString();

        String medicationValue =
                tblTreatmentMedications
                        .getValueAt(selectedRow, 1)
                        .toString();

        selectedTreatmentId =
                Integer.parseInt(
                        treatmentValue.split(" - ")[0]
                );

        selectedMedicationId =
                Integer.parseInt(
                        medicationValue.split(" - ")[0]
                );

        for (int i = 0; i < treatments.size(); i++) {

            if (treatments.get(i).getTreatmentId()
                    == selectedTreatmentId) {

                cmbTreatment.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < medications.size(); i++) {

            if (medications.get(i).getMedicationId()
                    == selectedMedicationId) {

                cmbMedication.setSelectedIndex(i);
                break;
            }
        }

        txtDosage.setText(
                tblTreatmentMedications
                        .getValueAt(selectedRow, 2)
                        .toString()
        );

        txtFrequency.setText(
                tblTreatmentMedications
                        .getValueAt(selectedRow, 3)
                        .toString()
        );

        txtDuration.setText(
                tblTreatmentMedications
                        .getValueAt(selectedRow, 4)
                        .toString()
        );

        Object instructions =
                tblTreatmentMedications
                        .getValueAt(selectedRow, 5);

        txtInstructions.setText(
                instructions == null
                        ? ""
                        : instructions.toString()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load selected prescription.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   private void updateTreatmentMedication() {

    if (selectedTreatmentId == -1
            || selectedMedicationId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a prescription from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    try {

        String dosage = txtDosage.getText().trim();
        String frequency = txtFrequency.getText().trim();
        String duration = txtDuration.getText().trim();
        String instructions = txtInstructions.getText().trim();

        if (dosage.isEmpty()
                || frequency.isEmpty()
                || duration.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Dosage, Frequency and Duration are required.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        TreatmentMedication treatmentMedication =
                new TreatmentMedication(
                        selectedTreatmentId,
                        selectedMedicationId,
                        dosage,
                        frequency,
                        duration,
                        instructions
                );

        boolean success =
                treatmentMedicationController
                        .updateTreatmentMedication(
                                treatmentMedication
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTreatmentMedications();
            clearTreatmentMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update prescription.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

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
   private void deleteTreatmentMedication() {

    if (selectedTreatmentId == -1
            || selectedMedicationId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a prescription from the table first.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this prescription?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        boolean success =
                treatmentMedicationController
                        .deleteTreatmentMedication(
                                selectedTreatmentId,
                                selectedMedicationId
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTreatmentMedications();
            clearTreatmentMedicationFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete prescription.",
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to delete prescription:\n"
                + e.getMessage(),
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   private void searchTreatmentMedications() {

    String keyword =
            txtSearch.getText().trim().toLowerCase();

    if (keyword.isEmpty()) {

        loadTreatmentMedications();
        return;
    }

    List<TreatmentMedication> filtered =
            treatmentMedications.stream()
                    .filter(tm -> {

                        String treatmentText =
                                treatments.stream()
                                        .filter(t ->
                                                t.getTreatmentId()
                                                        == tm.getTreatmentId())
                                        .map(t ->
                                                t.getDiagnosis())
                                        .findFirst()
                                        .orElse("");

                        String medicationText =
                                medications.stream()
                                        .filter(m ->
                                                m.getMedicationId()
                                                        == tm.getMedicationId())
                                        .map(m ->
                                                m.getMedicationName())
                                        .findFirst()
                                        .orElse("");

                        return String.valueOf(
                                    tm.getTreatmentId())
                                .contains(keyword)

                                || treatmentText
                                        .toLowerCase()
                                        .contains(keyword)

                                || medicationText
                                        .toLowerCase()
                                        .contains(keyword)

                                || tm.getDosage()
                                        .toLowerCase()
                                        .contains(keyword)

                                || tm.getFrequency()
                                        .toLowerCase()
                                        .contains(keyword)

                                || tm.getDuration()
                                        .toLowerCase()
                                        .contains(keyword)

                                || (tm.getInstructions() != null
                                    && tm.getInstructions()
                                            .toLowerCase()
                                            .contains(keyword));
                    })
                    .toList();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblTreatmentMedications.getModel();

    model.setRowCount(0);

    for (TreatmentMedication tm : filtered) {

        String treatmentName =
                treatments.stream()
                        .filter(t ->
                                t.getTreatmentId()
                                        == tm.getTreatmentId())
                        .map(t ->
                                t.getTreatmentId()
                                + " - "
                                + t.getDiagnosis())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        tm.getTreatmentId())
                        );

        String medicationName =
                medications.stream()
                        .filter(m ->
                                m.getMedicationId()
                                        == tm.getMedicationId())
                        .map(m ->
                                m.getMedicationId()
                                + " - "
                                + m.getMedicationName())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        tm.getMedicationId())
                        );

        model.addRow(new Object[]{
            treatmentName,
            medicationName,
            tm.getDosage(),
            tm.getFrequency(),
            tm.getDuration(),
            tm.getInstructions()
        });
    }
}
   private void btnSaveActionPerformed(
        java.awt.event.ActionEvent evt) {

    saveTreatmentMedication();
}

private void btnUpdateActionPerformed(
        java.awt.event.ActionEvent evt) {

    updateTreatmentMedication();
}

private void btnDeleteActionPerformed(
        java.awt.event.ActionEvent evt) {

    deleteTreatmentMedication();
}

private void btnClearActionPerformed(
        java.awt.event.ActionEvent evt) {

    clearTreatmentMedicationFields();
}

private void btnSearchActionPerformed(
        java.awt.event.ActionEvent evt) {

    searchTreatmentMedications();
}
   private void loadTreatments() {

    treatments = treatmentController.getAllTreatments();

    cmbTreatment.removeAllItems();

    for (Treatment treatment : treatments) {

        String displayText =
                treatment.getTreatmentId()
                + " - "
                + treatment.getDiagnosis()
                + " - "
                + treatment.getTreatmentDate();

        cmbTreatment.addItem(displayText);
    }
}
   private void loadMedications() {

    medications = medicationController.getAllMedications();

    cmbMedication.removeAllItems();

    for (Medication medication : medications) {

        String displayText =
                medication.getMedicationId()
                + " - "
                + medication.getMedicationName();

        cmbMedication.addItem(displayText);
    }
}
   private void loadTreatmentMedications() {

    treatmentMedications =
            treatmentMedicationController
                    .getAllTreatmentMedications();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
                    tblTreatmentMedications.getModel();

    model.setRowCount(0);

    for (TreatmentMedication tm : treatmentMedications) {

        String treatmentName =
                treatments.stream()
                        .filter(t ->
                                t.getTreatmentId()
                                == tm.getTreatmentId())
                        .map(t ->
                                t.getTreatmentId()
                                + " - "
                                + t.getDiagnosis())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        tm.getTreatmentId()
                                )
                        );

        String medicationName =
                medications.stream()
                        .filter(m ->
                                m.getMedicationId()
                                == tm.getMedicationId())
                        .map(m ->
                                m.getMedicationId()
                                + " - "
                                + m.getMedicationName())
                        .findFirst()
                        .orElse(
                                String.valueOf(
                                        tm.getMedicationId()
                                )
                        );

        model.addRow(new Object[]{
            treatmentName,
            medicationName,
            tm.getDosage(),
            tm.getFrequency(),
            tm.getDuration(),
            tm.getInstructions()
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
        cmbTreatment = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbMedication = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtDosage = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFrequency = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDuration = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInstructions = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTreatmentMedications = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("PRESCRIPTION / TREATMENT MEDICATION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 6, 240, -1));

        jLabel2.setText("Treatment");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        cmbTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbTreatment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, -1));

        jLabel3.setText("Medication");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        cmbMedication.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbMedication, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 230, -1));

        jLabel4.setText("Dosage");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel1.add(txtDosage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 230, -1));

        jLabel5.setText("Frequency");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        jPanel1.add(txtFrequency, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 230, -1));

        jLabel6.setText("Duration");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));
        jPanel1.add(txtDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 230, -1));

        jLabel7.setText("Instructions");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        txtInstructions.setColumns(20);
        txtInstructions.setRows(5);
        jScrollPane1.setViewportView(txtInstructions);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, -1));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, -1, -1));

        btnDelete.setText("Delete");
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, -1, -1));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, -1, -1));

        jLabel8.setText("Search");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, -1));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 140, -1));

        btnSearch.setText("Search ");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 600, -1, -1));

        tblTreatmentMedications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Treatment", "Medication", "Dosage", "Frequency", "Duration", "Instructions"
            }
        ));
        jScrollPane2.setViewportView(tblTreatmentMedications);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, -1, 160));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 840));

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
        java.awt.EventQueue.invokeLater(() -> new TreatmentMedicationForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbMedication;
    private javax.swing.JComboBox<String> cmbTreatment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTreatmentMedications;
    private javax.swing.JTextField txtDosage;
    private javax.swing.JTextField txtDuration;
    private javax.swing.JTextField txtFrequency;
    private javax.swing.JTextArea txtInstructions;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
