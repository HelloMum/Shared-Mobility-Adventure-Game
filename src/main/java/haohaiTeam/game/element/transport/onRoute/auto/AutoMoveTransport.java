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

public abstract class AutoMoveTransport extends TransportMode implements CommandListener {
    protected Color color;
    protected static final int MOVE_INTERVAL_MS = 100;

    private int totalSteps = 0;
    protected int headingX = 0;
    protected int headingY = 1;
    protected boolean autoMove;
    protected boolean isAtStation;
    private Station onStation = null;
    private Station lastStation = null;

    public AutoMoveTransport(int x, int y) {
        super(x, y);
        startMoving();
        autoMove = true;
        isAtStation = false;
        totalSteps = 0;
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
            totalSteps = 0;
        }
        lastStation = onStation;
        onStation = null;
        autoMove = !autoMove;
        isAtStation = !isAtStation;
        if (isAtStation && this.getLinkedElement() != null) {
            this.linkedElement.setBeingControlled(true);
            this.linkedElement.unlinkElement();
            this.unlinkElement();
        }
    }

    protected void moveOnRoad() {
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements());
        if (!autoMove) {
            return;
        }
        if (tryMoveInCurrentDirection(elements)) {
            return;
        }
        if (tryTurn(elements, headingY, -headingX)) {
            return;
        }
        if (tryTurn(elements, -headingY, headingX)) {
            return;
        }
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
            totalSteps++;
            return true;
        }
        return false;
    }

    protected boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {
        boolean transportModeFound = false;
        boolean roadFound = false;

        for (GameElement element : elements) {
            if ((element instanceof TransportMode || element instanceof Player) && element.X == x && element.Y == y) {
                transportModeFound = true;
            }
            if (element instanceof Road && element.X == x && element.Y == y) {
                roadFound = true;
            }
            if (element instanceof Station && element.X == x && element.Y == y) {
                onStation = (Station) element;
                element.goingToBeWalkedOverBy(this);
            }
        }
        return roadFound && !transportModeFound;
    }

    protected void updateHeading(int dx, int dy) {
        headingX = dx;
        headingY = dy;
    }
}
