package HaohaiTeam.game;

import java.util.List;

public class Route {

    private Location source;
    private Location destination;
    private List<TransportMode> transportModes;
    private double carbonFootprint;
    private int estimatedTime;

    public Route(Location source, Location destination, List<TransportMode> modes) {
        this.source = source;
        this.destination = destination;
        this.transportModes = modes;
        calculateCarbonFootprintAndTime();
    }

    public double getTotalDistance() {
        double totalDistance = 0.0;
        // 占位符逻辑：你应该用实际的计算逻辑替换这里的内容
        // 例如，汇总每个TransportMode的距离或基于起点和终点计算
        for (TransportMode mode : transportModes) {
            totalDistance += mode.getDistance(); // 假设每个TransportMode都有一个getDistance方法
        }
        return totalDistance;
    }


    // 计算路线碳足迹
    private void calculateCarbonFootprintAndTime() {
        double totalCarbon = 0.0;
        int totalTime = 0;
        for (TransportMode mode : transportModes) {
            TransportMode.MoveResult result = mode.moveAlongRoute(this);
            totalCarbon += result.getCarbonFootprint();
            totalTime += result.getEstimatedTime();
        }
        this.carbonFootprint = totalCarbon;
        this.estimatedTime = totalTime;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public List<TransportMode> getTransportationModes() {
        return transportModes;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    public int getEstimatedTime() {
        // 假设这里已经实现了根据交通方式计算预计时间的方法
        // 这里仅为示例，实际逻辑需要根据具体交通方式和距离来计算
        return estimatedTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "source=" + source.getName() +
                ", destination=" + destination.getName() +
                ", transportationModes=" + transportModes +
                ", carbonFootprint=" + carbonFootprint +
                ", estimatedTime=" + estimatedTime +
                '}';
    }



}

