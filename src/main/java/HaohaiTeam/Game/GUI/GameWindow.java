package HaohaiTeam.Game.GUI;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Input.CommandListener;
import HaohaiTeam.Game.Logic.GameStatus;
import HaohaiTeam.Game.Logic.OverlayHUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameWindow {
    public static final int CELL_SIZE = 30; // Size of each cell in pixels
    private static final int GRID_WIDTH = 32; // Number of grid cells horizontally
    private static final int GRID_HEIGHT = 18; // Number of grid cells vertically
    public static final int FRAME_WIDTH = CELL_SIZE * GRID_WIDTH; // This is equal to 720p resolution
    public static final int FRAME_HEIGHT = CELL_SIZE * GRID_HEIGHT;
    private final GamePanel gamePanel;
    private static List<GameElement> elements = null;
    private OverlayHUD overlayHUD; // Reference to the HUD overlay
    private CommandListener commandListener = new GameStatus();

    private double scaleX = 1.0; // Scale factor for X-axis
    private double scaleY = 1.0; // Scale factor for Y-axis

    public GameWindow() {
        elements = new ArrayList<>();
        this.gamePanel = new GamePanel();
        this.overlayHUD = new OverlayHUD(new GameStatus()); // Initialize OverlayHUD with a new GameStatus object
    }

    // For logic checking, game elements can access this
    public static List<GameElement> getElements() {
        return elements;
    }

    public void addElement(GameElement element) {
        element.setCommandListener(commandListener);
        elements.add(element);
    }

    public void openWindow() {
        JFrame frame = new JFrame("Game Window");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); // Allow frame to be resized
        frame.setLocationRelativeTo(null); // Center frame on the screen
        frame.setFocusable(true); // Ensure frame is focusable
        frame.setContentPane(gamePanel); // Set custom GamePanel as content pane
        frame.setVisible(true);

        // Start listening to key events
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Forward the key event to the game window
                handleKeyEvent(e);
            }
        });

        // Add a component listener to listen for resize events
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Recalculate scale factors when the frame is resized
                scaleX = (double) frame.getContentPane().getWidth() / FRAME_WIDTH;
                scaleY = (double) frame.getContentPane().getHeight() / FRAME_HEIGHT;
            }
        });

        // Create a timer to update the screen every 0.1 seconds
        Timer timer = new Timer(100, e -> gamePanel.repaint());
        timer.start();
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // Set preferred size
            setFocusable(true); // Allow panel to receive focus for key events
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            // Apply scaling transformation
            g2d.scale(scaleX, scaleY);
            renderMap(g2d); // Render the loaded map
            renderElements(g2d); // Render game elements
            // Update OverlayHUD with the current GameStatus before rendering
            renderHUD(g2d); // Render the HUD overlay
            g2d.dispose();
        }
    }

    private void renderHUD(Graphics g) {
        if (overlayHUD != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            overlayHUD.update(new GameStatus()); // Update OverlayHUD with the current GameStatus
            overlayHUD.render(g2d); // Render the HUD overlay
            g2d.dispose();
        }
    }

    private void renderMap(Graphics2D g) {
        // Rendering map grid lines (for debugging)
        g.setColor(Color.GRAY);
        for (int x = 0; x <= FRAME_WIDTH; x += CELL_SIZE) {
            g.drawLine(x, 0, x, FRAME_HEIGHT);
        }
        for (int y = 0; y <= FRAME_HEIGHT; y += CELL_SIZE) {
            g.drawLine(0, y, FRAME_WIDTH, y);
        }
    }

    private void renderElements(Graphics2D g) {
        // Sort elements based on their layer
        elements.sort(Comparator.comparingInt(GameElement::getLayer));
        for (GameElement element : elements) {
            if (element.isVisible()) { // not draw if is not visible
                element.draw(g);
            }
        }
    }

    private void handleKeyEvent(KeyEvent e) {
        for (GameElement element : elements) {
            element.handleKeyEvent(e);
        }
    }
}