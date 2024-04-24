package haohaiTeam.game.logic;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.input.CommandListener;
import haohaiTeam.game.map.MapLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;

import static haohaiTeam.game.element.GameElement.elements;

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
    private static final int MAX_CO2_LEVEL = 100;
    private Timer timer;
    private static final long TIMER_DELAY = 300;
    public static boolean co2increase = false;
    public static boolean saveGame = false;


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
                if (gameOver == true) {
                    cancel();
                } else {
                    updateElapsedTime(1);
                    if (co2increase == true) {
                        increaseCO2();
                    }
                    trackCO2Level();

                    if (isGameOver() == true || winningCondition() == true) {
                        //MapLoader.loadNextLevel();
                    }
                    if (saveGame == true) {
                        saveGame();
                        saveGame = false;
                    }
                }
            }
        }, TIMER_DELAY, TIMER_DELAY);
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    private void setScore(int newScore) {
        score = newScore;
    }

    private void setLives(int newLives) {
        lives = newLives;
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
    }

    public void increaseCO2() {
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

    public void setCoinsCollected(int newCoins) {
        coinsCollected = newCoins;
    }

    public void setGemsAcquired(int newGems) {
        gemsAcquired = newGems;
    }

    public void setCO2Collected(int newco2) {
        co2Collected = newco2;
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
        if (resetTriggered == true) {
            // Need to insert something to call a
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
        } else if (this.getGemsAcquired() == 20) {
            resetTriggered = true;
        }
        loseALive();
        losingCondition();
        System.out.println("GameConditionsHaveBeenChecked");
    }

    @Override
    public void onTick() {
        tickCount++;
        if (tickCount % 12000 == 0) {
            checkGameConditions();
        }
    }

    @Override
    public void onCO2Generated(int value) {

    }

    public void saveGame() {
        String fileExtension = ".json";
        String filePath = "Saved_Game_" + new Date().toString() + fileExtension;

        JSONObject savedGame = new JSONObject();

        savedGame.put("coinsCollected", coinsCollected);
        savedGame.put("gemsAcquired", gemsAcquired);
        savedGame.put("score", score);
        savedGame.put("co2Collected", co2Collected);
        savedGame.put("lives", lives);

        JSONArray elementsArray = new JSONArray(elements);
        savedGame.put("map", elementsArray);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(savedGame.toString());
            System.out.println("Game saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Save Game Error: " + e.getMessage());
            e.printStackTrace();

        }
    }

    private void loadScoreFromJson(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);

            int coins = json.getInt("coinsCollected");
            int gems = json.getInt("gemsAcquired");
            int score = json.getInt("score");
            int co2 = json.getInt("co2Collected");
            int lives = json.getInt("lives");

            this.setCoinsCollected(coins);
            this.setGemsAcquired(gems);
            this.setScore(score);
            this.setCO2Collected(co2);

            System.out.println("Game loaded from: " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

