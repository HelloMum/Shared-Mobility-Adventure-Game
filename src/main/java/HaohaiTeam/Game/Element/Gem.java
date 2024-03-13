package HaohaiTeam.Game.Element;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Gem extends GameElement {
    private static final Color GEM_COLOR = Color.PINK;

    public Gem(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(GEM_COLOR);
        g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
    }
}
