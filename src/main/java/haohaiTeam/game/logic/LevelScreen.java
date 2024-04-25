package haohaiTeam.game.logic;

import haohaiTeam.game.gui.GameWindow;
import haohaiTeam.app.AppStarter;

import java.awt.Font; 
import java.awt.*;

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
        if ((gameStatus.isGameOver() || gameStatus.isGameWon()) && gameStatus.shouldShowLevelScreen()) {
            // rectangle
            Color bgColour = new Color(0, 102, 17);
            g.setColor(bgColour);
            g.fillRect(0, 0, width, height);

            // text
            g.setColor(Color.WHITE);
            int fontSize = 20;
            Font font = new Font(AppStarter.gameFont.getName(), AppStarter.gameFont.getStyle(), fontSize);
            g.setFont(font);
            String gameOverMessage = "Game Over!";
            String winMessage = "Level finished!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOverMessage);
            int winWidth = g.getFontMetrics().stringWidth(winMessage);
            int widthWinMessage = (width - winWidth) / 2; // center the text
            int widthLoseMessage = (width - gameOverWidth) / 2;

            int score = gameStatus.getScore();
            int gems = gameStatus.getGemsAcquired();
            int coins = gameStatus.getCoinsCollected();
            int c02 = gameStatus.getCO2Collected();
            long timeCost = (gameStatus.getElapsedTimeInMileSeconds() / 1000);

            g.drawString("Score: " + score, 150, 200);
            g.drawString("Gems: " + gems, 150, 230);
            g.drawString("Coins: " + coins, 150, 260);
            g.drawString("C02: " + c02, 150, 290);
            g.drawString("Cost Time: " + timeCost, 150, 320);

            //Enviroventure at the bottom:
            String enviroVentureText = "EnviroVenture";
            int enviroVentureWidth = g.getFontMetrics().stringWidth(enviroVentureText);
            int enviroVentureX = (width - enviroVentureWidth) / 2;
            int enviroVentureY = height - 50;
            g.drawString(enviroVentureText, enviroVentureX, enviroVentureY);

            if (this.gameStatus.isGameWon()) {
                g.drawString(winMessage, widthWinMessage, 50);
            } else {
                if (this.gameStatus.isGameOver()) {
                    g.drawString(gameOverMessage, widthLoseMessage, 50);
                }
            }
        }
    }
}
