package HaohaiTeam.Game.Logic;

import java.awt.*;

public class OverlayHUD {
    private GameStatus gameStatus;

    public OverlayHUD(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void render(Graphics2D g) {
        // Render the HUD overlay
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + gameStatus.getScore(), 20, 30);
        g.drawString("Coins: " + gameStatus.getCoinsCollected(), 20, 60);
        g.drawString("Gems: " + gameStatus.getGemsAcquired(), 20, 90);
        // Add more HUD elements as needed
    }

    public void update(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}