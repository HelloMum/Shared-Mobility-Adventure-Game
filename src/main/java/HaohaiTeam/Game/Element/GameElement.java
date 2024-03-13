package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public abstract class GameElement {
    private static List<GameElement> elements;
    public int x;
    public int y;

    private static final int MOVE_COUNTDOWN_LIMIT = 15;
    private int moveCooldown = 0;
    private GameElement linkedElement;
    private boolean beingControlled = false; // Flag to enable key control

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void setElements(List<GameElement> elements) {
        GameElement.elements = elements; // Assign the list of elements
    }

    protected int getPosX() {
        return x * GameWindow.CELL_SIZE; // Use GameWindow.CELL_SIZE
    }

    protected int getPosY() {
        return y * GameWindow.CELL_SIZE; // Use GameWindow.CELL_SIZE
    }

    public void move(int dx, int dy) {
        if (moveCooldown == 0 && beingControlled) { // Check if movement is allowed based on cooldown and control flag
            // Calculate the next position
            int nextX = x + dx;
            int nextY = y + dy;

            // Check if the next position is within the game window bounds
            if (isWithinBounds(nextX, nextY)) {
                // Check if the next position will cause a collision
                if (!willCollide(nextX, nextY)) {
                    x = nextX;
                    y = nextY;

                    // If linked, move the linked element too
                    if (linkedElement != null) {
                        linkedElement.move(dx, dy); // Recursively move linked element
                    }

                    moveCooldown = MOVE_COUNTDOWN_LIMIT; // Reset cooldown
                }
            }
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

    private boolean willCollide(int nextX, int nextY) {
        // Check if the next position will cause a collision with any other element
        for (GameElement element : elements) {
            if (element != this && Math.abs(nextX - element.x) < GameWindow.CELL_SIZE &&
                    Math.abs(nextY - element.y) < GameWindow.CELL_SIZE) {
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }

    public void linkElement(GameElement other) {
        this.linkedElement = other;
        other.linkedElement = this; // Link the other element back
    }

    public void update() {
        if (moveCooldown > 0) {
            moveCooldown--;
        }
    }

    public abstract void draw(Graphics2D g2d);

    public static void updateList(List<GameElement> elements) {
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
