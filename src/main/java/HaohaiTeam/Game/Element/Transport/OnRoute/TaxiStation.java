package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;

import java.awt.*;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class TaxiStation extends Station{


    public TaxiStation(int x, int y) {
        super(x, y);
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
