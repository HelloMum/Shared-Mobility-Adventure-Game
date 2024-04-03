package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Logic.currentTransport;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Bus extends TransportMode {
    private static final Color BUS_COLOR = Color.GREEN;
    private static final int MOVE_INTERVAL_MS = 200; // Move every 1 second

    // HeadingX and HeadingY: initially point to the lower right (due east-south),
    // indicating the current direction of parking.
    private int headingX = 0;
    private int headingY = 1;

    public Bus(int x, int y) {
        super(x, y);
        startMoving(); // Call startMoving() to schedule bus movement
        layer = 102; // Drawn over player and roads
    }

    @Override
    public currentTransport getTransportState() {
        return null;
    }

    private void startMoving() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveOnRoad();
            }
        }, 0, MOVE_INTERVAL_MS);
    }
    private void moveOnRoad() {
        // First copy the current scene's list of game elements and then try to move in the current direction of travel.
        // If there is no road ahead, try turning right, turning left, and then backing up.
        // If you still can't find the road, stay where you are.
        // Specific steering rules and road detection logic are implemented within the method.
        // Find the road
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements()); // Create a copy of the list

        // Try moving in the current heading direction first
        int nextX = x + headingX * CELL_SIZE;
        int nextY = y + headingY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            logicalMove(headingX, headingY);
            return;
        }

        // If no road found in the current direction, try turning right
        int rightTurnX = headingY;
        int rightTurnY = -headingX;
        nextX = x + rightTurnX * CELL_SIZE;
        nextY = y + rightTurnY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(rightTurnX, rightTurnY);
            logicalMove(rightTurnX, rightTurnY);
            return;
        }

        // If no road found by turning right, try turning left
        int leftTurnX = -headingY;
        int leftTurnY = headingX;
        nextX = x + leftTurnX * CELL_SIZE;
        nextY = y + leftTurnY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(leftTurnX, leftTurnY);
            logicalMove(leftTurnX, leftTurnY);
            return;
        }

        // If no road found by turning left, try turning back
        int backX = -headingX;
        int backY = -headingY;
        nextX = x + backX * CELL_SIZE;
        nextY = y + backY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(backX, backY);
            logicalMove(backX, backY);
            return;
        }

        // If no road found in any direction, stay in the same place
    }

    private boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {
        for (GameElement element : elements) {
            if (element instanceof Road && element.x == x && element.y == y) {
                return true;
            }
        }
        return false;
    }


    private void updateHeading(int dx, int dy) {
        // Update the heading direction based on the road
        headingX = dx;
        headingY = dy;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(BUS_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE); // Draw the bus
    }
}
