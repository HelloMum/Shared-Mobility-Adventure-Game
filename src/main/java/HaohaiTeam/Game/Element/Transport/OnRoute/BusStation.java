package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.PopUp;
import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class BusStation extends Station{
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

    public void onBeingCollidedOnYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);
        if (gameElement instanceof Player) {

            PopUp busPopup = new PopUp(this.X, this.Y,"This is a bus stop.\n It costs " + busPrice + " to ride the bus.", 2000);
            Timer busPopupTimer = new Timer();
            busPopupTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    busPopup.remove();
                }
            }, 3000);
        }
    }
}
