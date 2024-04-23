package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.PopUp;
import haohaiTeam.game.element.transport.onRoute.auto.Bus;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static haohaiTeam.game.gui.GameWindow.CELL_SIZE;

public class BusStation extends Station {
    private static double busPrice;
    private static final double CO2_PER_CELL = 0.5;
    public BusStation(int x, int y) {
        super(x, y);
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(new Color(0, 200, 0, 100));
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);

        // Add "BUS" text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10)); // Adjust font size to fit in the cell
        FontMetrics fm = g2d.getFontMetrics();
        String busText = "BUS";
        int textWidth = fm.stringWidth(busText);
        int textX = renderX + (CELL_SIZE - textWidth) / 2; // Center the text horizontally
        int textY = renderY + (CELL_SIZE / 2) + fm.getAscent() / 2 - 5; // Position text in the upper half of the cell

        // Draw the "BUS" text
        g2d.drawString(busText, textX, textY);

        // Add "STOP" text below "BUS"
        String stopText = "STOP";
        textWidth = fm.stringWidth(stopText);
        textX = renderX + (CELL_SIZE - textWidth) / 2; // Center the text horizontally
        textY += fm.getHeight(); // Move "STOP" text below "BUS"

        // Draw the "STOP" text
        g2d.drawString(stopText, textX, textY);
    }
    @Override
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        if (gameElement instanceof Bus bus) {
            bus.toggleAutoStation();

            // Schedule a task to toggle autoStation again after 3 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bus.toggleAutoStation();
                }
            }, 3000);
        }
    }
}
