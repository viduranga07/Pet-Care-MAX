/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.ServiceFormController;

import com.petcaremax.controller.ServiceController;
import com.petcaremax.model.Service;
import com.petcaremax.util.PetCareTheme;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Bimsara
 */
public class ServiceForm extends javax.swing.JFrame {
    private final ServiceFormController formController;

    
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(ServiceForm.class.getName());

     private final ServiceController serviceController =
            new ServiceController();

    private int selectedServiceId = 0;
    /**
     * Creates new form ServiceForm
     */
    public ServiceForm() {
        initComponents();

    formController = new ServiceFormController(this);
        PetCareTheme.apply(this);
        // Add event listeners
        tblServices.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServicesMouseClicked(evt);
            }
        });

        // Load services from database
        loadServices();

        setLocationRelativeTo(null);
    }

    /**
     * Load all services from database
     */
    private void loadServices() {

        List<Service> services =
                serviceController.getAllServices();

        DefaultTableModel model =
                (DefaultTableModel) tblServices.getModel();

        model.setRowCount(0);

        for (Service service : services) {

            model.addRow(new Object[]{
                service.getServiceId(),
                service.getServiceName(),
                service.getDescription(),
                service.getPrice(),
                service.getStatus()
            });
        }
    }

    /**
     * Clear all input fields
     */
    private void clearFields() {

        txtServiceName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");

        cmbStatus.setSelectedItem("Active");

        selectedServiceId = 0;

        tblServices.clearSelection();
    }

    /**
     * Save Service
     */
    public void saveService() {

        String serviceName =
                txtServiceName.getText().trim();

        String description =
                txtDescription.getText().trim();

        String priceText =
                txtPrice.getText().trim();

        String status =
                cmbStatus.getSelectedItem().toString();

        // Validation
        if (serviceName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Service name is required."
            );

            txtServiceName.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price is required."
            );

            txtPrice.requestFocus();
            return;
        }

        double price;

        try {

            price = Double.parseDouble(priceText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

            txtPrice.requestFocus();
            return;
        }

        if (price <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price must be greater than zero."
            );

            txtPrice.requestFocus();
            return;
        }

        try {

            Service service = new Service(
                    0,
                    serviceName,
                    description,
                    price,
                    status
            );

            boolean added =
                    serviceController.addService(service);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Service added successfully!"
                );

                clearFields();
                loadServices();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add service."
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    /**
     * Update Service
     */
    public void updateService() {

        if (selectedServiceId == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a service from the table first."
            );

            return;
        }

        String serviceName =
                txtServiceName.getText().trim();

        String description =
                txtDescription.getText().trim();

        String priceText =
                txtPrice.getText().trim();

        String status =
                cmbStatus.getSelectedItem().toString();

        // Validation
        if (serviceName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Service name is required."
            );

            txtServiceName.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price is required."
            );

            txtPrice.requestFocus();
            return;
        }

        double price;

        try {

            price = Double.parseDouble(priceText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

            txtPrice.requestFocus();
            return;
        }

        if (price <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price must be greater than zero."
            );

            txtPrice.requestFocus();
            return;
        }

        try {

            Service service = new Service(
                    selectedServiceId,
                    serviceName,
                    description,
                    price,
                    status
            );

            boolean updated =
                    serviceController.updateService(service);

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Service updated successfully!"
                );

                clearFields();
                loadServices();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update service."
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    /**
     * Delete Service
     */
    public void deleteService() {

        if (selectedServiceId == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a service from the table first."
            );

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this service?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean deleted =
                    serviceController.deleteService(
                            selectedServiceId
                    );

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Service deleted successfully!"
                );

                clearFields();
                loadServices();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to delete service."
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    /**
     * Clear button
     */
    public void clearService() {

        clearFields();
        loadServices();
    }

    /**
     * Search Service
     */
    public void searchServices() {

        String searchText =
                txtSearch.getText().trim().toLowerCase();

        // If search box is empty, show all services
        if (searchText.isEmpty()) {

            loadServices();
            return;
        }

        try {

            List<Service> services =
                    serviceController.getAllServices();

            List<Service> filteredServices =
                    services.stream()
                            .filter(service ->
                                    String.valueOf(
                                            service.getServiceId()
                                    ).contains(searchText)

                                    || service.getServiceName()
                                            .toLowerCase()
                                            .contains(searchText)

                                    || (service.getDescription() != null
                                            && service.getDescription()
                                                    .toLowerCase()
                                                    .contains(searchText))

                                    || String.valueOf(
                                            service.getPrice()
                                    ).contains(searchText)

                                    || (service.getStatus() != null
                                            && service.getStatus()
                                                    .toLowerCase()
                                                    .contains(searchText))
                            )
                            .toList();

            DefaultTableModel model =
                    (DefaultTableModel) tblServices.getModel();

            model.setRowCount(0);

            for (Service service : filteredServices) {

                model.addRow(new Object[]{
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getDescription(),
                    service.getPrice(),
                    service.getStatus()
                });
            }

            if (filteredServices.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No service found."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    /**
     * Table row selection
     */
    private void tblServicesMouseClicked(
            java.awt.event.MouseEvent evt) {

        int selectedRow =
                tblServices.getSelectedRow();

        if (selectedRow >= 0) {

            selectedServiceId =
                    Integer.parseInt(
                            tblServices
                                    .getValueAt(selectedRow, 0)
                                    .toString()
                    );

            txtServiceName.setText(
                    tblServices
                            .getValueAt(selectedRow, 1)
                            .toString()
            );

            Object description =
                    tblServices.getValueAt(selectedRow, 2);

            if (description != null) {

                txtDescription.setText(
                        description.toString()
                );

            } else {

                txtDescription.setText("");
            }

            txtPrice.setText(
                    tblServices
                            .getValueAt(selectedRow, 3)
                            .toString()
            );

            cmbStatus.setSelectedItem(
                    tblServices
                            .getValueAt(selectedRow, 4)
                            .toString()
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
        txtServiceName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblServices = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PetCareMAX - Service Management");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Service Management");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, 500, 45));

        jLabel2.setText("Service Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 105, 240, 25));
        jPanel1.add(txtServiceName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 135, 520, 42));

        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 240, 25));

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 520, 115));

        jLabel4.setText("Price");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 390, 25));
        jPanel1.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 390, 42));

        jLabel5.setText("Status");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 105, 390, 25));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        jPanel1.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 135, 390, 42));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 120, 42));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, 120, 42));

        btnDelete.setText("Delete");
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 120, 42));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, 120, 42));

        jLabel6.setText("Search Service");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 140, 25));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 385, 420, 42));

        btnSearch.setText("Search");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 385, 110, 42));

        tblServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Service Name", "Description", "Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServices.setRowHeight(36);
        jScrollPane2.setViewportView(tblServices);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 465, 1120, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblServices;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtServiceName;
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
