package haohaiTeam.game.element.transport.onRoute.faketrans;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.PopUp;
import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;
import haohaiTeam.game.element.transport.onRoute.stationRoad.Station;
import haohaiTeam.game.gui.GameWindow;


public abstract class FakeVehicle extends AutoMoveTransport {
    private int cellsTraveled = 0;  // To count the cells traveled
    private boolean atStation = false;  // To check if the vehicle is at the station

    public FakeVehicle(int x, int y) {
        super(x, y);
        isVisible = false;
        layer = 110;
        autoMove = true;  // Ensure the vehicle starts moving immediately
    }

    @Override
    protected void moveOnRoad() {
        if (isAtStation()) {
            if (!atStation) {  // Check if this is the first time it's been at a station
                atStation = true;
                System.out.println("Fake vehicle has reached a station, stopping. Cells traveled: " + cellsTraveled);
                terminateVehicle();  // Handle vehicle termination
            }
            return;  // Stop further movement
        }

        super.moveOnRoad();
        if (headingX != 0 || headingY != 0) {
            cellsTraveled++;
        }
        System.out.println("fake is moving, cells traveled: " + cellsTraveled);
    }

    @Override
    public boolean isAtStation() {
        return super.isAtStation();
    }

    private void terminateVehicle() {
        // Custom logic to cleanly terminate and remove the vehicle from the game
        // Depending on how your game framework is designed to handle such cases
        GameWindow.removeElement(this);  // Example call to remove the vehicle
    }
    public int getCellsTraveled() {
        return cellsTraveled;
    }
}
