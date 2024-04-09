package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Luas extends AutoMoveTransport {
    public Luas(int x, int y) {

        super(x, y, Color.DARK_GRAY,20,1); // set color
        layer = 102;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        // Draw a horizontal yellow line through the ball
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(3)); // Adjust the thickness as needed
        int lineY = renderY + CELL_SIZE - CELL_SIZE/3; // Calculate the y-coordinate of the line
        int lineStartX = renderX; // Start x-coordinate of the line
        int lineEndX = renderX + CELL_SIZE; // End x-coordinate of the line
        g2d.drawLine(lineStartX, lineY, lineEndX, lineY);
    }
}



