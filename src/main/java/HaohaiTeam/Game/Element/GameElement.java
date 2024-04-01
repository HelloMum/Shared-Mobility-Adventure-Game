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
        // If the object is not being controlled, then exit the method without performing any movement.
        if (!beingControlled) return;

        // Calculate the proposed next position for the object by adding the current x-coordinate and the product of the horizontal speed (dx) and the speed value.
        int nextX = x + dx * speed;
        // Similarly, calculate the proposed next position for the object along the y-axis by adding the current y-coordinate and the product of the vertical speed (dy) and the speed value.
        int nextY = y + dy * speed;

        // If the calculated next position is within the allowed bounds of the game area AND there's no predicted collision at that position,
        if (isWithinBounds(nextX, nextY) && !checkCollision(nextX, nextY)) {
            // Update the actual position of the object to the calculated next position.
            x = nextX;
            y = nextY;
        }


    }

    // Method to handle key events for controlling movement
    public void handleKeyEvent(KeyEvent e) {
        if (beingControlled) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP) {
                move(0, -1); // Move up
            } else if (keyCode == KeyEvent.VK_DOWN) {
                move(0, 1); // Move down
            } else if (keyCode == KeyEvent.VK_LEFT) {
                move(-1, 0); // Move left
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                move(1, 0); // Move right
            }
        }
    }

    private boolean isWithinBounds(int nextX, int nextY) {
        // Check if the next position is within the game window bounds
        return (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT);
    }


    // Detects if an element is going to crash into another, ignores selfZ
    private boolean checkCollision(int nextX, int nextY) {
        // We check all the elements to see if the nextX and nextY match their own positions
        for (GameElement element : elements) {
            // Skip checking collision with itself
            if (element == this) {
                continue;
            }
            // Check screen limits and sends collision if is not
            if (nextX >= 0 && nextX < GameWindow.FRAME_WIDTH && nextY >= 0 && nextY < GameWindow.FRAME_HEIGHT)
                continue;

            // Check if the next position (nextX, nextY) collides with the current element's position (element.x, element.y)
            if (nextX == element.x && nextY == element.y) {
                return true; // Collision detected, return true
            }
        }
        return false; // No collision detected, return false
    }
    public void linkElement(GameElement other) {
        this.linkedElement = other;
        other.linkedElement = this; // Link the other element back
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
