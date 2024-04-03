package HaohaiTeam.Game.Element;
import HaohaiTeam.Game.GUI.GameWindow;
import java.awt.*;
import java.util.List;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Gem extends GameElement {
    private static final Color GEM_COLOR = Color.PINK;

    public Gem(int x, int y) {
        super(x, y);
        this.walkable = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(GEM_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
    @Override
    public void onBeingWalkedOverStart(GameElement gameElement) {
        List<GameElement> elements = GameWindow.getElements();
        System.out.println("Element " + gameElement + " is starting to walk on me " + this);

        int newX = GameWindow.FRAME_WIDTH - CELL_SIZE; // Start from the far right side of the screen
        int newY = 0; // top of the window
        boolean spotOccupied;

        do {
            spotOccupied = false; // Reset flag for each new position
            for (GameElement element : elements) {
                // Check if the current element is a Gem and its position matches the new position
                if (element instanceof Gem && element.x == newX && element.y == newY) {
                    spotOccupied = true; // Set flag to true if spot is occupied by a Gem
                    newX = newX - CELL_SIZE; // Move to the next x position and continue trying
                    break; // Exit the loop to avoid unnecessary iterations
                }
            }
        } while (spotOccupied); // Repeat until an empty spot is found

        // Set the position based on the found empty spot
        this.x = newX;
        this.y = newY; // Adjust y position based on the grid size
    }
}