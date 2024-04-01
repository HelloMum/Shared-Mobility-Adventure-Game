package HaohaiTeam.Game.GUI;

import HaohaiTeam.Game.Element.GameElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    public static final int CELL_SIZE = 30; // Size of each cell in pixels
    private static final int GRID_WIDTH = 32; // Number of grid cells horizontally
    private static final int GRID_HEIGHT = 18; // Number of grid cells vertically
    private static final int FPS = 30; // Target Frames Per Second
    private static final long FRAME_TIME = 1000 / FPS; // Time per frame in milliseconds
    public static final int FRAME_WIDTH = CELL_SIZE * GRID_WIDTH; // This is equal to 720p resolution
    public static final int FRAME_HEIGHT = CELL_SIZE * GRID_HEIGHT;
    private final GamePanel gamePanel;
    private final List<GameElement> elements;

    public GameWindow() {
        elements = new ArrayList<>();
        this.gamePanel = new GamePanel();
    }

    public void addElement(GameElement element) {
        elements.add(element);
    }

    public void openWindow() {
        JFrame frame = new JFrame("Game Window");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Don't allow frame change
        frame.setLocationRelativeTo(null); // Center frame on the screen
        frame.setFocusable(true); // Ensure frame is focusable
        frame.setContentPane(gamePanel); // Set custom GamePanel as content pane
        frame.setVisible(true);

        // Start the game loop
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (true) {
            long startTime = System.currentTimeMillis();

            updateGame();
            gamePanel.repaint();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = FRAME_TIME - elapsedTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateGame() {
        // Update game state here
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setFocusable(true); // Allow panel to receive focus for key events
            requestFocusInWindow(); // Request focus when the panel is displayed
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    // Forward the key event to the game window
                    handleKeyEvent(e);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderMap(g); // Render the loaded map
            renderElements(g); // Render game elements
        }
    }

    private void renderMap(Graphics g) {
        // Rendering map grid lines (for debugging)
        g.setColor(Color.GRAY);
        for (int x = 0; x <= FRAME_WIDTH; x += CELL_SIZE) {
            g.drawLine(x, 0, x, FRAME_HEIGHT);
        }
        for (int y = 0; y <= FRAME_HEIGHT; y += CELL_SIZE) {
            g.drawLine(0, y, FRAME_WIDTH, y);
        }
    }

    private void renderElements(Graphics g) {
        for (GameElement element : elements) {
            element.updateList(elements);
            element.draw((Graphics2D) g); // Draw each game element
        }
    }

    private void handleKeyEvent(KeyEvent e) {
        for (GameElement element : elements) {
            element.handleKeyEvent(e);
        }
    }
}
