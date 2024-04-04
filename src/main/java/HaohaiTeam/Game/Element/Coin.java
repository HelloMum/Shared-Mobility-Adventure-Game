package HaohaiTeam.Game.Element;
import HaohaiTeam.Game.GUI.GameWindow;
import java.awt.*;
import java.util.List;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Coin extends GameElement {
    private static final Color COIN_COLOR = Color.YELLOW;

    public Coin(int x, int y) {
        super(x, y);
        this.walkable = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int centerX = x + CELL_SIZE / 4; // Calculate the x-coordinate of the center of the cell
        int centerY = y + CELL_SIZE / 4; // Calculate the y-coordinate of the center of the cell
        g2d.setColor(COIN_COLOR);
        g2d.fillOval(centerX, centerY, CELL_SIZE / 2, CELL_SIZE / 2); // Draw the circle at the center of the cell
    }
    @Override
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        List<GameElement> elements = GameWindow.getElements();
        System.out.println("Element " + gameElement + " is starting to walk on me " + this);

        int newX = 0; // Start from the far right side of the screen
        int newY = 0; // top of the window
        boolean spotOccupied;

        do {
            spotOccupied = false; // Reset flag for each new position
            for (GameElement element : elements) {
                // Check if the current element is a Gem and its position matches the new position
                if (element instanceof Coin && element.x == newX && element.y == newY) {
                    spotOccupied = true; // Set flag to true if spot is occupied by a Gem
                    newX = newX + CELL_SIZE; // Move to the next x position and continue trying
                    break; // Exit the loop to avoid unnecessary iterations
                }
            }
        } while (spotOccupied); // Repeat until an empty spot is found

        // Set the position based on the found empty spot
        this.x = newX;
        this.y = newY; // Adjust y position based on the grid size
    }
}