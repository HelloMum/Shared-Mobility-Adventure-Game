package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;
import java.util.Set;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class TaxiStation extends Station{
    private static final Color RED_COLOR = Color.RED;
    public TaxiStation(int x, int y, Set<String> supportedTransportTypes, int waitTimeInSeconds) {
        super(x, y, supportedTransportTypes, waitTimeInSeconds);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(RED_COLOR);
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
}
