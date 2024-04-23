package haohaiTeam.game.element.transport.onRoute.stationRoad;


public abstract class Station extends Road {
    protected char stationType;// this is an identifier for station

    public Station(int x, int y) {
        super(x, y);
        this.walkable = false;
    }}

