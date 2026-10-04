// This class handles one part of the PetCareMAX application.
package com.petcaremax.view;

import com.petcaremax.controller.AddUserController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Bimsara
 */
public class AddUserForm extends javax.swing.JFrame {

    private AddUserController addUserController;

    /**
     * Creates new form AddUserForm
     */
    public AddUserForm() {
        initComponents();

        addUserController = new AddUserController(this);

        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();

        contentPanel = new javax.swing.JPanel();
        userCard = new javax.swing.JPanel();

        lblUserInformation = new javax.swing.JLabel();

        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();

        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();

        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        lblRole = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();

        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        buttonPanel = new javax.swing.JPanel();
        btnCreateUser = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PetCareMAX - Add User");

        rootPanel.setBackground(new java.awt.Color(245, 247, 250));

        headerPanel.setBackground(new java.awt.Color(245, 247, 250));

        titlePanel.setBackground(new java.awt.Color(245, 247, 250));

        lblTitle.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(new java.awt.Color(30, 39, 54));
        lblTitle.setText("Add User");

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new java.awt.Color(110, 120, 135));
        lblSubtitle.setText(
                "Create a new system user and assign access permissions."
        );

        javax.swing.GroupLayout titlePanelLayout =
                new javax.swing.GroupLayout(titlePanel);

        titlePanel.setLayout(titlePanelLayout);

        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(lblTitle)
            .addComponent(lblSubtitle)
        );

        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createSequentialGroup()
            .addComponent(
                lblTitle,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                36,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(4)
            .addComponent(
                lblSubtitle,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                22,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        lblBrand.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 16)
        );

        lblBrand.setForeground(
                new java.awt.Color(30, 39, 54)
        );

        lblBrand.setText("PETCAREMAX");

        javax.swing.GroupLayout headerPanelLayout =
                new javax.swing.GroupLayout(headerPanel);

        headerPanel.setLayout(headerPanelLayout);

        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createSequentialGroup()
            .addComponent(
                titlePanel,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
            .addGap(20)
            .addComponent(
                lblBrand,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                120,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.CENTER
            )
            .addComponent(
                titlePanel,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                65,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addComponent(
                lblBrand,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                25,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        // USER CARD

        userCard.setBackground(Color.WHITE);
        userCard.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(225, 228, 235)
                ),
                javax.swing.BorderFactory.createEmptyBorder(
                    25, 30, 30, 30
                )
            )
        );

        lblUserInformation.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 18)
        );

        lblUserInformation.setForeground(
                new java.awt.Color(30, 39, 54)
        );

        lblUserInformation.setText("User Information");

        lblFullName.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 13)
        );

        lblFullName.setForeground(
                new java.awt.Color(35, 40, 50)
        );

        lblFullName.setText("Full Name");

        txtFullName.setFont(
                new java.awt.Font("Segoe UI", Font.PLAIN, 13)
        );

        txtFullName.setPreferredSize(
                new java.awt.Dimension(280, 40)
        );

        lblUsername.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 13)
        );

        lblUsername.setForeground(
                new java.awt.Color(35, 40, 50)
        );

        lblUsername.setText("Username");

        txtUsername.setFont(
                new java.awt.Font("Segoe UI", Font.PLAIN, 13)
        );

        txtUsername.setPreferredSize(
                new java.awt.Dimension(280, 40)
        );

        lblPassword.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 13)
        );

        lblPassword.setForeground(
                new java.awt.Color(35, 40, 50)
        );

        lblPassword.setText("Password");

        txtPassword.setFont(
                new java.awt.Font("Segoe UI", Font.PLAIN, 13)
        );

        txtPassword.setPreferredSize(
                new java.awt.Dimension(280, 40)
        );

        lblRole.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 13)
        );

        lblRole.setForeground(
                new java.awt.Color(35, 40, 50)
        );

        lblRole.setText("Role");

        cmbRole.setFont(
                new java.awt.Font("Segoe UI", Font.PLAIN, 13)
        );

        cmbRole.setModel(
            new javax.swing.DefaultComboBoxModel<>(
                new String[]{
                    "Admin",
                    "Receptionist",
                    "Veterinarian"
                }
            )
        );

        cmbRole.setPreferredSize(
                new java.awt.Dimension(280, 40)
        );

        lblStatus.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 13)
        );

        lblStatus.setForeground(
                new java.awt.Color(35, 40, 50)
        );

        lblStatus.setText("Status");

        cmbStatus.setFont(
                new java.awt.Font("Segoe UI", Font.PLAIN, 13)
        );

        cmbStatus.setModel(
            new javax.swing.DefaultComboBoxModel<>(
                new String[]{
                    "Active",
                    "Inactive"
                }
            )
        );

        cmbStatus.setPreferredSize(
                new java.awt.Dimension(280, 40)
        );

        buttonPanel.setOpaque(false);

        btnCreateUser.setBackground(
                new java.awt.Color(245, 190, 55)
        );

        btnCreateUser.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 12)
        );

        btnCreateUser.setForeground(
                new java.awt.Color(30, 39, 54)
        );

        btnCreateUser.setText("Create User");

        btnCreateUser.setFocusPainted(false);
        btnCreateUser.setBorderPainted(false);

        btnClear.setBackground(
                new java.awt.Color(235, 238, 243)
        );

        btnClear.setFont(
                new java.awt.Font("Segoe UI", Font.BOLD, 12)
        );

        btnClear.setForeground(
                new java.awt.Color(30, 39, 54)
        );

        btnClear.setText("Clear");

        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);

        javax.swing.GroupLayout buttonPanelLayout =
                new javax.swing.GroupLayout(buttonPanel);

        buttonPanel.setLayout(buttonPanelLayout);

        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createSequentialGroup()
            .addComponent(
                btnCreateUser,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                140,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(12)
            .addComponent(
                btnClear,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                100,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.CENTER
            )
            .addComponent(
                btnCreateUser,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                42,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addComponent(
                btnClear,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                42,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        // CARD LAYOUT

        javax.swing.GroupLayout userCardLayout =
                new javax.swing.GroupLayout(userCard);

        userCard.setLayout(userCardLayout);

        userCardLayout.setHorizontalGroup(
            userCardLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(lblUserInformation)
            .addGroup(
                userCardLayout.createSequentialGroup()
                .addGroup(
                    userCardLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                    )
                    .addComponent(lblFullName)
                    .addComponent(
                        txtFullName,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        280,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addComponent(lblPassword)
                    .addComponent(
                        txtPassword,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        280,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addComponent(lblStatus)
                    .addComponent(
                        cmbStatus,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        280,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGap(35)
                .addGroup(
                    userCardLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                    )
                    .addComponent(lblUsername)
                    .addComponent(
                        txtUsername,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        280,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addComponent(lblRole)
                    .addComponent(
                        cmbRole,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        280,
                        javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addComponent(buttonPanel)
        );

        userCardLayout.setVerticalGroup(
            userCardLayout.createSequentialGroup()

            .addComponent(
                lblUserInformation,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                28,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )

            .addGap(25)

            .addGroup(
                userCardLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.BASELINE
                )
                .addComponent(lblFullName)
                .addComponent(lblUsername)
            )

            .addGap(7)

            .addGroup(
                userCardLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.BASELINE
                )
                .addComponent(
                    txtFullName,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    40,
                    javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                    txtUsername,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    40,
                    javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )

            .addGap(18)

            .addGroup(
                userCardLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.BASELINE
                )
                .addComponent(lblPassword)
                .addComponent(lblRole)
            )

            .addGap(7)

            .addGroup(
                userCardLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.BASELINE
                )
                .addComponent(
                    txtPassword,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    40,
                    javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                    cmbRole,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    40,
                    javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )

            .addGap(18)

            .addGroup(
                userCardLayout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.BASELINE
                )
                .addComponent(lblStatus)
            )

            .addGap(7)

            .addComponent(
                cmbStatus,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                40,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )

            .addGap(30)

            .addComponent(
                buttonPanel,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                42,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
        );

        // CONTENT

        contentPanel.setOpaque(false);

        javax.swing.GroupLayout contentPanelLayout =
                new javax.swing.GroupLayout(contentPanel);

        contentPanel.setLayout(contentPanelLayout);

        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(
                userCard,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
        );

        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(
                userCard,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
        );

        // ROOT

        javax.swing.GroupLayout rootPanelLayout =
                new javax.swing.GroupLayout(rootPanel);

        rootPanel.setLayout(rootPanelLayout);

        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addGroup(
                rootPanelLayout.createSequentialGroup()
                .addGap(30)
                .addGroup(
                    rootPanelLayout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                    )
                    .addComponent(
                        headerPanel,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE
                    )
                    .addComponent(
                        contentPanel,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE
                    )
                )
                .addGap(30)
            )
        );

        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createSequentialGroup()
            .addGap(25)
            .addComponent(
                headerPanel,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                65,
                javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(10)
            .addComponent(
                contentPanel,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
            .addGap(30)
        );

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(
                    getContentPane()
                );

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(
                rootPanel,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addComponent(
                rootPanel,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE
            )
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // GETTERS

    public JTextField getTxtFullName() {
        return txtFullName;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JComboBox<String> getCmbRole() {
        return cmbRole;
    }

    public JComboBox<String> getCmbStatus() {
        return cmbStatus;
    }

    public javax.swing.JButton getBtnCreateUser() {
        return btnCreateUser;
    }

    public javax.swing.JButton getBtnClear() {
        return btnClear;
    }

    // CLEAR FORM

    public void clearForm() {

        txtFullName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");

        cmbRole.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);

        txtFullName.requestFocus();
    }

    // MAIN

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {

            new AddUserForm().setVisible(true);

        });
    }

    // VARIABLES DECLARATION

    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreateUser;

    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JComboBox<String> cmbStatus;

    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel userCard;

    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserInformation;
    private javax.swing.JLabel lblUsername;

    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtUsername;

}
