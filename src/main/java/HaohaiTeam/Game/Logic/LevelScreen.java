package HaohaiTeam.Game.Logic;

import java.awt.*;

public class LevelScreen extends GameStatus {
    private GameStatus gameStatus;
    private boolean gameOver;

    public LevelScreen(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.gameOver = false;
    }
    
    public void checkGameOver() {
        if (gameStatus.isGameOver()) {
            gameOver = true;
        }
    }
    
    public void draw(Graphics g) {
        if (gameOver) {
            // Draw the rectangle
            g.setColor(Color.WHITE);
            g.fillRect(100, 100, 400, 200);
            
            // Draw text
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Level Finished!", 200, 150);
            
            // Display score, gem, coin, etc.
            int score = gameStatus.getScore();
            int gems = gameStatus.getGemsAcquired();
            int coins = gameStatus.getCoinsCollected();
            int c02 = getCO2Collected();
            
            g.drawString("Score: " + score, 150, 200);
            g.drawString("Gems: " + gems, 150, 230);
            g.drawString("Coins: " + coins, 150, 260);
            g.drawString("C02: " + c02, 150, 290);
        }
    }
}
