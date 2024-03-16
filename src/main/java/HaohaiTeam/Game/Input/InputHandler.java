package HaohaiTeam.Game.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class InputHandler extends KeyAdapter{

    private CommandListener commandListener;

    public InputHandler(CommandListener commandListener) {
        // Constructor
        this.commandListener = commandListener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        commandListener.listenForCommands(e);
    }

    public void handleInput() {
        // Method to handle input
        System.out.println("Handling input");
    }
}
