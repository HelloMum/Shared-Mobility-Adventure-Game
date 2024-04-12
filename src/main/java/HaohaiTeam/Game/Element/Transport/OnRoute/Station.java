package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.PopUp;
import HaohaiTeam.Game.GUI.GameWindow;

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

        double co2Emission = calculateCO2Emission();

        new PopUp(this.X, this.Y, getCollisionPopupMessage(co2Emission));
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