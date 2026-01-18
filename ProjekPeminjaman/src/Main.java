import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView app = new MainView();
            app.setVisible(true);
        });
    }
}