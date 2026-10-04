// This class handles one part of the PetCareMAX application.
package com.petcaremax;

import com.petcaremax.view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PetCareMax {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
