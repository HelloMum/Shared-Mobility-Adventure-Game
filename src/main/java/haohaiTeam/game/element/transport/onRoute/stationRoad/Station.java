package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.PopUp;
import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Station extends Road {
    public AutoMoveTransport transportReference; // Reference to the transport

    public static final double CO2_PER_CELL = 99;
    public int distanceNext = 99;

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
        this.transportReference = null;
    }

    protected void clearTransportReference() {
        this.transportReference = null;
    }

    public void setDistanceNextStation(int distance) {
        this.distanceNext = distance;
    }
    public int getDistanceNextStation() {
        return distanceNext;
    }

    @Override
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        if (gameElement instanceof AutoMoveTransport transport) {
            correctStationMethod(transport);
        }
    }
    public void correctStationMethod(AutoMoveTransport transport) {
        this.transportReference = transport;
        this.transportReference.toggleAutoStation();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                transport.toggleAutoStation();
                clearTransportReference();
            }
        }, 3000);
    }
    @Override
    public void handleNearbyElement(GameElement element) {
        System.out.println("The next station is at " + distanceNext);
        new PopUp(this.X, this.Y,"The next station cost " + distanceNext + " CO2",3000);
    }

    @Override
    public void onBeingCollidedOnYou(GameElement element) {
        new PopUp(this.X, this.Y,"The next station cost " + distanceNext + " CO2",3000);

        if (element instanceof Player player) {
            if (transportReference != null) {
                this.transportReference.linkElement(element);
                element.linkElement(this.transportReference);
                element.setBeingControlled(false);
                element.moveToLinked();
            }
        }
    }
}
