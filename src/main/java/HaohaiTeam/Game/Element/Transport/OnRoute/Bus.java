package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Bus extends AutoMoveTransport {

    public Bus(int x, int y) {
        super(x, y);
        layer = 102; // Drawn over player and roads
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw the fake shadow
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillOval(renderX + 5, renderY + 5, CELL_SIZE, CELL_SIZE);

        // Draw a green circle
        g2d.setColor(Color.GREEN);
        int circleSize = CELL_SIZE; // Adjust the size of the circle as needed
        int circleX = renderX + CELL_SIZE / 2 - circleSize / 2; // Calculate the x-coordinate of the circle
        int circleY = renderY + CELL_SIZE / 2 - circleSize / 2; // Calculate the y-coordinate of the circle
        g2d.fillOval(circleX, circleY, circleSize, circleSize);
    }
}