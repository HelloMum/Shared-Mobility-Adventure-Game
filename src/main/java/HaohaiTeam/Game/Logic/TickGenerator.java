package HaohaiTeam.Game.Logic;

import java.util.ArrayList;
import java.util.List;

//Implement this interface in a class that is required to have a listener. The 'onTick' method will be called for each
//implementation of the interface, add to the method in those classes what needs to happen for that class, at a tick.
public class TickGenerator {
    private boolean running;
    private int tickRate;
    private List<TickListener> listeners = new ArrayList<>();
    //Creates empty list of objects implementing TickListener

    public TickGenerator(int tickRate) {
        this.tickRate = tickRate;
    }

    // Method to start the ticks
    public void start()
    //method to start ticks - the method calls the onTick method for all listeners in the listener list
    {
        running = true;
        while (running) {

            Thread.sleep(tickRate); // Wait for the tick rate

            // Call 'onTick' method for listeners on each tick
            for (int i = 0; i < listeners.size(); i++) {
                TickListener listener = listeners.get(i);
                listener.onTick();
            }
        }
    }
    // Method to stop the ticks
    public void stop() {
        running = false;
    }

    // Method to add a listener
    public void addTickListener(TickListener listener) {
        listeners.add(listener);
    }

    // Method to remove a listener
    public void removeTickListener(TickListener listener) {
        listeners.remove(listener);
    }
}
