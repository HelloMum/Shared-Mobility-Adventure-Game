package HaohaiTeam.Game.Element;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Debugger extends GameElement {
    private static final Color DEBUGGER_COLOR = Color.RED;
    private static final int MOVE_INTERVAL_MS = 1000; // Move every 1 second
    private final Random random;

    public Debugger(int x, int y) {
        super(x, y);
        random = new Random();
        startMoving();
    }

    private void startMoving() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveRandomly();
                System.out.println("Debugger position: (" + x + ", " + y + ")");
            }
        }, 0, MOVE_INTERVAL_MS);
    }

    private void moveRandomly() {
        // Generate random movement
        int dx = random.nextInt(3) - 1; // Random value between -1, 0, 1
        int dy = random.nextInt(3) - 1; // Random value between -1, 0, 1
        move(dx, dy);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(DEBUGGER_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
}
