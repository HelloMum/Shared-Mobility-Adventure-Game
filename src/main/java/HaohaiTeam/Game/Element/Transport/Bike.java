package HaohaiTeam.Game.Element.Transport;

import java.awt.Graphics2D;
import java.awt.Color;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Bike extends TransportMode {
    private static final Color BIKE_COLOR = Color.GREEN;

    public Bike(int x, int y) {
        super(x, y); // assume 15km per hour and carbon footprint of 1.0 kg per km
        this.speed = 10;
        this.carbonFootprint = 1;

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(BIKE_COLOR);
        g2d.fillRect(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // draw the bike
    }


    }


