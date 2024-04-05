package HaohaiTeam.Game.Input;

import HaohaiTeam.Game.Element.Coin;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Gem;
import HaohaiTeam.Game.Element.Transport.TransportMode;

public interface CommandListener {
    void onPickedCoin(GameElement element);
    void onPickedGem(GameElement element);
    void onTransportEntered(GameElement element);
    void onTransportExited(GameElement element);
}
