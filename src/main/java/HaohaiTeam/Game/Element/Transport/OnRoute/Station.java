package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.util.Set;

public abstract class Station extends Road {

    private Set<String> supportedTransportTypes;
    private int waitTimeInSeconds; // the wait-time in the station

    public Station(int x, int y, Set<String> supportedTransportTypes, int waitTimeInSeconds) {
        super(x, y);
        this.supportedTransportTypes = supportedTransportTypes;
        this.waitTimeInSeconds = waitTimeInSeconds;
    }

    public boolean supportsTransport(String transportType) {
        return supportedTransportTypes.contains(transportType);
    }

    public int getWaitTimeInSeconds() {
        return waitTimeInSeconds * 1000;
    }


    // This method is called when a transport arrives at the station
    public void handleTransportArrival(AutoMoveTransport transport) {
        if (supportsTransport(transport.getClass().getSimpleName())) {
            transport.stayAtStation(getWaitTimeInSeconds());
        }
    }
}
