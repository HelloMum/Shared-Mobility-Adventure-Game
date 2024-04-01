package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Logic.currentTransport;

public abstract class TransportMode extends GameElement {
    protected double carbonFootprint;

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0;
    }
    public abstract currentTransport getTransportState();
}