package haohaiTeam.game.element.transport.onRoute.stationRoad;

import haohaiTeam.game.element.GameElement;
import haohaiTeam.game.element.Player;
import haohaiTeam.game.element.PopUp;
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

        if (gameElement instanceof Player) {
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
            vehicle.initiateMovement(this);
            System.out.println("key-------successfully");

        }
    }
    @Override
    public void onBeingCollidedOnYou(GameElement gameElement) {
        System.out.println(this + " collision on the element " + gameElement);

        if (!(gameElement instanceof Player)) {
            return;
        }

        // Notify the other element about the collision if needed
        gameElement.onBeingCollidedOnYou(this);

        double co2Emission = calculateCO2Emission();
        String popupStationName = null;

        if (stationType == 'l') {popupStationName = "Luas stop";}
        else if (stationType == 'b') {popupStationName = "Bus stop";}
        else if (stationType == 't') {popupStationName = "Taxi rank";}

        new PopUp(this.X, this.Y, "This is a " + popupStationName + ". " + getCollisionPopupMessage(co2Emission), 2000);
    }

    // sorry here I just use popup to check my co2 info so if you want to delete it is ok
    private String getCollisionPopupMessage(double co2Emission) {
        if (co2Emission > 0) {
            return String.format("CO2 Emission to next station: %.2f kg", co2Emission);
        } else {
            return "No next station found or zero distance.";
        }
    }

    // Override on children



    // Method to find the closest station of the same type
    public Station findClosestStation() {
        List<GameElement> elements = GameWindow.getElements(); // Get all elements from the game window
        Station closest = null;
        double minDistance = Double.MAX_VALUE;

        for (GameElement element : elements) {
            if (element instanceof Station && element != this && ((Station)element).stationType == this.stationType) {
                double distance = calculateDistance(this.X, this.Y, element.X, element.Y);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = (Station)element;
                }
            }
        }
        return closest; // Return the closest station found
    }

    // Helper method to calculate distance between two points
    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    // will implement in child classes
    protected void setStationType(char type) {
        this.stationType = type;
    }


    protected int calculateDistanceToNextStation() {
        List<GameElement> elements = GameWindow.getElements();
        int minDistance = Integer.MAX_VALUE;
        int currentX = this.X / GameWindow.CELL_SIZE;
        int currentY = this.Y / GameWindow.CELL_SIZE;

        for (GameElement element : elements) {
            if (element instanceof Station && ((Station)element).stationType == this.stationType && element != this) {
                int elementX = element.X / GameWindow.CELL_SIZE;
                int elementY = element.Y / GameWindow.CELL_SIZE;
                int distance = Math.abs(elementX - currentX) + Math.abs(elementY - currentY);

                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    protected abstract double getCO2PerCell(); //I dont want to add new variable so just use method to get inside each subclass
    public double calculateCO2Emission() {
        int distance = calculateDistanceToNextStation();
        if (distance > 0) {
            return distance * getCO2PerCell();
        }
        return 0;
    }
}