package mtgdeckanalyzer;

import mtgdeckanalyzer.ui.MainUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainUI mainUI = new MainUI();
                mainUI.displayUI();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}
