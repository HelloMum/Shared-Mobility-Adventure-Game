package HaohaiTeam.Game.Element.Transport.OnRoute;

import java.util.Set;

public abstract class Station extends Road {

    public Station(int x, int y) {
        super(x, y);
        this.walkable = true;
    }
}
