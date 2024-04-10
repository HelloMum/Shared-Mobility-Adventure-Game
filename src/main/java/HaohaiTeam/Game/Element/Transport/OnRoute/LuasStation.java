package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;
import java.util.Set;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class LuasStation extends Station{
    private static final Color YelliG_COLOR = Color.YELLOW;
    public LuasStation(int x, int y, Set<String> supportedTransportTypes, int waitTimeInSeconds) {
        super(x, y, supportedTransportTypes, waitTimeInSeconds);
    }
    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(YelliG_COLOR);
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
}
