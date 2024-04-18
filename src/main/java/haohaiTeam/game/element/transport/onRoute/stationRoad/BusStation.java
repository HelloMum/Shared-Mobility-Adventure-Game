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
        setStationType('b');
    }

    @Override
    protected double getCO2PerCell() {
        return CO2_PER_CELL;
    }
    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(new Color(0, 200, 0, 100));
        g2d.fillRect(renderX, renderY, CELL_SIZE, CELL_SIZE);
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
