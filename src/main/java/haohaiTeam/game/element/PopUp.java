package haohaiTeam.game.element;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class PopUp {

    private String message;
    private int duration;
    private JFrame frame;

    public PopUp(int x, int y, String message, int duration) {
        this.message = message;
        this.duration = duration;

        initComponents(x, y);
    }

    private void initComponents(int x, int y) {
        frame = new JFrame();
        frame.setUndecorated(true); // Remove window border
        frame.setTitle("Pop Up");
        frame.setSize(300, 100);

        // Calculate the center position of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        // Set the location based on the center position of the screen
        frame.setLocation(centerX, centerY);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.CENTER);

        // Start a timer to close the window after the specified duration
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                closePopUp();
            }
        }, duration);

        // Add a window listener to cancel the timer when the window is closed manually
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                timer.cancel();
            }
        });

        frame.setVisible(true);
    }

    private void closePopUp() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(false);
            frame.dispose(); // Close the window
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        });
    }
}

