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
        // Add more HUD elements as needed

        // Draw CO2 level indicator as a rectangle
        int co2Level = gameStatus.getCO2Collected();
        int maxCO2Level = 100;
        int maxWidth = 200; // Maximum width of the CO2 indicator rectangle
        int currentWidth = (int) ((double) co2Level / maxCO2Level * maxWidth);
        int x = 20; // X-coordinate of the CO2 indicator rectangle
        int y = 150; // Y-coordinate of the CO2 indicator rectangle
        int height = 20; // Height of the CO2 indicator rectangle
        g.setColor(Color.RED); // Set color for CO2 indicator
        g.fillRect(x, y, currentWidth, height);
        g.setColor(Color.BLACK); // Set color for outline
        g.drawRect(x, y, maxWidth, height);
    }
}