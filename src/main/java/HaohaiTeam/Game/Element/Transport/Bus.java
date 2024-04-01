package HaohaiTeam.Game.Element.Transport;

import HaohaiTeam.Game.Logic.currentTransport;
import HaohaiTeam.Game.Navigation.Route;

import java.awt.*;
import java.awt.Point;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
public class Bus extends TransportMode {
    private static final Color BUS_COLOR = Color.BLUE;
    private Route busRoute;
    public Bus(int x, int y) {
        super(x, y); // assume 30 km/h and 0.3 kg CO2/km
        this.carbonFootprint = 1;
    }

    public void startRoute() {

        // Initialize the route
        busRoute = new Route();
        busRoute.addPoint(new Point(10, 10)); // Starting point
        busRoute.addPoint(new Point(20, 10)); // Second point
        busRoute.addPoint(new Point(20, 20)); // Third point......add more
    }

    @Override
    public currentTransport getTransportState() {
        return currentTransport.RIDING_BUS;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(BUS_COLOR);
        // Draw the main body of the bus, making it larger
        g2d.fillRect(x, y, CELL_SIZE * 3 / 2, CELL_SIZE);

        // Draw the windows using a light gray color for contrast
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 4; i++) {
            g2d.fillRect(x + CELL_SIZE / 4 + i * CELL_SIZE / 4, y + CELL_SIZE / 8, CELL_SIZE / 4, CELL_SIZE / 4);
        }

        // Draw the driver's cabin window
        g2d.fillRect(x, y, CELL_SIZE / 3, CELL_SIZE / 2);

        // Draw the door separator line
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x + CELL_SIZE / 2, y, x + CELL_SIZE / 2, y + CELL_SIZE);

        // Draw the wheels
        g2d.fillOval(x + CELL_SIZE / 6, y + CELL_SIZE - CELL_SIZE / 3, CELL_SIZE / 3, CELL_SIZE / 3);
        g2d.fillOval(x + CELL_SIZE * 3 / 2 - CELL_SIZE / 3, y + CELL_SIZE - CELL_SIZE / 3, CELL_SIZE / 3, CELL_SIZE / 3);

        // Add highlights to the wheels for a more realistic effect
        g2d.setColor(Color.WHITE);
        g2d.drawLine(x + CELL_SIZE / 6, y + CELL_SIZE - CELL_SIZE / 6, x + CELL_SIZE / 3, y + CELL_SIZE - CELL_SIZE / 6); // Front wheel highlight
        g2d.drawLine(x + CELL_SIZE * 3 / 2 - CELL_SIZE / 6, y + CELL_SIZE - CELL_SIZE / 6, x + CELL_SIZE * 3 / 2 - CELL_SIZE / 3, y + CELL_SIZE - CELL_SIZE / 6); // Rear wheel highlight
    }
}
