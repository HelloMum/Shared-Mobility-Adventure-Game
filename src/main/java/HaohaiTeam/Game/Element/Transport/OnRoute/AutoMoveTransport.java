package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Input.CommandListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
/**
 * An abstract base class for transport modes that automatically move along roads.
 */
public abstract class AutoMoveTransport extends TransportMode implements CommandListener {
    protected Color color;
    protected static final int MOVE_INTERVAL_MS = 100; // Move every 1 second

    // HeadingX and HeadingY: initially point to the lower right (due east-south),
    // indicating the current direction of parking.
    protected int headingX = 0;
    protected int headingY = 1;

    public AutoMoveTransport(int x, int y) {
        super(x, y);
        startMoving();
    }

    protected void startMoving() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveOnRoad();
            }
        }, 0, MOVE_INTERVAL_MS);
    }

    protected void moveOnRoad() {
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements());

        // Try to move in the current direction first
        if (tryMoveInCurrentDirection(elements)) return;

        // If moving in the current direction is not possible, try to turn right
        if (tryTurn(elements, headingY, -headingX)) return;

        // If turning right is not successful, try to turn left
        if (tryTurn(elements, -headingY, headingX)) return;

        // If turning left is also not successful, try to turn around (180 degrees)
        tryTurn(elements, -headingX, -headingY);
    }

    private boolean tryMoveInCurrentDirection(List<GameElement> elements) {
        int nextX = X + headingX * CELL_SIZE;
        int nextY = Y + headingY * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            logicalMove(headingX, headingY);
            return true;
        }
        return false;
    }

    private boolean tryTurn(List<GameElement> elements, int dx, int dy) {
        int nextX = X + dx * CELL_SIZE;
        int nextY = Y + dy * CELL_SIZE;
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(dx, dy);
            logicalMove(dx, dy);
            return true;
        }
        return false;
    }


    protected boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {

        // Check if there is a road at the specified position
        // This method typically does not need to be overridden by subclasses

        for (GameElement element : elements) {
            if (element instanceof Road && element.X == x && element.Y == y) {
                return true;
            }
        }
        return false;
    }

    protected void updateHeading(int dx, int dy) {
        headingX = dx;
        headingY = dy;
    }

    }
