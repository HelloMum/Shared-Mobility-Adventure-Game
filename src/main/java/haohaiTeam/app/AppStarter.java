package haohaiTeam.app;
import haohaiTeam.game.GameStarter;

public class AppStarter {

    public static void main(String[] args) {
        // Create an instance of the AppStarter class and run it
        System.out.println("App is starting...");
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();
        
    }

    public void startApp() {
        System.out.println("App is starting...");

    }
}