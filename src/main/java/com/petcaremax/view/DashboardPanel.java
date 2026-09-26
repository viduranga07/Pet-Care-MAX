package com.petcaremax.view;

import com.petcaremax.service.DashboardService;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class DashboardPanel extends JPanel {

    private final DashboardService dashboardService;

    private JLabel lblCustomerCount;
    private JLabel lblPetCount;
    private JLabel lblAppointmentCount;
    private JLabel lblRevenue;
    private JLabel lblLoading;

    public DashboardPanel() {

        dashboardService = new DashboardService();

        initializeUI();

        loadDashboardData();
    }

    // ============================================================
    // INITIALIZE UI
    // ============================================================

    private void initializeUI() {

        setLayout(new BorderLayout());

        setBackground(
                new Color(245, 247, 250)
        );

        setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // ========================================================
        // TITLE
        // ========================================================

        JPanel headingPanel = new JPanel();

        headingPanel.setOpaque(false);

        headingPanel.setLayout(
                new javax.swing.BoxLayout(
                        headingPanel,
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

        lblTitle.setForeground(
                new Color(35, 40, 50)
        );

        JLabel lblSubtitle =
                new JLabel(
                        "Overview of your veterinary practice"
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblSubtitle.setForeground(
                new Color(110, 120, 135)
        );

        headingPanel.add(lblTitle);
        headingPanel.add(lblSubtitle);

        add(
                headingPanel,
                BorderLayout.NORTH
        );

        // ========================================================
        // CENTER
        // ========================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setOpaque(false);

        // ========================================================
        // STAT CARDS
        // ========================================================

        JPanel cardPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                20,
                                20
                        )
                );

        cardPanel.setOpaque(false);

        cardPanel.setBorder(
                new EmptyBorder(
                        25,
                        0,
                        25,
                        0
                )
        );

        lblCustomerCount =
                new JLabel("...");

        lblPetCount =
                new JLabel("...");

        lblAppointmentCount =
                new JLabel("...");

        lblRevenue =
                new JLabel("...");

        cardPanel.add(
                createStatCard(
                        "Customers",
                        lblCustomerCount
                )
        );

        cardPanel.add(
                createStatCard(
                        "Pets",
                        lblPetCount
                )
        );

        cardPanel.add(
                createStatCard(
                        "Appointments",
                        lblAppointmentCount
                )
        );

        cardPanel.add(
                createStatCard(
                        "Revenue",
                        lblRevenue
                )
        );

        centerPanel.add(
                cardPanel,
                BorderLayout.NORTH
        );

        // ========================================================
        // RECENT APPOINTMENTS
        // ========================================================

        JPanel recentPanel =
                new JPanel(
                        new BorderLayout()
                );

        recentPanel.setBackground(
                Color.WHITE
        );

        recentPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

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

        recentPanel.add(
                lblRecent,
                BorderLayout.NORTH
        );

        // --------------------------------------------------------
        // TABLE
        // --------------------------------------------------------

        String[] columnNames = {
            "Customer",
            "Pet",
            "Veterinarian",
            "Date",
            "Status"
        };

        Object[][] tableData = {
            {"-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-"}
        };

        JTable appointmentTable =
                new JTable(
                        tableData,
                        columnNames
                );

        appointmentTable.setRowHeight(32);

        appointmentTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        appointmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        recentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // --------------------------------------------------------
        // LOADING LABEL
        // --------------------------------------------------------

        lblLoading =
                new JLabel(
                        "Loading dashboard data..."
                );

        lblLoading.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblLoading.setForeground(
                new Color(
                        110,
                        120,
                        135
                )
        );

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
    // STAT CARD
    // ============================================================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        titleLabel.setForeground(
                new Color(
                        100,
                        110,
                        125
                )
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        valueLabel.setForeground(
                new Color(
                        30,
                        35,
                        45
                )
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
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

                        return new DashboardData(
                                customers,
                                pets,
                                appointments,
                                revenue
                        );
                    }

                    @Override
                    protected void done() {

                        try {

                            DashboardData data =
                                    get();

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

                            lblLoading.setText(
                                    "Dashboard data loaded successfully."
                            );

                        } catch (Exception e) {

                            lblCustomerCount.setText("0");

                            lblPetCount.setText("0");

                            lblAppointmentCount.setText("0");

                            lblRevenue.setText(
                                    "Rs. 0.00"
                            );

                            lblLoading.setText(
                                    "Unable to load dashboard data."
                            );

                            e.printStackTrace();
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

        public DashboardData(
                int customers,
                int pets,
                int appointments,
                double revenue
        ) {

            this.customers = customers;

            this.pets = pets;

            this.appointments = appointments;

            this.revenue = revenue;
        }
    }
}