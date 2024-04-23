package haohaiTeam.game.element.transport.onRoute.stationRoad;


import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Station extends Road {
    public AutoMoveTransport clearTransportReference = null; // Reference to the transport

    public static final double CO2_PER_CELL = 99; /// This should not be used, if big CO2 check the issue
    public int distanceNext = 0; /// This should not be used, if big CO2 check the issue

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
    }

    protected void clearTransportReference() {
        this.clearTransportReference = null;
    }
    public void setDistanceNextStation(int distance) {
        System.out.println("distance" + distance);
        this.distanceNext = distance;

    }
    @Override
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        if (gameElement instanceof AutoMoveTransport transport) {
            this.clearTransportReference = transport;
            clearTransportReference.toggleAutoStation();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    clearTransportReference();
                    gameElement.toggleAutoStation();
                }
            }, 3000);
        } else if (gameElement instanceof Player player) {
            if (clearTransportReference != null) {
                clearTransportReference.linkElement(player);
                player.moveToLinked();
                player.setBeingControlled(false);
            }
        }
    }
}