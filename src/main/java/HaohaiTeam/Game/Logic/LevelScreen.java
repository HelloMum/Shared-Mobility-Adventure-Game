package HaohaiTeam.Game.Logic;

import java.awt.*;
import HaohaiTeam.Game.GUI.GameWindow;

public class LevelScreen extends GameStatus {
    private GameStatus gameStatus;
    private boolean gameOver;
    

    public LevelScreen(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.gameOver = false;
        System.out.println("-------------------------");
        System.out.println("Level Screen Created");
        System.out.println("-------------------------");


    }
    
    public void checkGameOver(Graphics2D g2d) {
        if (gameStatus.isGameOver()) {
            this.draw(g2d);
        }
    }
    
    public void draw(Graphics g) {
        if (gameOver) {
            // rectangle
            g.setColor(Color.WHITE);
            g.fillRect(100, 100, 400, 200);
            
            // text
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Level Finished!", 200, 150);
            
            // Display score, gem, coin, and C02
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
