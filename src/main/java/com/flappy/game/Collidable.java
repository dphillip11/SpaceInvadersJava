package com.flappy.game;

import com.flappy.game.GameObject.ObjectType;

public class Collidable {
    protected float x;
    protected float y;
    protected float velocityX;
    protected float velocityY;

    protected float radius;
    protected boolean isActive = true;
    protected boolean hasCollided = false;

    protected ObjectType type = ObjectType.UNDEFINED;

    public ObjectType getType(){
        return type;
    }


    public void takeDamage(int damage) {
        isActive = false;
        hasCollided = false;
    }
    

    public void Collide(ObjectType otherType) {
    if (type == otherType) {
        return;
    }
    if (type == ObjectType.BULLET_ENEMY && otherType == ObjectType.ENEMY) {
        return;
    }
    if (type == ObjectType.BULLET_FRIENDLY && otherType == ObjectType.PLAYER) {
        return;
    }
    hasCollided = true;
}

    public boolean hasCollided() {
        return hasCollided;
    }
       
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int GetMaxX() {
        return (int) (x + radius);
    }

    public int GetMinX() {
        return (int) (x - radius);
    }

    public int GetMaxY() {
        return (int) (y + radius);
    }

    public int GetMinY() {
        return (int) (y - radius);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void SetSpeed(float x, float y) {
        velocityX = x;
        velocityY = y;
    }
    
    public void respectScreenBoundary()
    {
        if (x + radius > GameLoop.WINDOW_WIDTH) {
            x = GameLoop.WINDOW_WIDTH - radius;
            if (type == ObjectType.ENEMY) {
                velocityX = -velocityX;
            }
        }
        if (x - radius < 0) {
            x = radius;
            if (type == ObjectType.ENEMY) {
                velocityX = -velocityX;
            }
        }
        if (y > GameLoop.WINDOW_HEIGHT + radius) {
            if (type == ObjectType.ENEMY || type == ObjectType.BULLET_ENEMY) {
                isActive = false;
            }
            if (type == ObjectType.PLAYER) {
                y = GameLoop.WINDOW_HEIGHT - radius;
            }
        }
        if (y - radius < 0) {
            if (type == ObjectType.BULLET_FRIENDLY) {
                isActive = false;
            }
            if (type == ObjectType.PLAYER) {
                y = radius;
            }
        }
    }
}
