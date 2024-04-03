package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Logic.currentTransport;

public abstract class TransportMode extends GameElement {
    protected double carbonFootprint;
    protected currentTransport currentTransportState; // Adding the internal variable

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0; // These are the defaults properties
        this.currentTransportState = currentTransport.NOT_DEFINED; // Default to NOT_DEFINED
    }

    public currentTransport getTransportState() {
        return currentTransportState; // Return the current transport state
    }

    public void setTransportState(currentTransport newState) {
        this.currentTransportState = newState; // Set the current transport state
    }
}