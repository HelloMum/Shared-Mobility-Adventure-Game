package HaohaiTeam.App;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.GameStarter;
import HaohaiTeam.Game.Logic.TickGenerator;

public class AppStarter {

    public static void main(String[] args) {
        // Create an instance of the AppStarter class and run it
        System.out.println("App is starting...");
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();

        // add main code to start ticks
        TickGenerator tickGenerator = new TickGenerator(100);
        GameElement.DefaultListener exampleListener = new GameElement.DefaultListener();
        tickGenerator.addTickListener(exampleListener);
        tickGenerator.start();
    }

    public void startApp() {
        System.out.println("App is starting...");

    }
}
