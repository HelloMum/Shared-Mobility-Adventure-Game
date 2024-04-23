package haohaiTeam.game.element.transport.onRoute.auto;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.transport.TransportMode;
import haohaiTeam.game.element.transport.onRoute.stationRoad.Road;
import haohaiTeam.game.element.transport.onRoute.stationRoad.Station;
import haohaiTeam.game.gui.GameWindow;
import haohaiTeam.game.input.CommandListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static haohaiTeam.game.gui.GameWindow.CELL_SIZE;
/**
 * An abstract base class for transport modes that automatically move along roads.
 */
public abstract class AutoMoveTransport extends TransportMode implements CommandListener {
    protected Color color;
    protected static final int MOVE_INTERVAL_MS = 100; // Move every 1 second

    // HeadingX and HeadingY: initially point to the lower right (due east-south),
    // indicating the current direction of parking.
    private int totalSteps = 0;

    protected int headingX = 0;
    protected int headingY = 1;
    protected boolean autoMove;
    protected boolean isAtStation;
    public Station onStation = null; // Reference to the transport
    private Station lastStation = null;


    public AutoMoveTransport(int x, int y) {
        super(x, y);
        startMoving();
        autoMove = true;
        isAtStation = false;
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
    public void toggleAutoStation() {
        if (lastStation != null) {
            lastStation.setDistanceNextStation(this.totalSteps);
        }
        lastStation=onStation;
        onStation=null;
        totalSteps = 0;
        autoMove = !autoMove;
        isAtStation = !isAtStation;
        if (!autoMove && this.getLinkedElement() != null) {
            linkedElement.unlinkElement();
            linkedElement.setBeingControlled(true);
            this.unlinkElement();
        }

    }

    protected void moveOnRoad() {
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements());

        // Try to move in the current direction first
        if (tryMoveInCurrentDirection(elements)) {
            return;
        }

        // If moving in the current direction is not possible, try to turn right
        if (tryTurn(elements, headingY, -headingX)) {
            return;
        }

        // If turning right is not successful, try to turn left
        if (tryTurn(elements, -headingY, headingX)) {
            return;
        }

    }

    private boolean tryMoveInCurrentDirection(List<GameElement> elements) {
        if (!autoMove) {
            return false; // Ignore movement if auto mode is disabled
        }
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
        if (!autoMove) {
            return false;
        }
        if (isRoadAtPosition(nextX, nextY, elements)) {
            updateHeading(dx, dy);
            logicalMove(dx, dy);
            totalSteps++;
            return true;
        }

        return false;
    }


    protected boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {
        // Check if autoMove is false, return false
        if (!autoMove) {
            return false;
        }

        boolean transportModeFound = false;
        boolean roadFound = false;

        // Iterate through the list of elements
        for (GameElement element : elements) {
            // Check if the element is a TransportMode (e.g., Bus, Luas, Taxi) or Player and matches the position
            if ((element instanceof TransportMode || element instanceof Player) && element.X == x && element.Y == y) {
                transportModeFound = true;
            }
            // Check if the element is a Road and matches the position
            if (element instanceof Road && element.X == x && element.Y == y) {
                roadFound = true;}

            // Check if the element is a Station and matches the position
            if (element instanceof Station && element.X == x && element.Y == y) {
                onStation= (Station) element;
                element.goingToBeWalkedOverBy(this);
            }
        }

        // Return true if road is found and no transport mode or station is found
        return roadFound && !transportModeFound;
    }

    protected void updateHeading(int dx, int dy) {
        headingX = dx;
        headingY = dy;
    }

    }
