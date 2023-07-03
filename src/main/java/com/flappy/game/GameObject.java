package com.flappy.game;
import java.awt.Graphics;

public interface GameObject {
    void draw(Graphics g);

    void update(double deltaTime);
    
    void takeDamage(int damage);

    void applyHit();

    boolean hasCollided();

    boolean isActive();

    void setActive(boolean active);

    int GetMaxX();

    int GetMinX();

    int GetMaxY();

    int GetMinY();

    void SetSpeed(float x, float y);
}
