package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;

import java.util.Timer;
import java.util.TimerTask;


public abstract class Station extends Road {

    public Station(int x, int y) {
        super(x, y);
        this.walkable = true;
    }

    @Override
    public void interactKeyPressedOnYou(GameElement gameElement) {
        // If player wants to interact and bus linked to station give move player onto the bus and link to it
    }

    // Override on children

    }