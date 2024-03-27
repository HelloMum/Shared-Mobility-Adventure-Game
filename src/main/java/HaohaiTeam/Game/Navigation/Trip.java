package HaohaiTeam.Game.Navigation;


public class Trip {
    private Route route;
    private double duration; // 行程时间
    private double carbonFootprint; // 碳足迹

    public Trip(Route route) {
        this.route = route;
        this.duration = 0; // 初始值，可以根据实际计算更新
        this.carbonFootprint = 0; // 初始值，可以根据实际计算更新
    }

    // Getter方法
    public Route getRoute() {
        return route;
    }

    public double getDuration() {
        return duration;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    // Setter方法
    public void setDuration(double newDuration) {
        this.duration = newDuration;
    }

    public void setCarbonFootprint(double newCarbonFootprint) {
        this.carbonFootprint = newCarbonFootprint;
    }


}

