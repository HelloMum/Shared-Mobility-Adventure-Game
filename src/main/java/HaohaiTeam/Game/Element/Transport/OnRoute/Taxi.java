package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Taxi extends Bus {
    private static final Color TAXI_COLOR = Color.YELLOW;

    public Taxi(int x, int y) {
        super(x, y);
        this.speed = 15;
        this.carbonFootprint = 1;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(TAXI_COLOR);
        g2d.fillOval(renderX, renderY, CELL_SIZE, CELL_SIZE);


        // Add highlights to the taxi's body for a more realistic effect
        g2d.setColor(Color.WHITE);
        // Draw the taxi sign
        g2d.setColor(Color.BLACK);
        g2d.drawString("Taxi", renderX + CELL_SIZE / 8, renderY + CELL_SIZE / 2 );

    }
}

