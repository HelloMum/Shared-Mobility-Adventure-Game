package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.Navigation.Route;
import HaohaiTeam.Game.Navigation.Trip;
import java.awt.*;
import java.awt.event.KeyEvent;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Player extends GameElement {
    private Trip playerTrip;
    // Assuming there is a status flag indicating whether the player is currently using a means of transportation.
    public boolean usingTransport = false;
    private TransportMode currentTransport;

    public Player(int x, int y) {
        super(x, y);
        boolean beingControlled = true; // Set beingControlled to true by default

    }
    public Player(int x, int y, Route route) {
        super(x, y);
        boolean beingControlled = true; // Set beingControlled to true by default
        this.playerTrip = new Trip(route);
    }


    public void setTrip(Route route) {
        this.playerTrip = new Trip(route);
    }

    // Using a means of transportation
    // This method sets the current transportation mode in use.
    public void useTransport(TransportMode transport) {
        this.usingTransport = true; // Set the flag indicating that a transport is being used
        this.currentTransport = transport; // Assign the current transport object
        // Adjust the player's position and visibility based on requirements
    }

    // Cease using the means of transportation, return to walking
    // This method discontinues the use of any transportation and resets it to walking.
    public void stopUsingTransport() {
        this.usingTransport = false; // Reset the flag to indicate no transport is being used
        this.currentTransport = null; // Clear the reference to the current transport
        // Update the player's position and visibility as necessary after stopping the transport
    }

    public void handleKeyEvent(KeyEvent e) {
        // Player movement control when not using a means of transportation
        if (!usingTransport && beingControlled) {
            // Logic for controlling player movement while walking
        }
        // Different processing or ignoring keyboard input may be needed when a means of transportation is being used
    }
        @Override
        public void move(int dx, int dy) {
            if (!usingTransport) {
                super.move(dx, dy); // Move the player by walking (Normal speed)
            }
            // The movement logic might vary when a means of transportation is being used
        }

        @Override
        public void draw(Graphics2D g2d) {
            if (!usingTransport) {
                g2d.setColor(Color.BLUE);
                g2d.fillOval(x, y, CELL_SIZE, CELL_SIZE);
            } else {
                g2d.setColor(Color.cyan);
                g2d.fillOval(getPosX(), getPosY(), CELL_SIZE, CELL_SIZE); // Draw the player
            }
        }

    }
