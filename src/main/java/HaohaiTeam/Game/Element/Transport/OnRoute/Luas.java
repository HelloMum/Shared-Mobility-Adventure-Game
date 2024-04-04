package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.Logic.currentTransport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Luas extends Bus {
    private static final Color LUAS_COLOR = Color.DARK_GRAY;


    public Luas(int x, int y) {
        super(x, y); // assume speed is 45 km/h and carbon footprint is 0.6 kg/km
        this.speed = 20;
        this.carbonFootprint = 1;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(LUAS_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);

        // Draw a horizontal yellow line through the ball
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(3)); // Adjust the thickness as needed
        int lineY = y + CELL_SIZE - CELL_SIZE/3; // Calculate the y-coordinate of the line
        int lineStartX = x ; // Start x-coordinate of the line
        int lineEndX = x + CELL_SIZE; // End x-coordinate of the line
        g2d.drawLine(lineStartX, lineY, lineEndX, lineY);
    }
}


