package game;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private static final double INITIAL_X = 0.5;
    private static final double INITIAL_Y = 0.05;
    private static final double SIZE = 0.03;
    private static final double SPEED = 0.01;
    private static final long FIRING_INTERVAL = 500;

    private long lastFired;

    public Player() {
        super(INITIAL_X, INITIAL_Y, SIZE, Color.BLACK);
        lastFired = System.currentTimeMillis();
    }

    public void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            setXPosition(this.getXPosition() - SPEED);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            setXPosition(this.getXPosition() + SPEED);
        }
    }

    public boolean isFiring() {
        long now = System.currentTimeMillis();
        if (now - lastFired > FIRING_INTERVAL) {
            lastFired = now;
            return true;
        } else {
            return false;
        }
       /*
       if (now - lastFired > FIRING_INTERVAL) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                lastFired = now;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        */ 
    }
}
