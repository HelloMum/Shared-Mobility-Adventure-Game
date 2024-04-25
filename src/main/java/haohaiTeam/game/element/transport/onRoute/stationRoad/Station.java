package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;


import java.util.Timer;
import java.util.TimerTask;

public abstract class Station extends Road {
    public AutoMoveTransport transportReference; // Reference to the transport
    //    CommandListener commandListener;
    public static final int CO2_PER_CELL = 0;
    public int distanceNext = 0;

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
        this.transportReference = null;
    }

    protected void clearTransportReference() {
        this.transportReference = null;
    }

    public void setDistanceNextStation(int distance) {
        System.out.println("Distance next station set to" + distance);
        this.distanceNext = distance;
    }

    public int getDistanceNextStation() {
        return distanceNext;
    }

    @Override
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        if (gameElement instanceof AutoMoveTransport transport) {
            this.transportReference = (AutoMoveTransport) gameElement;
            this.transportReference.toggleAutoStation();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    transport.toggleAutoStation(); // Changed to transport
                    clearTransportReference();
                }
            }, 3000);
        }
    }

    @Override
    public void handleNearbyElement(GameElement element) {
        System.out.println("This station takes " + getDistanceNextStation() + "to the next station!");
    }

    @Override
    public void onBeingCollidedOnYou(GameElement element) {
        System.out.println("This station takes " + getDistanceNextStation() + "to the next station!");
        if (element instanceof Player player) {
            if (transportReference != null) {
                this.transportReference.linkElement(element);
                element.linkElement(this.transportReference);
                element.setBeingControlled(false);
                element.moveToLinked();
                // if player linked with vehicles, we should add co2 as we know from current station
                // here use getDistanceNextStation() * CO2_PER_CELL to calculate co2
                // record the trips and vehicles that the user used as we need to implement pop-up environment impact
                this.commandListener.onCO2Generated((int)this.getDistanceNextStation() * CO2_PER_CELL);
            }
        }
    }
}
