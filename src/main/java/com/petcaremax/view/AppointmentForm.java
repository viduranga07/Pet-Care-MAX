/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;


import com.petcaremax.controller.AppointmentController;
import com.petcaremax.controller.PetController;
import com.petcaremax.controller.VeterinarianController;
import com.petcaremax.controller.ServiceController;

import com.petcaremax.model.Pet;
import com.petcaremax.model.Veterinarian;
import com.petcaremax.model.Service;
import com.petcaremax.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

import java.util.List;

/**
 *
 * @author Bimsara
 */
public class AppointmentForm extends javax.swing.JFrame {

    private final AppointmentController appointmentController;
    private final PetController petController;
    private final VeterinarianController veterinarianController;
    private final ServiceController serviceController;

    private List<Pet> pets;
    private List<Veterinarian> veterinarians;
    private List<Service> services;

    private int selectedAppointmentId = -1;
    
    private static final java.util.logging.Logger logger = 
            java.util.logging.Logger.getLogger(AppointmentForm.class.getName());

    /**
     * Creates new form AppointmentForm
     */
public AppointmentForm() {
    initComponents();

    appointmentController = new AppointmentController();
    petController = new PetController();
    veterinarianController = new VeterinarianController();
    serviceController = new ServiceController();

    loadPets();
    loadVeterinarians();
    loadServices();
    loadAppointments();
    
    btnSave.addActionListener(e -> saveAppointment());
    btnClear.addActionListener(e -> clearAppointmentFields());
    btnUpdate.addActionListener(e -> updateAppointment());
    btnDelete.addActionListener(e -> deleteAppointment());
    btnSearch.addActionListener(e -> searchAppointments());
    
    tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        selectAppointmentFromTable();
    }
});
}
private void loadAppointments() {

    List<Appointment> appointments =
            appointmentController.getAllAppointments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblAppointments.getModel();

    model.setRowCount(0);

    for (Appointment appointment : appointments) {

        String petName = pets.stream()
                .filter(p -> p.getPetId() == appointment.getPetId())
                .map(Pet::getPetName)
                .findFirst()
                .orElse("Unknown");

        String veterinarianName = veterinarians.stream()
                .filter(v -> v.getVeterinarianId()
                        == appointment.getVeterinarianId())
                .map(Veterinarian::getFullName)
                .findFirst()
                .orElse("Unknown");

        String serviceName = services.stream()
                .filter(s -> s.getServiceId()
                        == appointment.getServiceId())
                .map(Service::getServiceName)
                .findFirst()
                .orElse("Unknown");

        model.addRow(new Object[]{
            appointment.getAppointmentId(),
            petName,
            veterinarianName,
            serviceName,
            appointment.getAppointmentDate(),
            appointment.getAppointmentTime(),
            appointment.getReason(),
            appointment.getStatus()
        });
    }
}
private void saveAppointment() {

    try {
        if (cmbPet.getSelectedIndex() == -1
                || cmbVeterinarian.getSelectedIndex() == -1
                || cmbService.getSelectedIndex() == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select Pet, Veterinarian and Service."
            );
            return;
        }

        if (txtDate.getText().trim().isEmpty()
                || txtTime.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Date and Time."
            );
            return;
        }

        Pet selectedPet = pets.get(cmbPet.getSelectedIndex());
        Veterinarian selectedVeterinarian =
                veterinarians.get(cmbVeterinarian.getSelectedIndex());
        Service selectedService =
                services.get(cmbService.getSelectedIndex());

        LocalDate appointmentDate =
                LocalDate.parse(txtDate.getText().trim());

        LocalTime appointmentTime =
                LocalTime.parse(txtTime.getText().trim());

        String reason = txtReason.getText().trim();
        String status = cmbStatus.getSelectedItem().toString();

        Appointment appointment = new Appointment(
                0,
                selectedPet.getPetId(),
                selectedVeterinarian.getVeterinarianId(),
                selectedService.getServiceId(),
                appointmentDate,
                appointmentTime,
                reason,
                status
        );

        boolean success =
                appointmentController.addAppointment(appointment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment saved successfully!"
            );

            loadAppointments();
            clearAppointmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save appointment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date or time format.\n\n"
                + "Date: YYYY-MM-DD\n"
                + "Time: HH:MM:SS",
                "Invalid Input",
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
private void clearAppointmentFields() {

    if (cmbPet.getItemCount() > 0) {
        cmbPet.setSelectedIndex(0);
    }

    if (cmbVeterinarian.getItemCount() > 0) {
        cmbVeterinarian.setSelectedIndex(0);
    }

    if (cmbService.getItemCount() > 0) {
        cmbService.setSelectedIndex(0);
    }

    txtDate.setText("");
    txtTime.setText("");
    txtReason.setText("");

    cmbStatus.setSelectedItem("Scheduled");

    selectedAppointmentId = -1;

    tblAppointments.clearSelection();
    
    loadAppointments();
txtSearch.setText("");
}
private void selectAppointmentFromTable() {

    int row = tblAppointments.getSelectedRow();

    if (row == -1) {
        return;
    }

    selectedAppointmentId =
            Integer.parseInt(tblAppointments.getValueAt(row, 0).toString());

    String petName =
            tblAppointments.getValueAt(row, 1).toString();

    String veterinarianName =
            tblAppointments.getValueAt(row, 2).toString();

    String serviceName =
            tblAppointments.getValueAt(row, 3).toString();

    txtDate.setText(
            tblAppointments.getValueAt(row, 4).toString()
    );

    txtTime.setText(
            tblAppointments.getValueAt(row, 5).toString()
    );

    txtReason.setText(
            tblAppointments.getValueAt(row, 6).toString()
    );

    cmbStatus.setSelectedItem(
            tblAppointments.getValueAt(row, 7).toString()
    );

    // Select matching Pet
    for (int i = 0; i < pets.size(); i++) {
        if (pets.get(i).getPetName().equals(petName)) {
            cmbPet.setSelectedIndex(i);
            break;
        }
    }

    // Select matching Veterinarian
    for (int i = 0; i < veterinarians.size(); i++) {
        if (veterinarians.get(i).getFullName().equals(veterinarianName)) {
            cmbVeterinarian.setSelectedIndex(i);
            break;
        }
    }

    // Select matching Service
    for (int i = 0; i < services.size(); i++) {
        if (services.get(i).getServiceName().equals(serviceName)) {
            cmbService.setSelectedIndex(i);
            break;
        }
    }
}
private void updateAppointment() {

    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select an appointment from the table first."
        );
        return;
    }

    try {
        Pet selectedPet = pets.get(cmbPet.getSelectedIndex());

        Veterinarian selectedVeterinarian =
                veterinarians.get(cmbVeterinarian.getSelectedIndex());

        Service selectedService =
                services.get(cmbService.getSelectedIndex());

        LocalDate appointmentDate =
                LocalDate.parse(txtDate.getText().trim());

        LocalTime appointmentTime =
                LocalTime.parse(txtTime.getText().trim());

        String reason = txtReason.getText().trim();

        String status =
                cmbStatus.getSelectedItem().toString();

        Appointment appointment = new Appointment(
                selectedAppointmentId,
                selectedPet.getPetId(),
                selectedVeterinarian.getVeterinarianId(),
                selectedService.getServiceId(),
                appointmentDate,
                appointmentTime,
                reason,
                status
        );

        boolean success =
                appointmentController.updateAppointment(appointment);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment updated successfully!"
            );

            loadAppointments();
            clearAppointmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update appointment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date or time format.\n\n"
                + "Date: YYYY-MM-DD\n"
                + "Time: HH:MM:SS",
                "Invalid Input",
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
private void deleteAppointment() {

    if (selectedAppointmentId == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Please select an appointment from the table first."
        );
        return;
    }

    int confirmation = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this appointment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirmation != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        boolean success =
                appointmentController.deleteAppointment(
                        selectedAppointmentId
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment deleted successfully!"
            );

            loadAppointments();
            clearAppointmentFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete appointment.",
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
private void searchAppointments() {

    String keyword = txtSearch.getText().trim().toLowerCase();

    List<Appointment> appointments =
            appointmentController.getAllAppointments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) tblAppointments.getModel();

    model.setRowCount(0);

    appointments.stream()
            .filter(appointment -> {

                String petName = pets.stream()
                        .filter(p -> p.getPetId() == appointment.getPetId())
                        .map(Pet::getPetName)
                        .findFirst()
                        .orElse("");

                String veterinarianName = veterinarians.stream()
                        .filter(v -> v.getVeterinarianId()
                                == appointment.getVeterinarianId())
                        .map(Veterinarian::getFullName)
                        .findFirst()
                        .orElse("");

                String serviceName = services.stream()
                        .filter(s -> s.getServiceId()
                                == appointment.getServiceId())
                        .map(Service::getServiceName)
                        .findFirst()
                        .orElse("");

                return String.valueOf(appointment.getAppointmentId())
                        .contains(keyword)
                        || petName.toLowerCase().contains(keyword)
                        || veterinarianName.toLowerCase().contains(keyword)
                        || serviceName.toLowerCase().contains(keyword)
                        || appointment.getStatus()
                                .toLowerCase()
                                .contains(keyword);
            })
            .forEach(appointment -> {

                String petName = pets.stream()
                        .filter(p -> p.getPetId() == appointment.getPetId())
                        .map(Pet::getPetName)
                        .findFirst()
                        .orElse("Unknown");

                String veterinarianName = veterinarians.stream()
                        .filter(v -> v.getVeterinarianId()
                                == appointment.getVeterinarianId())
                        .map(Veterinarian::getFullName)
                        .findFirst()
                        .orElse("Unknown");

                String serviceName = services.stream()
                        .filter(s -> s.getServiceId()
                                == appointment.getServiceId())
                        .map(Service::getServiceName)
                        .findFirst()
                        .orElse("Unknown");

                model.addRow(new Object[]{
                    appointment.getAppointmentId(),
                    petName,
                    veterinarianName,
                    serviceName,
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getReason(),
                    appointment.getStatus()
                });
            });
}
private void loadPets() {
    pets = petController.getAllPets();

    cmbPet.removeAllItems();

    for (Pet pet : pets) {
        cmbPet.addItem(
            pet.getPetId() + " - " + pet.getPetName()
        );
    }
}

private void loadVeterinarians() {
    veterinarians = veterinarianController.getAllVeterinarians();

    cmbVeterinarian.removeAllItems();

    for (Veterinarian veterinarian : veterinarians) {
        cmbVeterinarian.addItem(
            veterinarian.getVeterinarianId()
            + " - "
            + veterinarian.getFullName()
        );
    }
}

private void loadServices() {
    services = serviceController.getAllServices();

    cmbService.removeAllItems();

    for (Service service : services) {
        cmbService.addItem(
            service.getServiceId()
            + " - "
            + service.getServiceName()
        );
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
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        cmbPet = new javax.swing.JComboBox<>();
        cmbVeterinarian = new javax.swing.JComboBox<>();
        cmbService = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReason = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("APPOINTMENT MANAGEMENT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 170, -1));

        jLabel2.setText("Pet ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setText("Veterinarian ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 150, -1));

        cmbPet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, -1));

        cmbVeterinarian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbVeterinarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));

        cmbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 150, -1));

        jLabel4.setText("Service  ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setText("Date");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel6.setText("Time");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));
        jPanel1.add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 140, -1));

        jLabel7.setText("Reason ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        txtReason.setColumns(20);
        txtReason.setRows(5);
        jScrollPane1.setViewportView(txtReason);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 320, -1));

        jLabel8.setText("Status");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scheduled", "Completed", "Cancelled" }));
        jPanel1.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 150, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, -1, -1));

        btnDelete.setText("Delete");
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, -1, -1));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, -1));

        jLabel9.setText("Search Appointment ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 500, 120, -1));

        txtSearch.addActionListener(this::txtSearchActionPerformed);
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 150, -1));

        btnSearch.setText("Search ");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, -1, -1));

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Pet", "Veterinarian", "Service", "Date", "Time", "Reason", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblAppointments);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 580, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new AppointmentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbPet;
    private javax.swing.JComboBox<String> cmbService;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbVeterinarian;
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
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtReason;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
