package com.flappy.game;

import java.awt.Graphics;

public class TestObject extends Collidable implements GameObject {

    public TestObject(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Graphics g) {
    }

    public void update(double deltaTime) {
    }
}
