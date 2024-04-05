package HaohaiTeam.Game.Element;

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
        g2d.fillOval(renderX, renderY, CELL_SIZE, CELL_SIZE);

        // Draw eyes based on movement direction
        g2d.setColor(Color.BLACK);
        switch (direction) {
            case UP:
                g2d.fillOval(renderX + 8, renderY + 2, 4, 4); // Left eye
                g2d.fillOval(renderX + 16, renderY + 2, 4, 4); // Right eye
                break;
            case DOWN:
                g2d.fillOval(renderX + 8, renderY + 20, 4, 4); // Left eye
                g2d.fillOval(renderX + 16, renderY + 20, 4, 4); // Right eye
                break;
            case LEFT:
                g2d.fillOval(renderX + 4, renderY + 6, 4, 4); // Left eye
                g2d.fillOval(renderX + 12, renderY + 6, 4, 4); // Right eye
                break;
            case RIGHT:
                g2d.fillOval(renderX + 12, renderY + 6, 4, 4); // Left eye
                g2d.fillOval(renderX + 20, renderY + 6, 4, 4); // Right eye
                break;
            default:
                break;
        }
    }}
