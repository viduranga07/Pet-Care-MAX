/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.AppointmentFormController;


import com.petcaremax.controller.AppointmentController;
import com.petcaremax.controller.PetController;
import com.petcaremax.controller.VeterinarianController;
import com.petcaremax.controller.ServiceController;
import com.petcaremax.util.PetCareTheme;

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
    private final AppointmentFormController formController;


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

    formController = new AppointmentFormController(this);

    appointmentController = new AppointmentController();
    petController = new PetController();
    veterinarianController = new VeterinarianController();
    serviceController = new ServiceController();

    loadPets();
    loadVeterinarians();
    loadServices();
    loadAppointments();
    PetCareTheme.apply(this);
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
public void saveAppointment() {

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
public void clearAppointmentFields() {

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
public void updateAppointment() {

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
public void deleteAppointment() {

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
public void searchAppointments() {

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
        jLabelSubtitle = new javax.swing.JLabel();
        jLabelSystem = new javax.swing.JLabel();
        jPanelForm = new javax.swing.JPanel();
        jLabelFormTitle = new javax.swing.JLabel();
        jLabelFormHint = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbPet = new javax.swing.JComboBox();
        cmbVeterinarian = new javax.swing.JComboBox();
        cmbService = new javax.swing.JComboBox();
        txtDate = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReason = new javax.swing.JTextArea();
        cmbStatus = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanelList = new javax.swing.JPanel();
        jLabelListTitle = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PetCareMAX - Appointment Management");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(53, 64, 80));
        jLabel1.setText("Appointment Management");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 28, 500, 40));

        jLabelSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabelSubtitle.setText("Schedule and manage veterinary appointments");
        jPanel1.add(jLabelSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 500, 24));

        jLabelSystem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSystem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSystem.setText("PETCAREMAX");
        jPanel1.add(jLabelSystem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 40, 130, 24));

        jPanelForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelFormTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelFormTitle.setForeground(new java.awt.Color(53, 64, 80));
        jLabelFormTitle.setText("Appointment Details");
        jPanelForm.add(jLabelFormTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 20, 250, 28));

        jLabelFormHint.setText("Enter appointment information");
        jPanelForm.add(jLabelFormHint, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 49, 300, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 64, 80));
        jLabel2.setText("Pet");
        jPanelForm.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 88, 120, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(53, 64, 80));
        jLabel3.setText("Veterinarian");
        jPanelForm.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 158, 140, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(53, 64, 80));
        jLabel4.setText("Service");
        jPanelForm.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 228, 120, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(53, 64, 80));
        jLabel5.setText("Date");
        jPanelForm.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 298, 160, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(53, 64, 80));
        jLabel6.setText("Time");
        jPanelForm.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 298, 160, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(53, 64, 80));
        jLabel7.setText("Reason");
        jPanelForm.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 368, 120, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(53, 64, 80));
        jLabel8.setText("Status");
        jPanelForm.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 466, 120, 20));

        cmbPet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbPet.setForeground(new java.awt.Color(53, 64, 80));
        jPanelForm.add(cmbPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 110, 382, 34));

        cmbVeterinarian.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbVeterinarian.setForeground(new java.awt.Color(53, 64, 80));
        jPanelForm.add(cmbVeterinarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 382, 34));

        cmbService.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbService.setForeground(new java.awt.Color(53, 64, 80));
        jPanelForm.add(cmbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 250, 382, 34));

        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDate.setForeground(new java.awt.Color(53, 64, 80));
        jPanelForm.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 320, 180, 34));

        txtTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTime.setForeground(new java.awt.Color(53, 64, 80));
        jPanelForm.add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 186, 34));

        txtReason.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtReason.setColumns(20);
        txtReason.setRows(3);
        txtReason.setLineWrap(true);
        txtReason.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtReason);

        jPanelForm.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 390, 382, 64));

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbStatus.setForeground(new java.awt.Color(53, 64, 80));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scheduled", "Completed", "Cancelled" }));
        jPanelForm.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 488, 382, 34));

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(53, 64, 80));
        btnSave.setText("Save");
        jPanelForm.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 548, 86, 36));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(53, 64, 80));
        btnUpdate.setText("Update");
        jPanelForm.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 548, 86, 36));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        jPanelForm.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 548, 86, 36));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        jPanelForm.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 548, 100, 36));

        jPanel1.add(jPanelForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 430, 585));

        jPanelList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelListTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelListTitle.setForeground(new java.awt.Color(53, 64, 80));
        jLabelListTitle.setText("Appointment List");
        jPanelList.add(jLabelListTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 20, 250, 28));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Search Appointments");
        jPanelList.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 66, 150, 20));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(53, 64, 80));
        jPanelList.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 62, 410, 34));

        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(53, 64, 80));
        btnSearch.setText("Search");
        jPanelList.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 86, 34));

        tblAppointments.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblAppointments.setForeground(new java.awt.Color(53, 64, 80));
        tblAppointments.setRowHeight(36);
        tblAppointments.setShowHorizontalLines(true);
        tblAppointments.setAutoCreateRowSorter(true);
        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Pet", "Veterinarian", "Service", "Date", "Time", "Reason", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAppointments);
        if (tblAppointments.getColumnModel().getColumnCount() > 0) {
            tblAppointments.getColumnModel().getColumn(0).setPreferredWidth(55);
            tblAppointments.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblAppointments.getColumnModel().getColumn(2).setPreferredWidth(125);
            tblAppointments.getColumnModel().getColumn(3).setPreferredWidth(110);
            tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblAppointments.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblAppointments.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblAppointments.getColumnModel().getColumn(7).setPreferredWidth(95);
        }

        jPanelList.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 112, 650, 440));

        jPanel1.add(jPanelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 700, 585));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void styleLabel(javax.swing.JLabel label, String text) {
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        label.setForeground(new java.awt.Color(35, 40, 50));
        label.setText(text);
    }

    private void styleField(javax.swing.JTextField field) {
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        field.setForeground(new java.awt.Color(35, 40, 50));
        field.setBackground(java.awt.Color.WHITE);
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(225, 228, 235)),
                javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
    }

    private void styleCombo(javax.swing.JComboBox<String> combo) {
        combo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        combo.setForeground(new java.awt.Color(35, 40, 50));
        combo.setBackground(java.awt.Color.WHITE);
    }

    private void stylePrimaryButton(javax.swing.JButton button, String text) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setText(text);
        button.setBackground(new java.awt.Color(245, 190, 55));
        button.setForeground(new java.awt.Color(35, 40, 50));
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 12, 5, 12));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleSecondaryButton(javax.swing.JButton button, String text) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setText(text);
        button.setBackground(new java.awt.Color(235, 232, 225));
        button.setForeground(new java.awt.Color(35, 40, 50));
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 12, 5, 12));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleDeleteButton(javax.swing.JButton button, String text) {
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setText(text);
        button.setBackground(new java.awt.Color(217, 83, 79));
        button.setForeground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 12, 5, 12));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private static class AppointmentTableCellRenderer
            extends javax.swing.table.DefaultTableCellRenderer {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {

            java.awt.Component component =
                    super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);

            setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

            if (!isSelected) {
                setBackground(java.awt.Color.WHITE);
                setForeground(new java.awt.Color(35, 40, 50));
            }

            if (column == 7 && value != null && !isSelected) {
                String status = value.toString().trim();

                if ("Completed".equalsIgnoreCase(status)) {
                    setForeground(new java.awt.Color(46, 155, 98));
                    setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                } else if ("Cancelled".equalsIgnoreCase(status)) {
                    setForeground(new java.awt.Color(217, 83, 79));
                    setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                } else if ("Scheduled".equalsIgnoreCase(status)) {
                    setForeground(new java.awt.Color(207, 145, 30));
                    setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                }
            }

            return component;
        }
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
    private javax.swing.JComboBox cmbPet;
    private javax.swing.JComboBox cmbService;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbVeterinarian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFormHint;
    private javax.swing.JLabel jLabelFormTitle;
    private javax.swing.JLabel jLabelListTitle;
    private javax.swing.JLabel jLabelSubtitle;
    private javax.swing.JLabel jLabelSystem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPanel jPanelList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtReason;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTime;
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
