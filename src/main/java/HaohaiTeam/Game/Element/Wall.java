package HaohaiTeam.Game.Element;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Wall extends GameElement {
    private static final Color WALL_COLOR = Color.GRAY;

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(WALL_COLOR);
        g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }
}
