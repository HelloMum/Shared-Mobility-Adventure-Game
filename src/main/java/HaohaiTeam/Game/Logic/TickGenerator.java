package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Input.CommandListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickGenerator {
    private static ScheduledExecutorService executor;
    private static int tickRate;
    private static CommandListener commandListener;

    public TickGenerator(GameStatus gameStatus) {
        tickRate = 100;
        commandListener = gameStatus; // we need to start this later
        executor = Executors.newSingleThreadScheduledExecutor(); // Create a thread for tick time tracking
    }

    public static void start() {
        executor.scheduleAtFixedRate(() -> {
            if (commandListener != null) {
                commandListener.onTick();
                System.out.println("Tac!");
            }
        }, 0, tickRate, TimeUnit.MILLISECONDS);
    }

    public static void setCommandListener(CommandListener commandListener) {
        TickGenerator.commandListener = commandListener;
    }

    public static void stop() {
        executor.shutdown();
    }

    public static void addTickListener(Runnable listener) {
        // Implement if needed
    }

    public static void changeTickRate(int tickRate) {
        TickGenerator.tickRate = tickRate;
    }

    public static void removeTickListener(Runnable listener) {
        // Implement if needed
    }
}
