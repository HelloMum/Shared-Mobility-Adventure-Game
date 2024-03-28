package HaohaiTeam.Game.Navigation;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Route {
    private Queue<Point> points;

    public Route() {
        this.points = new LinkedList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getNextPoint() {
        return points.poll(); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    }

    public boolean hasMorePoints() {
        return !points.isEmpty();
    }
}


