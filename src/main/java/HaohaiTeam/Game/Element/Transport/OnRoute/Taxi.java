package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.Transport.TransportMode;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Taxi extends TransportMode {
    public Taxi(int x, int y) {
        super(x, y);
        layer = 102;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        int circleSize = CELL_SIZE; // Adjust the size of the circle as needed
        int circleX = renderX + CELL_SIZE / 2 - circleSize / 2; // Calculate the x-coordinate of the circle
        int circleY = renderY + CELL_SIZE / 2 - circleSize / 2; // Calculate the y-coordinate of the circle
        g2d.fillOval(circleX, circleY, circleSize, circleSize);
    }

}

