package HaohaiTeam.Game.Element;
import HaohaiTeam.Game.Logic.currentTransport;
import java.awt.*;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Player extends GameElement {
    public Player(int x, int y) {
        super(x, y);
    }
    public currentTransport state = currentTransport.WALKING;
    public boolean beingControlled = true;

    //Setter method currentTransport
    public void setState(currentTransport state) {
        this.state = state;
    }

    //Getter method currentTransport
    public currentTransport getState() {
        return state;
    }

    // This method discontinues the use of any transportation and resets it to walking.
    public void stopUsingTransport() {
        this.state = currentTransport.WALKING; // Reset to walking state
        // Update the player's position and visibility as necessary after stopping the transport
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (getState() != currentTransport.WALKING) { // Check if the player is not in the walking state
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
        } else {
            g2d.setColor(Color.CYAN);
            g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE); // Draw the player
        }
    }


    }
