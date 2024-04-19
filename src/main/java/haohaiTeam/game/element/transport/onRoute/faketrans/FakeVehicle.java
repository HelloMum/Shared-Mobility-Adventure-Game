package haohaiTeam.game.element.transport.onRoute.faketrans;

import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;
import haohaiTeam.game.element.transport.onRoute.stationRoad.Station;

public abstract class FakeVehicle extends AutoMoveTransport {
    public FakeVehicle(int x, int y) {
        super(x, y);
        isVisible = false;
    }
}
