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
        // Draw the fake shadow
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillOval(renderX + 5, renderY + 5, CELL_SIZE, CELL_SIZE);


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
    }
    @Override
    public void helperDrawer(Graphics2D g2d) {
        int stepSizeScaled = 5; // 1.5 * 2 = 3

        // Calculate the direction of movement
        int dx = (prevX - renderX) * 2;
        int dy = (prevY - renderY) * 2;

        // Move renderX and renderY towards prevX and prevY by the scaled step size
        if (Math.abs(dx) > stepSizeScaled) {
            renderX += (int) (Math.signum(dx) * (stepSizeScaled / 2));
        } else {
            renderX = prevX;
        }

        if (Math.abs(dy) > stepSizeScaled) {
            renderY += (int) (Math.signum(dy) * (stepSizeScaled / 2));
        } else {
            renderY = prevY;
        }

        // Draw using the updated positions
        draw(g2d);

        // Save the current position for the next pass
        this.prevX = X;
        this.prevY = Y;
    }

}
