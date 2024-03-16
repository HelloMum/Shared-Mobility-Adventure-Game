package HaohaiTeam.Game.Element.Transport;
import HaohaiTeam.Game.Element.GameElement;
import java.awt.*;
public abstract class TransportMode extends GameElement{

    protected String name;
    protected double speed;
    protected double carbonFootprint;

    public TransportMode(int x, int y, String name, double speed, double carbonFootprint) {
        super(x, y);
        this.name = name;
        this.speed = speed;
        this.carbonFootprint = carbonFootprint;
    }

    @Override
    public abstract void draw(Graphics2D g2d);

    // use speed value to impact movement
    @Override
    public void move(int dx, int dy) {
        // a simple logic of add speed to x and y but we need more complex logic
        super.move((int)(dx * speed), (int)(dy * speed));
    }
}
