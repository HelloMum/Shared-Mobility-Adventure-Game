package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.Element.Transport.Bike;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Player extends GameElement {
    public Player(int x, int y) {
        super(x, y);
        beingControlled = true; // Set beingControlled to true in the constructor
        layer = 100; // Set the layer value higher than the road's layer
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);

        // Draw eyes based on movement direction
        g2d.setColor(Color.BLACK);
        switch (direction) {
            case UP:
                g2d.fillOval(x + 8, y + 2, 4, 4); // Left eye
                g2d.fillOval(x + 16, y + 2, 4, 4); // Right eye
                break;
            case DOWN:
                g2d.fillOval(x + 8, y + 20, 4, 4); // Left eye
                g2d.fillOval(x + 16, y + 20, 4, 4); // Right eye
                break;
            case LEFT:
                g2d.fillOval(x + 4, y + 6, 4, 4); // Left eye
                g2d.fillOval(x + 12, y + 6, 4, 4); // Right eye
                break;
            case RIGHT:
                g2d.fillOval(x + 12, y + 6, 4, 4); // Left eye
                g2d.fillOval(x + 20, y + 6, 4, 4); // Right eye
                break;
            default:
                break;
        }
    }}
