package com.flappy.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements GameObject {
    public static final float MAX_RANGE = 1000;
    private float speed = 15;
    private float range = MAX_RANGE;
    private float x;
    private float y;
    public boolean isActive = true;

    public void Init(int x, int y) {
        this.x = x;
        this.y = y;
        isActive = true;
        range = MAX_RANGE;
    }

    @Override
    public void draw(Graphics g) {
        if (!isActive)
            return;
        g.setColor(Color.RED);
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
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
}
