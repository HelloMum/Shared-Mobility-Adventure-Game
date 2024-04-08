package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Bus extends AutoMoveTransport {

    public Bus(int x, int y) {
        super(x, y, Color.GREEN, 1, 0.0);
        layer = 102; // Drawn over player and roads
    }


    protected boolean isRoadAtPosition(int x, int y, List<GameElement> elements) {
        return super.isRoadAtPosition(x, y, elements);
    }


    protected void updateHeading(int dx, int dy) {
        // Update the heading direction based on the road
        super.updateHeading(dx, dy);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}