package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class AnyRoute extends TransportMode {
    private static final Color BUS_COLOR = Color.GREEN;
    private static final int MOVE_INTERVAL_MS = 200; // Move every 1 second
    private int headingX = 0;
    private int headingY = 1;

    public AnyRoute(int x, int y) {
        super(x, y);
    }

    private void startMoving() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveOnRoad();
                System.out.println("Bus position: (" + renderX + ", " + renderY + ")");
            }
        }, 0, MOVE_INTERVAL_MS);
    }
    private void moveOnRoad() {
        // Find the road
        java.util.List<GameElement> elements = new ArrayList<>(GameWindow.getElements()); // Create a copy of the list

        // Try moving in the current heading direction first
        int nextX = renderX + headingX * CELL_SIZE;
        int nextY = renderY + headingY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            logicalMove(headingX, headingY);
            return;
        }

        // If no road found in the current direction, try turning right
        int rightTurnX = headingY;
        int rightTurnY = -headingX;
        nextX = renderX + rightTurnX * CELL_SIZE;
        nextY = renderY + rightTurnY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(rightTurnX, rightTurnY);
            logicalMove(rightTurnX, rightTurnY);
            return;
        }

        // If no road found by turning right, try turning left
        int leftTurnX = -headingY;
        int leftTurnY = headingX;
        nextX = renderX + leftTurnX * CELL_SIZE;
        nextY = renderY + leftTurnY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(leftTurnX, leftTurnY);
            logicalMove(leftTurnX, leftTurnY);
            return;
        }

        // If no road found by turning left, try turning back
        int backX = -headingX;
        int backY = -headingY;
        nextX = renderX + backX * CELL_SIZE;
        nextY = renderY + backY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(backX, backY);
            logicalMove(backX, backY);
            return;
        }

        // If no road found in any direction, do nothing
    }

    private boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {
        for (GameElement element : elements) {
            if (element instanceof Road && element.renderX == x && element.renderY == y) {
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
}
