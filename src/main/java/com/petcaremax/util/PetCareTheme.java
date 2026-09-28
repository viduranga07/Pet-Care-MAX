package com.petcaremax.util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;

public class PetCareTheme {

    // =========================================================
    // PETCAREMAX COLORS
    // =========================================================

    public static final Color NAVY =
            new Color(15, 27, 43);

    public static final Color NAVY_LIGHT =
            new Color(30, 45, 65);

    public static final Color GOLD =
            new Color(245, 190, 55);

    public static final Color GOLD_DARK =
            new Color(210, 155, 25);

    public static final Color BLUE =
            new Color(40, 125, 230);

    public static final Color BLUE_DARK =
            new Color(25, 100, 200);

    public static final Color RED =
            new Color(235, 70, 80);

    public static final Color RED_DARK =
            new Color(205, 50, 60);

    public static final Color GREEN =
            new Color(35, 175, 105);

    public static final Color GREEN_DARK =
            new Color(25, 145, 85);

    public static final Color LIGHT =
            new Color(245, 247, 250);

    public static final Color WHITE =
            Color.WHITE;

    public static final Color TEXT =
            new Color(25, 35, 50);

    public static final Color MUTED =
            new Color(100, 115, 135);

    public static final Color BORDER =
            new Color(215, 222, 232);


    // =========================================================
    // APPLY MAIN THEME
    // =========================================================

    public static void apply(JFrame frame) {

        frame.getContentPane().setBackground(LIGHT);

        frame.setBackground(LIGHT);

        styleComponents(frame.getContentPane());

        styleTables(frame.getContentPane());

        styleButtons(frame.getContentPane());

        styleTextFields(frame.getContentPane());

        styleComboBoxes(frame.getContentPane());
    }


    // =========================================================
    // STYLE ALL COMPONENTS
    // =========================================================

    private static void styleComponents(Container container) {

        for (Component component : container.getComponents()) {

            // -------------------------
            // LABELS
            // -------------------------

            if (component instanceof JLabel label) {

                label.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                14
                        )
                );

                label.setForeground(TEXT);
            }


            // -------------------------
            // PANELS
            // -------------------------

            if (component instanceof JPanel panel) {

                panel.setBackground(WHITE);

                panel.setBorder(
                        BorderFactory.createEmptyBorder(
                                8,
                                8,
                                8,
                                8
                        )
                );
            }


            // -------------------------
            // SCROLL PANES
            // -------------------------

            if (component instanceof JScrollPane scrollPane) {

                scrollPane.setBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        )
                );

                scrollPane.getViewport()
                        .setBackground(WHITE);
            }


            // -------------------------
            // TEXT AREAS
            // -------------------------

            if (component instanceof JTextArea textArea) {

                textArea.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                14
                        )
                );

                textArea.setLineWrap(true);

                textArea.setWrapStyleWord(true);

                textArea.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        BORDER
                                ),
                                BorderFactory.createEmptyBorder(
                                        8,
                                        10,
                                        8,
                                        10
                                )
                        )
                );
            }


            // -------------------------
            // RECURSIVE STYLING
            // -------------------------

            if (component instanceof Container child) {

                styleComponents(child);
            }
        }
    }


    // =========================================================
    // BUTTONS
    // =========================================================

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

        String text =
                button.getText()
                        .toLowerCase();

        Color normalColor;
        Color hoverColor;

        // SAVE
        if (text.contains("save")
                || text.contains("add")) {

            normalColor = GOLD;
            hoverColor = GOLD_DARK;
        }

        // DELETE
        else if (text.contains("delete")
                || text.contains("remove")) {

            normalColor = RED;
            hoverColor = RED_DARK;
        }

        // UPDATE
        else if (text.contains("update")
                || text.contains("edit")) {

            normalColor = BLUE;
            hoverColor = BLUE_DARK;
        }

        // SEARCH
        else if (text.contains("search")
                || text.contains("find")) {

            normalColor = NAVY_LIGHT;
            hoverColor = NAVY;
        }

        // CLEAR
        else if (text.contains("clear")
                || text.contains("reset")) {

            normalColor = new Color(230, 235, 242);
            hoverColor = new Color(210, 218, 228);
        }

        // DEFAULT
        else {

            normalColor = BLUE;
            hoverColor = BLUE_DARK;
        }


        button.setBackground(normalColor);

        button.setForeground(
                text.contains("clear")
                        ? TEXT
                        : Color.WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );


        // HOVER EFFECT

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        button.setBackground(
                                hoverColor
                        );
                    }


                    @Override
                    public void mouseExited(MouseEvent e) {

                        button.setBackground(
                                normalColor
                        );
                    }
                }
        );
    }


    // =========================================================
    // TEXT FIELDS
    // =========================================================

    private static void styleTextFields(Container container) {

        for (Component component :
                container.getComponents()) {

            if (component instanceof JTextField field) {

                field.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                14
                        )
                );

                field.setForeground(TEXT);

                field.setBackground(WHITE);

                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        BORDER,
                                        1
                                ),
                                BorderFactory.createEmptyBorder(
                                        8,
                                        10,
                                        8,
                                        10
                                )
                        )
                );
            }

            if (component instanceof Container child) {

                styleTextFields(child);
            }
        }
    }


    // =========================================================
    // COMBO BOXES
    // =========================================================

    private static void styleComboBoxes(Container container) {

        for (Component component :
                container.getComponents()) {

            if (component instanceof JComboBox<?> combo) {

                combo.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                14
                        )
                );

                combo.setBackground(WHITE);

                combo.setForeground(TEXT);

                combo.setBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        )
                );
            }

            if (component instanceof Container child) {

                styleComboBoxes(child);
            }
        }
    }


    // =========================================================
    // TABLE DESIGN
    // =========================================================

    private static void styleTables(Container container) {

        for (Component component :
                container.getComponents()) {

            if (component instanceof JTable table) {

                table.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                13
                        )
                );

                table.setRowHeight(34);

                table.setSelectionBackground(
                        new Color(255, 239, 190)
                );

                table.setSelectionForeground(
                        TEXT
                );

                table.setGridColor(
                        new Color(225, 230, 238)
                );

                table.setShowVerticalLines(false);

                table.setIntercellSpacing(
                        new Dimension(0, 1)
                );


                // HEADER

                JTableHeader header =
                        table.getTableHeader();

                header.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

                header.setForeground(
                        Color.WHITE
                );

                header.setBackground(
                        NAVY
                );

                header.setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


                // ALTERNATING ROWS

                table.setDefaultRenderer(
                        Object.class,
                        new DefaultTableCellRenderer() {

                            @Override
                            public Component getTableCellRendererComponent(
                                    JTable table,
                                    Object value,
                                    boolean isSelected,
                                    boolean hasFocus,
                                    int row,
                                    int column
                            ) {

                                Component c =
                                        super.getTableCellRendererComponent(
                                                table,
                                                value,
                                                isSelected,
                                                hasFocus,
                                                row,
                                                column
                                        );

                                if (!isSelected) {

                                    c.setBackground(
                                            row % 2 == 0
                                                    ? Color.WHITE
                                                    : new Color(
                                                            248,
                                                            250,
                                                            253
                                                    )
                                    );

                                    c.setForeground(
                                            TEXT
                                    );
                                }

                                setBorder(
                                        BorderFactory.createEmptyBorder(
                                                0,
                                                8,
                                                0,
                                                8
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
}