package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;

import java.awt.*;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class BusStation extends Station{

    public BusStation(int x, int y) {
        super(x, y);
    }


    @Override
    public void draw(Graphics2D g2d) {
        // Draw black background square
        g2d.setColor(Color.green);
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
