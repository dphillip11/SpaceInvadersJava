package com.flappy.game;
import java.awt.Graphics;

public interface GameObject {
    enum ObjectType {
        PLAYER,
        ENEMY,
        BULLET_FRIENDLY,
        BULLET_ENEMY,
        POWERUP,
        TEST,
        UNDEFINED
    }

    ObjectType getType();

    void draw(Graphics g);

    void update(double deltaTime);
    
    void takeDamage(int damage);

    void Collide(ObjectType otherType);

    boolean hasCollided();

    boolean isActive();

    void setActive(boolean active);

    int GetMaxX();

    int GetMinX();

    int GetMaxY();

    int GetMinY();

    void SetSpeed(float x, float y);
}
