package com.flappy.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Collidable implements GameObject {
    private float speed = 800;

    public void Init(int x, int y, boolean up) {
        this.x = x;
        this.y = y;
        this.radius = 4;
        if (up) {
            speed = Math.abs(speed);
            type = ObjectType.BULLET_FRIENDLY;
        } else {
            speed = -Math.abs(speed);
            type = ObjectType.BULLET_ENEMY;
        }
        isActive = true;
        hasCollided = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!isActive)
            return;
        g.setColor(Color.RED);
        g.fillOval((int) x - (int)radius, (int) y - (int)radius, 2*(int)radius,2*(int)radius);
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive)
            return;
        float step = speed * (float) deltaTime;
        y -= step;
        if (hasCollided) {
            isActive = false;
        }
        respectScreenBoundary();
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

    public float getSpeed() {
        return speed;
    }
}
