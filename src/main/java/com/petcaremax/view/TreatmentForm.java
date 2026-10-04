/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.TreatmentFormController;

import com.petcaremax.controller.TreatmentController;
import com.petcaremax.controller.AppointmentController;
import com.petcaremax.util.PetCareTheme;

import com.petcaremax.model.Treatment;
import com.petcaremax.model.Appointment;

import java.time.LocalDate;
import javax.swing.JOptionPane;

import java.util.List;

/**
 *
 * @author Bimsara
 */
public class TreatmentForm extends javax.swing.JFrame {
    private final TreatmentFormController formController;

    
    private final TreatmentController treatmentController;
private final AppointmentController appointmentController;

private List<Appointment> appointments;

private int selectedTreatmentId = -1;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TreatmentForm.class.getName());

    /**
     * Creates new form TreatmentForm
     */
    public TreatmentForm() {
        initComponents();

    formController = new TreatmentFormController(this);
        
        treatmentController = new TreatmentController();
    appointmentController = new AppointmentController();
    
    loadAppointments();
    loadTreatments();
    PetCareTheme.apply(this);
    tblTreatments.addMouseListener(
        new java.awt.event.MouseAdapter() {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        selectTreatmentFromTable();
    }
});
    }
private void loadAppointments() {

    appointments = appointmentController.getAllAppointments();

    cmbAppointment.removeAllItems();

    for (Appointment appointment : appointments) {

        cmbAppointment.addItem(
                appointment.getAppointmentId()
                + " - "
                + appointment.getAppointmentDate()
                + " - Appointment"
        );
    }
}
private void loadTreatments() {

    List<Treatment> treatments =
            treatmentController.getAllTreatments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblTreatments.getModel();

    model.setRowCount(0);

    for (Treatment treatment : treatments) {

        String appointmentDisplay =
                appointments.stream()
                        .filter(a -> a.getAppointmentId()
                                == treatment.getAppointmentId())
                        .map(a -> a.getAppointmentId()
                                + " - "
                                + a.getAppointmentDate())
                        .findFirst()
                        .orElse("Unknown");

        model.addRow(new Object[]{
            treatment.getTreatmentId(),
            appointmentDisplay,
            treatment.getDiagnosis(),
            treatment.getTreatmentDescription(),
            treatment.getTreatmentDate(),
            treatment.getNotes()
        });
    }
}
public void saveTreatment() {

    try {

        if (cmbAppointment.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment."
            );
            return;
        }

        if (txtDiagnosis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the diagnosis."
            );
            return;
        }

        if (txtTreatmentDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment description."
            );
            return;
        }

        if (txtTreatmentDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment date."
            );
            return;
        }

        Appointment selectedAppointment =
                appointments.get(cmbAppointment.getSelectedIndex());

        LocalDate treatmentDate =
                LocalDate.parse(txtTreatmentDate.getText().trim());

        String diagnosis =
                txtDiagnosis.getText().trim();

        String treatmentDescription =
                txtTreatmentDescription.getText().trim();

        String notes =
                txtNotes.getText().trim();

        Treatment treatment = new Treatment(
                0,
                selectedAppointment.getAppointmentId(),
                diagnosis,
                treatmentDescription,
                treatmentDate,
                notes
        );

        boolean success =
                treatmentController.addTreatment(treatment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment saved successfully!"
            );

            loadTreatments();
            clearTreatmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save treatment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date format.\n\n"
                + "Please use: YYYY-MM-DD",
                "Invalid Date",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void clearTreatmentFields() {

    if (cmbAppointment.getItemCount() > 0) {
        cmbAppointment.setSelectedIndex(0);
    }

    txtDiagnosis.setText("");
    txtTreatmentDescription.setText("");
    txtTreatmentDate.setText("");
    txtNotes.setText("");

    selectedTreatmentId = -1;

    tblTreatments.clearSelection();
    
    loadTreatments();
txtSearch.setText("");
}
private void selectTreatmentFromTable() {

    int row = tblTreatments.getSelectedRow();

    if (row == -1) {
        return;
    }

    selectedTreatmentId =
            Integer.parseInt(
                    tblTreatments.getValueAt(row, 0).toString()
            );

    String appointmentDisplay =
            tblTreatments.getValueAt(row, 1).toString();

    txtDiagnosis.setText(
            tblTreatments.getValueAt(row, 2).toString()
    );

    txtTreatmentDescription.setText(
            tblTreatments.getValueAt(row, 3).toString()
    );

    txtTreatmentDate.setText(
            tblTreatments.getValueAt(row, 4).toString()
    );

    Object notes =
            tblTreatments.getValueAt(row, 5);

    txtNotes.setText(
            notes == null ? "" : notes.toString()
    );

    // Find the appointment using its displayed ID
    try {

        int appointmentId =
                Integer.parseInt(
                        appointmentDisplay.split(" - ")[0]
                );

        for (int i = 0; i < appointments.size(); i++) {

            if (appointments.get(i).getAppointmentId()
                    == appointmentId) {

                cmbAppointment.setSelectedIndex(i);
                break;
            }
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load appointment details.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void updateTreatment() {

    if (selectedTreatmentId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a treatment from the table first."
        );
        return;
    }

    try {

        if (cmbAppointment.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment."
            );
            return;
        }

        if (txtDiagnosis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the diagnosis."
            );
            return;
        }

        if (txtTreatmentDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment description."
            );
            return;
        }

        Appointment selectedAppointment =
                appointments.get(cmbAppointment.getSelectedIndex());

        LocalDate treatmentDate =
                LocalDate.parse(txtTreatmentDate.getText().trim());

        Treatment treatment = new Treatment(
                selectedTreatmentId,
                selectedAppointment.getAppointmentId(),
                txtDiagnosis.getText().trim(),
                txtTreatmentDescription.getText().trim(),
                treatmentDate,
                txtNotes.getText().trim()
        );

        boolean success =
                treatmentController.updateTreatment(treatment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment updated successfully!"
            );

            loadTreatments();
            clearTreatmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update treatment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date format.\n\nPlease use: YYYY-MM-DD",
                "Invalid Date",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void deleteTreatment() {

    if (selectedTreatmentId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a treatment from the table first."
        );
        return;
    }

    int confirmation = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this treatment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirmation != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        boolean success =
                treatmentController.deleteTreatment(
                        selectedTreatmentId
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment deleted successfully!"
            );

            loadTreatments();
            clearTreatmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete treatment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
public void searchTreatments() {

    String keyword = txtSearch.getText().trim().toLowerCase();

    List<Treatment> treatments =
            treatmentController.getAllTreatments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblTreatments.getModel();

    model.setRowCount(0);

    treatments.stream()
            .filter(treatment -> {

                String appointmentId =
                        String.valueOf(treatment.getAppointmentId());

                String diagnosis =
                        treatment.getDiagnosis() == null
                        ? ""
                        : treatment.getDiagnosis().toLowerCase();

                String description =
                        treatment.getTreatmentDescription() == null
                        ? ""
                        : treatment.getTreatmentDescription().toLowerCase();

                return String.valueOf(treatment.getTreatmentId())
                        .contains(keyword)
                        || appointmentId.contains(keyword)
                        || diagnosis.contains(keyword)
                        || description.contains(keyword);
            })
            .forEach(treatment -> {

                String appointmentDisplay =
                        appointments.stream()
                                .filter(a -> a.getAppointmentId()
                                        == treatment.getAppointmentId())
                                .map(a -> a.getAppointmentId()
                                        + " - "
                                        + a.getAppointmentDate())
                                .findFirst()
                                .orElse("Unknown");

                model.addRow(new Object[]{
                    treatment.getTreatmentId(),
                    appointmentDisplay,
                    treatment.getDiagnosis(),
                    treatment.getTreatmentDescription(),
                    treatment.getTreatmentDate(),
                    treatment.getNotes()
                });
            });
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
        txtDiagnosis = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTreatmentDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtTreatmentDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTreatments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PetCareMAX - Treatment Management");
        setPreferredSize(new java.awt.Dimension(1280, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // PAGE HEADER
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
        jLabel1.setText("Treatment Management");
        jPanel1.add(jLabel1,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 28, 500, 40));

        // SECTION TITLES
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel2.setText("Treatment Information");
        jPanel1.add(jLabel2,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 125, 350, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel3.setText("Treatment List");
        jPanel1.add(jLabel3,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 125, 300, 30));

        // APPOINTMENT
        jLabel8.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel8.setText("Treatment Information");
        jPanel1.add(jLabel8,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 125, 350, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jLabel2.setText("Appointment");
        jPanel1.add(jLabel2,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 180, 140, 20));

        cmbAppointment.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jPanel1.add(cmbAppointment,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 202, 382, 40));

        // Diagnosis
        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jLabel3.setText("Diagnosis");
        jPanel1.add(jLabel3,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 250, 140, 20));

        txtDiagnosis.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jPanel1.add(txtDiagnosis,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 272, 382, 40));

        // Treatment Description
        jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jLabel4.setText("Treatment Description");
        jPanel1.add(jLabel4,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 320, 200, 20));

        txtTreatmentDescription.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        txtTreatmentDescription.setColumns(20);
        txtTreatmentDescription.setRows(5);
        jScrollPane1.setViewportView(txtTreatmentDescription);
        jPanel1.add(jScrollPane1,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 342, 382, 70));

        // Treatment Date
        jLabel5.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jLabel5.setText("Treatment Date");
        jPanel1.add(jLabel5,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 425, 140, 20));

        txtTreatmentDate.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jPanel1.add(txtTreatmentDate,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 447, 382, 40));

        // Notes
        jLabel6.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jLabel6.setText("Notes");
        jPanel1.add(jLabel6,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 500, 140, 20));

        txtNotes.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jPanel1.add(txtNotes,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 522, 382, 40));

        // ACTION BUTTONS
        btnSave.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnSave.setText("Save");
        jPanel1.add(btnSave,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 580, 86, 36));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 580, 86, 36));

        btnDelete.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnDelete.setText("Delete");
        jPanel1.add(btnDelete,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 580, 86, 36));

        btnClear.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnClear.setText("Clear");
        jPanel1.add(btnClear,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 580, 94, 36));

        // SEARCH
        jLabel7.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        jLabel7.setText("Search Treatment");
        jPanel1.add(jLabel7,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 140, 20));

        txtSearch.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jPanel1.add(txtSearch,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 174, 420, 40));

        btnSearch.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnSearch.setText("Search");
        jPanel1.add(btnSearch,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 177, 86, 34));

        // TABLE
        tblTreatments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Appointment", "Diagnosis", "Description", "Date", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblTreatments.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        tblTreatments.setRowHeight(36);
        tblTreatments.setAutoCreateRowSorter(true);
        tblTreatments.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTreatments);

        jPanel1.add(jScrollPane2,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 656, 450));

        getContentPane().add(jPanel1,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        pack();
        setSize(1280, 760);
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTreatments;
    private javax.swing.JTextField txtDiagnosis;
    private javax.swing.JTextField txtNotes;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTreatmentDate;
    private javax.swing.JTextArea txtTreatmentDescription;
    // End of variables declaration//GEN-END:variables

    // Button accessors are used by the controller.
    public javax.swing.JButton getBtnSave() {
        return btnSave;
    }
    public javax.swing.JButton getBtnClear() {
        return btnClear;
    }
    public javax.swing.JButton getBtnUpdate() {
        return btnUpdate;
    }
    public javax.swing.JButton getBtnDelete() {
        return btnDelete;
    }
    public javax.swing.JButton getBtnSearch() {
        return btnSearch;
    }

}
