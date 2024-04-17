package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.transport.onRoute.auto.Taxi;
import haohaiTeam.game.element.transport.onRoute.stationRoad.Station;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static haohaiTeam.game.gui.GameWindow.CELL_SIZE;

public class TaxiStation extends Station {
    private static final double CO2_PER_CELL = 1.0;

    public TaxiStation(int x, int y) {
        super(x, y);
        setStationType('t');
    }

    @Override
    protected double getCO2PerCell() {
        return CO2_PER_CELL;
    }
    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(Color.orange);
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
    }
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        if (gameElement instanceof Taxi taxi) {
            taxi.toggleAutoStation();

            // Schedule a task to toggle autoStation again after 3 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    taxi.toggleAutoStation();
                }
            }, 3000);
        }
    }
}
