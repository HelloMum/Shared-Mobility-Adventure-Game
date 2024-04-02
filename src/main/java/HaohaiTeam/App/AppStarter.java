package HaohaiTeam.App;
import HaohaiTeam.Game.GameStarter;
import HaohaiTeam.Game.Logic.TickGenerator;
import HaohaiTeam.Game.Logic.DefaultListener;

public class AppStarter {

    public static void main(String[] args) {
        // Create an instance of the AppStarter class and run it
        System.out.println("App is starting...");
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();

        // add main code to start ticks
        TickGenerator tickGenerator = new TickGenerator(100);
        DefaultListener exampleListener = new DefaultListener();
        tickGenerator.addTickListener(exampleListener);
        tickGenerator.start();
    }

    public void startApp() {
        System.out.println("App is starting...");

    }
}
