package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Input.CommandListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickGenerator implements CommandListener {
    private static ScheduledExecutorService executor;
    private static int tickRate;
    private static CommandListener commandListener;

    public TickGenerator() {
        tickRate = 100;
        executor = Executors.newSingleThreadScheduledExecutor(); // Create a thread for tick time tracking
        System.out.println("Tic");

    }

    public void start() {
        System.out.println("Start");
        executor.scheduleAtFixedRate(() -> {
            if (commandListener != null) {
                this.onTick();
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

    @Override
    public void onPickedCoin(GameElement element) {

    }

    @Override
    public void onPickedGem(GameElement element) {

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
