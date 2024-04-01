package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Element.Coin;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Gem;
import HaohaiTeam.Game.Element.Transport.*;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Navigation.Route;
import java.awt.*;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ElementBehavior {
    List<GameElement> elements;
    private Player player;
    public ElementBehavior() {
        // Constructor
        this.elements = new ArrayList<>();
    }

    public void addElement(GameElement element) {
        elements.add(element);
    }

    public void updateElements() {
        for (GameElement element : elements) {
            updateBehavior(element);
        }
    }

    public void updateBehavior(GameElement element) {
        // Method to update element behavior
        // Perform different actions based on the type of the element
        if (element instanceof Bike) {
            // Handle behavior for bicycles
            handleBikeBehavior((Bike) element);
        } else if (element instanceof Taxi) {
            // Handle behavior for taxis
            handleTaxiBehavior((Taxi) element);
        } else if (element instanceof Luas) {
            // Handle behavior for light rail transit (Luas)
            handleLuasBehavior((Luas) element);
        }
    }
    public void moveElementAlongRoute(GameElement element, Route route) {
        if (route.hasMorePoints()) {
            Point nextPoint = route.getNextPoint();

            element.x = nextPoint.x;
            element.y = nextPoint.y;
        }
    }
    private void handleBikeBehavior(Bike bike) {
        // Implement specific behaviors for the bike here, such as adjusting speed or direction, etc.
    }

    private void handleTaxiBehavior(Taxi taxi) {
        // Implement specific behaviors for the taxi here, such as changing routes based on trips, etc.
    }

    private void handleLuasBehavior(Luas luas) {
        // Implement specific behaviors for the Luas light rail system here, such as moving along predefined tracks, etc.
    }

    public void renderElements(Graphics2D g2d) {
        for (GameElement element : elements) {
            element.draw(g2d);
        }
    }

    public void handlePlayerVehicleInteraction(Player player, GameElement vehicle) {
        if (vehicle instanceof TransportMode) {
            if (!player.isBeingControlled() && player.getLinkedElement() == null) {
                // 玩家开始控制交通工具
                player.linkElement(vehicle);
                vehicle.setBeingControlled(true);
                player.setBeingControlled(false);
            } else if (vehicle == player.getLinkedElement()) {
                // 玩家停止控制交通工具
                player.unlinkElement();
                vehicle.setBeingControlled(false);
                player.setBeingControlled(true);
            }
        }
    }


    //For collisions between the player and elements such as coins or gems, the is not for transport
    // we typically want to trigger certain events (such as increasing the score) upon contact with these elements, rather than preventing the player from moving.
    // This is a new collision detection method that can be used to check for collisions between any two game elements.
    // Then, this method can be called within the main game loop to check if the player has collided with elements like coins or gems,
    // and to execute the corresponding logic accordingly.
    public void checkAllCollisions(Player player) {
        // Iterate through all game elements
        Iterator<GameElement> it = elements.iterator();
        while (it.hasNext()) {
            GameElement element = it.next();
            // If the player collides with an element other than itself
            if (player != element && player.getBounds().intersects(element.getBounds())) {
                // For coins, increment the player's score and remove the coin from the game
                if (element instanceof Coin) {
                    // Increase player score
                    it.remove(); // Remove the coin
                }
                // For gems, increment the player's score and remove the gem from the game
                else if (element instanceof Gem) {
                    // Increase player score
                    it.remove(); // Remove the gem
                }
                else if (element instanceof Bike || element instanceof Bus || element instanceof Taxi || element instanceof Luas) {
                    // Handle player-vehicle interaction here
                    // For example, allow player to "ride" the bike or "enter" the bus
                    handlePlayerVehicleInteraction(player, element);
                }
                // Additional collision logic can be added here as needed
            }
        }
    }

}




