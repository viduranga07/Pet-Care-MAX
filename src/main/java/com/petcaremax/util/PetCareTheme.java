// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.*;

/**
 * PetCareMAX centralized UI theme.
 *
 * Apply once to a JFrame:
 *     PetCareTheme.apply(this);
 *
 * The theme is intentionally centralized so all PetCareMAX forms
 * can share the same visual language and animations.
 */
public final class PetCareTheme {

    private PetCareTheme() {
    }

    // PETCAREMAX COLORS

    public static final Color NAVY       = new Color(15, 27, 43);
    public static final Color NAVY_LIGHT = new Color(30, 45, 65);

    public static final Color GOLD       = new Color(245, 190, 55);
    public static final Color GOLD_DARK  = new Color(210, 155, 25);

    public static final Color BLUE       = new Color(40, 125, 230);
    public static final Color BLUE_DARK  = new Color(25, 100, 200);

    public static final Color RED        = new Color(235, 70, 80);
    public static final Color RED_DARK   = new Color(205, 50, 60);

    public static final Color GREEN      = new Color(35, 175, 105);
    public static final Color GREEN_DARK = new Color(25, 145, 85);

    public static final Color LIGHT      = new Color(245, 247, 250);
    public static final Color WHITE      = Color.WHITE;
    public static final Color TEXT       = new Color(25, 35, 50);
    public static final Color MUTED      = new Color(100, 115, 135);
    public static final Color BORDER     = new Color(215, 222, 232);
    public static final Color FOCUS      = new Color(40, 125, 230, 150);

    private static final Font LABEL_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    private static final Font FORM_TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 32);

    private static final Font FIELD_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    private static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 13);

    private static final Font TABLE_FONT =
            new Font("Segoe UI", Font.PLAIN, 13);

    private static final int ANIMATION_STEP_MS = 15;
    private static final int ANIMATION_DURATION_MS = 140;

    // APPLY MAIN THEME

    public static void apply(JFrame frame) {
        if (frame == null) {
            return;
        }

        frame.getContentPane().setBackground(LIGHT);
        frame.setBackground(LIGHT);

        styleComponents(frame.getContentPane());
        styleTables(frame.getContentPane());
        styleButtons(frame.getContentPane());
        styleTextFields(frame.getContentPane());
        styleComboBoxes(frame.getContentPane());

        installSmoothFormEntrance(frame);
    }

    // GENERAL COMPONENTS

    private static void styleComponents(Container container) {

        for (Component component : container.getComponents()) {

            if (component instanceof JLabel label) {
                if (isFormTitle(label)) {
                    label.setFont(FORM_TITLE_FONT);
                } else {
                    label.setFont(LABEL_FONT);
                }

                label.setForeground(TEXT);
            }

            if (component instanceof JPanel panel) {
                panel.setBackground(WHITE);

                // Keep NetBeans layout intact. The border is only visual.
                panel.setBorder(
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                );
            }

            if (component instanceof JScrollPane scrollPane) {
                scrollPane.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(BORDER, 1),
                                BorderFactory.createEmptyBorder(0, 0, 0, 0)
                        )
                );
                scrollPane.getViewport().setBackground(WHITE);
                scrollPane.setBackground(WHITE);
            }

            if (component instanceof JTextArea textArea) {
                textArea.setFont(FIELD_FONT);
                textArea.setForeground(TEXT);
                textArea.setBackground(WHITE);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                textArea.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(BORDER, 1),
                                BorderFactory.createEmptyBorder(8, 10, 8, 10)
                        )
                );

                installFocusAnimation(textArea);
            }

            if (component instanceof Container child) {
                styleComponents(child);
            }
        }
    }

    /**
     * Identifies the main form topic/title without affecting
     * ordinary field and section labels.
     */
    private static boolean isFormTitle(JLabel label) {
        String title = label.getText();

        if (title == null) {
            return false;
        }

        title = title.trim();

        return title.endsWith("Management");
    }

    // BUTTONS

    private static void styleButtons(Container container) {

        for (Component component : container.getComponents()) {

            if (component instanceof JButton button) {
                styleButton(button);
            }

            if (component instanceof Container child) {
                styleButtons(child);
            }
        }
    }

    public static void styleButton(JButton button) {

        String text = button.getText() == null
                ? ""
                : button.getText().trim().toLowerCase();

        Color normalColor;
        Color hoverColor;
        Color pressedColor;

        if (text.contains("save") || text.contains("add")) {
            normalColor = GOLD;
            hoverColor = GOLD_DARK;
            pressedColor = new Color(185, 135, 15);

        } else if (text.contains("delete") || text.contains("remove")) {
            normalColor = RED;
            hoverColor = RED_DARK;
            pressedColor = new Color(175, 40, 50);

        } else if (text.contains("update") || text.contains("edit")) {
            normalColor = BLUE;
            hoverColor = BLUE_DARK;
            pressedColor = new Color(15, 80, 175);

        } else if (text.contains("search") || text.contains("find")) {
            normalColor = NAVY_LIGHT;
            hoverColor = NAVY;
            pressedColor = new Color(8, 18, 30);

        } else if (text.contains("clear") || text.contains("reset")) {
            normalColor = new Color(230, 235, 242);
            hoverColor = new Color(210, 218, 228);
            pressedColor = new Color(195, 205, 218);

        } else {
            normalColor = BLUE;
            hoverColor = BLUE_DARK;
            pressedColor = new Color(15, 80, 175);
        }

        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Explicitly control button text color.
        // Search and all colored action buttons use white text.
        // Clear/Reset keeps dark text for readability.
        boolean lightText = !text.contains("clear");
        button.setForeground(lightText ? WHITE : TEXT);

        RoundedButtonUI ui = new RoundedButtonUI(
                normalColor,
                hoverColor,
                pressedColor,
                lightText
        );

        button.setUI(ui);

        button.setBorder(
                BorderFactory.createEmptyBorder(9, 18, 9, 18)
        );

        installButtonAnimation(button, ui);
    }

    private static void installButtonAnimation(
            JButton button,
            RoundedButtonUI ui) {

        final Timer[] timer = {null};

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                animateButton(button, ui, true, timer);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                animateButton(button, ui, false, timer);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ui.setPressed(true);
                button.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ui.setPressed(false);
                button.repaint();
            }
        });
    }

    private static void animateButton(
            JButton button,
            RoundedButtonUI ui,
            boolean entering,
            Timer[] timerHolder) {

        if (timerHolder[0] != null && timerHolder[0].isRunning()) {
            timerHolder[0].stop();
        }

        int start = ui.getAnimationValue();
        int end = entering ? 100 : 0;
        int distance = end - start;

        if (distance == 0) {
            return;
        }

        int frames = Math.max(
                1,
                ANIMATION_DURATION_MS / ANIMATION_STEP_MS
        );

        final int[] frame = {0};

        timerHolder[0] = new Timer(
                ANIMATION_STEP_MS,
                e -> {
                    frame[0]++;

                    double progress =
                            Math.min(1.0, frame[0] / (double) frames);

                    // Smooth ease-out curve.
                    double eased =
                            1.0 - Math.pow(1.0 - progress, 3);

                    int value =
                            start + (int) Math.round(distance * eased);

                    ui.setAnimationValue(value);
                    button.repaint();

                    if (progress >= 1.0) {
                        ((Timer) e.getSource()).stop();
                    }
                }
        );

        timerHolder[0].start();
    }

    // TEXT FIELDS

    private static void styleTextFields(Container container) {

        for (Component component : container.getComponents()) {

            if (component instanceof JTextField field) {

                field.setFont(FIELD_FONT);
                field.setForeground(TEXT);
                field.setBackground(WHITE);
                field.setCaretColor(BLUE);
                field.setOpaque(true);

                // Clean premium input appearance.
                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        BORDER, 1
                                ),
                                BorderFactory.createEmptyBorder(
                                        9, 12, 9, 12
                                )
                        )
                );

                installFocusAnimation(field);
            }

            if (component instanceof Container child) {
                styleTextFields(child);
            }
        }
    }

    // FOCUS ANIMATION

    private static void installFocusAnimation(JComponent component) {

        component.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                animateFocus(component, true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                animateFocus(component, false);
            }
        });
    }

    private static void animateFocus(
            JComponent component,
            boolean focused) {

        final Color target =
                focused ? FOCUS : BORDER;

        final Border startBorder = component.getBorder();

        Timer timer = new Timer(ANIMATION_STEP_MS, null);
        final int[] frame = {0};

        int frames = Math.max(
                1,
                ANIMATION_DURATION_MS / ANIMATION_STEP_MS
        );

        timer.addActionListener(e -> {

            frame[0]++;

            double progress =
                    Math.min(1.0, frame[0] / (double) frames);

            double eased =
                    1.0 - Math.pow(1.0 - progress, 3);

            Color current;

            if (focused) {
                current = blend(BORDER, target, eased);
            } else {
                current = blend(target, BORDER, eased);
            }

            component.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    current,
                                    focused ? 2 : 1
                            ),
                            BorderFactory.createEmptyBorder(
                                    7, 9, 7, 9
                            )
                    )
            );

            component.repaint();

            if (progress >= 1.0) {
                timer.stop();
            }
        });

        timer.start();
    }

    // COMBO BOXES

    private static void styleComboBoxes(Container container) {

        for (Component component : container.getComponents()) {

            if (component instanceof JComboBox<?> combo) {

                combo.setFont(FIELD_FONT);
                combo.setBackground(WHITE);
                combo.setForeground(TEXT);
                combo.setOpaque(true);
                combo.setCursor(new Cursor(Cursor.HAND_CURSOR));

                combo.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        BORDER, 1
                                ),
                                BorderFactory.createEmptyBorder(
                                        5, 9, 5, 9
                                )
                        )
                );

                combo.setUI(new BasicComboBoxUI() {

                    @Override
                    protected JButton createArrowButton() {
                        JButton arrow = new JButton("▾");
                        arrow.setFont(
                                new Font("Segoe UI", Font.BOLD, 15)
                        );
                        arrow.setBorder(
                                BorderFactory.createEmptyBorder(
                                        0, 8, 0, 8
                                )
                        );
                        arrow.setFocusPainted(false);
                        arrow.setContentAreaFilled(false);
                        arrow.setOpaque(false);
                        arrow.setForeground(NAVY_LIGHT);
                        arrow.setCursor(
                                new Cursor(Cursor.HAND_CURSOR)
                        );
                        return arrow;
                    }
                });

                installFocusAnimation(combo);
            }

            if (component instanceof Container child) {
                styleComboBoxes(child);
            }
        }
    }

    // TABLE DESIGN

    private static void styleTables(Container container) {

        for (Component component : container.getComponents()) {

            if (component instanceof JTable table) {

                table.setFont(TABLE_FONT);
                table.setForeground(TEXT);
                table.setBackground(WHITE);
                table.setOpaque(true);

                table.setRowHeight(38);

                table.setSelectionBackground(
                        new Color(255, 239, 190)
                );

                table.setSelectionForeground(TEXT);

                table.setGridColor(
                        new Color(228, 233, 240)
                );

                table.setShowVerticalLines(false);
                table.setShowHorizontalLines(true);

                table.setIntercellSpacing(
                        new Dimension(0, 1)
                );

                table.setFillsViewportHeight(true);
                table.setAutoCreateRowSorter(true);
                table.setSelectionMode(
                        ListSelectionModel.SINGLE_SELECTION
                );

                JTableHeader header =
                        table.getTableHeader();

                header.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

                // Table column titles use black text for clear readability.
                header.setForeground(Color.BLACK);
                header.setBackground(NAVY);
                header.setOpaque(true);
                header.setBorder(
                        BorderFactory.createEmptyBorder(
                                0, 8, 0, 8
                        )
                );

                header.setPreferredSize(
                        new Dimension(0, 42)
                );

                header.setReorderingAllowed(false);

                table.setDefaultRenderer(
                        Object.class,
                        new DefaultTableCellRenderer() {

                            @Override
                            public Component
                            getTableCellRendererComponent(
                                    JTable table,
                                    Object value,
                                    boolean isSelected,
                                    boolean hasFocus,
                                    int row,
                                    int column) {

                                Component c =
                                        super
                                        .getTableCellRendererComponent(
                                                table,
                                                value,
                                                isSelected,
                                                hasFocus,
                                                row,
                                                column
                                        );

                                c.setFont(TABLE_FONT);

                                if (!isSelected) {
                                    c.setBackground(
                                            row % 2 == 0
                                                    ? WHITE
                                                    : new Color(
                                                            248,
                                                            250,
                                                            253
                                                    )
                                    );

                                    c.setForeground(TEXT);
                                }

                                setHorizontalAlignment(SwingConstants.LEFT);
                                setVerticalAlignment(SwingConstants.CENTER);

                                setBorder(
                                        BorderFactory
                                        .createEmptyBorder(
                                                0, 12, 0, 12
                                        )
                                );

                                return c;
                            }
                        }
                );
            }

            if (component instanceof Container child) {
                styleTables(child);
            }
        }
    }

    // FORM ENTRANCE ANIMATION

    public static void animateEntrance(
            final Window window) {

        if (window == null) {
            return;
        }

        if (!window.isDisplayable()) {
            return;
        }

        final Point target = window.getLocation();
        final Point start = new Point(
                target.x,
                target.y + 18
        );

        window.setLocation(start);

        final Timer timer = new Timer(ANIMATION_STEP_MS, null);
        final int[] frame = {0};

        int frames = 14;

        timer.addActionListener(e -> {

            frame[0]++;

            double progress =
                    Math.min(
                            1.0,
                            frame[0] / (double) frames
                    );

            double eased =
                    1.0 - Math.pow(1.0 - progress, 3);

            int y =
                    start.y
                    + (int) Math.round(
                            (target.y - start.y) * eased
                    );

            window.setLocation(
                    target.x,
                    y
            );

            if (progress >= 1.0) {
                window.setLocation(target);
                timer.stop();
            }
        });

        timer.start();
    }

    private static void installSmoothFormEntrance(
            JFrame frame) {

        // Delayed so NetBeans' generated layout and pack()
        // complete before the animation starts.
        SwingUtilities.invokeLater(() -> {

            if (!frame.isVisible()) {
                return;
            }

            animateEntrance(frame);
        });
    }

    // TOAST NOTIFICATION

    public static void showToast(
            Window owner,
            String message) {

        showToast(owner, message, BLUE);
    }

    public static void showSuccessToast(
            Window owner,
            String message) {

        showToast(owner, message, GREEN);
    }

    public static void showErrorToast(
            Window owner,
            String message) {

        showToast(owner, message, RED);
    }

    public static void showToast(
            Window owner,
            String message,
            Color accent) {

        if (owner == null || message == null) {
            return;
        }

        JWindow toast = new JWindow(owner);

        JPanel panel = new JPanel(
                new BorderLayout(10, 0)
        ) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(NAVY);
                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        16,
                        16
                );

                g2.setColor(accent);
                g2.fillRoundRect(
                        0,
                        0,
                        5,
                        getHeight(),
                        5,
                        5
                );

                g2.dispose();
            }
        };

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 16, 12, 18
                )
        );

        JLabel label = new JLabel(message);
        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );
        label.setForeground(WHITE);

        panel.add(label, BorderLayout.CENTER);

        toast.setContentPane(panel);
        toast.setAlwaysOnTop(true);
        toast.pack();

        Point ownerLocation = owner.getLocationOnScreen();

        int x =
                ownerLocation.x
                + owner.getWidth()
                - toast.getWidth()
                - 24;

        int y =
                ownerLocation.y
                + owner.getHeight()
                - toast.getHeight()
                - 24;

        toast.setLocation(x, y);

        toast.setOpacity(0.0f);
        toast.setVisible(true);

        animateToast(toast);
    }

    private static void animateToast(JWindow toast) {

        final Timer timer = new Timer(ANIMATION_STEP_MS, null);
        final int[] frame = {0};

        int frames = 18;

        timer.addActionListener(e -> {

            frame[0]++;

            double progress =
                    Math.min(
                            1.0,
                            frame[0] / (double) frames
                    );

            toast.setOpacity(
                    (float) progress
            );

            if (progress >= 1.0) {
                timer.stop();

                Timer hideTimer = new Timer(
                        2300,
                        event -> fadeOutToast(toast)
                );

                hideTimer.setRepeats(false);
                hideTimer.start();
            }
        });

        timer.start();
    }

    private static void fadeOutToast(JWindow toast) {

        final Timer timer = new Timer(ANIMATION_STEP_MS, null);
        final int[] frame = {0};

        int frames = 16;

        timer.addActionListener(e -> {

            frame[0]++;

            double progress =
                    Math.min(
                            1.0,
                            frame[0] / (double) frames
                    );

            toast.setOpacity(
                    (float) (1.0 - progress)
            );

            if (progress >= 1.0) {
                timer.stop();
                toast.dispose();
            }
        });

        timer.start();
    }

    // SHADOW PANEL

    public static JPanel createCardPanel() {

        return new ShadowPanel();
    }

    private static class ShadowPanel extends JPanel {

        ShadowPanel() {
            setOpaque(false);
            setBorder(
                    BorderFactory.createEmptyBorder(
                            12, 12, 12, 12
                    )
            );
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = getWidth();
            int h = getHeight();

            g2.setColor(
                    new Color(0, 0, 0, 18)
            );

            g2.fillRoundRect(
                    3,
                    5,
                    w - 6,
                    h - 7,
                    18,
                    18
            );

            g2.setColor(WHITE);

            g2.fillRoundRect(
                    0,
                    0,
                    w - 6,
                    h - 8,
                    18,
                    18
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // COLOR UTILITIES

    private static Color blend(
            Color first,
            Color second,
            double ratio) {

        ratio = Math.max(
                0,
                Math.min(1, ratio)
        );

        int r =
                (int) Math.round(
                        first.getRed()
                        + (second.getRed() - first.getRed())
                        * ratio
                );

        int g =
                (int) Math.round(
                        first.getGreen()
                        + (second.getGreen() - first.getGreen())
                        * ratio
                );

        int b =
                (int) Math.round(
                        first.getBlue()
                        + (second.getBlue() - first.getBlue())
                        * ratio
                );

        return new Color(r, g, b);
    }

    // ROUNDED BUTTON UI

    private static class RoundedButtonUI
            extends javax.swing.plaf.basic.BasicButtonUI {

        private final Color normalColor;
        private final Color hoverColor;
        private final Color pressedColor;

        private int animationValue = 0;
        private boolean pressed = false;

        RoundedButtonUI(
                Color normalColor,
                Color hoverColor,
                Color pressedColor,
                boolean lightText) {

            this.normalColor = normalColor;
            this.hoverColor = hoverColor;
            this.pressedColor = pressedColor;
        }

        int getAnimationValue() {
            return animationValue;
        }

        void setAnimationValue(int value) {
            animationValue = Math.max(0, Math.min(100, value));
        }

        void setPressed(boolean value) {
            pressed = value;
        }

        @Override
        public void paint(
                Graphics g,
                JComponent component) {

            AbstractButton button = (AbstractButton) component;

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int width = button.getWidth();
            int height = button.getHeight();

            Color color = pressed
                    ? pressedColor
                    : blend(
                            normalColor,
                            hoverColor,
                            animationValue / 100.0
                    );

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    width,
                    height,
                    12,
                    12
            );

            g2.dispose();

            // Let BasicButtonUI handle the normal text/icon painting.
            super.paint(g, component);
        }
    }
}
