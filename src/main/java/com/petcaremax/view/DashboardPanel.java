package com.petcaremax.view;

import com.petcaremax.service.DashboardService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DashboardPanel extends JPanel {

    private final DashboardService dashboardService;

    private JLabel lblCustomerCount;
    private JLabel lblPetCount;
    private JLabel lblAppointmentCount;
    private JLabel lblRevenue;
    private JLabel lblLoading;

    private JTable appointmentTable;

    // ============================================================
    // PETCAREMAX THEME COLORS
    // ============================================================

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color CARD_WHITE =
            Color.WHITE;

    private static final Color TEXT =
            new Color(35, 40, 50);

    private static final Color MUTED =
            new Color(110, 120, 135);

    private static final Color BORDER =
            new Color(225, 228, 235);

    private static final Color GOLD =
            new Color(245, 190, 55);

    private static final Color GREEN =
            new Color(46, 155, 98);

    private static final Color RED =
            new Color(217, 83, 79);

    private static final Color AMBER =
            new Color(207, 145, 30);

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public DashboardPanel() {

        dashboardService =
                new DashboardService();

        initializeUI();

        loadDashboardData();
    }

    // ============================================================
    // INITIALIZE UI
    // ============================================================

    private void initializeUI() {

        setLayout(
                new BorderLayout(0, 18)
        );

        setBackground(BACKGROUND);

        setBorder(
                new EmptyBorder(
                        28,
                        30,
                        28,
                        30
                )
        );

        // ========================================================
        // PAGE HEADER
        // ========================================================

        JPanel headingPanel =
                new JPanel(
                        new BorderLayout()
                );

        headingPanel.setOpaque(false);

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
                new JLabel("Dashboard");

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        lblTitle.setForeground(TEXT);

        JLabel lblSubtitle =
                new JLabel(
                        "Welcome to PetCareMAX — an overview of your veterinary practice."
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblSubtitle.setForeground(MUTED);

        titlePanel.add(lblTitle);

        titlePanel.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        titlePanel.add(lblSubtitle);

        JLabel lblSystem =
                new JLabel("PETCAREMAX");

        lblSystem.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        lblSystem.setForeground(GOLD);

        lblSystem.setHorizontalAlignment(
                JLabel.RIGHT
        );

        headingPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headingPanel.add(
                lblSystem,
                BorderLayout.EAST
        );

        add(
                headingPanel,
                BorderLayout.NORTH
        );

        // ========================================================
        // CENTER PANEL
        // ========================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(0, 18)
                );

        centerPanel.setOpaque(false);

        // ========================================================
        // STATISTIC CARDS
        // ========================================================

        JPanel cardPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                16,
                                0
                        )
                );

        cardPanel.setOpaque(false);

        lblCustomerCount =
                createValueLabel();

        lblPetCount =
                createValueLabel();

        lblAppointmentCount =
                createValueLabel();

        lblRevenue =
                createValueLabel();

        // --------------------------------------------------------
        // CUSTOMER CARD
        // --------------------------------------------------------

        cardPanel.add(
                createStatCard(
                        "Customers",
                        "Registered customers",
                        lblCustomerCount,
                        new Color(
                                67,
                                112,
                                198
                        )
                )
        );

        // --------------------------------------------------------
        // PET CARD
        // --------------------------------------------------------

        cardPanel.add(
                createStatCard(
                        "Pets",
                        "Registered pets",
                        lblPetCount,
                        new Color(
                                92,
                                151,
                                112
                        )
                )
        );

        // --------------------------------------------------------
        // APPOINTMENT CARD
        // --------------------------------------------------------

        cardPanel.add(
                createStatCard(
                        "Appointments",
                        "Total appointments",
                        lblAppointmentCount,
                        GOLD
                )
        );

        // --------------------------------------------------------
        // REVENUE CARD
        // --------------------------------------------------------

        cardPanel.add(
                createStatCard(
                        "Revenue",
                        "Paid revenue",
                        lblRevenue,
                        new Color(
                                142,
                                93,
                                175
                        )
                )
        );

        centerPanel.add(
                cardPanel,
                BorderLayout.NORTH
        );

        // ========================================================
        // RECENT APPOINTMENTS CARD
        // ========================================================

        RoundedPanel recentPanel =
                new RoundedPanel(
                        16,
                        CARD_WHITE
                );

        recentPanel.setLayout(
                new BorderLayout(
                        0,
                        14
                )
        );

        recentPanel.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        14,
                        20
                )
        );

        // ========================================================
        // RECENT APPOINTMENTS HEADER
        // ========================================================

        JPanel recentHeader =
                new JPanel(
                        new BorderLayout()
                );

        recentHeader.setOpaque(false);

        JLabel lblRecent =
                new JLabel(
                        "Recent Appointments"
                );

        lblRecent.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        lblRecent.setForeground(TEXT);

        JLabel lblRecentHint =
                new JLabel(
                        "Latest scheduled activity"
                );

        lblRecentHint.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblRecentHint.setForeground(MUTED);

        lblRecentHint.setHorizontalAlignment(
                JLabel.RIGHT
        );

        recentHeader.add(
                lblRecent,
                BorderLayout.WEST
        );

        recentHeader.add(
                lblRecentHint,
                BorderLayout.EAST
        );

        recentPanel.add(
                recentHeader,
                BorderLayout.NORTH
        );

        // ========================================================
        // TABLE
        // ========================================================

        String[] columnNames = {
            "Customer",
            "Pet",
            "Veterinarian",
            "Date",
            "Status"
        };

        DefaultTableModel tableModel =
                new DefaultTableModel(
                        new Object[][]{
                            {
                                "-",
                                "-",
                                "-",
                                "-",
                                "-"
                            },
                            {
                                "-",
                                "-",
                                "-",
                                "-",
                                "-"
                            },
                            {
                                "-",
                                "-",
                                "-",
                                "-",
                                "-"
                            }
                        },
                        columnNames
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        appointmentTable =
                new JTable(tableModel);

        appointmentTable.setRowHeight(36);

        appointmentTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        appointmentTable.setForeground(TEXT);

        appointmentTable.setBackground(
                Color.WHITE
        );

        appointmentTable.setSelectionBackground(
                new Color(
                        255,
                        247,
                        222
                )
        );

        appointmentTable.setSelectionForeground(
                TEXT
        );

        appointmentTable.setGridColor(
                new Color(
                        238,
                        240,
                        244
                )
        );

        appointmentTable.setShowVerticalLines(
                false
        );

        appointmentTable.setShowHorizontalLines(
                true
        );

        appointmentTable.setIntercellSpacing(
                new java.awt.Dimension(
                        0,
                        1
                )
        );

        appointmentTable.setAutoCreateRowSorter(
                true
        );

        appointmentTable.setFillsViewportHeight(
                true
        );

        // ========================================================
        // TABLE HEADER
        // ========================================================

        appointmentTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        appointmentTable
                .getTableHeader()
                .setForeground(TEXT);

        appointmentTable
                .getTableHeader()
                .setBackground(
                        new Color(
                                248,
                                249,
                                251
                        )
                );

        appointmentTable
                .getTableHeader()
                .setPreferredSize(
                        new java.awt.Dimension(
                                0,
                                38
                        )
                );

        appointmentTable
                .getTableHeader()
                .setBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                BORDER
                        )
                );

        // ========================================================
        // TABLE CELL RENDERER
        // ========================================================

        appointmentTable.setDefaultRenderer(
                Object.class,
                new DashboardTableCellRenderer()
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        scrollPane
                .getViewport()
                .setBackground(
                        Color.WHITE
                );

        recentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ========================================================
        // LOADING MESSAGE
        // ========================================================

        lblLoading =
                new JLabel(
                        "Loading dashboard data..."
                );

        lblLoading.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblLoading.setForeground(MUTED);

        recentPanel.add(
                lblLoading,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                recentPanel,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // ============================================================
    // VALUE LABEL
    // ============================================================

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel("...");

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        label.setForeground(TEXT);

        return label;
    }

    // ============================================================
    // STAT CARD
    // ============================================================

    private JPanel createStatCard(
            String title,
            String description,
            JLabel valueLabel,
            Color accent
    ) {

        RoundedPanel card =
                new RoundedPanel(
                        16,
                        CARD_WHITE
                );

        card.setLayout(
                new BorderLayout(
                        0,
                        5
                )
        );

        card.setBorder(
                new EmptyBorder(
                        16,
                        18,
                        15,
                        18
                )
        );

        // --------------------------------------------------------
        // ACCENT BAR
        // --------------------------------------------------------

        JPanel accentBar =
                new JPanel();

        accentBar.setBackground(
                accent
        );

        accentBar.setPreferredSize(
                new java.awt.Dimension(
                        0,
                        4
                )
        );

        // --------------------------------------------------------
        // CARD TITLE
        // --------------------------------------------------------

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        titleLabel.setForeground(MUTED);

        JLabel dot =
                new JLabel("●");

        dot.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        dot.setForeground(accent);

        top.add(
                titleLabel,
                BorderLayout.WEST
        );

        top.add(
                dot,
                BorderLayout.EAST
        );

        // --------------------------------------------------------
        // DESCRIPTION
        // --------------------------------------------------------

        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        descriptionLabel.setForeground(
                MUTED
        );

        // --------------------------------------------------------
        // VALUE AREA
        // --------------------------------------------------------

        JPanel valueArea =
                new JPanel(
                        new BorderLayout()
                );

        valueArea.setOpaque(false);

        valueArea.add(
                valueLabel,
                BorderLayout.NORTH
        );

        valueArea.add(
                descriptionLabel,
                BorderLayout.SOUTH
        );

        // --------------------------------------------------------
        // ADD COMPONENTS
        // --------------------------------------------------------

        card.add(
                accentBar,
                BorderLayout.NORTH
        );

        card.add(
                top,
                BorderLayout.CENTER
        );

        card.add(
                valueArea,
                BorderLayout.SOUTH
        );

        return card;
    }

    // ============================================================
    // LOAD DASHBOARD DATA
    // ============================================================

    private void loadDashboardData() {

        SwingWorker<DashboardData, Void> worker =
                new SwingWorker<DashboardData, Void>() {

                    @Override
                    protected DashboardData doInBackground()
                            throws Exception {

                        // ------------------------------------------------
                        // LOAD DASHBOARD STATISTICS
                        // ------------------------------------------------

                        int customers =
                                dashboardService
                                        .getCustomerCount();

                        int pets =
                                dashboardService
                                        .getPetCount();

                        int appointments =
                                dashboardService
                                        .getAppointmentCount();

                        double revenue =
                                dashboardService
                                        .getRevenue();

                        // ------------------------------------------------
                        // LOAD RECENT APPOINTMENTS
                        // ------------------------------------------------

                        List<Object[]> recentAppointments =
                                dashboardService
                                        .getRecentAppointments();

                        return new DashboardData(
                                customers,
                                pets,
                                appointments,
                                revenue,
                                recentAppointments
                        );
                    }

                    @Override
                    protected void done() {

                        try {

                            DashboardData data =
                                    get();

                            // ============================================
                            // STATISTICS
                            // ============================================

                            lblCustomerCount.setText(
                                    String.valueOf(
                                            data.customers
                                    )
                            );

                            lblPetCount.setText(
                                    String.valueOf(
                                            data.pets
                                    )
                            );

                            lblAppointmentCount.setText(
                                    String.valueOf(
                                            data.appointments
                                    )
                            );

                            lblRevenue.setText(
                                    String.format(
                                            "Rs. %,.2f",
                                            data.revenue
                                    )
                            );

                            // ============================================
                            // RECENT APPOINTMENTS
                            // ============================================

                            DefaultTableModel model =
                                    (DefaultTableModel)
                                            appointmentTable
                                                    .getModel();

                            model.setRowCount(0);

                            if (
                                    data.recentAppointments != null
                                    && !data.recentAppointments.isEmpty()
                            ) {

                                for (
                                        Object[] appointment
                                        : data.recentAppointments
                                ) {

                                    model.addRow(
                                            appointment
                                    );
                                }

                            } else {

                                model.addRow(
                                        new Object[]{
                                            "-",
                                            "-",
                                            "-",
                                            "-",
                                            "-"
                                        }
                                );
                            }

                            lblLoading.setText(
                                    "Dashboard data loaded successfully."
                            );

                        } catch (Exception e) {

                            e.printStackTrace();

                            // ============================================
                            // ERROR VALUES
                            // ============================================

                            lblCustomerCount.setText(
                                    "0"
                            );

                            lblPetCount.setText(
                                    "0"
                            );

                            lblAppointmentCount.setText(
                                    "0"
                            );

                            lblRevenue.setText(
                                    "Rs. 0.00"
                            );

                            // ============================================
                            // ERROR TABLE
                            // ============================================

                            DefaultTableModel model =
                                    (DefaultTableModel)
                                            appointmentTable
                                                    .getModel();

                            model.setRowCount(0);

                            model.addRow(
                                    new Object[]{
                                        "-",
                                        "-",
                                        "-",
                                        "-",
                                        "-"
                                    }
                            );

                            lblLoading.setText(
                                    "Unable to load dashboard data."
                            );
                        }
                    }
                };

        worker.execute();
    }

    // ============================================================
    // DASHBOARD DATA CLASS
    // ============================================================

    private static class DashboardData {

        private final int customers;

        private final int pets;

        private final int appointments;

        private final double revenue;

        private final List<Object[]> recentAppointments;

        public DashboardData(
                int customers,
                int pets,
                int appointments,
                double revenue,
                List<Object[]> recentAppointments
        ) {

            this.customers =
                    customers;

            this.pets =
                    pets;

            this.appointments =
                    appointments;

            this.revenue =
                    revenue;

            this.recentAppointments =
                    recentAppointments;
        }
    }

    // ============================================================
    // ROUNDED PANEL
    // ============================================================

    private static class RoundedPanel
            extends JPanel {

        private final int radius;

        private final Color background;

        RoundedPanel(
                int radius,
                Color background
        ) {

            this.radius =
                    radius;

            this.background =
                    background;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                java.awt.Graphics g
        ) {

            java.awt.Graphics2D g2 =
                    (java.awt.Graphics2D)
                            g.create();

            g2.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(background);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    radius,
                    radius
            );

            g2.setColor(BORDER);

            g2.drawRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // ============================================================
    // TABLE CELL RENDERER
    // ============================================================

    private static class DashboardTableCellRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
        ) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            setBorder(
                    BorderFactory.createEmptyBorder(
                            0,
                            10,
                            0,
                            10
                    )
            );

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            if (!isSelected) {

                setBackground(
                        Color.WHITE
                );

                setForeground(
                        TEXT
                );
            }

            // --------------------------------------------------------
            // STATUS COLUMN
            // --------------------------------------------------------

            if (
                    column == 4
                    && value != null
            ) {

                String status =
                        value.toString()
                                .trim();

                if (
                        "Completed"
                                .equalsIgnoreCase(
                                        status
                                )
                ) {

                    setForeground(
                            GREEN
                    );

                    setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    12
                            )
                    );

                } else if (
                        "Cancelled"
                                .equalsIgnoreCase(
                                        status
                                )
                ) {

                    setForeground(
                            RED
                    );

                    setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    12
                            )
                    );

                } else if (
                        "Scheduled"
                                .equalsIgnoreCase(
                                        status
                                )
                ) {

                    setForeground(
                            AMBER
                    );

                    setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    12
                            )
                    );
                }
            }

            return component;
        }
    }
}