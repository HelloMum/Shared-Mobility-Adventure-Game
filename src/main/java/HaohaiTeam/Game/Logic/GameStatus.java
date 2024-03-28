package HaohaiTeam.Game.Logic;

public class GameStatus {
    // Used for managing game states, such as player scores and health points
    private int score = 0;
    private int lives = 3; // assume 3 lives at start
    private boolean gameOver = false;

    public void addScore(int points) {
        this.score += points;
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

    // add more methods as needed
}

