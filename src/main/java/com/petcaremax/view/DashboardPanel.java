package com.petcaremax.view;

import com.petcaremax.service.DashboardService;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

public class DashboardPanel extends JPanel {

    private final DashboardService dashboardService;

    private JLabel lblCustomerCount;
    private JLabel lblPetCount;
    private JLabel lblAppointmentCount;
    private JLabel lblRevenue;
    private JLabel lblLoading;

    private JTable appointmentTable;

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

        /*
         * IMPORTANT:
         * Use DefaultTableModel explicitly.
         * This prevents ClassCastException when we update
         * the table after loading database data.
         */

        DefaultTableModel tableModel =
                new DefaultTableModel(
                        new Object[][]{
                                {"-", "-", "-", "-", "-"},
                                {"-", "-", "-", "-", "-"},
                                {"-", "-", "-", "-", "-"}
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

        appointmentTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        recentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ========================================================
        // LOADING LABEL
        // ========================================================

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

                        // ----------------------------------------
                        // Load dashboard statistics
                        // ----------------------------------------

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

                        // ----------------------------------------
                        // Load recent appointments
                        // ----------------------------------------

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

                            // ====================================
                            // STATISTICS
                            // ====================================

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

                            // ====================================
                            // RECENT APPOINTMENTS
                            // ====================================

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

                            // ====================================
                            // ERROR VALUES
                            // ====================================

                            lblCustomerCount.setText("0");

                            lblPetCount.setText("0");

                            lblAppointmentCount.setText("0");

                            lblRevenue.setText(
                                    "Rs. 0.00"
                            );

                            // ====================================
                            // ERROR TABLE
                            // ====================================

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
}