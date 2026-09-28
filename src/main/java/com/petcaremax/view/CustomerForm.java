/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;

import com.petcaremax.controller.CustomerController;
import com.petcaremax.model.Customer;
import com.petcaremax.util.PetCareTheme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Customer Management Form
 *
 * Responsibilities:
 * - Create, read, update and delete customers
 * - Search customers using ArrayList + Stream + Lambda
 * - Display customer records in a professional Swing UI
 *
 * The controller/service/DAO/database logic is kept separate.
 */
public class CustomerForm extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(CustomerForm.class.getName());

    // ============================================================
    // CONTROLLER
    // ============================================================

    private final CustomerController customerController;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public CustomerForm() {

        /*
         * Creates the Swing components.
         * In the original NetBeans project this section is generated
         * by the GUI Builder.
         */
        initComponents();

        // Apply the common PetCareMAX theme.
        PetCareTheme.apply(this);

        // Create controller.
        customerController = new CustomerController();

        /*
         * Build the professional layout AFTER the components exist.
         * This replaces the old small GUI Builder layout without
         * changing the CRUD/controller logic.
         */
        buildProfessionalLayout();

        // ========================================================
        // BUTTON EVENTS
        // ========================================================

        btnSave.addActionListener(
                this::btnSaveActionPerformed
        );

        btnUpdate.addActionListener(
                this::btnUpdateActionPerformed
        );

       

        btnClear.addActionListener(
                this::btnClearActionPerformed
        );

        btnSearch.addActionListener(
                this::btnSearchActionPerformed
        );

        // ========================================================
        // TABLE ROW SELECTION
        // ========================================================

        jTable1.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent evt
                    ) {
                        loadSelectedCustomer();
                    }
                }
        );

        // Load database records.
        loadCustomers();
    }

    // ============================================================
    // PROFESSIONAL UI
    // ============================================================

    private void buildProfessionalLayout() {

        // ========================================================
        // MAIN PAGE
        // ========================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(
                PetCareTheme.LIGHT
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // ========================================================
        // PAGE HEADER
        // ========================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Customer Management"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                PetCareTheme.NAVY
        );

        JLabel subtitle =
                new JLabel(
                        "Manage customer information and customer records"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                PetCareTheme.MUTED
        );

        headerPanel.add(title);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitle);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ========================================================
        // CENTER AREA
        // ========================================================

        JPanel centerPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        centerPanel.setOpaque(false);

        // ========================================================
        // CUSTOMER INFORMATION CARD
        // ========================================================

        JPanel formCard =
                createCard();

        formCard.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        12,
                        7,
                        12
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        JLabel formTitle =
                new JLabel(
                        "Customer Information"
                );

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        formTitle.setForeground(
                PetCareTheme.NAVY
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        formCard.add(
                formTitle,
                gbc
        );

        JLabel formSubtitle =
                new JLabel(
                        "Enter customer details below"
                );

        formSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        formSubtitle.setForeground(
                PetCareTheme.MUTED
        );

        gbc.gridy++;

        formCard.add(
                formSubtitle,
                gbc
        );

        // ========================================================
        // FULL NAME
        // ========================================================

        gbc.gridy++;
        gbc.gridwidth = 2;

        formCard.add(
                createFieldLabel("Full Name"),
                gbc
        );

        gbc.gridy++;

        formCard.add(
                txtFullName,
                gbc
        );

        // ========================================================
        // PHONE
        // ========================================================

        gbc.gridy++;

        formCard.add(
                createFieldLabel("Phone"),
                gbc
        );

        gbc.gridy++;

        formCard.add(
                txtPhone,
                gbc
        );

        // ========================================================
        // EMAIL
        // ========================================================

        gbc.gridy++;

        formCard.add(
                createFieldLabel("Email"),
                gbc
        );

        gbc.gridy++;

        formCard.add(
                txtEmail,
                gbc
        );

        // ========================================================
        // ADDRESS
        // ========================================================

        gbc.gridy++;

        formCard.add(
                createFieldLabel("Address"),
                gbc
        );

        gbc.gridy++;

        formCard.add(
                txtAddress,
                gbc
        );

        // ========================================================
        // BUTTONS
        // ========================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        styleButton(
                btnSave,
                "Save",
                PetCareTheme.GOLD
        );

        styleButton(
                btnUpdate,
                "Update",
                new Color(
                        52,
                        122,
                        190
                )
        );

        styleButton(
                btnDelete,
                "Delete",
                new Color(
                        205,
                        70,
                        70
                )
        );

        styleButton(
                btnClear,
                "Clear",
                new Color(
                        110,
                        120,
                        135
                )
        );

        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        gbc.gridy++;
        gbc.insets =
                new Insets(
                        18,
                        12,
                        8,
                        12
                );

        formCard.add(
                buttonPanel,
                gbc
        );

        // ========================================================
        // CUSTOMER TABLE CARD
        // ========================================================

        JPanel tableCard =
                createCard();

        tableCard.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        // ========================================================
        // TABLE HEADER
        // ========================================================

        JPanel tableHeader =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        tableHeader.setOpaque(false);

        JPanel tableTitlePanel =
                new JPanel();

        tableTitlePanel.setLayout(
                new BoxLayout(
                        tableTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        tableTitlePanel.setOpaque(false);

        JLabel tableTitle =
                new JLabel(
                        "Customer List"
                );

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        tableTitle.setForeground(
                PetCareTheme.NAVY
        );

        JLabel tableSubtitle =
                new JLabel(
                        "View and manage all customers"
                );

        tableSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tableSubtitle.setForeground(
                PetCareTheme.MUTED
        );

        tableTitlePanel.add(tableTitle);

        tableTitlePanel.add(
                tableSubtitle
        );

        // ========================================================
        // SEARCH AREA
        // ========================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                8,
                                0
                        )
                );

        searchPanel.setOpaque(false);

        txtSearch.setPreferredSize(
                new Dimension(
                        190,
                        36
                )
        );

        styleSearchField(
                txtSearch
        );

        styleButton(
                btnSearch,
                "Search",
                PetCareTheme.GOLD
        );

        btnSearch.setPreferredSize(
                new Dimension(
                        90,
                        36
                )
        );

        searchPanel.add(
                txtSearch,
                BorderLayout.CENTER
        );

        searchPanel.add(
                btnSearch,
                BorderLayout.EAST
        );

        tableHeader.add(
                tableTitlePanel,
                BorderLayout.WEST
        );

        tableHeader.add(
                searchPanel,
                BorderLayout.EAST
        );

        tableCard.add(
                tableHeader,
                BorderLayout.NORTH
        );

        // ========================================================
        // TABLE STYLING
        // ========================================================

        styleTable();

        tableCard.add(
                jScrollPane1,
                BorderLayout.CENTER
        );

        // ========================================================
        // ADD CARDS
        // ========================================================

        centerPanel.add(formCard);
        centerPanel.add(tableCard);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // Replace old GUI Builder content.
        setContentPane(mainPanel);

        setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        setSize(
                1150,
                700
        );

        setLocationRelativeTo(null);

        revalidate();
        repaint();
    }

    // ============================================================
    // CARD STYLE
    // ============================================================

    private JPanel createCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        230,
                                        238
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        return panel;
    }

    // ============================================================
    // FIELD LABEL
    // ============================================================

    private JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                PetCareTheme.TEXT
        );

        return label;
    }

    // ============================================================
    // BUTTON STYLE
    // ============================================================

    private void styleButton(
            JButton button,
            String text,
            Color background
    ) {

        button.setText(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setBackground(
                background
        );

        button.setForeground(
                background.equals(PetCareTheme.GOLD)
                        ? new Color(30, 35, 45)
                        : Color.WHITE
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        9,
                        10,
                        9,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setOpaque(true);

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                background.brighter()
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                background
                        );
                    }
                }
        );
    }

    // ============================================================
    // SEARCH FIELD STYLE
    // ============================================================

    private void styleSearchField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        225
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        field.setBackground(
                Color.WHITE
        );
    }

    // ============================================================
    // TABLE STYLE
    // ============================================================

    private void styleTable() {

        jTable1.setRowHeight(32);

        jTable1.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        jTable1.setForeground(
                new Color(
                        45,
                        50,
                        60
                )
        );

        jTable1.setBackground(
                Color.WHITE
        );

        jTable1.setSelectionBackground(
                new Color(
                        255,
                        239,
                        190
                )
        );

        jTable1.setSelectionForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        jTable1.setGridColor(
                new Color(
                        235,
                        238,
                        243
                )
        );

        jTable1.setShowVerticalLines(false);

        jTable1.setShowHorizontalLines(true);

        jTable1.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        jTable1.setAutoCreateRowSorter(true);

        jTable1.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        jTable1.getTableHeader().setForeground(
                new Color(
                        45,
                        50,
                        60
                )
        );

        jTable1.getTableHeader().setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        jTable1.getTableHeader().setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        jTable1.getTableHeader().setReorderingAllowed(
                false
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        jTable1.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        jScrollPane1.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                225,
                                230,
                                238
                        )
                )
        );
    }

    // ============================================================
    // COMPONENT INITIALIZATION
    // ============================================================
    //
    // IMPORTANT:
    // In your actual NetBeans project, this section is normally
    // generated by the GUI Builder. Do not manually edit the
    // generated section if you want NetBeans Designer to remain
    // the source of truth.
    //
    // This reference version initializes the same components so
    // you can compare the complete working structure.
    // ============================================================

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Customer Management");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        jLabel2.setText("Customer Information");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        jLabel3.setText("Customer List");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 160, -1));

        jLabel4.setText("Full Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setText("Phone");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel7.setText("Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        jPanel1.add(txtFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 230, -1));
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 230, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 230, -1));
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 230, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        btnUpdate.setText("Update");
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        btnClear.setText("Clear");
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jLabel8.setText("Search Customer");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 100, -1));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 160, -1));

        btnSearch.setText("Search");
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "Phone", "Email", "Address"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 330, 210));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // ============================================================
    // SAVE CUSTOMER
    // ============================================================

    private void btnSaveActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        try {

            String name =
                    txtFullName.getText().trim();

            String phone =
                    txtPhone.getText().trim();

            String email =
                    txtEmail.getText().trim();

            String address =
                    txtAddress.getText().trim();

            boolean saved =
                    customerController.addCustomer(
                            name,
                            phone,
                            email,
                            address
                    );

            if (saved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer saved successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer could not be saved.",
                        "Error",
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

            e.printStackTrace();
        }
    }

    // ============================================================
    // UPDATE CUSTOMER
    // ============================================================

    private void btnUpdateActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        int row =
                jTable1.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer from the table first.",
                    "No Customer Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            // Convert the displayed row back to the model row
            // because table sorting is enabled.
            int modelRow =
                    jTable1.convertRowIndexToModel(row);

            int id =
                    Integer.parseInt(
                            jTable1
                                    .getModel()
                                    .getValueAt(
                                            modelRow,
                                            0
                                    )
                                    .toString()
                    );

            String name =
                    txtFullName.getText().trim();

            String phone =
                    txtPhone.getText().trim();

            String email =
                    txtEmail.getText().trim();

            String address =
                    txtAddress.getText().trim();

            boolean updated =
                    customerController.updateCustomer(
                            id,
                            name,
                            phone,
                            email,
                            address
                    );

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer could not be updated.",
                        "Error",
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
                    "Error updating customer:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ============================================================
    // CLEAR
    // ============================================================

    private void btnClearActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        clearFields();

        jTable1.clearSelection();

        txtSearch.setText("");

        loadCustomers();
    }

    // ============================================================
    // SEARCH
    // ============================================================

    private void btnSearchActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        String searchText =
                txtSearch.getText()
                        .trim()
                        .toLowerCase();

        if (searchText.isEmpty()) {

            loadCustomers();

            return;
        }

        try {

            /*
             * ArrayList is intentionally used here
             * for the coursework Collections requirement.
             */
            ArrayList<Customer> customers =
                    new ArrayList<>(
                            customerController
                                    .getAllCustomers()
                    );

            /*
             * Lambda + Stream filtering.
             */
            List<Customer> filtered =
                    customers.stream()
                            .filter(
                                    customer ->
                                            String.valueOf(
                                                    customer.getCustomerId()
                                            ).contains(searchText)

                                            ||

                                            customer.getFullName()
                                                    .toLowerCase()
                                                    .contains(searchText)

                                            ||

                                            customer.getPhone()
                                                    .toLowerCase()
                                                    .contains(searchText)

                                            ||

                                            (
                                                    customer.getEmail() != null
                                                    &&
                                                    customer.getEmail()
                                                            .toLowerCase()
                                                            .contains(searchText)
                                            )
                            )
                            .collect(
                                    Collectors.toList()
                            );

            displayCustomers(
                    filtered
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching customers:\n"
                    + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ============================================================
    // LOAD CUSTOMERS
    // ============================================================

    private void loadCustomers() {

        try {

            List<Customer> customers =
                    customerController
                            .getAllCustomers();

            displayCustomers(
                    customers
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load customers:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // ============================================================
    // DISPLAY CUSTOMERS
    // ============================================================

    private void displayCustomers(
            List<Customer> customers
    ) {

        DefaultTableModel model =
                (DefaultTableModel)
                        jTable1.getModel();

        model.setRowCount(0);

        for (Customer customer : customers) {

            model.addRow(
                    new Object[]{
                            customer.getCustomerId(),
                            customer.getFullName(),
                            customer.getPhone(),
                            customer.getEmail() == null
                                    ? ""
                                    : customer.getEmail(),
                            customer.getAddress() == null
                                    ? ""
                                    : customer.getAddress()
                    }
            );
        }
    }

    // ============================================================
    // LOAD SELECTED CUSTOMER
    // ============================================================

    private void loadSelectedCustomer() {

        int row =
                jTable1.getSelectedRow();

        if (row == -1) {
            return;
        }

        int modelRow =
                jTable1.convertRowIndexToModel(row);

        txtFullName.setText(
                valueFromTable(
                        modelRow,
                        1
                )
        );

        txtPhone.setText(
                valueFromTable(
                        modelRow,
                        2
                )
        );

        txtEmail.setText(
                valueFromTable(
                        modelRow,
                        3
                )
        );

        txtAddress.setText(
                valueFromTable(
                        modelRow,
                        4
                )
        );
    }

    // ============================================================
    // TABLE VALUE HELPER
    // ============================================================

    private String valueFromTable(
            int row,
            int column
    ) {

        Object value =
                jTable1.getModel().getValueAt(
                        row,
                        column
                );

        return value == null
                ? ""
                : value.toString();
    }

    // ============================================================
    // CLEAR FIELDS
    // ============================================================

    private void clearFields() {

        txtFullName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

        txtFullName.requestFocus();
    }

     // DELETE CUSTOMER
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
     {

        int row =
                jTable1.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer to delete.",
                    "No Customer Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            jTable1
                                    .getValueAt(row, 0)
                                    .toString()
                    );

            String name =
                    jTable1
                            .getValueAt(row, 1)
                            .toString();

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete customer:\n"
                            + name
                            + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirmation !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            boolean deleted =
                    customerController.deleteCustomer(
                            id
                    );

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer could not be deleted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error deleting customer:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_btnDeleteActionPerformed
     

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
try {

            for (
                    javax.swing.UIManager.LookAndFeelInfo info
                    :
                    javax.swing.UIManager
                            .getInstalledLookAndFeels()
            ) {

                if (
                        "Nimbus".equals(
                                info.getName()
                        )
                ) {

                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (
                ReflectiveOperationException
                |
                javax.swing.UnsupportedLookAndFeelException ex
        ) {

            logger.log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

         java.awt.EventQueue.invokeLater(
            () -> {

                CustomerForm form =
                        new CustomerForm();

                form.setVisible(true);
            }
    );
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CustomerForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
