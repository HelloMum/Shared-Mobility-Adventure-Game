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
    /**
     * Constructs an instance of AutoMoveTransport with specified position, color,
     * speed, and carbon footprint.
     *
     * @param x       The initial x-coordinate of the transport.
     * @param y       The initial y-coordinate of the transport.
     * @param color   The color used to draw the transport.
     * @param speed   The speed at which the transport moves.
     * @param carbonFootprint The carbon footprint associated with the transport.
     */
    public AutoMoveTransport(int x, int y, Color color, int speed, double carbonFootprint) {
        super(x, y);
        this.color = color;
        this.speed = speed;
        this.carbonFootprint = carbonFootprint;
        startMoving();
    }

    /**
     * Initiates the automatic movement of the transport by scheduling a timer task.
     * The task calls the {@link #moveOnRoad()} method periodically.
     */
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


    /**
     * Checks whether there is a road at the specified position within the given list of game elements.
     *
     * @param x         The x-coordinate to check for a road.
     * @param y         The y-coordinate to check for a road.
     * @param elements  The list of game elements containing potential roads.
     * @return          True if there is a road at the specified position, false otherwise.
     */
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


    /**
     * Updates the heading (direction) of the transport.
     * Subclasses may override this method to customize the heading update logic.
     *
     * @param dx  The change in x-direction.
     * @param dy  The change in y-direction.
     */
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
