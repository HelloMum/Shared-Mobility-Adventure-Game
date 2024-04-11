package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;

public abstract class TransportMode extends GameElement {
    protected int carbonFootprint;

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0; // These are the defaults properties
    }


}