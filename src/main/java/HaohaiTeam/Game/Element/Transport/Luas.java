package HaohaiTeam.Game.Element.Transport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Luas extends TransportMode {
    private static final Color LUAS_COLOR = Color.RED;


    public Luas(int x, int y) {
        super(x, y); // assume speed is 45 km/h and carbon footprint is 0.6 kg/km
        this.speed = 20;
        this.carbonFootprint = 1;

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(LUAS_COLOR);
        g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE); // draw the luas
    }
}

