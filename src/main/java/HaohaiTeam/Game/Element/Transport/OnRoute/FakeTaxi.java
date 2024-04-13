package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.awt.*;

public class FakeTaxi extends FakeVehicle {
    public FakeTaxi(int x, int y) {
        super(x, y);
    }

    @Override
    protected double getCO2PerCell() {
        return 1.0;
    }

    @Override
    public void draw(Graphics2D g2d) {
    }
}

