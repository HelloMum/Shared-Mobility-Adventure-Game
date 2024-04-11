package HaohaiTeam.Game;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Map.MapLoader;

public class GameStarter {

    public void startGame() { // 之前的startApp现在改名为startGame
        System.out.println("Welcome to Dublin!");
        GameWindow gameWindow = new GameWindow(); // Create a new gameWindows (Empty)
        MapLoader mapLoader = new MapLoader(gameWindow); // Create the map element
        mapLoader.loadMap(MapLoader.level_1); // Load the level 1 onto the map
        gameWindow.openWindow(); // Open the window
    }

    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter(); // Start the above functions
        gameStarter.startGame(); // Start the game
    }

}