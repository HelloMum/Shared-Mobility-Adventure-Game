package HaohaiTeam.Game.Element.Transport;

import HaohaiTeam.Game.Logic.PlayerTransportState;

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
    public PlayerTransportState getTransportState() {
        return PlayerTransportState.RIDING_LUAS;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(LUAS_COLOR);
        // Draw the main body of the eight-carriage light rail
        for (int i = 0; i < 8; i++) {
            g2d.fillRect(x + i * (CELL_SIZE / 2), y, CELL_SIZE / 2, CELL_SIZE / 2);
        }

        // Draw the doors
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                g2d.drawRect(x + i * (CELL_SIZE / 2) + CELL_SIZE / 8, y + CELL_SIZE / 8, CELL_SIZE / 4, CELL_SIZE / 3);
            }
        }

        // Draw the tracks
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y + CELL_SIZE / 2, CELL_SIZE * 4, CELL_SIZE / 10);

        // Add highlights to the tracks for a more three-dimensional effect
        g2d.setColor(Color.WHITE);
        g2d.drawLine(x, y + CELL_SIZE / 2 - CELL_SIZE / 20, x + CELL_SIZE / 40, y + CELL_SIZE / 2); // Track highlight
    }
}

