package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Input.CommandListener;
import HaohaiTeam.Game.Logic.TickListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class GameElement {

    /// The basics of the Game element
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<GameElement> elements;
    public int x; // These x coordinates uses the pixel position for drawing
    public int y; // These y coordinates uses the pixel position for drawing
    public int layer; // This refers if the that is going to be drawn, higher number higher preference.
    public boolean walkable; // This refers if the element can be walked though
    public int speed; // This refers to the maximum speed of the element
    public GameElement linkedElement; // This to link two element on the same cell so one follows the other, it also overdrives the control of the other
    public boolean beingControlled = false; // Flag to enable key control by keys
    public boolean isVisible; // hide the visibility
    public boolean playerOnTop;
    public Direction direction; // Direction the element is facing
    CommandListener commandListener;


    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.walkable = false;
        this.layer = 99; // Default layer
        this.isVisible = true;
        this.playerOnTop =false;
        this.direction = Direction.DOWN; // Default direction
        this.commandListener = null; // we need to start this later
    }
    public void setCommandListener(CommandListener commandListener) { // set up a command listen
        this.commandListener = commandListener;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    // direction that the element is facing
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    /// Moving and locating elements
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //These two can be used to locate the x and y of the element in the logical cell grid
    // Should be the only one using convert cell_size
    public int convertToLogicalPos(int unitToConvert) {
        return unitToConvert * CELL_SIZE;
    }

    public int getLogicalPosX() {
        return convertToLogicalPos(x);
    }

    public int getLogicalPosY() {
        return convertToLogicalPos(y);
    }

    //These implement a return for the real grid position, for the logic implementation
    public void setToLogicalPosX(int posX) {
        x += convertToLogicalPos(posX);
    }

    public void setToLogicalPosY(int posY) {
        y += convertToLogicalPos(posY);
    }

    // Legacy move, this is to move the elements using pixels
    public void logicalMove(int dx, int dy) {
        // Update the actual position of the object to the calculated next position.
        if (checkCollision(dx, dy)) {
            setToLogicalPosX(dx);
            setToLogicalPosY(dy);
            if (this.linkedElement != null) {
                linkedElement.setToLogicalPosX(dx);
                linkedElement.setToLogicalPosY(dy);
            }
        }

    }
    public void moveLogical(int dx, int dy) {
        // Update the actual position of the object based on logic
        if (checkCollision(dx, dy)) {
            setToLogicalPosX(dx);
            setToLogicalPosY(dy);
        }
    }
    /// Check for the direction of the element
    // Getter method for direction
    public Direction getDirection() {
        return direction;
    }

    // Setter method for direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    private boolean isWithinBounds(int nextX, int nextY) {
        // Check if the next position is within the game window bounds
        return (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT);
    }

    // Collision checker and element parsing to create reactions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected boolean checkCollision(int nextX, int nextY) {
        List<GameElement> elements = GameWindow.getElements();
        if (beingControlled) {
            for (GameElement element : elements) {
                // Calculate the next step
                int nextPosX = convertToLogicalPos(nextX) + this.x;
                int nextPosY = convertToLogicalPos(nextY) + this.y;

                // Check if the next position collides with the current position of the other element
                if (nextPosX == element.x && nextPosY == element.y) {
                    if (element.walkable) {
                        System.out.println("Collision expected with element but is walkable: " + element);
                        if (element instanceof Player && element.x == this.x && element.y == this.y) {
                            element.playerOnTop = true;
                        }
                            System.out.println("Element " + element + " is being stepped by" + this);
                        element.goingToBeWalkedOverBy(this);
                        return true; // No collision detected, return false
                    } else {
                        onBeingCollidedByYou(element); // Tell the element that something is crashing onto him
                        return false; // Collision detected, return true
                    }
                }
            }
        }
        return true; // No collision detected, return false
    }


    ////  Linking elements
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void linkElement(GameElement other) {
        this.linkedElement = other;
        System.out.println(this + " has added linked to " + other );
        this.linkedElement = other; // Link the other element back
    }

    // Get the linkedElement
    public GameElement getLinkedElement() {
        return this.linkedElement;
    }

    // Unlink elements to release the link, so it can be used again
    public void unlinkElement() {
        if (this.linkedElement != null) {
            this.linkedElement.linkedElement = null; // Unlink the other element
            this.linkedElement = null;
        }
    }

    // Toggle the link state
    public void toggleLink(GameElement other) {
        if (this.linkedElement == other) {
            unlinkElement();
        } else {
            linkElement(other);
        }
    }

    ////  Keys controls
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isBeingControlled() {
        return beingControlled;
    }

    public void setBeingControlled(boolean beingControlled) {
        this.beingControlled = beingControlled;
    }

    public void handleKeyEvent(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0); // Exit the program gracefully
        }
        if (beingControlled) {
            System.out.println("Key pressed - Key Code: " + key); // Print the pressed key code
            switch (key) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    direction = Direction.LEFT;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    direction = Direction.RIGHT;
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    direction = Direction.UP;
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    direction = Direction.DOWN;
                    break;
                case KeyEvent.VK_SPACE:
                    interactKeyPressedByYou(); // Send interact to the item that has control
                    break;
            }
            moveFacing(); // Move the player in the direction it is facing
        }
    }

    public void moveFacing() {
        int[] direction = getDirectionBasedMovement();
        logicalMove(direction[0], direction[1]);
    }

    public void getFacingXY() {
        int[] direction = getDirectionBasedMovement();
        // This could implement giving back the item that is looking at or the XY
    }

    private int[] getDirectionBasedMovement() {
        int[] movement = new int[2];
        // Adjust movement direction based on the player's direction
        switch (direction) {
            case UP:
                movement[1] = -1; // dy = -1
                break;
            case DOWN:
                movement[1] = 1;  // dy = 1
                break;
            case LEFT:
                movement[0] = -1; // dx = -1
                break;
            case RIGHT:
                movement[0] = 1;  // dx = 1
                break;
        }
        return movement;
    }




    ////  Drawing methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public abstract void draw(Graphics2D g2d);

    public int getLayer() {
        return layer;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean bool) {
        if (isVisible != bool) { // Check if the visibility is actually changing
            isVisible = bool;
            System.out.println("Visibility has changed for: " + this);
        }
    }
    public void toggleVisibility() {
        // Toggle the visibility of the element
        isVisible = !isVisible;
        System.out.println("Visibility of the element has changed to: " + isVisible);
    }


    //// Interactions with the rest of the elements
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        // Triggered when something walks over this element, probably a player
        //     @Override on your class, someone / something on top
    }
    public void goingToBeWalkOn(GameElement gameElement) {
        // Triggered when something walks over this element, probably a player
        //     @Override on your class, someone / something on top
    }

    // Trigger by being walked over by something
    // Default sends self to the cell that you are upon
    public void interactKeyPressedByYou() {
        System.out.println(this + " wants to interact");
        // this.moveFacing();
        int currentLogicalPosX = this.getLogicalPosX();
        int currentLogicalPosY = this.getLogicalPosY();
        List<GameElement> elements = GameWindow.getElements();
        // Change to the cell that you are looking into
//        switch (direction) {
//            case UP:
//                currentLogicalPosY -= 1; // Move up
//                break;
//            case DOWN:
//                currentLogicalPosY += 1; // Move down
//                break;
//            case LEFT:
//                currentLogicalPosX -= 1; // Move left
//                break;
//            case RIGHT:
//                currentLogicalPosX += 1; // Move right
//                break;
//        }
        for (GameElement element : elements) {
            // Check the element that this is on
            if (element != this && element.getLogicalPosX() == currentLogicalPosX && element.getLogicalPosY() == currentLogicalPosY) {
                // Call a method on the element to handle the interaction
                element.interactKeyPressedOnYou(this);
            }
        }
    }
    public void interactKeyPressedOnYou(GameElement gameElement) {
        System.out.println(gameElement + " wants to interact with" + this);
        // Override in your class
    }
    public void onBeingCollidedByYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);
        //// Hey other class, this silly guy wants to go through you! , and you are not walkable
        // Tell the other class with the element that is bouncing him
        gameElement.onBeingCollidedOnYou(this);

    }
    public void onBeingCollidedOnYou(GameElement gameElement) {
        //// What a stupid guy I won't move as only my children will implement this behaviour  by using @Override
    }

    public static class DefaultListener implements TickListener {

        @Override
        public void onTick() {

        }
    }
}

