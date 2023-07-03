package com.flappy.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements GameObject {
    public static final float MAX_RANGE = 1000;
    private float speed = 20;
    private float range = MAX_RANGE;
    private float x;
    private float y;
    private int radius = 5;
    public boolean isActive = true;

    public void Init(int x, int y, boolean up) {
        this.x = x;
        this.y = y;
        isActive = true;
        range = MAX_RANGE;
        if (up) {
            speed = Math.abs(speed);
        } else {
            speed = -Math.abs(speed);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!isActive)
            return;
        g.setColor(Color.RED);
        g.fillOval((int) x - radius, (int) y - radius, 2*radius,2*radius);
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive)
            return;
        float step = speed * (float) deltaTime;
        y -= step;
        range -= step;
        if (range <= 0) {
            isActive = false;
        }
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void takeDamage(int damage) {
        isActive = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

     @Override
   public int GetMaxX(){return (int)x+ radius;}
@Override
    public int GetMinX(){return (int)x- radius;}
@Override
    public int GetMaxY(){return (int)y+ radius;}
@Override
   public int GetMinY(){return (int)y- radius;}
	
}
