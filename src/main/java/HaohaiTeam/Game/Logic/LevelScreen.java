package HaohaiTeam.Game.Logic;

import java.awt.*;
import HaohaiTeam.Game.GUI.GameWindow;
public class LevelScreen {
    private GameStatus gameStatus;
    private int width;
    private int height;
    
    public LevelScreen(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.width = GameWindow.FRAME_WIDTH;
        this.height = GameWindow.FRAME_HEIGHT;
        System.out.println("-------------------------");
        System.out.println("Level Screen Created");
        System.out.println("-------------------------");
    }
    
    public void render(Graphics g) {
        if (gameStatus.isGameOver() == true || this.gameStatus.winningCondition() == true) {
        // rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        
        // text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String gameOverMessage = "Game Over!";
        String winMessage = "Level finished!";
        int messageWidth = g.getFontMetrics().stringWidth(gameOverMessage);
        int x = (width - messageWidth) / 2; // center the text 
        
        int score = gameStatus.getScore();
        int gems = gameStatus.getGemsAcquired();
        int coins = gameStatus.getCoinsCollected();
        int c02 = gameStatus.getCO2Collected();
        
        g.drawString("Score: " + score, 150, 200);
        g.drawString("Gems: " + gems, 150, 230);
        g.drawString("Coins: " + coins, 150, 260);
        g.drawString("C02: " + c02, 150, 290);
        
        //Enviroventure at the bottom:
        String enviroVentureText = "EnviroVenture";
        int enviroVentureWidth = g.getFontMetrics().stringWidth(enviroVentureText);
        int enviroVentureX = (width - enviroVentureWidth) / 2;
        int enviroVentureY = height - 50;
        g.drawString(enviroVentureText, enviroVentureX, enviroVentureY);
    
        if (this.gameStatus.isGameOver() == true) {
            g.drawString(gameOverMessage, x, 50);
        }
        if (this.gameStatus.winningCondition() == true) {
            g.drawString(winMessage, x, 50);
        }
    }    
}
}
