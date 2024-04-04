package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.GUI.GameWindow;
import java.awt.*;
import java.util.List;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
public class Road extends GameElement {
    private static final Color ROAD_COLOR = Color.black;

    public Road(int x, int y) {
        super(x, y);
        this.walkable = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(ROAD_COLOR);
        g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }
}
