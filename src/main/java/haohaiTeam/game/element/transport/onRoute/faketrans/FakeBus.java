package haohaiTeam.game.element.transport.onRoute.faketrans;

import java.awt.*;

public class FakeBus extends FakeVehicle {
    public FakeBus(int x, int y) {
        super(x, y);
    }

    @Override
    protected double getCO2PerCell() {
        return 0.5;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // no draw
    }
}

