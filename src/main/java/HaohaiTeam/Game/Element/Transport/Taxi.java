package HaohaiTeam.Game.Element.Transport;

import HaohaiTeam.Game.Logic.PlayerTransportState;

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
    public PlayerTransportState getTransportState() {
        return PlayerTransportState.RIDING_TAXI;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(TAXI_COLOR);
        // Draw the taxi's body, making it smaller
        g2d.fillRect(x, y + CELL_SIZE / 4, CELL_SIZE / 2, CELL_SIZE / 2);

        // Draw the roof of the taxi
        g2d.fillRect(x + CELL_SIZE / 8, y, CELL_SIZE / 2 - CELL_SIZE / 4, CELL_SIZE / 4);

        // Draw the headlights
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(x + CELL_SIZE / 16, y, CELL_SIZE / 8, CELL_SIZE / 8);
        g2d.fillRect(x + CELL_SIZE / 2 - CELL_SIZE / 8, y, CELL_SIZE / 8, CELL_SIZE / 8);

        // Draw the taxi sign
        g2d.setColor(Color.BLACK);
        g2d.drawString("Taxi", x + CELL_SIZE / 8, y + CELL_SIZE / 8);

        // Add highlights to the taxi's body for a more realistic effect
        g2d.setColor(Color.WHITE);
        g2d.drawLine(x + CELL_SIZE / 4, y + CELL_SIZE / 4, x + CELL_SIZE / 2, y); // Body highlight
    }
}

