// This class handles one part of the PetCareMAX application.
package com.petcaremax.view;

import com.petcaremax.controller.ReportsPanelController;
import com.petcaremax.util.Session;
import com.petcaremax.util.PetCareIcons;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class MainFrame extends JFrame {

    // COLORS

    private static final Color SIDEBAR_COLOR =
            new Color(24, 31, 43);

    private static final Color HEADER_COLOR =
            new Color(30, 39, 54);

    private static final Color GOLD_COLOR =
            new Color(245, 190, 55);

    private static final Color TEXT_COLOR =
            Color.WHITE;

    private static final Color MUTED_COLOR =
            new Color(170, 180, 195);

    private static final Color CONTENT_COLOR =
            new Color(245, 247, 250);

    private static final Color BUTTON_HOVER_COLOR =
            new Color(38, 48, 65);

    private static final Color BUTTON_SELECTED_COLOR =
            new Color(55, 69, 90);

    // MAIN COMPONENTS

    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel contentCards;

    private CardLayout cardLayout;

    // NAVIGATION BUTTONS

    private JButton btnDashboard;
    private JButton btnAddUser;
    private JButton btnCustomers;
    private JButton btnPets;
    private JButton btnVeterinarians;
    private JButton btnServices;
    private JButton btnMedications;
    private JButton btnAppointments;
    private JButton btnTreatments;
    private JButton btnTreatmentMedications;
    private JButton btnPayments;
    private JButton btnReports;
    private JButton btnLogout;

    // CONSTRUCTOR

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

    // INITIALIZE UI

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

    // SIDEBAR

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

        // LOGO

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
                new javax.swing.BoxLayout(
                        logoContainer,
                        javax.swing.BoxLayout.Y_AXIS
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

        // NAVIGATION

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
                new javax.swing.BoxLayout(
                        navigationPanel,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        btnDashboard =
                createMenuButton("Dashboard");
        btnDashboard.setIcon(
                PetCareIcons.dashboard()
        );

        btnAddUser =
                createMenuButton("Add User");
        btnAddUser.setIcon(
                PetCareIcons.userAdd()
        );

        btnCustomers =
                createMenuButton("Customers");
        btnCustomers.setIcon(
                PetCareIcons.customers()
        );

        btnPets =
                createMenuButton("Pets");
        btnPets.setIcon(
                PetCareIcons.pets()
        );

        btnVeterinarians =
                createMenuButton("Veterinarians");
        btnVeterinarians.setIcon(
                PetCareIcons.veterinarians()
        );

        btnServices =
                createMenuButton("Services");
        btnServices.setIcon(
                PetCareIcons.services()
        );

        btnMedications =
                createMenuButton("Medications");
        btnMedications.setIcon(
                PetCareIcons.medications()
        );

        btnAppointments =
                createMenuButton("Appointments");
        btnAppointments.setIcon(
                PetCareIcons.appointments()
        );

        btnTreatments =
                createMenuButton("Treatments");
        btnTreatments.setIcon(
                PetCareIcons.treatments()
        );

        btnTreatmentMedications =
                createMenuButton("Treatment Medications");
        btnTreatmentMedications.setIcon(
                PetCareIcons.treatmentMedications()
        );

        btnPayments =
                createMenuButton("Payments");
        btnPayments.setIcon(
                PetCareIcons.payments()
        );

        btnReports =
                createMenuButton("Reports");
        btnReports.setIcon(
                PetCareIcons.reports()
        );

        String role = "";

        if (Session.isLoggedIn()) {
            role =
                    Session.getCurrentUser().getRole();
        }

        // ADMIN

        if (role.equalsIgnoreCase("Admin")) {

            navigationPanel.add(btnDashboard);
            navigationPanel.add(btnAddUser);
            navigationPanel.add(btnCustomers);
            navigationPanel.add(btnPets);
            navigationPanel.add(btnVeterinarians);
            navigationPanel.add(btnServices);
            navigationPanel.add(btnMedications);
            navigationPanel.add(btnAppointments);
            navigationPanel.add(btnTreatments);
            navigationPanel.add(btnTreatmentMedications);
            navigationPanel.add(btnPayments);
            navigationPanel.add(btnReports);
        }

        // RECEPTIONIST

        else if (role.equalsIgnoreCase("Receptionist")) {

            navigationPanel.add(btnDashboard);
            navigationPanel.add(btnCustomers);
            navigationPanel.add(btnPets);
            navigationPanel.add(btnAppointments);
            navigationPanel.add(btnPayments);
        }

        // VETERINARIAN

        else if (role.equalsIgnoreCase("Veterinarian")) {

            navigationPanel.add(btnDashboard);
            navigationPanel.add(btnPets);
            navigationPanel.add(btnAppointments);
            navigationPanel.add(btnTreatments);
            navigationPanel.add(btnTreatmentMedications);
        }

        // BUTTON ACTIONS

        btnDashboard.addActionListener(
                e -> showPage("DASHBOARD")
        );

        btnAddUser.addActionListener(
                e -> showPage("ADD_USER")
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

        btnMedications.addActionListener(
                e -> showPage("MEDICATIONS")
        );

        btnAppointments.addActionListener(
                e -> showPage("APPOINTMENTS")
        );

        btnTreatments.addActionListener(
                e -> showPage("TREATMENTS")
        );

        btnTreatmentMedications.addActionListener(
                e -> showPage("TREATMENT_MEDICATIONS")
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

        // LOGOUT

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

        btnLogout.setIcon(
                PetCareIcons.logout()
        );

        btnLogout.addActionListener(
                e -> {

                    int result =
                            javax.swing.JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Logout",
                                    javax.swing.JOptionPane.YES_NO_OPTION
                            );

                    if (
                            result ==
                            javax.swing.JOptionPane.YES_OPTION
                    ) {

                        Session.logout();

                        dispose();

                        LoginForm loginForm =
                                new LoginForm();

                        loginForm.setVisible(true);
                    }
                }
        );

        logoutPanel.add(
                btnLogout,
                BorderLayout.CENTER
        );

        sidebarPanel.add(
                logoutPanel,
                BorderLayout.SOUTH
        );
    }

    // CONTENT AREA

    private void createContentArea() {

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                CONTENT_COLOR
        );

        // HEADER

        contentPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // CARD LAYOUT

        cardLayout =
                new CardLayout();

        contentCards =
                new JPanel(
                        cardLayout
                );

        contentCards.setBackground(
                CONTENT_COLOR
        );

        // DASHBOARD

        contentCards.add(
                new DashboardPanel(),
                "DASHBOARD"
        );

        // ADD USER

        contentCards.add(
                createEmbeddedPage(
                        new AddUserForm()
                ),
                "ADD_USER"
        );

        // CUSTOMER

        contentCards.add(
                createEmbeddedPage(
                        new CustomerForm()
                ),
                "CUSTOMERS"
        );

        // PET

        contentCards.add(
                createEmbeddedPage(
                        new PetForm()
                ),
                "PETS"
        );

        // VETERINARIAN

        contentCards.add(
                createEmbeddedPage(
                        new VeterinarianForm()
                ),
                "VETERINARIANS"
        );

        // SERVICE

        contentCards.add(
                createEmbeddedPage(
                        new ServiceForm()
                ),
                "SERVICES"
        );

        // MEDICATION

        contentCards.add(
                createEmbeddedPage(
                        new MedicationForm()
                ),
                "MEDICATIONS"
        );

        // APPOINTMENT

        contentCards.add(
                createEmbeddedPage(
                        new AppointmentForm()
                ),
                "APPOINTMENTS"
        );

        // TREATMENT

        contentCards.add(
                createEmbeddedPage(
                        new TreatmentForm()
                ),
                "TREATMENTS"
        );

        // TREATMENT MEDICATION

        contentCards.add(
                createEmbeddedPage(
                        new TreatmentMedicationForm()
                ),
                "TREATMENT_MEDICATIONS"
        );

        // PAYMENT

        contentCards.add(
                createEmbeddedPage(
                        new PaymentForm()
                ),
                "PAYMENTS"
        );

        // REPORTS

        contentCards.add(
                createReportsPage(),
                "REPORTS"
        );

        contentPanel.add(
                contentCards,
                BorderLayout.CENTER
        );
    }

    // EMBED JFrame FORM INTO MAINFRAME

    private JPanel createEmbeddedPage(
            JFrame form
    ) {

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(
                CONTENT_COLOR
        );

        /*
         * The existing forms are JFrame objects.
         * We reuse their existing content pane
         * inside the MainFrame CardLayout.
         */

        JPanel formContent =
                (JPanel) form.getContentPane();

        page.add(
                formContent,
                BorderLayout.CENTER
        );

        return page;
    }

    // REPORTS PAGE

    private JPanel createReportsPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(
                CONTENT_COLOR
        );

        page.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // PAGE HEADER

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);

        JPanel headingPanel =
                new JPanel();

        headingPanel.setOpaque(false);

        headingPanel.setLayout(
                new javax.swing.BoxLayout(
                        headingPanel,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Reports"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Generate and view reports for your veterinary practice"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                new Color(
                        110,
                        120,
                        135
                )
        );

        headingPanel.add(title);

        headingPanel.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        headingPanel.add(subtitle);

        JLabel reportIcon =
                new JLabel(
                        "REPORT CENTER"
                );

        reportIcon.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        reportIcon.setForeground(
                GOLD_COLOR
        );

        topPanel.add(
                headingPanel,
                BorderLayout.WEST
        );

        topPanel.add(
                reportIcon,
                BorderLayout.EAST
        );

        page.add(
                topPanel,
                BorderLayout.NORTH
        );

        // CENTER

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setOpaque(false);

        centerPanel.setBorder(
                new EmptyBorder(
                        30,
                        0,
                        0,
                        0
                )
        );

        // REPORT CARD

        JPanel reportCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        reportCard.setBackground(
                Color.WHITE
        );

        reportCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                28,
                                30,
                                28,
                                30
                        )
                )
        );

        // REPORT CARD HEADER

        JPanel reportHeader =
                new JPanel(
                        new BorderLayout()
                );

        reportHeader.setOpaque(false);

        JPanel reportTitlePanel =
                new JPanel();

        reportTitlePanel.setOpaque(false);

        reportTitlePanel.setLayout(
                new javax.swing.BoxLayout(
                        reportTitlePanel,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        JLabel reportTitle =
                new JLabel(
                        "Veterinary Reports"
                );

        reportTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        reportTitle.setForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        JLabel reportSubtitle =
                new JLabel(
                        "Generate detailed reports from the PetCareMAX database"
                );

        reportSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        reportSubtitle.setForeground(
                new Color(
                        110,
                        120,
                        135
                )
        );

        reportTitlePanel.add(
                reportTitle
        );

        reportTitlePanel.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        reportTitlePanel.add(
                reportSubtitle
        );

        JPanel indicator =
                new JPanel();

        indicator.setBackground(
                GOLD_COLOR
        );

        indicator.setPreferredSize(
                new Dimension(
                        5,
                        55
                )
        );

        reportHeader.add(
                indicator,
                BorderLayout.WEST
        );

        reportHeader.add(
                reportTitlePanel,
                BorderLayout.CENTER
        );

        reportCard.add(
                reportHeader,
                BorderLayout.NORTH
        );

        // REPORT INFORMATION

        JPanel informationPanel =
                new JPanel();

        informationPanel.setOpaque(false);

        informationPanel.setLayout(
                new javax.swing.BoxLayout(
                        informationPanel,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        JLabel description =
                new JLabel(
                        "<html>"
                        + "Select a report below to generate detailed "
                        + "information from the PetCareMAX database."
                        + "</html>"
                );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                new Color(
                        80,
                        90,
                        105
                )
        );

        informationPanel.add(
                description
        );

        informationPanel.add(
                javax.swing.Box.createVerticalStrut(22)
        );

        JLabel includedTitle =
                new JLabel(
                        "Available Reports"
                );

        includedTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        includedTitle.setForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        informationPanel.add(
                includedTitle
        );

        informationPanel.add(
                javax.swing.Box.createVerticalStrut(10)
        );

        informationPanel.add(
                createReportItem(
                        "Appointment information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Customer information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Pet information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Veterinarian information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Service information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Payment information"
                )
        );

        informationPanel.add(
                createReportItem(
                        "Payment amount and status"
                )
        );

        reportCard.add(
                informationPanel,
                BorderLayout.CENTER
        );

        // REPORT BUTTONS

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton btnAppointmentReport =
                new JButton(
                        "View Appointment Report"
                );

        JButton btnPaymentReport =
                new JButton(
                        "View Payment Report"
                );

        styleReportButton(
                btnAppointmentReport
        );

        styleReportButton(
                btnPaymentReport
        );

        buttonPanel.add(
                btnAppointmentReport
        );

        buttonPanel.add(
                btnPaymentReport
        );

        reportCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        /*
         * IMPORTANT:
         *
         * The MainFrame does NOT handle report actions.
         * ReportsPanelController is responsible for
         * handling both report buttons.
         */

        ReportsPanelController reportController =
                new ReportsPanelController(
                        btnAppointmentReport,
                        btnPaymentReport,
                        this
                );

        // Prevent unused variable warning in some IDEs.
        if (reportController == null) {
            return page;
        }

        centerPanel.add(
                reportCard,
                BorderLayout.NORTH
        );

        page.add(
                centerPanel,
                BorderLayout.CENTER
        );

        return page;
    }

    // REPORT BUTTON STYLE

    private void styleReportButton(
            JButton button
    ) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                new Color(
                        30,
                        35,
                        45
                )
        );

        button.setBackground(
                GOLD_COLOR
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        12,
                        24,
                        12,
                        24
                )
        );

        button.setCursor(
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        255,
                                        202,
                                        70
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                GOLD_COLOR
                        );
                    }
                }
        );
    }

    // REPORT ITEM

    private JLabel createReportItem(
            String text
    ) {

        JLabel item =
                new JLabel(
                        "  •  " + text
                );

        item.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        item.setForeground(
                new Color(
                        90,
                        100,
                        115
                )
        );

        item.setBorder(
                new EmptyBorder(
                        3,
                        0,
                        3,
                        0
                )
        );

        return item;
    }

    // HEADER

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setPreferredSize(
                new Dimension(
                        0,
                        75
                )
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
                new javax.swing.BoxLayout(
                        titlePanel,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        JLabel lblTitle =
                new JLabel(
                        "PetCareMAX"
                );

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

        // DYNAMIC LOGGED-IN USER

        String userDisplay =
                "User";

        if (Session.isLoggedIn()) {

            userDisplay =
                    Session.getCurrentUser().getFullName()
                    + "  •  "
                    + Session.getCurrentUser().getRole();
        }

        JLabel lblUser =
                new JLabel(
                        userDisplay
                );

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

    // MENU BUTTON

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        /*
         * BasicButtonUI prevents Windows Look & Feel
         * from changing our colors.
         */

        button.setUI(
                new BasicButtonUI()
        );

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

        button.setMinimumSize(
                new Dimension(
                        200,
                        45
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setHorizontalTextPosition(
                SwingConstants.RIGHT
        );

        button.setIconTextGap(12);

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

        button.setFocusable(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);

        // HOVER

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                BUTTON_HOVER_COLOR
                        );

                        button.setForeground(
                                TEXT_COLOR
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                SIDEBAR_COLOR
                        );

                        button.setForeground(
                                TEXT_COLOR
                        );
                    }
                }
        );

        return button;
    }

    // SHOW PAGE

    private void showPage(
            String page
    ) {

        cardLayout.show(
                contentCards,
                page
        );

        updateSelectedButton(page);
    }

    // UPDATE SELECTED BUTTON

    private void updateSelectedButton(
            String page
    ) {

        resetButton(btnDashboard);
        resetButton(btnAddUser);
        resetButton(btnCustomers);
        resetButton(btnPets);
        resetButton(btnVeterinarians);
        resetButton(btnServices);
        resetButton(btnMedications);
        resetButton(btnAppointments);
        resetButton(btnTreatments);
        resetButton(btnTreatmentMedications);
        resetButton(btnPayments);
        resetButton(btnReports);

        switch (page) {

            case "DASHBOARD":
                selectButton(btnDashboard);
                break;

            case "ADD_USER":
                selectButton(btnAddUser);
                break;

            case "CUSTOMERS":
                selectButton(btnCustomers);
                break;

            case "PETS":
                selectButton(btnPets);
                break;

            case "VETERINARIANS":
                selectButton(btnVeterinarians);
                break;

            case "SERVICES":
                selectButton(btnServices);
                break;

            case "MEDICATIONS":
                selectButton(btnMedications);
                break;

            case "APPOINTMENTS":
                selectButton(btnAppointments);
                break;

            case "TREATMENTS":
                selectButton(btnTreatments);
                break;

            case "TREATMENT_MEDICATIONS":
                selectButton(btnTreatmentMedications);
                break;

            case "PAYMENTS":
                selectButton(btnPayments);
                break;

            case "REPORTS":
                selectButton(btnReports);
                break;

            default:
                break;
        }
    }

    // RESET BUTTON

    private void resetButton(
            JButton button
    ) {

        if (button == null) {
            return;
        }

        button.setBackground(
                SIDEBAR_COLOR
        );

        button.setForeground(
                TEXT_COLOR
        );
    }

    // SELECT BUTTON

    private void selectButton(
            JButton button
    ) {

        if (button == null) {
            return;
        }

        button.setBackground(
                BUTTON_SELECTED_COLOR
        );

        button.setForeground(
                Color.WHITE
        );
    }

    private boolean hasRole(
            String role
    ) {

        if (!Session.isLoggedIn()) {
            return false;
        }

        return role.equalsIgnoreCase(
                Session.getCurrentUser().getRole()
        );
    }
}