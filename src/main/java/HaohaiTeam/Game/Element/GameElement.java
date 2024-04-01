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
    public boolean walkable;
    public int speed;
    // This refers to the maximum speed of the element
    private GameElement linkedElement;
    public boolean beingControlled = false; // Flag to enable key control

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.walkable = false;
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

    // Checks if the next position collides with any other game element
    private boolean checkCollision(int nextX, int nextY) {
        List<GameElement> elements = GameWindow.getElements();
        if (beingControlled) {
            for (GameElement element : elements) {
                // Calculate the next step
                int nextPosX = nextX * CELL_SIZE + this.x ;
                int nextPosY = nextY * CELL_SIZE + this.y ;

                // Check if the next position collides with the current position of the other element
                if (nextPosX == element.x && nextPosY == element.y) {
                    if (element.walkable) {
                        System.out.println("Collision expected with element but is walkable: " + element);
                        return false; // No collision detected, return false
                    } else {
                        System.out.println("Collision expected with element: " + element);
                        return true; // Collision detected, return true
                    }
                }
            }
        }
        return false; // No collision detected, return false
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


        move(dx, dy);
    }
}
