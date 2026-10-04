// This class handles one part of the PetCareMAX application.
package com.petcaremax.view;

import com.petcaremax.controller.LoginController;
import com.petcaremax.model.User;
import com.petcaremax.util.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class LoginForm extends JFrame {

    // COLORS

    private static final Color NAVY =
            new Color(24, 32, 44);

    private static final Color NAVY_LIGHT =
            new Color(30, 39, 54);

    private static final Color GOLD =
            new Color(245, 190, 55);

    private static final Color GOLD_DARK =
            new Color(220, 165, 35);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color TEXT =
            new Color(30, 39, 54);

    private static final Color MUTED =
            new Color(110, 120, 135);

    private static final Color RED =
            new Color(217, 83, 79);

    private static final Color GREEN =
            new Color(46, 155, 98);

    // CONTROLLERS

    private final LoginController loginController;

    // UI COMPONENTS

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private JButton btnLogin;

    private JCheckBox chkShowPassword;

    private JLabel lblStatus;
    private JLabel lblCapsLock;

    private JLabel lblBrand;
    private JLabel lblBrandSubtitle;

    private JLabel lblWelcome;
    private JLabel lblSubtitle;

    private JLabel lblUsername;
    private JLabel lblPassword;

    private JLabel lblSecurity;

    private JProgressBar progressBar;

    private char defaultEchoChar;

    // CONSTRUCTOR

    public LoginForm() {

        loginController = new LoginController();

        initializeWindow();
        buildUI();
        setupEvents();

        SwingUtilities.invokeLater(() -> {
            txtUsername.requestFocusInWindow();
        });
    }

    // WINDOW

    private void initializeWindow() {

        setTitle("PetCareMAX - Login");

        setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(950, 600)
        );

        setSize(
                1100,
                700
        );

        setLocationRelativeTo(null);

        setResizable(true);
    }

    // BUILD UI

    private void buildUI() {

        JPanel root =
                new GradientPanel(
                        NAVY,
                        NAVY_LIGHT
                );

        root.setLayout(
                new GridBagLayout()
        );

        root.setBorder(
                new EmptyBorder(
                        35,
                        35,
                        35,
                        35
                )
        );

        // --------------------------------------------------------
        // MAIN CARD
        // --------------------------------------------------------

        JPanel card =
                new RoundedPanel(
                        25,
                        WHITE
                );

        card.setPreferredSize(
                new Dimension(
                        850,
                        500
                )
        );

        card.setLayout(
                new BorderLayout()
        );

        // --------------------------------------------------------
        // LEFT BRAND AREA
        // --------------------------------------------------------

        JPanel brandPanel =
                new GradientPanel(
                        NAVY,
                        new Color(35, 48, 67)
                );

        brandPanel.setLayout(
                new GridBagLayout()
        );

        brandPanel.setPreferredSize(
                new Dimension(
                        360,
                        500
                )
        );

        JPanel brandContent =
                new JPanel();

        brandContent.setOpaque(false);

        brandContent.setLayout(
                new BoxLayout(
                        brandContent,
                        BoxLayout.Y_AXIS
                )
        );

        lblBrand =
                new JLabel(
                        "<html>PetCare<span style='color:#F5BE37'>MAX</span></html>"
                );

        lblBrand.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        38
                )
        );

        lblBrand.setForeground(
                WHITE
        );

        lblBrand.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        lblBrandSubtitle =
                new JLabel(
                        "<html>Pet Care &amp; Veterinary<br>"
                        + "Management System</html>"
                );

        lblBrandSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        lblBrandSubtitle.setForeground(
                new Color(
                        185,
                        195,
                        210
                )
        );

        lblBrandSubtitle.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        lblBrandSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel line =
                new JLabel(
                        "━━━━━━━━━━━━━━━━"
                );

        line.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        line.setForeground(
                GOLD
        );

        line.setBorder(
                new EmptyBorder(
                        35,
                        0,
                        25,
                        0
                )
        );

        line.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description =
                new JLabel(
                        "<html>Manage customers, pets,<br>"
                        + "appointments, treatments<br>"
                        + "and veterinary services<br>"
                        + "from one secure system.</html>"
                );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        description.setForeground(
                new Color(
                        205,
                        212,
                        222
                )
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandContent.add(
                lblBrand
        );

        brandContent.add(
                lblBrandSubtitle
        );

        brandContent.add(
                line
        );

        brandContent.add(
                description
        );

        GridBagConstraints brandGbc =
                new GridBagConstraints();

        brandGbc.gridx = 0;
        brandGbc.gridy = 0;

        brandGbc.anchor =
                GridBagConstraints.CENTER;

        brandPanel.add(
                brandContent,
                brandGbc
        );

        // --------------------------------------------------------
        // LOGIN AREA
        // --------------------------------------------------------

        JPanel loginPanel =
                new JPanel();

        loginPanel.setBackground(
                WHITE
        );

        loginPanel.setBorder(
                new EmptyBorder(
                        45,
                        55,
                        45,
                        55
                )
        );

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS
                )
        );

        lblWelcome =
                new JLabel(
                        "Welcome Back"
                );

        lblWelcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        lblWelcome.setForeground(
                TEXT
        );

        lblWelcome.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        lblSubtitle =
                new JLabel(
                        "Sign in to continue to PetCareMAX"
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lblSubtitle.setForeground(
                MUTED
        );

        lblSubtitle.setBorder(
                new EmptyBorder(
                        6,
                        0,
                        30,
                        0
                )
        );

        lblSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // --------------------------------------------------------
        // USERNAME
        // --------------------------------------------------------

        lblUsername =
                createLabel(
                        "Username"
                );

        txtUsername =
                new JTextField();

        styleTextField(
                txtUsername
        );

        // --------------------------------------------------------
        // PASSWORD
        // --------------------------------------------------------

        lblPassword =
                createLabel(
                        "Password"
                );

        txtPassword =
                new JPasswordField();

        styleTextField(
                txtPassword
        );

        defaultEchoChar =
                txtPassword.getEchoChar();

        // --------------------------------------------------------
        // SHOW PASSWORD
        // --------------------------------------------------------

        chkShowPassword =
                new JCheckBox(
                        "Show password"
                );

        chkShowPassword.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        chkShowPassword.setForeground(
                MUTED
        );

        chkShowPassword.setBackground(
                WHITE
        );

        chkShowPassword.setFocusPainted(
                false
        );

        chkShowPassword.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // --------------------------------------------------------
        // CAPS LOCK
        // --------------------------------------------------------

        lblCapsLock =
                new JLabel(
                        " "
                );

        lblCapsLock.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblCapsLock.setForeground(
                RED
        );

        lblCapsLock.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // --------------------------------------------------------
        // STATUS
        // --------------------------------------------------------

        lblStatus =
                new JLabel(
                        " "
                );

        lblStatus.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblStatus.setForeground(
                RED
        );

        lblStatus.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // --------------------------------------------------------
        // LOGIN BUTTON
        // --------------------------------------------------------

        btnLogin =
                new JButton(
                        "LOGIN"
                );

        btnLogin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        btnLogin.setForeground(
                TEXT
        );

        btnLogin.setBackground(
                GOLD
        );

        btnLogin.setFocusPainted(
                false
        );

        btnLogin.setBorderPainted(
                false
        );

        btnLogin.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        btnLogin.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        btnLogin.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        // --------------------------------------------------------
        // PROGRESS BAR
        // --------------------------------------------------------

        progressBar =
                new JProgressBar();

        progressBar.setIndeterminate(
                true
        );

        progressBar.setVisible(
                false
        );

        progressBar.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        progressBar.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        4
                )
        );

        // --------------------------------------------------------
        // SECURITY TEXT
        // --------------------------------------------------------

        lblSecurity =
                new JLabel(
                        "🔒 Secure enterprise access"
                );

        lblSecurity.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblSecurity.setForeground(
                MUTED
        );

        lblSecurity.setBorder(
                new EmptyBorder(
                        22,
                        0,
                        0,
                        0
                )
        );

        lblSecurity.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // --------------------------------------------------------
        // ADD COMPONENTS
        // --------------------------------------------------------

        loginPanel.add(
                lblWelcome
        );

        loginPanel.add(
                lblSubtitle
        );

        loginPanel.add(
                lblUsername
        );

        loginPanel.add(
                Box.createVerticalStrut(7)
        );

        loginPanel.add(
                txtUsername
        );

        loginPanel.add(
                Box.createVerticalStrut(18)
        );

        loginPanel.add(
                lblPassword
        );

        loginPanel.add(
                Box.createVerticalStrut(7)
        );

        loginPanel.add(
                txtPassword
        );

        loginPanel.add(
                chkShowPassword
        );

        loginPanel.add(
                lblCapsLock
        );

        loginPanel.add(
                Box.createVerticalStrut(12)
        );

        loginPanel.add(
                btnLogin
        );

        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        loginPanel.add(
                progressBar
        );

        loginPanel.add(
                lblStatus
        );

        loginPanel.add(
                lblSecurity
        );

        card.add(
                brandPanel,
                BorderLayout.WEST
        );

        card.add(
                loginPanel,
                BorderLayout.CENTER
        );

        root.add(
                card
        );

        setContentPane(
                root
        );
    }

    // EVENTS

    private void setupEvents() {

        btnLogin.addActionListener(
                e -> login()
        );

        chkShowPassword.addActionListener(
                e -> togglePasswordVisibility()
        );

        txtUsername.addActionListener(
                e -> txtPassword.requestFocusInWindow()
        );

        txtPassword.addActionListener(
                e -> login()
        );

        btnLogin.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (btnLogin.isEnabled()) {

                            btnLogin.setBackground(
                                    GOLD_DARK
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (btnLogin.isEnabled()) {

                            btnLogin.setBackground(
                                    GOLD
                            );
                        }
                    }
                }
        );

        txtPassword.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    @Override
                    public void keyPressed(
                            KeyEvent e
                    ) {

                        updateCapsLockStatus(
                                e
                        );
                    }

                    @Override
                    public void keyReleased(
                            KeyEvent e
                    ) {

                        updateCapsLockStatus(
                                e
                        );
                    }
                }
        );
    }

    // LOGIN

    private void login() {

        String username =
                txtUsername
                        .getText()
                        .trim();

        char[] password =
                txtPassword
                        .getPassword();

        clearStatus();

        if (username.isEmpty()) {

            showError(
                    "Please enter your username."
            );

            txtUsername.requestFocusInWindow();

            Arrays.fill(
                    password,
                    '\0'
            );

            return;
        }

        if (password.length == 0) {

            showError(
                    "Please enter your password."
            );

            txtPassword.requestFocusInWindow();

            return;
        }

        setLoading(
                true
        );

        SwingWorker<User, Void> worker =
                new SwingWorker<>() {

                    private Exception error;

                    @Override
                    protected User doInBackground() {

                        try {

                            String passwordString =
                                    new String(
                                            password
                                    );

                            return loginController.login(
                                    username,
                                    passwordString
                            );

                        } catch (Exception e) {

                            error = e;

                            return null;

                        } finally {

                            Arrays.fill(
                                    password,
                                    '\0'
                            );
                        }
                    }

                    @Override
                    protected void done() {

                        try {

                            User user =
                                    get();

                            if (user == null) {

                                showError(
                                        "Invalid username or password."
                                );

                                setLoading(
                                        false
                                );

                                txtPassword
                                        .setText("");

                                txtPassword
                                        .requestFocusInWindow();

                                return;
                            }

                            Session.start(
                                    user
                            );

                            txtPassword.setText(
                                    ""
                            );

                            MainFrame mainFrame =
                                    new MainFrame();

                            mainFrame.setVisible(
                                    true
                            );

                            dispose();

                        } catch (Exception e) {

                            showError(
                                    getLoginErrorMessage(
                                            e
                                    )
                            );

                            setLoading(
                                    false
                            );

                            txtPassword.setText("");

                            txtPassword
                                    .requestFocusInWindow();
                        }
                    }
                };

        worker.execute();
    }

    // LOADING STATE

    private void setLoading(
            boolean loading
    ) {

        btnLogin.setEnabled(
                !loading
        );

        txtUsername.setEnabled(
                !loading
        );

        txtPassword.setEnabled(
                !loading
        );

        chkShowPassword.setEnabled(
                !loading
        );

        progressBar.setVisible(
                loading
        );

        if (loading) {

            btnLogin.setText(
                    "SIGNING IN..."
            );

        } else {

            btnLogin.setText(
                    "LOGIN"
            );
        }

        revalidate();
        repaint();
    }

    // PASSWORD VISIBILITY

    private void togglePasswordVisibility() {

        if (chkShowPassword.isSelected()) {

            txtPassword.setEchoChar(
                    (char) 0
            );

        } else {

            txtPassword.setEchoChar(
                    defaultEchoChar
            );
        }
    }

    // CAPS LOCK

    private void updateCapsLockStatus(
            KeyEvent event
    ) {

        try {

            boolean caps =
                    Toolkit
                            .getDefaultToolkit()
                            .getLockingKeyState(
                                    KeyEvent.VK_CAPS_LOCK
                            );

            if (caps) {

                lblCapsLock.setText(
                        "⚠ Caps Lock is ON"
                );

            } else {

                lblCapsLock.setText(
                        " "
                );
            }

        } catch (Exception ignored) {

            lblCapsLock.setText(
                    " "
            );
        }
    }

    // ERROR HANDLING

    private void showError(
            String message
    ) {

        lblStatus.setForeground(
                RED
        );

        lblStatus.setText(
                message
        );
    }

    private void showSuccess(
            String message
    ) {

        lblStatus.setForeground(
                GREEN
        );

        lblStatus.setText(
                message
        );
    }

    private void clearStatus() {

        lblStatus.setText(
                " "
        );
    }

    private String getLoginErrorMessage(
            Exception e
    ) {

        Throwable cause =
                e;

        while (cause.getCause() != null) {

            cause =
                    cause.getCause();
        }

        if (cause instanceof IllegalArgumentException) {

            return cause.getMessage();
        }

        return "Unable to connect to the system.";
    }

    // LABEL

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // TEXT FIELD STYLE

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setForeground(
                TEXT
        );

        field.setBackground(
                WHITE
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        215,
                                        220,
                                        228
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );
    }

    // ROUNDED PANEL

    private static class RoundedPanel
            extends JPanel {

        private final int radius;
        private final Color color;

        public RoundedPanel(
                int radius,
                Color color
        ) {

            this.radius = radius;
            this.color = color;

            setOpaque(
                    false
            );
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    color
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(
                    g
            );
        }
    }

    // GRADIENT PANEL

    private static class GradientPanel
            extends JPanel {

        private final Color color1;
        private final Color color2;

        public GradientPanel(
                Color color1,
                Color color2
        ) {

            this.color1 = color1;
            this.color2 = color2;

            setOpaque(
                    false
            );
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
            );

            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            color1,
                            getWidth(),
                            getHeight(),
                            color2
                    );

            g2.setPaint(
                    gradient
            );

            g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );

            g2.dispose();

            super.paintComponent(
                    g
            );
        }
    }

    // MAIN

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );

                    } catch (Exception ignored) {
                    }

                    new LoginForm()
                            .setVisible(true);
                }
        );
    }
}
