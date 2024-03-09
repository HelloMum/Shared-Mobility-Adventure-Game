package HaohaiTeam.game;

public abstract class TransportMode implements Movable {
    protected double distance; // 行驶距离

    public TransportMode(double dist) {
        this.distance = dist;
    }

    public abstract double getCarbonFootprint();

    public abstract double getAverageSpeed();

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return distance / getAverageSpeed();
    }

    public abstract MoveResult moveAlongRoute(Route route);

    @Override
    public void move(int dx, int dy) {
        distance += Math.sqrt(dx * dx + dy * dy);
    }

    public static class MoveResult {
        private final double carbonFootprint;
        private final int estimatedTime;

        public MoveResult(double footprint, int time) {
            this.carbonFootprint = footprint;
            this.estimatedTime = time;
        }

        public double getCarbonFootprint() {
            return carbonFootprint;
        }

        public int getEstimatedTime() {
            return estimatedTime;
        }
    }
}

class Walk extends TransportMode {
    public Walk(double dist) {
        super(dist);
    }

    @Override
    public double getCarbonFootprint() {
        // 假设行走的碳排放量较低，例如0.1kg CO2/km
        return 0.1 * distance;
    }

    @Override
    public double getAverageSpeed() {
        // 假设平均步行速度为5km/h
        return 5.0;
    }

    @Override
    public MoveResult moveAlongRoute(Route route) {
        double distance = route.getTotalDistance(); // 使用Route类提供的方法计算总距离
        this.distance += distance; // 更新总距离
        double carbonFootprint = getCarbonFootprint(); // 根据更新后的距离计算碳足迹
        int estimatedTime = (int) (distance / getAverageSpeed() * 60); // 预计时间（分钟）
        return new MoveResult(carbonFootprint, estimatedTime);
    }
}

class Bike extends TransportMode {
    public Bike(double dist) {
        super(dist);
    }

    @Override
    public double getCarbonFootprint() {
        // 假设骑行的碳排放量较低，例如0.05kg CO2/km
        return 0.05 * distance;
    }

    @Override
    public double getAverageSpeed() {
        // 假设平均骑行速度为15km/h
        return 15.0;
    }

    @Override
    public MoveResult moveAlongRoute(Route route) {
        double distance = route.getTotalDistance();
        this.distance += distance;
        double carbonFootprint = getCarbonFootprint();
        int estimatedTime = (int) (distance / getAverageSpeed() * 60);
        return new MoveResult(carbonFootprint, estimatedTime);
    }
}

class Bus extends TransportMode {
    public Bus(double dist) {
        super(dist);
    }

    @Override
    public double getCarbonFootprint() {
        // 假设公交车的碳排放量较高，例如0.8kg CO2/km
        return 0.8 * distance;
    }

    @Override
    public double getAverageSpeed() {
        // 假设公交车的平均速度为30km/h
        return 30.0;
    }

    @Override
    public MoveResult moveAlongRoute(Route route) {
        double distance = route.getTotalDistance();
        this.distance += distance;
        double carbonFootprint = getCarbonFootprint();
        int estimatedTime = (int) (distance / getAverageSpeed() * 60);
        return new MoveResult(carbonFootprint, estimatedTime);
    }
}

class Luas extends TransportMode {
    public Luas(double dist) {
        super(dist);
    }

    @Override
    public double getCarbonFootprint() {
        // 假设Luas的碳排放量中等，例如0.2kg CO2/km
        return 0.2 * distance;
    }

    @Override
    public double getAverageSpeed() {
        // 假设Luas的平均速度为60km/h
        return 60.0;
    }

    @Override
    public MoveResult moveAlongRoute(Route route) {
        double distance = route.getTotalDistance();
        this.distance += distance;
        double carbonFootprint = getCarbonFootprint();
        int estimatedTime = (int) (distance / getAverageSpeed() * 60);
        return new MoveResult(carbonFootprint, estimatedTime);
    }
}

class Taxi extends TransportMode {
    public Taxi(double dist) {
        super(dist);
    }

    @Override
    public double getCarbonFootprint() {
        // 假设出租车的碳排放量最高，例如1.0kg CO2/km
        return 1.0 * distance;
    }

    @Override
    public double getAverageSpeed() {
        // 假设出租车的平均速度为40km/h
        return 40.0;
    }

    @Override
    public MoveResult moveAlongRoute(Route route) {
        double distance = route.getTotalDistance();
        this.distance += distance;
        double carbonFootprint = getCarbonFootprint();
        int estimatedTime = (int) (distance / getAverageSpeed() * 60);
        return new MoveResult(carbonFootprint, estimatedTime);
    }
}

