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
/**
 * An abstract base class for transport modes that automatically move along roads.
 */
public abstract class AutoMoveTransport extends TransportMode {
    protected Color color;
    protected static final int MOVE_INTERVAL_MS = 100; // Move every 1 second

    // HeadingX and HeadingY: initially point to the lower right (due east-south),
    // indicating the current direction of parking.
    protected int headingX = 0;
    protected int headingY = 1;
    private boolean isMoving = true; // Indicates whether the transport is moving or not

    private Timer timer; // Define timer as a class field
    private boolean timerActive = false; // Flag to track if the timer is active
    private boolean isStationary = false;
    protected boolean atStation = false;
    public AutoMoveTransport(int x, int y, Color color, int speed, double carbonFootprint) {
        super(x, y);
        this.color = color;
        this.speed = speed;
        this.carbonFootprint = carbonFootprint;
        startMoving();
    }


    protected void startMoving() {
        if (!timerActive) {
            if (timer != null) {
                timer.cancel();
            }
            timer = new Timer();
            timerActive = true;
            isMoving = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (isMoving) {
                        moveOnRoad();
                    }
                }
            }, 0, MOVE_INTERVAL_MS);
        }
    }

    protected void pauseMoving(int waitTime) {
        isStationary = true;
        isMoving = false;
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        timerActive = false;


        Timer waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isStationary = false;
                startMoving();
            }
        }, waitTime);
    }


    protected void moveOnRoad() {
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements());

        if (!atStation && currentPositionIsStation(elements)) {
            Station station = (Station) elements.stream()
                    .filter(e -> e instanceof Station && e.X == this.X && e.Y == this.Y)
                    .findFirst().orElse(null);
            if (station != null && station.supportsTransport(this.getClass().getSimpleName())) {
                atStation = true;
                pauseMoving(station.getWaitTimeInSeconds());
            }
        } else {
            startMoving();
            // Try to move in the current direction first
            if (tryMoveInCurrentDirection(elements)) return;

            // If moving in the current direction is not possible, try to turn right
            if (tryTurn(elements, headingY, -headingX)) return;

            // If turning right is not successful, try to turn left
            if (tryTurn(elements, -headingY, headingX)) return;

            // If turning left is also not successful, try to turn around (180 degrees)
            tryTurn(elements, -headingX, -headingY);
        }
    }

    private boolean currentPositionIsStation(List<GameElement> elements) {
        return elements.stream().anyMatch(e -> e instanceof Station && e.X == this.X && e.Y == this.Y);
    }
    private boolean stationSupportsThisTransport(Station station) {
        return true;
    }

    public void stayAtStation(int duration) {
        System.out.println(this.getClass().getSimpleName() + " is starting to stay at the station for " + duration / 1000 + " seconds.");

        isMoving = false;
        isStationary = true;
        if (timer != null) {
            timer.cancel();
        }
        timerActive = false;


        Timer stayTimer = new Timer();
        stayTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isStationary = false;
                isMoving = true;
                System.out.println(this.getClass().getSimpleName() + " has finished staying at the station and is resuming movement.");
                startMoving();
            }
        }, duration);
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

    @Override
    public void draw(Graphics2D g2d) {
        // Update the heading
        // Subclasses can override this method as needed
        g2d.setColor(this.color);
        g2d.fillOval(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
}
