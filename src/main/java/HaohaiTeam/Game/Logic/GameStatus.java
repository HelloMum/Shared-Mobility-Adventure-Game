package HaohaiTeam.Game.Logic;


import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Input.CommandListener;

public class GameStatus implements CommandListener {
    private int score = 0;
    private int lives = 3; // assume 3 lives at start
    private int coinsCollected = 0;
    private int gemsAcquired = 0;
    private int co2Collected = 0;
    private long elapsedTimeInSeconds = 0; // Variable to track elapsed time
    private boolean gameOver = false;

    public void addScore(int points) {
        this.score += points;
    }
    public enum currentTransport {
/// implement current transport
    }
    public void loseLife() {
        this.lives--;
        if (this.lives <= 0) {
            this.gameOver = true;
        }
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
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
        int points = numGems * 50;
        addScore(points);
    }

    public void addCO2(int amount) {
        this.co2Collected += amount;
        // Adjust score or perform any other relevant actions
        System.out.println(amount + " CO2 added to the game status.");
    }

    // Method to update elapsed time
    public void updateElapsedTime(long elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds += elapsedTimeInSeconds;
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
    }

    @Override
    public void onTransportEntered(GameElement element) {

    }

    @Override
    public void onTransportExited(GameElement element) {

    }

    @Override
    public void onTick() {

    }
}
