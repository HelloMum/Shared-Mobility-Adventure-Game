package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.PopUp;

import java.awt.*;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class BusStation extends Station {

    public BusStation(int x, int y) {
        super(x, y);
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

            double busPrice = 2.60;
            new PopUp(this.X, this.Y, "This is a bus stop.\n It costs " + busPrice + " to ride the bus.");
        }
    }

}