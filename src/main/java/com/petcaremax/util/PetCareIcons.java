// This class handles one part of the PetCareMAX application.
package com.petcaremax.util;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * Central icon library for PetCareMAX.
 *
 * Requires:
 *   org.kordamp.ikonli:ikonli-swing:12.4.0
 *   org.kordamp.ikonli:ikonli-fontawesome5-pack:12.4.0
 *
 * Keep icon creation here so the whole application uses one visual language.
 */
public final class PetCareIcons {

    private PetCareIcons() {
    }

    // ICON SIZES

    public static final int NAV_SIZE = 19;
    public static final int BUTTON_SIZE = 16;
    public static final int FORM_SIZE = 20;
    public static final int HERO_SIZE = 30;

    // PETCAREMAX COLORS

    private static final Color NAVY = new Color(15, 27, 43);
    private static final Color GOLD = new Color(245, 190, 55);
    private static final Color BLUE = new Color(40, 125, 230);
    private static final Color RED = new Color(235, 70, 80);
    private static final Color GREEN = new Color(35, 175, 105);
    private static final Color WHITE = Color.WHITE;
    private static final Color MUTED = new Color(100, 115, 135);

    // MAIN NAVIGATION ICONS

    public static Icon dashboard() {
        return icon(FontAwesomeSolid.TACHOMETER_ALT, NAV_SIZE, GOLD);
    }

    public static Icon customers() {
        return icon(FontAwesomeSolid.USERS, NAV_SIZE, GOLD);
    }

    public static Icon pets() {
        return icon(FontAwesomeSolid.PAW, NAV_SIZE, GOLD);
    }

    public static Icon veterinarians() {
        return icon(FontAwesomeSolid.STETHOSCOPE, NAV_SIZE, GOLD);
    }

    public static Icon services() {
        return icon(FontAwesomeSolid.HOSPITAL, NAV_SIZE, GOLD);
    }

    public static Icon medications() {
        return icon(FontAwesomeSolid.PILLS, NAV_SIZE, GOLD);
    }

    public static Icon appointments() {
        return icon(FontAwesomeSolid.CALENDAR_ALT, NAV_SIZE, GOLD);
    }

    public static Icon treatments() {
        return icon(FontAwesomeSolid.HEARTBEAT, NAV_SIZE, GOLD);
    }

    public static Icon treatmentMedications() {
        return icon(
                FontAwesomeSolid.PRESCRIPTION_BOTTLE_ALT,
                NAV_SIZE,
                GOLD
        );
    }

    public static Icon payments() {
        return icon(FontAwesomeSolid.MONEY_BILL_WAVE, NAV_SIZE, GOLD);
    }

    public static Icon reports() {
        return icon(FontAwesomeSolid.CHART_BAR, NAV_SIZE, GOLD);
    }

    public static Icon settings() {
        return icon(FontAwesomeSolid.COG, NAV_SIZE, GOLD);
    }

    public static Icon logout() {
        return icon(FontAwesomeSolid.SIGN_OUT_ALT, NAV_SIZE, WHITE);
    }

    // VETERINARY / PET ICONS

    public static Icon dog() {
        return icon(FontAwesomeSolid.DOG, FORM_SIZE, BLUE);
    }

    public static Icon cat() {
        return icon(FontAwesomeSolid.CAT, FORM_SIZE, BLUE);
    }

    public static Icon paw() {
        return icon(FontAwesomeSolid.PAW, FORM_SIZE, GOLD);
    }

    public static Icon veterinarian() {
        return icon(FontAwesomeSolid.USER_MD, FORM_SIZE, BLUE);
    }

    public static Icon medical() {
        return icon(FontAwesomeSolid.FILE_MEDICAL, FORM_SIZE, BLUE);
    }

    public static Icon stethoscope() {
        return icon(FontAwesomeSolid.STETHOSCOPE, FORM_SIZE, BLUE);
    }

    public static Icon syringe() {
        return icon(FontAwesomeSolid.SYRINGE, FORM_SIZE, BLUE);
    }

    public static Icon medication() {
        return icon(FontAwesomeSolid.PILLS, FORM_SIZE, GREEN);
    }

    public static Icon prescription() {
        return icon(
                FontAwesomeSolid.PRESCRIPTION_BOTTLE_ALT,
                FORM_SIZE,
                GREEN
        );
    }

    public static Icon treatment() {
        return icon(FontAwesomeSolid.HEARTBEAT, FORM_SIZE, RED);
    }

    // ACTION ICONS

    public static Icon add() {
        return icon(FontAwesomeSolid.PLUS, BUTTON_SIZE, WHITE);
    }

    public static Icon save() {
        return icon(FontAwesomeSolid.SAVE, BUTTON_SIZE, WHITE);
    }

    public static Icon update() {
        return icon(FontAwesomeSolid.EDIT, BUTTON_SIZE, WHITE);
    }

    public static Icon delete() {
        return icon(FontAwesomeSolid.TRASH_ALT, BUTTON_SIZE, WHITE);
    }

    public static Icon search() {
        return icon(FontAwesomeSolid.SEARCH, BUTTON_SIZE, WHITE);
    }

    public static Icon clear() {
        return icon(FontAwesomeSolid.TIMES, BUTTON_SIZE, NAVY);
    }

    public static Icon refresh() {
        return icon(FontAwesomeSolid.SYNC_ALT, BUTTON_SIZE, WHITE);
    }

    public static Icon print() {
        return icon(FontAwesomeSolid.PRINT, BUTTON_SIZE, WHITE);
    }

    public static Icon back() {
        return icon(FontAwesomeSolid.ARROW_LEFT, BUTTON_SIZE, WHITE);
    }

    public static Icon next() {
        return icon(FontAwesomeSolid.ARROW_RIGHT, BUTTON_SIZE, WHITE);
    }

    // USER / CUSTOMER / RECORD ICONS

    public static Icon user() {
        return icon(FontAwesomeSolid.USER, FORM_SIZE, BLUE);
    }

    public static Icon userAdd() {
        return icon(FontAwesomeSolid.USER_PLUS, FORM_SIZE, GREEN);
    }

    public static Icon userEdit() {
        return icon(FontAwesomeSolid.USER_EDIT, FORM_SIZE, BLUE);
    }

    public static Icon userDelete() {
        return icon(FontAwesomeSolid.USER_TIMES, FORM_SIZE, RED);
    }

    public static Icon calendar() {
        return icon(FontAwesomeSolid.CALENDAR_ALT, FORM_SIZE, BLUE);
    }

    public static Icon payment() {
        return icon(FontAwesomeSolid.CREDIT_CARD, FORM_SIZE, GREEN);
    }

    public static Icon report() {
        return icon(FontAwesomeSolid.FILE_MEDICAL_ALT, FORM_SIZE, BLUE);
    }

    public static Icon home() {
        return icon(FontAwesomeSolid.HOME, FORM_SIZE, GOLD);
    }

    // LABEL / TITLE HELPERS

    /**
     * Adds a leading icon to a JLabel while preserving its existing text.
     */
    public static void decorateLabel(JLabel label, Icon icon) {
        if (label == null || icon == null) {
            return;
        }

        label.setIcon(icon);
        label.setIconTextGap(9);
        label.setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Adds an icon to a JButton while preserving its existing text.
     */
    public static void decorateButton(JButton button, Icon icon) {
        if (button == null || icon == null) {
            return;
        }

        button.setIcon(icon);
        button.setIconTextGap(8);
        button.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // ICON FACTORY

    /**
     * Creates a FontAwesome 5 icon.
     */
    public static Icon icon(
            FontAwesomeSolid ikon,
            int size,
            Color color) {

        return FontIcon.of(ikon, size, color);
    }

    // PUBLIC COLOR HELPERS

    public static Color navy() {
        return NAVY;
    }

    public static Color gold() {
        return GOLD;
    }

    public static Color blue() {
        return BLUE;
    }

    public static Color red() {
        return RED;
    }

    public static Color green() {
        return GREEN;
    }

    public static Color muted() {
        return MUTED;
    }
}
