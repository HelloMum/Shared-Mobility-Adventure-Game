package HaohaiTeam.Game.GUI;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Logic.ElementBehavior;
import HaohaiTeam.Game.Map.MapLoader;

import javax.swing.*;
import java.awt.*;
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
    private long lastUpdateTime; // Time of the last update

    ElementBehavior elementBehavior;
    public GameWindow() {
        elements = new ArrayList<>();
        this.gamePanel = new GamePanel();
        lastUpdateTime = System.currentTimeMillis(); // Initialize last update time
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
        startGameLoop();
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderMap(g); // Render the loaded map
            renderElements(g); // Render game elements
        }
    }

    private void renderMap(Graphics g) {

        // Rendering map grid lines (if needed)
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

    // This is just to keep the fps consistent so we can move at a certain speed or any other...
    private void startGameLoop() {
        new Thread(() -> {
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastUpdateTime;

                if (elapsedTime >= FRAME_TIME) {
                    updateGame();
                    lastUpdateTime += FRAME_TIME; // Update lastUpdateTime for next frame
                }
                try {
                    // Calculate time to sleep until next frame
                    long sleepTime = Math.max(0, FRAME_TIME - elapsedTime);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateGame() {
        // 更新游戏状态
        // 此处可以添加更多逻辑，比如更新元素状态、检查游戏规则等

        // 直接请求重绘GamePanel，无需重新获取JFrame
        gamePanel.repaint();
    }
}
