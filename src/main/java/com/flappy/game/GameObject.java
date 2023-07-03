package com.flappy.game;
import java.awt.Graphics;

public interface GameObject {
    void draw(Graphics g);

    void update(double deltaTime);
    
    void takeDamage(int damage);

    boolean isActive();

    void setActive(boolean active);
}
