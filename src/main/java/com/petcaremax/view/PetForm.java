/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.petcaremax.view;
import com.petcaremax.controller.CustomerController;
import com.petcaremax.controller.PetController;
import com.petcaremax.model.Customer;
import com.petcaremax.model.Pet;
import com.petcaremax.util.PetCareTheme;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Bimsara
 */
public class PetForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = 
            java.util.logging.Logger.getLogger(PetForm.class.getName());

    // Controllers
    private final PetController petController;
    private final CustomerController customerController;

    // Collections
    private List<Pet> currentPets = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    /**
     * Creates new form PetMangementForm
     */
    public PetForm() {
        initComponents();
         // Create controllers
        petController = new PetController();
        customerController = new CustomerController();
        
        PetCareTheme.apply(this);
        buildProfessionalLayout();

        // Table row selection
        tblPets.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent evt
                    ) {
                        loadSelectedPet();
                    }
                }
        );

        // Load data
        loadCustomers();
        loadPets();

        // Center window
        setLocationRelativeTo(null);
    }
    private void buildProfessionalLayout() {

    Color background = PetCareTheme.LIGHT;
    Color white = PetCareTheme.WHITE;
    Color text = PetCareTheme.TEXT;
    Color muted = PetCareTheme.MUTED;
    Color border = PetCareTheme.BORDER;

    // ============================================================
    // MAIN PAGE
    // ============================================================

    JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
    mainPanel.setBackground(background);

    mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                    25, 30, 25, 30
            )
    );


    // ============================================================
    // PAGE HEADER
    // ============================================================

    JPanel headerPanel = new JPanel();
    headerPanel.setOpaque(false);

    headerPanel.setLayout(
            new javax.swing.BoxLayout(
                    headerPanel,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

   JLabel title =
        new JLabel("Pet Management");

title.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                30
        )
);

title.setForeground(text);

    JLabel subtitle = new JLabel(
            "Manage pet information and pet records"
    );

    subtitle.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    16
            )
    );

    subtitle.setForeground(muted);

    headerPanel.add(title);
    headerPanel.add(
            Box.createVerticalStrut(5)
    );
    headerPanel.add(subtitle);

    mainPanel.add(
            headerPanel,
            BorderLayout.NORTH
    );


    // ============================================================
    // TWO MAIN CARDS
    // ============================================================

    JPanel centerPanel =
            new JPanel(
                    new java.awt.GridLayout(
                            1,
                            2,
                            25,
                            0
                    )
            );

    centerPanel.setOpaque(false);


    // ============================================================
    // LEFT CARD
    // ============================================================

    JPanel informationCard =
            createPetSectionCard(
                    "Pet Information",
                    "Enter pet details below"
            );


    JPanel informationContent =
            new JPanel(
                    new GridBagLayout()
            );

    informationContent.setOpaque(false);


    GridBagConstraints gbc =
            new GridBagConstraints();

    gbc.gridx = 0;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.insets =
            new Insets(
                    1,
                    0,
                    1,
                    0
            );


    // Pet Name
    gbc.gridy = 0;
    informationContent.add(
            jLabel1,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            txtPetName,
            gbc
    );


    // Species
    gbc.gridy++;
    informationContent.add(
            jLabel2,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            cmbSpecies,
            gbc
    );


    // Breed
    gbc.gridy++;
    informationContent.add(
            jLabel3,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            txtBreed,
            gbc
    );


    // Gender
    gbc.gridy++;
    informationContent.add(
            jLabel4,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            cmbGender,
            gbc
    );


    // Date of Birth
    gbc.gridy++;
    informationContent.add(
            jLabel5,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            txtDateOfBirth,
            gbc
    );


    // Weight
    gbc.gridy++;
    informationContent.add(
            jLabel11,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            txtWeight,
            gbc
    );


    // Owner
    gbc.gridy++;
    informationContent.add(
            jLabel6,
            gbc
    );

    gbc.gridy++;
    informationContent.add(
            cmbOwner,
            gbc
    );


    // Notes
    gbc.gridy++;
    informationContent.add(
            jLabel12,
            gbc
    );

    gbc.gridy++;

    jScrollPane2.setPreferredSize(
            new Dimension(
                    100,
                    45
            )
    );

    informationContent.add(
            jScrollPane2,
            gbc
    );


    // ============================================================
    // BUTTON ROW
    // ============================================================

    JPanel buttonPanel =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.LEFT,
                            8,
                            2
                    )
            );

    buttonPanel.setOpaque(false);
btnSave.setPreferredSize(new Dimension(105, 40));
btnUpdate.setPreferredSize(new Dimension(105, 40));
btnDelete.setPreferredSize(new Dimension(105, 40));
btnClear.setPreferredSize(new Dimension(105, 40));

    buttonPanel.add(btnSave);
    buttonPanel.add(btnUpdate);
    buttonPanel.add(btnDelete);
    buttonPanel.add(btnClear);

    gbc.gridy++;
    gbc.insets =
            new Insets(
                    8,
                    0,
                    0,
                    0
            );

    informationContent.add(
            buttonPanel,
            gbc
    );


    informationCard.add(
            informationContent,
            BorderLayout.CENTER
    );


    // ============================================================
    // RIGHT CARD
    // ============================================================

    JPanel listCard =
            createPetSectionCard(
                    "Pet List",
                    "View and manage all pets"
            );


    JPanel listContent =
            new JPanel(
                    new BorderLayout(
                            0,
                            12
                    )
            );

    listContent.setOpaque(false);


    // Search
    JPanel searchPanel =
            new JPanel(
                    new BorderLayout(
                            10,
                            0
                    )
            );

    searchPanel.setOpaque(false);

    searchPanel.add(
            txtSearch,
            BorderLayout.CENTER
    );

    searchPanel.add(
            btnSearch,
            BorderLayout.EAST
    );


    listContent.add(
            searchPanel,
            BorderLayout.NORTH
    );


    // Table
    listContent.add(
            jScrollPane1,
            BorderLayout.CENTER
    );


    listCard.add(
            listContent,
            BorderLayout.CENTER
    );


    // ============================================================
    // ADD BOTH CARDS
    // ============================================================

    centerPanel.add(
            informationCard
    );

    centerPanel.add(
            listCard
    );


    mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
    );


    // ============================================================
    // REPLACE OLD CONTENT
    // ============================================================

    setContentPane(mainPanel);

    revalidate();
    repaint();
}
private JPanel createPetSectionCard(
        String titleText,
        String subtitleText
) {

    JPanel card =
            new JPanel(
                    new BorderLayout(
                            0,
                            15
                    )
            );

    card.setBackground(
            PetCareTheme.WHITE
    );

    card.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            PetCareTheme.BORDER
                    ),
                    BorderFactory.createEmptyBorder(
                            25,
                            25,
                            25,
                            25
                    )
            )
    );


    JPanel header =
            new JPanel();

    header.setOpaque(false);

    header.setLayout(
            new javax.swing.BoxLayout(
                    header,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );


    JLabel title =
            new JLabel(
                    titleText
            );

    title.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    30
            )
    );

    title.setForeground(
            PetCareTheme.TEXT
    );


    JLabel subtitle =
            new JLabel(
                    subtitleText
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


    header.add(title);

    header.add(
            Box.createVerticalStrut(
                    5
            )
    );

    header.add(subtitle);


    card.add(
            header,
            BorderLayout.NORTH
    );


    return card;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPetName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbSpecies = new javax.swing.JComboBox<>();
        txtBreed = new javax.swing.JTextField();
        cmbGender = new javax.swing.JComboBox<>();
        txtDateOfBirth = new javax.swing.JTextField();
        cmbOwner = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPets = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 247, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 900));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 40, 50));
        jLabel1.setText("Pet Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 40, 50));
        jLabel2.setText("Species");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 60, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 40, 50));
        jLabel3.setText("Breed");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 50, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 40, 50));
        jLabel4.setText("Gender");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 70, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(35, 40, 50));
        jLabel5.setText("Date of Birth");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 100, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(35, 40, 50));
        jLabel6.setText("Owner ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 50, -1));

        txtPetName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPetName.setForeground(new java.awt.Color(35, 40, 50));
        jPanel1.add(txtPetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 140, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(35, 40, 50));
        jLabel7.setText("Pet Management ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(35, 40, 50));
        jLabel8.setText("Pet Information");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        cmbSpecies.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSpecies.setForeground(new java.awt.Color(35, 40, 50));
        cmbSpecies.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dog", "Cat", "Bird", "Rabbit", "Other" }));
        jPanel1.add(cmbSpecies, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, -1));

        txtBreed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBreed.setForeground(new java.awt.Color(35, 40, 50));
        jPanel1.add(txtBreed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 140, -1));

        cmbGender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbGender.setForeground(new java.awt.Color(35, 40, 50));
        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel1.add(cmbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 140, -1));

        txtDateOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDateOfBirth.setForeground(new java.awt.Color(35, 40, 50));
        jPanel1.add(txtDateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 140, -1));

        cmbOwner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbOwner.setForeground(new java.awt.Color(35, 40, 50));
        cmbOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbOwner.addActionListener(this::cmbOwnerActionPerformed);
        jPanel1.add(cmbOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 140, -1));

        btnSave.setBackground(new java.awt.Color(245, 190, 55));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(35, 40, 50));
        btnSave.setText("Save");
        btnSave.setMaximumSize(new java.awt.Dimension(120, 43));
        btnSave.setMinimumSize(new java.awt.Dimension(120, 43));
        btnSave.setPreferredSize(new java.awt.Dimension(120, 43));
        btnSave.addActionListener(this::btnSaveActionPerformed);
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 120, 43));

        btnUpdate.setBackground(new java.awt.Color(52, 125, 190));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(120, 43));
        btnUpdate.setMinimumSize(new java.awt.Dimension(120, 43));
        btnUpdate.setPreferredSize(new java.awt.Dimension(120, 43));
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 120, 43));

        btnDelete.setBackground(new java.awt.Color(217, 83, 79));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(120, 43));
        btnDelete.setMinimumSize(new java.awt.Dimension(120, 43));
        btnDelete.setPreferredSize(new java.awt.Dimension(120, 43));
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 610, 120, 43));

        btnClear.setBackground(new java.awt.Color(110, 120, 135));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.setMaximumSize(new java.awt.Dimension(120, 43));
        btnClear.setMinimumSize(new java.awt.Dimension(120, 43));
        btnClear.setPreferredSize(new java.awt.Dimension(120, 43));
        btnClear.addActionListener(this::btnClearActionPerformed);
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 120, 43));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(35, 40, 50));
        jLabel9.setText("Search Pet");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 70, -1));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(35, 40, 50));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 250, 45));

        btnSearch.setBackground(new java.awt.Color(245, 190, 55));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(35, 40, 50));
        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 87, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(35, 40, 50));
        jLabel10.setText("Pet List");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        tblPets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Species", "Breed", "Gender", "DOB", "Weight", "Owner", "Notes"
            }
        ));
        jScrollPane1.setViewportView(tblPets);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 400, 440));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(35, 40, 50));
        jLabel11.setText("Weight");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 50, 20));

        txtWeight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtWeight.setForeground(new java.awt.Color(35, 40, 50));
        jPanel1.add(txtWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 140, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(35, 40, 50));
        jLabel12.setText("Notes");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 60, 30));

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane2.setViewportView(txtNotes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 320, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
         int row = tblPets.getSelectedRow();
try {

            String petName =
                    txtPetName.getText().trim();

            String species =
                    cmbSpecies.getSelectedItem() == null
                            ? ""
                            : cmbSpecies.getSelectedItem().toString();

            String breed =
                    txtBreed.getText().trim();

            String gender =
                    cmbGender.getSelectedItem() == null
                            ? ""
                            : cmbGender.getSelectedItem().toString();

            String dobText =
                    txtDateOfBirth.getText().trim();

            String weightText =
                    txtWeight.getText().trim();

            String notes =
                    txtNotes.getText().trim();


            // ----------------------------------------------------
            // Get selected owner
            // ----------------------------------------------------

            int ownerIndex =
                    cmbOwner.getSelectedIndex();

            if (ownerIndex < 0
                    || ownerIndex >= customers.size()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an owner.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Customer selectedCustomer =
                    customers.get(ownerIndex);

            int customerId =
                    selectedCustomer.getCustomerId();


            // ----------------------------------------------------
            // Date of birth
            // ----------------------------------------------------

            LocalDate dateOfBirth = null;

            if (!dobText.isEmpty()) {

                try {

                    dateOfBirth =
                            LocalDate.parse(dobText);

                } catch (DateTimeParseException e) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid date format.\n"
                            + "Please use YYYY-MM-DD.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }


            // ----------------------------------------------------
            // Weight
            // ----------------------------------------------------

            Double weight = null;

            if (!weightText.isEmpty()) {

                try {

                    weight =
                            Double.parseDouble(weightText);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Weight must be a valid number.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }


            // ----------------------------------------------------
            // Create Pet object
            // ----------------------------------------------------

            Pet pet = new Pet(
                    0,
                    customerId,
                    petName,
                    species,
                    breed,
                    gender,
                    dateOfBirth,
                    weight,
                    notes
            );


            // ----------------------------------------------------
            // Save
            // ----------------------------------------------------

            boolean saved =
                    petController.addPet(pet);

            if (saved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet saved successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadPets();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet could not be saved.",
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
    

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int row =
                tblPets.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a pet from the table first.",
                    "No Pet Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (row >= currentPets.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid pet selection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            Pet selectedPet =
                    currentPets.get(row);

            int petId =
                    selectedPet.getPetId();


            String petName =
                    txtPetName.getText().trim();

            String species =
                    cmbSpecies.getSelectedItem() == null
                            ? ""
                            : cmbSpecies.getSelectedItem().toString();

            String breed =
                    txtBreed.getText().trim();

            String gender =
                    cmbGender.getSelectedItem() == null
                            ? ""
                            : cmbGender.getSelectedItem().toString();

            String dobText =
                    txtDateOfBirth.getText().trim();

            String weightText =
                    txtWeight.getText().trim();

            String notes =
                    txtNotes.getText().trim();


            // ----------------------------------------------------
            // Owner
            // ----------------------------------------------------

            int ownerIndex =
                    cmbOwner.getSelectedIndex();

            if (ownerIndex < 0
                    || ownerIndex >= customers.size()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an owner.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Customer selectedCustomer =
                    customers.get(ownerIndex);

            int customerId =
                    selectedCustomer.getCustomerId();


            // ----------------------------------------------------
            // Date
            // ----------------------------------------------------

            LocalDate dateOfBirth = null;

            if (!dobText.isEmpty()) {

                try {

                    dateOfBirth =
                            LocalDate.parse(dobText);

                } catch (DateTimeParseException e) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid date format.\n"
                            + "Please use YYYY-MM-DD.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }


            // ----------------------------------------------------
            // Weight
            // ----------------------------------------------------

            Double weight = null;

            if (!weightText.isEmpty()) {

                try {

                    weight =
                            Double.parseDouble(weightText);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Weight must be a valid number.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }


            // ----------------------------------------------------
            // Create updated Pet
            // ----------------------------------------------------

            Pet pet = new Pet(
                    petId,
                    customerId,
                    petName,
                    species,
                    breed,
                    gender,
                    dateOfBirth,
                    weight,
                    notes
            );


            // ----------------------------------------------------
            // Update
            // ----------------------------------------------------

            boolean updated =
                    petController.updatePet(pet);

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadPets();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet could not be updated.",
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
  
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String searchText =
                txtSearch.getText()
                        .trim()
                        .toLowerCase();

        if (searchText.isEmpty()) {

            loadPets();

            return;
        }

        try {

            ArrayList<Pet> pets =
                    new ArrayList<>(
                            petController.getAllPets()
                    );

            List<Pet> filtered =
                    pets.stream()

                            .filter(pet ->

                                    String.valueOf(
                                            pet.getPetId()
                                    )
                                    .contains(searchText)

                                    ||

                                    pet.getPetName()
                                            .toLowerCase()
                                            .contains(searchText)

                                    ||

                                    pet.getSpecies()
                                            .toLowerCase()
                                            .contains(searchText)

                                    ||

                                    (
                                        pet.getBreed() != null
                                        &&
                                        pet.getBreed()
                                                .toLowerCase()
                                                .contains(searchText)
                                    )

                                    ||

                                    getOwnerName(
                                            pet.getCustomerId()
                                    )
                                    .toLowerCase()
                                    .contains(searchText)

                            )

                            .collect(
                                    Collectors.toList()
                            );

            currentPets =
                    new ArrayList<>(filtered);

            displayPets(filtered);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching pets:\n"
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

            customers =
                    new ArrayList<>(
                            customerController.getAllCustomers()
                    );

            cmbOwner.removeAllItems();

            for (Customer customer : customers) {

                cmbOwner.addItem(
                        customer.getFullName()
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load owners:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // ============================================================
    // LOAD PETS
    // ============================================================

    private void loadPets() {

        try {

            currentPets =
                    new ArrayList<>(
                            petController.getAllPets()
                    );

            displayPets(currentPets);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load pets:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // ============================================================
    // DISPLAY PETS IN TABLE
    // ============================================================

    private void displayPets(
            List<Pet> pets
    ) {

        DefaultTableModel model =
                (DefaultTableModel) tblPets.getModel();

        model.setRowCount(0);

        for (Pet pet : pets) {

            model.addRow(
                    new Object[]{

                        pet.getPetId(),

                        pet.getPetName(),

                        pet.getSpecies(),

                        pet.getBreed() == null
                                ? ""
                                : pet.getBreed(),

                        pet.getGender(),

                        pet.getDateOfBirth() == null
                                ? ""
                                : pet.getDateOfBirth(),

                        pet.getWeight() == null
                                ? ""
                                : pet.getWeight(),

                        getOwnerName(
                                pet.getCustomerId()
                        ),

                        pet.getNotes() == null
                                ? ""
                                : pet.getNotes()
                    }
            );
        }
    }


    // ============================================================
    // GET OWNER NAME
    // ============================================================

    private String getOwnerName(
            int customerId
    ) {

        return customers.stream()

                .filter(customer ->
                        customer.getCustomerId()
                        == customerId
                )

                .map(Customer::getFullName)

                .findFirst()

                .orElse("Unknown");
    }


    // ============================================================
    // LOAD SELECTED PET
    // ============================================================

    private void loadSelectedPet() {

        int row =
                tblPets.getSelectedRow();

        if (row == -1) {
            return;
        }

        if (row >= currentPets.size()) {
            return;
        }

        Pet pet =
                currentPets.get(row);


        txtPetName.setText(
                pet.getPetName()
        );


        cmbSpecies.setSelectedItem(
                pet.getSpecies()
        );


        txtBreed.setText(
                pet.getBreed() == null
                        ? ""
                        : pet.getBreed()
        );


        cmbGender.setSelectedItem(
                pet.getGender()
        );


        txtDateOfBirth.setText(
                pet.getDateOfBirth() == null
                        ? ""
                        : pet.getDateOfBirth().toString()
        );


        txtWeight.setText(
                pet.getWeight() == null
                        ? ""
                        : pet.getWeight().toString()
        );


        txtNotes.setText(
                pet.getNotes() == null
                        ? ""
                        : pet.getNotes()
        );


        selectOwner(
                pet.getCustomerId()
        );
    }


    // ============================================================
    // SELECT OWNER
    // ============================================================

    private void selectOwner(
            int customerId
    ) {

        for (int i = 0;
                i < customers.size();
                i++) {

            Customer customer =
                    customers.get(i);

            if (customer.getCustomerId()
                    == customerId) {

                cmbOwner.setSelectedIndex(i);

                return;
            }
        }
    }


    // ============================================================
    // CLEAR FIELDS
    // ============================================================

    private void clearFields() {

        txtPetName.setText("");

        txtBreed.setText("");

        txtDateOfBirth.setText("");

        txtWeight.setText("");

        txtNotes.setText("");

        txtSearch.setText("");


        if (cmbSpecies.getItemCount() > 0) {

            cmbSpecies.setSelectedIndex(0);
        }


        if (cmbGender.getItemCount() > 0) {

            cmbGender.setSelectedIndex(0);
        }


        if (cmbOwner.getItemCount() > 0) {

            cmbOwner.setSelectedIndex(0);
        }


        tblPets.clearSelection();

        txtPetName.requestFocus();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

        try {

            for (
                    javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager
                            .getInstalledLookAndFeels()
                    ) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (
                ReflectiveOperationException
                | javax.swing.UnsupportedLookAndFeelException ex
                ) {

            logger.log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(
                () -> new PetForm().setVisible(true)
        );
    
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOwnerActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearFields();

        tblPets.clearSelection();

        txtSearch.setText("");

        loadPets();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
         int row =
                tblPets.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a pet from the table first.",
                    "No Pet Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (row >= currentPets.size()) {
            return;
        }

        try {

            Pet selectedPet =
                    currentPets.get(row);

            int petId =
                    selectedPet.getPetId();

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this pet?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }

            boolean deleted =
                    petController.deletePet(petId);

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadPets();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet could not be deleted.",
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
                    "Error deleting pet:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbOwner;
    private javax.swing.JComboBox<String> cmbSpecies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTable tblPets;
    private javax.swing.JTextField txtBreed;
    private javax.swing.JTextField txtDateOfBirth;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPetName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
