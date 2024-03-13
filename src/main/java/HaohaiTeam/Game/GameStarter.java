package HaohaiTeam.Game;
import HaohaiTeam.Game.Map.MapLoader;

public class GameStarter {

    public static void main(String[] args) {
        System.out.println("Welcome to Dublin!");
        GameStarter gameStarter = new GameStarter();
        gameStarter.startApp();
    }

    public void startApp() {
        // Create a MapLoader instance, this could be used to send a specific map
        MapLoader mapLoader = new MapLoader();


        // Load the starting screen
        mapLoader.loadStartingScreen();

    }
}