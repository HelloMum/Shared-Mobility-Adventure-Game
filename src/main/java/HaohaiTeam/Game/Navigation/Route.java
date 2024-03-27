package HaohaiTeam.Game.Navigation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Point> points;

    public Route() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }
}

