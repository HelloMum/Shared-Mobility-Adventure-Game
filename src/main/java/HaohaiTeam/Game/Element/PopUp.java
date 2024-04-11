package HaohaiTeam.Game.Element;

import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.List;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public class PopUp extends GameElement {

    private String message;
    private int renderCount;


    public PopUp(int x, int y) {
        super(x, y + CELL_SIZE*-1);
        layer = 120;
        this.isVisible = true;
        this.message = "";
        this.walkable = true; // Make the PopUp walkable
        this.renderCount = 0;
        GameWindow.addElement(this);

    }
    public PopUp(int x, int y,String message) {
        super(x, y + CELL_SIZE*-1);
        layer = 120;
        this.isVisible = true;
        this.message = "";
        this.walkable = true; // Make the PopUp walkable
        this.renderCount = 0;
        GameWindow.addElement(this);
        this.message = message;
        setMessage(message);
        System.out.println("Showing message: " + message);

    }


    public void setMessage(String message) {
        this.message = message;
    }


    // Method to show the PopUp with a message
    public void show(String message) {
        setMessage(message);
        System.out.println("Showing message: " + message);
    }

    // Method to hide the PopUp if needed
    public void hide() {
        this.isVisible = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isVisible) {
            int boxWidth = 200; // Set the width of the box
            int boxHeight = 30; // Set the height of the box

            // Calculate the current opacity based on the render count
            float opacity = Math.max(1.0f - ((float) renderCount / 100.0f), 0.0f);

            // Save the current composite to restore it later
            Composite originalComposite = g2d.getComposite();

            // Set the composite with the calculated opacity
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Set the color to black
            g2d.setColor(Color.BLACK);
            // Fill the rectangle with the black color
            g2d.fillRect(getRenderX(), getRenderY(), boxWidth, boxHeight);

            // Set the color to white
            g2d.setColor(Color.WHITE);
            // Draw the message inside the box
            g2d.drawString(message, getRenderX() + 10, getRenderY() + 20);

            // Reset the composite to its original value
            g2d.setComposite(originalComposite);

            // Increment the render count
            renderCount++;
        }
    }
}
