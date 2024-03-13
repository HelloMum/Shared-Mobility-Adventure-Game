package HaohaiTeam.App;
import HaohaiTeam.Game.GameStarter;
public class AppStarter {

    public static void main(String[] args) {
        // Create an instance of the AppStarter class and run it
        GameStarter gameStarter = new GameStarter();
        gameStarter.startApp();
    }

    public void startApp() {
        System.out.println("App is starting...");

    }
}
