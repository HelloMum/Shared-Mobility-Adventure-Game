package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.PopUp;

public class Rails extends Road{
    public Rails(int x, int y) {
        super(x, y);
    }


    public void onBeingCollidedOnYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);
        if (gameElement instanceof Player) {
            new PopUp(this.X, this.Y,"These are for trains. You are not a train.");

        }
    }

}
