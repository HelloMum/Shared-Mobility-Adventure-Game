package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Taxi extends AutoMoveTransport {
    public Taxi(int x, int y) {
        super(x, y, Color.YELLOW, 12, 3);
    }


    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        // Add highlights to the taxi's body for a more realistic effect
        g2d.setColor(Color.WHITE);
        // Draw the taxi sign
        g2d.setColor(Color.BLACK);
        g2d.drawString("Taxi", renderX + CELL_SIZE / 8, renderY + CELL_SIZE / 2 );
    }
}

