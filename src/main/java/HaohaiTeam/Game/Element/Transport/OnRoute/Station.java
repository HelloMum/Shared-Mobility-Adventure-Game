package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;

import java.util.Set;

public abstract class Station extends Road {

    public Station(int x, int y) {
        super(x, y);
        this.walkable = true;
    }
    @Override
    public void interactKeyPressedOnYou(GameElement gameElement) {
    }       // If player wants to interact and bus linked to station give move player onto the bus and link to it
    public void goingToBeWalkedOverBy(GameElement gameElement) {
        // If this will be override by subclass with their elements if bus on top stop move of bus for 3 seconds and keep the vehicle instance in case player wants  to get on bus,
        // if player wants to get on bus move player take out control until next station
    }
}
