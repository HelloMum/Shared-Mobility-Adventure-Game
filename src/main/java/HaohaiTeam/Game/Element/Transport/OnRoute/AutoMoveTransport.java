package HaohaiTeam.Game.Element.Transport.OnRoute;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.GUI.GameWindow;
import java.util.ArrayList;
import java.util.List;

public abstract class AutoMoveTransport extends GameElement {
    public boolean moving; // This refers if moving

    @Override
    public void onTick() {
        tickCount++;
        System.out.println("Tic");
        // Move every 5 ticks
        if (tickCount % 5 == 0) {
            moveOnRoad(this.getLogicalPosX(), this.getLogicalPosY()); // Start moving on road
            System.out.println("Tic");


        }
    }
    public AutoMoveTransport(int x, int y) {
        super(x,y);
        moving = true;
        layer = 101; // Above player and road
    }

    private void moveOnRoad(int onX, int onY) {
        while (moving) {
            tryMoveInCurrentDirection(onX, onY);
        }
    }

    private void tryMoveInCurrentDirection(int onX, int onY) {
        List<GameElement> elements = new ArrayList<>(GameWindow.getElements());

        // Calculate the new position based on the current position and direction
        int newX = this.getLogicalPosX() + onX;
        int newY = this.getLogicalPosY() + onY;

        // Check if the new position is valid and free from obstacles
        if (isValidMove(newX, newY, elements)) {
            // Move to the new position
            this.setToLogicalPos(newX, newY);
        }
    }

    // Check if the move to the new position is valid
    private boolean isValidMove(int newX, int newY, List<GameElement> elements) {
        // Check if the new position is occupied by any obstacles
        for (GameElement element : elements) {
            if (element.getLogicalPosX() == newX && element.getLogicalPosY() == newY && !(element instanceof Road)) {
                return false;
            }
        }
        return true;
    }
}