package HaohaiTeam.Game.Element.Transport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Taxi extends TransportMode {
    private static final Color TAXI_COLOR = Color.YELLOW;

    public Taxi(int x, int y) {
        super(x, y, "Taxi", 60.0, 0.9); // assume taxi is 60km/h and 0.9 carbon footprint factor
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(TAXI_COLOR);
        g2d.fillRect(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // draw Taxi
    }
}

