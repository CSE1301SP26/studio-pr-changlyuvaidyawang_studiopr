package game;

import java.awt.Color;

public class Enemy extends Entity {

    private static final double MAX_X_SPAWN = 0.9;
    private static final double MAX_Y_SPAWN = 0.8;
    private static final double MIN_Y_SPAWN = 0.15;
    private static final double SIZE = 0.03;
    private static final double MAX_SPEED = 0.05;
    private static final double UPPER_BOUND = 1.0;
    private static final double LOWER_BOUND = 0.0;
    private static final double ENEMY_LOWER_BOUND = 0.15;
    private static final long FIRING_INTERVAL = 1000;

    private double xSpeed;
    private double ySpeed;
    private long lastFired;

    public Enemy() {
        super(Math.random() * MAX_X_SPAWN, Math.random() * MAX_Y_SPAWN + MIN_Y_SPAWN, SIZE, Color.RED);
        xSpeed = Math.random() * MAX_SPEED;
        ySpeed = Math.random() * MAX_SPEED;
        lastFired = System.currentTimeMillis();
    }

    public void move() {
        bounceOffWall();
        setXPosition(this.getXPosition() + xSpeed);
        setYPosition(this.getYPosition() + ySpeed);
    }

    private void bounceOffWall() {
        if (this.getXPosition() < LOWER_BOUND || this.getXPosition() > UPPER_BOUND) {
            xSpeed = -xSpeed;
        }
        if (this.getYPosition() < LOWER_BOUND || this.getYPosition() > UPPER_BOUND) {
            ySpeed = -ySpeed;
        }
       /*
       if (Math.abs(this.getXPosition()) > UPPER_BOUND) {
            xSpeed = -xSpeed;
        }
        if (Math.abs(this.getYPosition()) > UPPER_BOUND) {
            ySpeed = -ySpeed;
        }
        */ 
       /* 
       if(this.getXPosition() < LOWER_BOUND) {
            xSpeed = -xSpeed;
        }
        if(this.getXPosition() > UPPER_BOUND) {
            xSpeed = -xSpeed;
        }
        if(this.getYPosition() > UPPER_BOUND) {
            ySpeed = -ySpeed;
        }
        if(this.getYPosition() < ENEMY_LOWER_BOUND) {
            ySpeed = -ySpeed;
        }
            */ 
    }

    public boolean isFiring() {
        long now = System.currentTimeMillis();
        if(now - lastFired > FIRING_INTERVAL) {
            lastFired = now;
            return true;
        } else {
            return false;
        }
    }
    
}
