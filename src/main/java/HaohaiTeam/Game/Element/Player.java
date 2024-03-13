
package HaohaiTeam.Game.Element;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Player extends GameElement {
    private static final Color PLAYER_COLOR = Color.BLUE;

    public Player(int x, int y) {
        super(x, y);
        boolean beingControlled = true; // Set beingControlled to true by default
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(PLAYER_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
}
