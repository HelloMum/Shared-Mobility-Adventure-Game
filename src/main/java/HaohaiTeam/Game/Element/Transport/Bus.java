package HaohaiTeam.Game.Element.Transport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Bus extends TransportMode {
    private static final Color BUS_COLOR = Color.BLUE;

    public Bus(int x, int y) {
        super(x, y, "Bus", 30.0, 0.3); // assume 30 km/h and 0.3 kg CO2/km
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(BUS_COLOR);
        g2d.fillRect(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // draw the bus
    }
}
