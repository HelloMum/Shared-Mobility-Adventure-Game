package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Logic.PlayerTransportState;

public abstract class TransportMode extends GameElement {
    protected double carbonFootprint;

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0;
    }
    public abstract PlayerTransportState getTransportState();
}