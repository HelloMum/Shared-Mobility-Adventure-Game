package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;
import java.util.Set;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class BusStation extends Station{

    public BusStation(int x, int y) {
        super(x, y);
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(Color.GREEN);
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
}
