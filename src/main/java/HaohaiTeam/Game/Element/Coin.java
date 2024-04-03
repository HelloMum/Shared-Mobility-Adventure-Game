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
        g2d.setColor(COIN_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
    @Override
    public void onBeingWalkedOver(GameElement gameElement) {
        List<GameElement> elements = GameWindow.getElements();
        System.out.println("Element " + this + " is being walked over.");
        int newX = 0;
        int newY = 0;
        boolean spotOccupied;
        do {
            spotOccupied = false; // Reset flag for each new position
            for (GameElement element : elements) {
                if (element instanceof Coin && element.x == newX && element.y == newY) {
                    spotOccupied = true;
                    newX = newX + CELL_SIZE; // Move to the next x position and continue trying
                }
            }
        } while (spotOccupied);
        // Set the position based on the found empty spot
        this.x = newX ;
        this.y = 0;
    }
}