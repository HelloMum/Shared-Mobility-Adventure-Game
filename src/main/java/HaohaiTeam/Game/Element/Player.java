package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.Element.Transport.Bike;
import HaohaiTeam.Game.Element.Transport.TransportMode;
import HaohaiTeam.Game.Logic.currentTransport;

import java.awt.*;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class Player extends GameElement {
    public Player(int x, int y) {
        super(x, y);
        beingControlled = true; // Set beingControlled to true in the constructor
        layer = 100; // Set the layer value higher than the road's layer
    }
    public currentTransport state = currentTransport.WALKING;


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
        this.beingControlled = true; // to make player move we need set beingControlled to true
    }

    public void interactWith(TransportMode other) {
        this.setVisible(false);
        other.setBeingControlled(true);
        this.x = other.x;
        this.y = other.y;
        this.setState(other.getTransportState());
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
