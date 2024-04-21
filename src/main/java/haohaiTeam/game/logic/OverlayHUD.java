package haohaiTeam.game.logic;

import java.awt.*;

public class OverlayHUD {
    private GameStatus gameStatus;

    public OverlayHUD(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void render(Graphics2D g) {
        // Render the HUD overlay
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + gameStatus.getScore(), 20, 30);
        g.drawString("Coins: " + gameStatus.getCoinsCollected(), 20, 60);
        g.drawString("Gems: " + gameStatus.getGemsAcquired(), 20, 90);
        g.drawString("CO2: " + gameStatus.getCO2Collected(), 20, 120);

        // CO2 level indicator as a rectangle
        int currentWidth = (int) ((double) gameStatus.getCO2Collected() / 100 * 100);
        g.setColor(Color.RED); 
        g.fillRect(20, 150, currentWidth, 20);
        g.setColor(Color.BLACK); 
        g.drawRect(20, 150, 100, 20);
    }
}