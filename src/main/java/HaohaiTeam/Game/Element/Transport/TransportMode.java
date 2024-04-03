package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Logic.currentTransport;

public abstract class TransportMode extends GameElement {
    protected double carbonFootprint;
    protected currentTransport transportType; // Adding the internal variable

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0; // These are the defaults properties
        this.transportType = currentTransport.NOT_DEFINED; // Default to NOT_DEFINED
    }

    public currentTransport getTransportState() {
        return transportType; // Return the current transport state
    }

    public void setTransportState(currentTransport newState) {
        this.transportType = newState; // Set the current transport state
    }

}