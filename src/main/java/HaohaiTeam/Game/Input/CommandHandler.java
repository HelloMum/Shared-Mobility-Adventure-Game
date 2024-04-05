package HaohaiTeam.Game.Input;

import HaohaiTeam.Game.Element.Coin;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Gem;
import HaohaiTeam.Game.Element.Transport.TransportMode;

public class CommandHandler implements CommandListener {
    @Override
    public void onPickedCoin(GameElement element) {
        System.out.println("Coin picked!");
        // Handle coin picked event
    }

    @Override
    public void onPickedGem(GameElement element) {
        System.out.println("Gem picked!");
        // Handle gem picked event
    }

    @Override
    public void onTransportEntered(GameElement element) {
        // Handle transport entered event
    }

    @Override
    public void onTransportExited(GameElement element) {
        // Handle transport exited event
    }
}
