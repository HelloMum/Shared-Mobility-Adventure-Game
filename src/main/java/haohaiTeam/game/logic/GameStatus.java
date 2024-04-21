package haohaiTeam.game.logic;
import java.util.Timer;
import java.util.TimerTask;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.input.CommandListener;

public class GameStatus implements CommandListener {
    private int score = 0;
    private int lives = 3; // assume 3 lives at start
    private int coinsCollected = 0;
    private int gemsAcquired = 0;
    private int co2Collected = 0;
    private long elapsedTimeInSeconds = 0; // Variable to track elapsed time
    private boolean gameOver = false;
    private boolean resetTriggered = false;
    private int tickCount;
    private static final int MAX_CO2_LEVEL = 10;
    private Timer timer;
    private static final long TIMER_DELAY = 1000;


    public void addScore(int points) {
        this.score += points;
    }
    public GameStatus() {
        // Initialize the CO2 timer
        gameTimer();
    }
    public enum currentTransport {
    /// implement current transport
    }
    public void loseLife() {
        this.lives--;
        checkGameConditions();
    }
    public void gameTimer() {
        // Initialize the timer
        timer = new Timer();
        // Schedule the task to update elapsed time every second
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateElapsedTime(1);
                trackCO2Level(); // Increment elapsed time by 1 second
            }
        }, TIMER_DELAY, TIMER_DELAY);
    }
    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public void addCoins(int numCoins) {
        this.coinsCollected += numCoins;
        int points = numCoins * 10;
        addScore(points);
        System.out.println(numCoins + " coin(s) added to the game status. Total coins collected: " + coinsCollected);
    }


    public void addGems(int numGems) {
        this.gemsAcquired += numGems;
        checkGameConditions();
        int points = numGems * 50;
        addScore(points);
    }

    public void trackCO2Level() {
        if (this.getCO2Collected() > MAX_CO2_LEVEL) {
            this.setGameOver(true);
            System.out.println("CO2 level exceeded maximum amount. Game Over!");
        }
    }
    // Method to update elapsed time
    public void updateElapsedTime(long elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds += elapsedTimeInSeconds;
        this.co2Collected++;
    }

    // Method to get elapsed time
    public long getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getGemsAcquired() {
        return gemsAcquired;
    }

    public int getCO2Collected() {
        return co2Collected;
    }

    @Override
    public void onPickedCoin(GameElement element) {
        addCoins(1);
        System.out.println("Coin received signal");
    }

    @Override
    public void onPickedGem(GameElement element) {
        // Implementation for handling picked gems if needed
        addGems(1);
        System.out.println("Gem received signal");
        checkGameConditions();
    }
    public boolean losingCondition() {
        return false;
    }
    public boolean winningCondition() {
        if (resetTriggered == true ) {
            return true;
        }
        return false;
    }

    public boolean loseALive() {
        return false;
    }
    public void checkGameConditions() {
        if (this.getLives() == 0) {
            gameOver = true;
        }
        else if (this.getGemsAcquired() == 20) {
            resetTriggered = true;
        }
        loseALive();
        losingCondition();
        System.out.println("GameConditionsHaveBeenChecked");    }
    @Override
    public void onTick() {
        tickCount++;
        if (tickCount % 12000 == 0) {
            checkGameConditions();
        }
    }
}
