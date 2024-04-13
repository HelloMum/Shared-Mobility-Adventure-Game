package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.PopUp;
import HaohaiTeam.Game.GUI.GameWindow;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static HaohaiTeam.Game.GUI.GameWindow.CELL_SIZE;

public abstract class FakeVehicle extends AutoMoveTransport {
    private Station nextStation;
    private int totalSteps = 0;

    public FakeVehicle(int x, int y) {
        super(x, y);
        // 设置为不可见
        isVisible = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isVisible) {
            // 设置虚线效果
            float[] dash1 = {10.0f};
            BasicStroke bs1 = new BasicStroke(1,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND,
                    1.0f,
                    dash1,
                    2f);
            g2d.setStroke(bs1);
            g2d.setColor(Color.RED);
            g2d.drawLine(X, Y, nextStation.X, nextStation.Y);
        }
    }

    public void initiateMovement(Station currentStation) {
        this.nextStation = currentStation.findClosestStation(); // Directly use the method from Station class
        if (nextStation != null) {
            System.out.println("Next station found: " + nextStation.getClass().getSimpleName() + " at (" + nextStation.X + ", " + nextStation.Y + ")");
            moveTowardsStation(); // Call moveTowardsStation to simulate movement
        } else {
            System.out.println("No nearby station found.");
        }
    }

    private void moveTowardsStation() {
        Timer movementTimer = new Timer();
        movementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (approximateArrival()) {
                    movementTimer.cancel(); // Stop the timer as the destination is reached

                    double co2Emission = totalSteps * getCO2PerCell();
                    String message = String.format("Travelled: %d steps, CO2 Emission: %.2f kg", totalSteps, co2Emission);
                    System.out.println("Fake " + getClass().getSimpleName() + " travelled: " + totalSteps + " steps, CO2 Emission: " + co2Emission + " kg");

                    // Create and show pop-up message
                    PopUp popUp = new PopUp(X, Y, message, 5000);
                    GameWindow.addElement(popUp); // Assuming GameWindow has a method to add elements
                    popUp.show(message); // Display the message

                    // Set a timer to hide and remove the pop-up after 5 seconds
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            popUp.hide(); // Make the pop-up invisible
                            GameWindow.removeElement(popUp); // Remove the pop-up from the game elements
                        }
                    }, 5000);

                } else {
                    simulateStep();
                    isVisible = true; // For drawing the dashed line, if needed
                }
            }
        }, 0, 1000); // Execute this TimerTask every second
    }




    private boolean approximateArrival() {
        // 判断是否到达站点，这里简化处理，实际可能需要更复杂的坐标判断
        return Math.abs(X - nextStation.X) <= CELL_SIZE && Math.abs(Y - nextStation.Y) <= CELL_SIZE;
    }

    private void simulateStep() {
        if (X < nextStation.X) X += CELL_SIZE;
        else if (X > nextStation.X) X -= CELL_SIZE;
        if (Y < nextStation.Y) Y += CELL_SIZE;
        else if (Y > nextStation.Y) Y -= CELL_SIZE;
        totalSteps++;
    }

    protected abstract double getCO2PerCell();
}
