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

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}