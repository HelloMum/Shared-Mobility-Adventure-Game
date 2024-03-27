package HaohaiTeam.Game.Element;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Coin extends GameElement {
    private static final Color COIN_COLOR = Color.YELLOW;

    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(COIN_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
}
