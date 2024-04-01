package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class GameElement {
    private static List<GameElement> elements;
    public int x;
    public int y;
    public int speed;
    // This refers to the maximum speed of the element
    private GameElement linkedElement;
    public boolean beingControlled = false; // Flag to enable key control

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
    }

    public static void setElements(List<GameElement> elements) {
        GameElement.elements = elements; // Assign the list of elements
    }
    //These two can be used to locate the x and y of the element, real center DON'T USE FOR LOGIC
    protected int getPosX() {
        return x * CELL_SIZE;
    }
    protected int getPosY() {
        return y * CELL_SIZE;
    }

    //These implement a return for the real grid position, for the logic implementation
    public int logicPosX() {
        return x ;
    }
    public int logicPosY() {
        return y ;
    }
    //
    public Rectangle getBounds() {
        return new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
    }
    public void move(int dx, int dy) {
        // Update the actual position of the object to the calculated next position.
        if (!checkCollision(dx, dy)){
            x += dx * CELL_SIZE;
            y += dy * CELL_SIZE;
        }
    }

    private boolean isWithinBounds(int nextX, int nextY) {
        // Check if the next position is within the game window bounds
        return (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT);
    }


    // Detects if an element is going to crash into another, ignores selfZ
    private boolean checkCollision(int nextX, int nextY) {
        // Null iterator need to be fixed
//        for (GameElement element : elements) {
//            // Check screen limits and sends collision if is not
//            if (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT)
//                continue;
//
//            // Check if the next position (nextX, nextY) collides with the current element's position (element.x, element.y)
//            if (nextX == element.x && nextY == element.y) {
//                return true; // Collision detected, return true
//            }
//        }
        return false; // No collision detected, return false
    }

    // Method to handle key events for controlling movement
    public void handleKeyEvent(KeyEvent e) {
        if (beingControlled) {
            int keyCode = e.getKeyCode();
            // Print the key code for debugging
            System.out.println("Key pressed: " + keyCode);

            // Check if arrow key is pressed
            if (keyCode == KeyEvent.VK_UP) {
                move(0, -1); // Move up
                System.out.println("Moving up");
            } else if (keyCode == KeyEvent.VK_DOWN) {
                move(0, 1); // Move down
                System.out.println("Moving down");
            } else if (keyCode == KeyEvent.VK_LEFT) {
                move(-1, 0); // Move left
                System.out.println("Moving left");
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                move(1, 0); // Move right
                System.out.println("Moving right");
            } else {
                // Print unknown key press for debugging
                System.out.println("Unknown key pressed");
            }
        } else {
            // Print message if not being controlled for debugging
            System.out.println("Not being controlled");
        }
    }

    public void linkElement(GameElement other) {
        this.linkedElement = other;
        other.linkedElement = this; // Link the other element back
    }
    // need to get the linkedElement
    public GameElement getLinkedElement() {
        return this.linkedElement;
    }
    // need unlinkElement method to release the link so it can be used again
    public void unlinkElement() {
        if (this.linkedElement != null) {
            this.linkedElement.setBeingControlled(false);
            this.linkedElement = null;
            this.setBeingControlled(true); // 玩家恢复直接控制
        }
    }

    public abstract void draw(Graphics2D g2d);

    public void updateList(List<GameElement> elements) {
        GameElement.elements = elements; // Update the static list of elements
    }

    // Getter and setter for beingControlled flag
    public boolean isBeingControlled() {
        return beingControlled;
    }

    public void setBeingControlled(boolean beingControlled) {
        this.beingControlled = beingControlled;
    }
}
