package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import java.awt.*;
public abstract class TransportMode extends GameElement {
    protected double carbonFootprint;

    public TransportMode(int x, int y) {
        super(x, y);
        this.carbonFootprint = 0;

    }
}