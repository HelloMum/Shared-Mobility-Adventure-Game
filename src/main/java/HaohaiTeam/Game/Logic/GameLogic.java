package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Input.CommandListener;

import java.awt.*;

public class GameLogic implements CommandListener {

// Example of good use gameStatus
//    public void render(Graphics2D g) {
//        // Render the HUD overlay
//        g.setColor(Color.DARK_GRAY);
//        g.setFont(new Font("Arial", Font.BOLD, 20));
//        g.drawString("Score: " + gameStatus.getScore(), 20, 30);
//        g.drawString("Coins: " + gameStatus.getCoinsCollected(), 20, 60);
//        g.drawString("Gems: " + gameStatus.getGemsAcquired(), 20, 90);
//        // Add more HUD elements as needed
//    }
    public void losingCondition(GameElement element) {
    }
    public void loseALive(GameElement element) {
    }
    @Override
    public void onPickedCoin(GameElement element) {

    }

    @Override
    public void onPickedGem(GameElement element) {

    }

    @Override
    public void onTick() {

    }
}
