package com.petcaremax.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private static final Color SIDEBAR_COLOR =
            new Color(24, 31, 43);

    private static final Color HEADER_COLOR =
            new Color(30, 39, 54);

    private static final Color GOLD_COLOR =
            new Color(245, 190, 55);

    private static final Color TEXT_COLOR =
            new Color(240, 243, 247);

    private static final Color MUTED_COLOR =
            new Color(170, 180, 195);

    private static final Color CONTENT_COLOR =
            new Color(245, 247, 250);

    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel contentCards;

    private CardLayout cardLayout;

    private JButton btnDashboard;
    private JButton btnCustomers;
    private JButton btnPets;
    private JButton btnVeterinarians;
    private JButton btnServices;
    private JButton btnAppointments;
    private JButton btnTreatments;
    private JButton btnPayments;
    private JButton btnReports;
    private JButton btnLogout;

    public MainFrame() {

        setTitle(
                "PetCareMAX - Pet Care & Veterinary Management System"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(1100, 700)
        );

        setSize(
                1280,
                800
        );

        setLocationRelativeTo(null);

        initializeUI();
    }

    private void initializeUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                CONTENT_COLOR
        );

        createSidebar();

        createContentArea();

        mainPanel.add(
                sidebarPanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        setContentPane(mainPanel);

        showPage("DASHBOARD");
    }

    private void createSidebar() {

        sidebarPanel =
                new JPanel(
                        new BorderLayout()
                );

        sidebarPanel.setPreferredSize(
                new Dimension(230, 0)
        );

        sidebarPanel.setBackground(
                SIDEBAR_COLOR
        );

        // Logo
        JPanel logoPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoPanel.setBackground(
                SIDEBAR_COLOR
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        25,
                        20,
                        20,
                        20
                )
        );

        JPanel logoText =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        logoText.setOpaque(false);

        JLabel lblPetCare =
                new JLabel("PetCare");

        lblPetCare.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        lblPetCare.setForeground(
                TEXT_COLOR
        );

        JLabel lblMax =
                new JLabel("MAX");

        lblMax.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        lblMax.setForeground(
                GOLD_COLOR
        );

        logoText.add(lblPetCare);
        logoText.add(lblMax);

        JLabel lblSubtitle =
                new JLabel(
                        "Pet Care Management"
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        lblSubtitle.setForeground(
                MUTED_COLOR
        );

        JPanel logoContainer =
                new JPanel();

        logoContainer.setOpaque(false);

        logoContainer.setLayout(
                new BoxLayout(
                        logoContainer,
                        BoxLayout.Y_AXIS
                )
        );

        logoContainer.add(logoText);
        logoContainer.add(lblSubtitle);

        logoPanel.add(
                logoContainer,
                BorderLayout.CENTER
        );

        sidebarPanel.add(
                logoPanel,
                BorderLayout.NORTH
        );

        // Navigation
        JPanel navigationPanel =
                new JPanel();

        navigationPanel.setOpaque(false);

        navigationPanel.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        navigationPanel.setLayout(
                new BoxLayout(
                        navigationPanel,
                        BoxLayout.Y_AXIS
                )
        );

        btnDashboard =
                createMenuButton("Dashboard");

        btnCustomers =
                createMenuButton("Customers");

        btnPets =
                createMenuButton("Pets");

        btnVeterinarians =
                createMenuButton("Veterinarians");

        btnServices =
                createMenuButton("Services");

        btnAppointments =
                createMenuButton("Appointments");

        btnTreatments =
                createMenuButton("Treatments");

        btnPayments =
                createMenuButton("Payments");

        btnReports =
                createMenuButton("Reports");

        navigationPanel.add(btnDashboard);
        navigationPanel.add(btnCustomers);
        navigationPanel.add(btnPets);
        navigationPanel.add(btnVeterinarians);
        navigationPanel.add(btnServices);
        navigationPanel.add(btnAppointments);
        navigationPanel.add(btnTreatments);
        navigationPanel.add(btnPayments);
        navigationPanel.add(btnReports);

        btnDashboard.addActionListener(
                e -> showPage("DASHBOARD")
        );

        btnCustomers.addActionListener(
                e -> showPage("CUSTOMERS")
        );

        btnPets.addActionListener(
                e -> showPage("PETS")
        );

        btnVeterinarians.addActionListener(
                e -> showPage("VETERINARIANS")
        );

        btnServices.addActionListener(
                e -> showPage("SERVICES")
        );

        btnAppointments.addActionListener(
                e -> showPage("APPOINTMENTS")
        );

        btnTreatments.addActionListener(
                e -> showPage("TREATMENTS")
        );

        btnPayments.addActionListener(
                e -> showPage("PAYMENTS")
        );

        btnReports.addActionListener(
                e -> showPage("REPORTS")
        );

        sidebarPanel.add(
                navigationPanel,
                BorderLayout.CENTER
        );

        // Logout
        JPanel logoutPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoutPanel.setOpaque(false);

        logoutPanel.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        20,
                        12
                )
        );

        btnLogout =
                createMenuButton("Logout");

        btnLogout.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                dispose();
            }
        });

        logoutPanel.add(
                btnLogout,
                BorderLayout.CENTER
        );

        sidebarPanel.add(
                logoutPanel,
                BorderLayout.SOUTH
        );
    }

    private void createContentArea() {

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                CONTENT_COLOR
        );

        contentPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        cardLayout =
                new CardLayout();

        contentCards =
                new JPanel(
                        cardLayout
                );

        contentCards.setBackground(
                CONTENT_COLOR
        );

        // Real Dashboard
        contentCards.add(
                new DashboardPanel(),
                "DASHBOARD"
        );

        // Temporary pages
        contentCards.add(
                createPlaceholderPanel(
                        "Customer Management"
                ),
                "CUSTOMERS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Pet Management"
                ),
                "PETS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Veterinarian Management"
                ),
                "VETERINARIANS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Service Management"
                ),
                "SERVICES"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Appointment Management"
                ),
                "APPOINTMENTS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Treatment Management"
                ),
                "TREATMENTS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Payment Management"
                ),
                "PAYMENTS"
        );

        contentCards.add(
                createPlaceholderPanel(
                        "Reports"
                ),
                "REPORTS"
        );

        contentPanel.add(
                contentCards,
                BorderLayout.CENTER
        );
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setPreferredSize(
                new Dimension(0, 75)
        );

        header.setBackground(
                HEADER_COLOR
        );

        header.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblTitle =
                new JLabel("PetCareMAX");

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        lblTitle.setForeground(
                TEXT_COLOR
        );

        JLabel lblSubtitle =
                new JLabel(
                        "Pet Care & Veterinary Management System"
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblSubtitle.setForeground(
                MUTED_COLOR
        );

        titlePanel.add(lblTitle);
        titlePanel.add(lblSubtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        JLabel lblUser =
                new JLabel("Admin  ●");

        lblUser.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        lblUser.setForeground(
                TEXT_COLOR
        );

        header.add(
                lblUser,
                BorderLayout.EAST
        );

        return header;
    }

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setPreferredSize(
                new Dimension(
                        200,
                        45
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setForeground(
                TEXT_COLOR
        );

        button.setBackground(
                SIDEBAR_COLOR
        );

        button.setBorder(
                new EmptyBorder(
                        0,
                        15,
                        0,
                        10
                )
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        return button;
    }

    private JPanel createPlaceholderPanel(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                CONTENT_COLOR
        );

        panel.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        JLabel label =
                new JLabel(title);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        label.setForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        panel.add(
                label,
                BorderLayout.NORTH
        );

        return panel;
    }

    private void showPage(
            String page
    ) {

        cardLayout.show(
                contentCards,
                page
        );
    }
}