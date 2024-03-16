package HaohaiTeam.Game.Element.Transport;

import HaohaiTeam.Game.Element.GameElement;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

import java.awt.*;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
public class Bike extends TransportMode {
    private static final Color BIKE_COLOR = Color.GREEN;

    public Bike(int x, int y) {
        super(x, y, "Bike", 15.0, 0.0); // assume 15km per hour and carbon footprint of 1.0 kg per km
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(BIKE_COLOR);
        g2d.fillRect(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // draw the bike
    }

    // directly override the handleKeyEvent method
    public void handleKeyEvent(KeyEvent e) {
        // 假定被控制标志已经适当设置
        if (isBeingControlled()) {
            int dx = 0, dy = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    dy = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    dy = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    dx = -1;
                    break;
                case KeyEvent.VK_RIGHT:
                    dx = 1;
                    break;
            }
            move(dx, dy);
        }
    }

}
