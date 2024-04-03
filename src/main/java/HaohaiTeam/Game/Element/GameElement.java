package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class GameElement {

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


    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.walkable = false;
        this.layer = 99; // Default layer
        this.isVisible = true;
        this.playerOnTop =false;
        this.direction = Direction.DOWN; // Default direction
    }
    // direction that the element is facing
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /// LOCATING THE GAME ELEMENTS USING LOGICAL POSITIONS
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

    private boolean isWithinBounds(int nextX, int nextY) {
        // Check if the next position is within the game window bounds
        return (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT);
    }

    // Checks if the next position collides with any other game element
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
                        element.onBeingWalkedOver(this);
                        return true; // No collision detected, return false
                    } else {
                        System.out.println("Collision expected with element: " + element);
                        return false; // Collision detected, return true
                    }
                }
            }
        }
        return true; // No collision detected, return false
    }


    // Linking elements, to link different elements to same x and Y
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

    /// Keys controls
    // Getter and setter for beingControlled flag
    public boolean isBeingControlled() {
        return beingControlled;
    }

    public void setBeingControlled(boolean beingControlled) {
        this.beingControlled = beingControlled;
    }

    public void handleKeyEvent(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = 0, dy = 0;
        if (key == KeyEvent.VK_ESCAPE){
            System.exit(0); // Exit the program gracefully
        }
        if (beingControlled) {
            System.out.println("Key pressed - Key Code: " + key); // Print the pressed key code
            if (key == KeyEvent.VK_LEFT) {
                direction = Direction.LEFT;
                dx = -1;
                logicalMove(dx, dy);
            } else if (key == KeyEvent.VK_RIGHT) {
                direction = Direction.RIGHT;
                dx = 1;
                logicalMove(dx, dy);
            } else if (key == KeyEvent.VK_UP) {
                direction = Direction.UP;
                dy = -1;
                logicalMove(dx, dy);
            } else if (key == KeyEvent.VK_DOWN) {
                direction = Direction.DOWN;
                dy = 1;
                logicalMove(dx, dy);
            }else if (key == KeyEvent.VK_SPACE) {
                interactKeyPressedByYou(); // Send interact to the item that has control
            }

        }


    }


    // Drawing the elements
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
    public void onBeingWalkedOver(GameElement gameElement) {
        // Triggered when something walks over this element, probably a player
        if (gameElement instanceof Player) {
            onBeingWalkedOverStart(gameElement); // Call the method for start walking over
        }
    }

    public void onBeingWalkedOverStart(GameElement gameElement) {
        //     @Override on your class, someone on top
    }

    public void onBeingWalkedOverStop(GameElement gameElement) {
        //     @Override on your class, someone on top left
    }

    // Trigger by being walked over by something
    // Default sends self to the cell that you are upon
    public void interactKeyPressedByYou() {
        System.out.println(this + " wants to interact");
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
            // Check the element the element is looking into
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
}

