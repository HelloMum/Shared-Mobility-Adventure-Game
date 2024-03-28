package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.Logic.PlayerTransportState;
import HaohaiTeam.Game.Navigation.Route;
import HaohaiTeam.Game.Navigation.Trip;
import java.awt.*;
import java.awt.event.KeyEvent;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;



public class Player extends GameElement {
    private Trip playerTrip;
    // Assuming there is a status flag indicating whether the player is currently using a means of transportation.
    public boolean usingTransport = false;
    private PlayerTransportState state = PlayerTransportState.WALKING; //default state is walking
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


    public void setState(PlayerTransportState state) {
        this.state = state;
    }

    public PlayerTransportState getState() {
        return state;
    }

    public void setTrip(Route route) {
        this.playerTrip = new Trip(route);
    }

    // Using a means of transportation
    // This method sets the current transportation mode in use.
    public void useTransport(TransportMode transport) {
        this.state = transport.getTransportState(); // Set the player's state to the corresponding transportation state
        this.currentTransport = transport; // Set the reference to the current transport
    }

    // Cease using the means of transportation, return to walking
    // This method discontinues the use of any transportation and resets it to walking.
    public void stopUsingTransport() {
        this.state = PlayerTransportState.WALKING; // Reset to walking state
        this.currentTransport = null;
        // Other logic...
        // Update the player's position and visibility as necessary after stopping the transport
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        // Check if the player is being controlled
        if (beingControlled) {
            int keyCode = e.getKeyCode();

            // Decide how to handle keyboard input based on the player's current state
            if (!usingTransport) {
                // Handle keyboard input when walking
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        move(0, -1); // Move up
                        break;
                    case KeyEvent.VK_DOWN:
                        move(0, 1); // Move down
                        break;
                    case KeyEvent.VK_LEFT:
                        move(-1, 0); // Move left
                        break;
                    case KeyEvent.VK_RIGHT:
                        move(1, 0); // Move right
                        break;
                    // Other walking control logic...
                }
            } else {
                // Handle keyboard input when using a transport
                // Assuming 'usingTransport' being true means the player is using some form of transport
                if (state == PlayerTransportState.RIDING_BIKE) {
                    // Specific control logic for a bicycle
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            move(0, -speed); // Move up using current speed
                            break;
                        case KeyEvent.VK_DOWN:
                            move(0, speed); // Move down using current speed
                            break;
                        case KeyEvent.VK_LEFT:
                            move(-speed, 0); // Move left using current speed
                            break;
                        case KeyEvent.VK_RIGHT:
                            move(speed, 0); // Move right using current speed
                            break;
                        // Additional bicycle control logic can be added, such as accelerating or braking
                    }
                } else {
                    // Control logic for other transports (if applicable)
                    // Note: For transports like buses that move automatically along predetermined routes,
                    // keyboard input might not need to be handled here.
                }
            }
        }
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
