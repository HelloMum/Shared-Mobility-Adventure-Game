package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.PopUp;
import haohaiTeam.game.element.transport.onRoute.auto.AutoMoveTransport;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeBus;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeLuas;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeTaxi;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeVehicle;
import haohaiTeam.game.gui.GameWindow;

import java.util.List;

public abstract class Station extends Road {
    protected char stationType;// this is an identifier for station

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
    }

    @Override
    public void interactKeyPressedOnYou(GameElement gameElement) {
        // If player wants to interact and bus linked to station give move player onto the bus and link to it
    }
    @Override
    public void onBeingCollidedOnYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);

        if (!(gameElement instanceof Player)) {
            return;
        }

        // Notify the other element about the collision if needed
        gameElement.onBeingCollidedOnYou(this);


        FakeVehicle vehicle;
        if (this instanceof BusStation) {
            vehicle = new FakeBus(this.X, this.Y);
        } else if (this instanceof LuasStation) {
            vehicle = new FakeLuas(this.X, this.Y);
        } else if (this instanceof TaxiStation) {
            vehicle = new FakeTaxi(this.X, this.Y);
        } else {
            return;
        }
        System.out.println("cell here:" + vehicle.getCellsTraveled());

        // Check if the player is on any vehicle and handle accordingly
        // Find an available vehicle at the station
        AutoMoveTransport real_vehicle = findVehicleAtStation();

        // If a vehicle is found at the station and it is currently at the station
        if (real_vehicle != null && real_vehicle.isAtStation()) {
            // Link the game element to the vehicle
            real_vehicle.linkElement(gameElement);

            // Link the vehicle to the game element
            gameElement.linkElement(real_vehicle);

            // Move the game element to the linked vehicle's position
            gameElement.moveToLinked();

            // Set the vehicle's being controlled status to false
            real_vehicle.setBeingControlled(false);

            // Set the game element's being controlled status to false
            gameElement.setBeingControlled(false);

            // Print a message indicating that the player is now on board the vehicle
            System.out.println("Player is now on board the " + real_vehicle.getClass().getSimpleName());
        }
    }


    private AutoMoveTransport findVehicleAtStation() {
        // Try to find an AutoMoveTransport at the same coordinates
        for (GameElement element : GameWindow.getElements()) {
            if (element instanceof AutoMoveTransport && element.getLogicalPosX() == this.getLogicalPosX() && element.getLogicalPosY() == this.getLogicalPosY()) {
                return (AutoMoveTransport) element;
            }
        }
        return null;
    }

    // Override on children


    protected void setStationType(char type) {
        this.stationType = type;
    }
    protected abstract double getCO2PerCell(); //I dont want to add new variable so just use method to get inside each subclass

}