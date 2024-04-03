package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class GameElement {

    private static List<GameElement> elements;
    public int x; // These x coordinates uses the pixel position for drawing
    public int y; // These y coordinates uses the pixel position for drawing
    public int layer; // This refers if the that is going to be drawn, higher number higher preference.
    public boolean walkable; // This refers if the element can be walked though
    public int speed; // This refers to the maximum speed of the element
    private GameElement linkedElement; // This to link two element on the same cell so one follows the other, it also overdrives the control of the other
    public boolean beingControlled = false; // Flag to enable key control by keys

    private boolean isVisible = true; // add a flag to control visibility

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.walkable = false;
        this.layer = 99; // Default layer 
    }
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    /// LOCATING THE GAME ELEMENTS USING LOGICAL POSITIONS
    //These two can be used to locate the x and y of the element in the logical cell grid
    // Should be the only one using convert cell_size
    public int convertToLogicalPos(int unitToConvert) {
        return unitToConvert * CELL_SIZE ;

    public boolean isVisible() {
        return isVisible;
    }
    public static void setElements(List<GameElement> elements) {
        GameElement.elements = elements; // Assign the list of elements
    }
    public void getLogicalPosX(int posX) {
        convertToLogicalPos(x);
    }
    public void getLogicalPosY(int posY) {
        convertToLogicalPos(y);
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
        if (checkCollision(dx, dy)){
            setToLogicalPosX(dx);
            setToLogicalPosY(dy);
        }
    }
    public void moveLogical(int dx, int dy) {
        // Update the actual position of the object based on logic
        if (checkCollision(dx, dy)){
            setToLogicalPosX(dx);
            setToLogicalPosY(dy);
        }
        return false;
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
                int nextPosX = convertToLogicalPos(nextX) + this.x ;
                int nextPosY = convertToLogicalPos(nextY) + this.y ;

                // Check if the next position collides with the current position of the other element
                if (nextPosX == element.x && nextPosY == element.y) {
                    if (element.walkable) {
                        System.out.println("Collision expected with element but is walkable: " + element);
                        element.onBeingWalkedOver();
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
    public void onBeingWalkedOver() {
        // Implement the behavior when this element is being walked over
        System.out.println("Element " + this + " is being walked over.");
    }


    // Linking elements, to link different elements
    public void linkElement(GameElement other) {
        this.linkedElement = other;
        other.linkedElement = this; // Link the other element back
    }
    // need to get the linkedElement
    public GameElement getLinkedElement() {
        return this.linkedElement;
    }
    // need unlinkElement method to release the link, so it can be used again
    public void unlinkElement() {
        if (this.linkedElement != null) {
            this.linkedElement.setBeingControlled(false);
            this.linkedElement = null;
            this.setBeingControlled(true); // 玩家恢复直接控制
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
        if (beingControlled) {
            System.out.println("Key pressed - Key Code: " + key); // Print the pressed key code
            if (key == KeyEvent.VK_LEFT) {
                dx = -1;
            } else if (key == KeyEvent.VK_RIGHT) {
                dx = 1;
            } else if (key == KeyEvent.VK_UP) {
                dy = -1;
            } else if (key == KeyEvent.VK_DOWN) {
                dy = 1;
            }
        }
        logicalMove(dx, dy);
    }

    // Drawing the elements
    public abstract void draw(Graphics2D g2d);

    public int getLayer() {
        return layer;
    }
}
