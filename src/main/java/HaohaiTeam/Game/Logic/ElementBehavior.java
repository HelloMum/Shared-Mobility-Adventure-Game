package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Element.Coin;
import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Gem;
import HaohaiTeam.Game.Element.Transport.*;
import HaohaiTeam.Game.Element.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ElementBehavior {
    List<GameElement> elements;
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

    private void handleBikeBehavior(Bike bike) {
        // 这里实现自行车的具体行为，例如调整速度或方向等
    }

    private void handleTaxiBehavior(Taxi taxi) {
        // 这里实现出租车的具体行为，例如根据行程更改路线等
    }

    private void handleLuasBehavior(Luas luas) {
        // 这里实现轻轨的具体行为，例如在固定轨道上移动等
    }

    public void renderElements(Graphics2D g2d) {
        for (GameElement element : elements) {
            element.draw(g2d);
        }
    }

    //For collisions between the player and elements such as coins or gems,
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
                // Additional collision logic can be added here as needed
            }
        }
    }

}




