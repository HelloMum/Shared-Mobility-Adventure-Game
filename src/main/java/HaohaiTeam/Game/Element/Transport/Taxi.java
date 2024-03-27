package HaohaiTeam.Game.Element.Transport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Taxi extends TransportMode {
    private static final Color TAXI_COLOR = Color.YELLOW;

    public Taxi(int x, int y) {
        super(x, y);
        this.speed = 15;
        this.carbonFootprint = 1;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(TAXI_COLOR);
        g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE); // draw Taxi
    }
}

