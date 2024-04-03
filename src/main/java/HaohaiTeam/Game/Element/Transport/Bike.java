package HaohaiTeam.Game.Element.Transport;


import HaohaiTeam.Game.Logic.currentTransport;
import java.awt.Graphics2D;
import java.awt.Color;
import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;
import java.awt.image.BufferedImage;

public class Bike extends TransportMode {
    private static final Color BIKE_COLOR = Color.GREEN;
    private BufferedImage bikeImage; // this will be used later when we need use the real pics


    public Bike(int x, int y) {
        super(x, y); // assume 15km per hour and carbon footprint of 1.0 kg per km
        this.speed = 10;
        this.carbonFootprint = 1;
        this.layer = 102; // Drawn over player and roads
    }

    @Override
    public void draw(Graphics2D g2d) {
        int wheelRadius = CELL_SIZE / 4;
        int frameSize = wheelRadius * 2;

        // Calculate the positions of the wheel centers and other key points
        int frontWheelCenterX = x + (CELL_SIZE / 4);
        int rearWheelCenterX = x + (3 * CELL_SIZE / 4);
        int wheelCenterY = y + CELL_SIZE - wheelRadius;
        int seatX = x + (CELL_SIZE / 2);
        int seatY = y + (CELL_SIZE / 2);

        // Draw the wheels
        g2d.setColor(Color.BLACK);
        g2d.fillOval(frontWheelCenterX - wheelRadius, wheelCenterY - wheelRadius, 2 * wheelRadius, 2 * wheelRadius);
        g2d.fillOval(rearWheelCenterX - wheelRadius, wheelCenterY - wheelRadius, 2 * wheelRadius, 2 * wheelRadius);

        // Draw the bike frame
        g2d.setColor(BIKE_COLOR);
        g2d.drawLine(frontWheelCenterX, wheelCenterY, seatX, seatY); // Front triangle
        g2d.drawLine(rearWheelCenterX, wheelCenterY, seatX, seatY); // Rear triangle
        g2d.drawLine(seatX, seatY, seatX, y); // Seat post
        g2d.drawLine(frontWheelCenterX, wheelCenterY, rearWheelCenterX, wheelCenterY); // Down tube
        g2d.drawLine(seatX, seatY, frontWheelCenterX, y + CELL_SIZE / 3); // Top tube

        // Draw the bike seat
        g2d.fillRect(seatX - (wheelRadius / 2), y + (CELL_SIZE / 2) - (wheelRadius / 4), wheelRadius, wheelRadius / 4);

        // Draw the handlebars
        int handleBarY = y + CELL_SIZE / 3;
        g2d.drawLine(frontWheelCenterX, handleBarY, frontWheelCenterX - wheelRadius, handleBarY - wheelRadius / 2);
        g2d.drawLine(frontWheelCenterX, handleBarY, frontWheelCenterX + wheelRadius, handleBarY - wheelRadius / 2);
        g2d.drawLine(seatX, seatY, frontWheelCenterX, handleBarY); // Handlebar stem

        // Draw the pedals
        int pedalCenterX = seatX;
        int pedalCenterY = wheelCenterY;
        int pedalWidth = wheelRadius / 2;
        g2d.fillRect(pedalCenterX - (pedalWidth / 2), pedalCenterY - (pedalWidth / 4), pedalWidth, pedalWidth / 2);

        // Connect the pedals to the wheels
        g2d.drawLine(pedalCenterX, pedalCenterY, rearWheelCenterX, wheelCenterY);
    }


}


