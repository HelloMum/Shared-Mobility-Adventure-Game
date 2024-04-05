package HaohaiTeam.Game.Input;

import HaohaiTeam.Game.Element.GameElement;

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

    @Override
    public void onTick() {

    }
}
