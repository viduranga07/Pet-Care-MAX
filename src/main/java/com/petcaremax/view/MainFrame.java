package com.petcaremax.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class MainFrame extends JFrame {

    // ============================================================
    // COLORS
    // ============================================================

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

    // ============================================================
    // MAIN COMPONENTS
    // ============================================================

    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel contentCards;

    private CardLayout cardLayout;

    // ============================================================
    // NAVIGATION BUTTONS
    // ============================================================

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

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

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

    // ============================================================
    // INITIALIZE UI
    // ============================================================

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

    // ============================================================
    // SIDEBAR
    // ============================================================

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

        // ========================================================
        // LOGO
        // ========================================================

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

        // ========================================================
        // NAVIGATION
        // ========================================================

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

        // ========================================================
        // BUTTON ACTIONS
        // ========================================================

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

        // ========================================================
        // LOGOUT
        // ========================================================

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

        btnLogout.addActionListener(
                e -> {

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            result ==
                            JOptionPane.YES_OPTION
                    ) {

                        dispose();
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

    // ============================================================
    // CONTENT AREA
    // ============================================================

    private void createContentArea() {

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                CONTENT_COLOR
        );

        // Header
        contentPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // CardLayout
        cardLayout =
                new CardLayout();

        contentCards =
                new JPanel(
                        cardLayout
                );

        contentCards.setBackground(
                CONTENT_COLOR
        );

        // ========================================================
        // DASHBOARD
        // ========================================================

        contentCards.add(
                new DashboardPanel(),
                "DASHBOARD"
        );

        // ========================================================
        // CUSTOMER
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new CustomerForm()
                ),
                "CUSTOMERS"
        );

        // ========================================================
        // PET
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new PetForm()
                ),
                "PETS"
        );

        // ========================================================
        // VETERINARIAN
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new VeterinarianForm()
                ),
                "VETERINARIANS"
        );

        // ========================================================
        // SERVICE
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new ServiceForm()
                ),
                "SERVICES"
        );

        // ========================================================
        // APPOINTMENT
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new AppointmentForm()
                ),
                "APPOINTMENTS"
        );

        // ========================================================
        // TREATMENT
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new TreatmentForm()
                ),
                "TREATMENTS"
        );

        // ========================================================
        // PAYMENT
        // ========================================================

        contentCards.add(
                createEmbeddedPage(
                        new PaymentForm()
                ),
                "PAYMENTS"
        );

        // ========================================================
        // REPORTS
        // ========================================================

        contentCards.add(
                createReportsPage(),
                "REPORTS"
        );

        contentPanel.add(
                contentCards,
                BorderLayout.CENTER
        );
    }

    // ============================================================
    // EMBED JFrame FORM INTO MAINFRAME
    // ============================================================

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

    // ============================================================
    // REPORTS PAGE
    // ============================================================

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

        JLabel title =
                new JLabel(
                        "Reports"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(
                        35,
                        40,
                        50
                )
        );

        page.add(
                title,
                BorderLayout.NORTH
        );

        JLabel message =
                new JLabel(
                        "Jasper Reports will be available here."
                );

        message.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        message.setForeground(
                new Color(
                        100,
                        110,
                        125
                )
        );

        page.add(
                message,
                BorderLayout.CENTER
        );

        return page;
    }

    // ============================================================
    // HEADER
    // ============================================================

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

        JLabel lblUser =
                new JLabel(
                        "Admin  ●"
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

    // ============================================================
    // MENU BUTTON
    // ============================================================

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

        // ========================================================
        // HOVER
        // ========================================================

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

    // ============================================================
    // SHOW PAGE
    // ============================================================

    private void showPage(
            String page
    ) {

        cardLayout.show(
                contentCards,
                page
        );

        updateSelectedButton(page);
    }

    // ============================================================
    // UPDATE SELECTED BUTTON
    // ============================================================

    private void updateSelectedButton(
            String page
    ) {

        resetButton(btnDashboard);
        resetButton(btnCustomers);
        resetButton(btnPets);
        resetButton(btnVeterinarians);
        resetButton(btnServices);
        resetButton(btnAppointments);
        resetButton(btnTreatments);
        resetButton(btnPayments);
        resetButton(btnReports);

        switch (page) {

            case "DASHBOARD":
                selectButton(btnDashboard);
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

            case "APPOINTMENTS":
                selectButton(btnAppointments);
                break;

            case "TREATMENTS":
                selectButton(btnTreatments);
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

    // ============================================================
    // RESET BUTTON
    // ============================================================

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

    // ============================================================
    // SELECT BUTTON
    // ============================================================

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
}