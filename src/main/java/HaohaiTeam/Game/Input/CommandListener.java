package HaohaiTeam.Game.Input;

import HaohaiTeam.Game.Element.GameElement;


public interface CommandListener {
    void onPickedCoin(GameElement element);
    void onPickedGem(GameElement element);
    void onTransportEntered(GameElement element);
    void onTransportExited(GameElement element);
    void onTick();


}
