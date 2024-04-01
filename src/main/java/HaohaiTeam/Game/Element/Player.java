package HaohaiTeam.Game.Element;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.Logic.currentTransport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;



public class Player extends GameElement {
    public Player(int x, int y) {
        super(x, y);
    }

    public currentTransport state = currentTransport.WALKING;

    public boolean beingControlled = true;

    public void setState(currentTransport state) {
        this.state = state;
    }

    public currentTransport getState() {
        return state;
    }

    // Using a means of transportation
    // This method sets the current transportation mode in use.
    public void useTransport(currentTransport transport) {
        this.state = transport;
    }

    // Cease using the means of transportation, return to walking
    // This method discontinues the use of any transportation and resets it to walking.
    public void stopUsingTransport() {
        this.state = currentTransport.WALKING; // Reset to walking state
        // Other logic...
        // Update the player's position and visibility as necessary after stopping the transport
    }

    @Override
    public void move(int dx, int dy) {
        if (getState() != currentTransport.WALKING) {
            super.move(dx, dy); // Move the player by walking (Normal speed)
        }
        // The movement logic might vary when a means of transportation is being used
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (getState() != currentTransport.WALKING) { // Check if the player is not in the walking state
            g2d.setColor(Color.BLUE);
            g2d.fillOval(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE);
        } else {
            g2d.setColor(Color.CYAN);
            g2d.fillOval(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // Draw the player
        }
    }

    }
