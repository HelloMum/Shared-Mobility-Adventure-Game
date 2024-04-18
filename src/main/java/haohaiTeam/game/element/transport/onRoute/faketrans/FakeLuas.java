package haohaiTeam.game.element.transport.onRoute.faketrans;

import java.awt.*;

public class FakeLuas extends FakeVehicle {
    public FakeLuas(int x, int y) {
        super(x, y);
    }

    @Override
    protected double getCO2PerCell() {
        return 0.7;
    }

    @Override
    public void draw(Graphics2D g2d) {
    }
}


