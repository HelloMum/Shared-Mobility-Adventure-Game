package HaohaiTeam.Game.Element.Transport;

import HaohaiTeam.Game.Navigation.Route;

import java.awt.*;
import java.awt.Point;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
import HaohaiTeam.Game.Navigation.Route;
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
    public void draw(Graphics2D g2d) {
        g2d.setColor(BUS_COLOR);
        g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE); // draw the bus
    }
}
