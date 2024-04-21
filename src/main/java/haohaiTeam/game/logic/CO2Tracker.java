package haohaiTeam.game.logic;

public class CO2Tracker {
    private static final int MAX_CO2_LEVEL = 100;

    private GameStatus gameStatus;

    public CO2Tracker(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void trackCO2Level() {
        if (gameStatus.getCO2Collected() > MAX_CO2_LEVEL) {
            gameStatus.setGameOver(true);
            System.out.println("CO2 level exceeded maximum. Game Over!");
        }
    }
}
