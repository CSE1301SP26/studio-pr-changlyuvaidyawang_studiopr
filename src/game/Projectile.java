package game;

import java.awt.Color;

public class Projectile extends Entity {

    private static final double speed = 0.01;
    private static final double size = 0.01;
    private static final double upperBound = 1.0;
    private static final double lowerBound = 0.0;

    public Projectile(double x, double y, Color color) {
        super(x, y, size, color);
    }

    public void moveUp() {
        setYPosition(this.getYPosition() + speed);
    }

    public void moveDown() {
        setYPosition(this.getYPosition() - speed);
    }

    public boolean isOutOfBounds() {
        return (lowerBound > this.getYPosition() || this.getYPosition() > upperBound);
    }
}
