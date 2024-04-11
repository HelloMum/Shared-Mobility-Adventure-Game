package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.PopUp;

import java.util.Timer;
import java.util.TimerTask;


public abstract class Station extends Road {

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
    }

    @Override
    public void interactKeyPressedOnYou(GameElement gameElement) {
        // If player wants to interact and bus linked to station give move player onto the bus and link to it
    }
    @Override
    public void onBeingCollidedOnYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);
        if (gameElement instanceof Player) {
            // If the collision is with a Wall, show a pop-up indicating inability to walk through walls
            new PopUp(this.X, this.Y,"This station is empty right now ... ");
        }
        // Notify the other element about the collision if needed
        gameElement.onBeingCollidedOnYou(this);
    }
    // Override on children

    }