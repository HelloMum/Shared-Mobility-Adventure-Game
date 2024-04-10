package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;
import java.util.Set;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class BusStation extends Station{
    private static final Color BLACK_COLOR = Color.BLACK;

    public BusStation(int x, int y, Set<String> supportedTransportTypes, int waitTimeInSeconds) {
        super(x, y, supportedTransportTypes, waitTimeInSeconds);
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(BLACK_COLOR);
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
}
