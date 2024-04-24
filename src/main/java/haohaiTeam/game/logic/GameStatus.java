package haohaiTeam.game.logic;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.input.CommandListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;

import static haohaiTeam.game.element.GameElement.elements;

public class GameStatus implements CommandListener {
    private int score = 0;
    private static final int lives = 3; // assume 3 lives at start
    private int coinsCollected = 0;
    private int gemsAcquired = 0;
    private int co2Collected = 0;
    private long elapsedTimeInMileSeconds = 0; // Variable to track elapsed time
    private boolean gameOver = false;
    private int tickCount;
    private static final int MAX_CO2_LEVEL = 100;
    private Timer timer;
    private static final long TIMER_DELAY = 300;
    public static boolean co2increase = false;
    private static final int REQUIRED_GEMS = 20; // Number of gems required to win
    private boolean gameWon = false;

    // a timer for checking game time
    public static final long TIME_LIMIT_IN_MILESECONDS = 10 * 1000; // this is for limiting the player to pass current level in 600 seconds

    public static boolean saveGame = false;
    public GameStatus() {
        // Initialize the CO2 timer
        gameTimer();
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public long getElapsedTimeInMileSeconds() {
        return elapsedTimeInMileSeconds;
    }

    public int getCutDownTime() {
        if (TIME_LIMIT_IN_MILESECONDS - elapsedTimeInMileSeconds > 0) {
            return (int) (TIME_LIMIT_IN_MILESECONDS - elapsedTimeInMileSeconds) / 1000;
        }
        return 0;

    }

    private void setScore(int newScore) {
        score = newScore;
    }
    public void setGemsAcquired(int newGems) {
        gemsAcquired = newGems;
    }
    public void setCO2Collected(int newco2) {
        co2Collected = newco2;
    }
    public void setCoinsCollected(int newCoins) {
        coinsCollected = newCoins;
    }
    public void gameTimer() {
        // Initialize the timer
        timer = new Timer();
        // Schedule the task to update elapsed time every second
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameOver) {
                    cancel();
                } else {
                    updateElapsedTime(TIMER_DELAY);
                    if (co2increase) {
                        increaseCO2();
                    }
                    trackCO2Level();
                }
            }
        }, TIMER_DELAY, TIMER_DELAY);
    }

    // Method to update elapsed time
    public void updateElapsedTime(long elapsedTimeInMileSeconds) {
        this.elapsedTimeInMileSeconds += elapsedTimeInMileSeconds;
        // check if time limit is exceeded
        if (this.elapsedTimeInMileSeconds > TIME_LIMIT_IN_MILESECONDS) {
            System.out.println("Time's up! Game over!");
            setGameOver(true);
        }
    }

    public void trackCO2Level() {
        if (this.getCO2Collected() > MAX_CO2_LEVEL) {
            this.setGameOver(true);
            System.out.println("CO2 level exceeded maximum amount. Game Over!");
        }
    }

    public void increaseCO2() {
        this.co2Collected++;
    }

    public void addCO2(int co2Cost) {
        this.co2Collected += co2Cost;
        System.out.println(co2Cost + " CO2(s) added to the game status. Total Co2 costed: " + co2Collected);
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

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getGemsAcquired() {
        return gemsAcquired;
    }

    public int getCO2Collected() {
        return co2Collected;
    }

    public void checkGameConditions() {
        if (lives <= 0 || elapsedTimeInMileSeconds > TIME_LIMIT_IN_MILESECONDS || co2Collected > MAX_CO2_LEVEL) {
            gameOver = true;
            System.out.println("Game Over! You have lost.");
        } else if (gemsAcquired >= REQUIRED_GEMS) {
            gameOver = true;
            setGameWon(true);
            System.out.println("Congratulations! You have won.");
        }
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

    @Override
    public void onTick() {
        tickCount++;
        if (tickCount % 12000 == 0) {
            checkGameConditions();
        }
    }

    @Override
    public void onCO2Generated(int value) {
        addCO2(value);
    }

    public void saveGame() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateTime = dateFormat.format(new Date());
        String filePath = "Saved_Game_" + dateTime + ".json";

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