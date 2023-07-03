package com.flappy.game;

public class Collidable {
    protected float x;
    protected float y;
    protected float velocityX;
    protected float velocityY;

    protected float radius;
    protected boolean isActive = true;
    protected boolean hasCollided = false;


    public void takeDamage(int damage) {
        isActive = false;
        hasCollided = false;
    }
    
    public void applyHit() {
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

    public void SetSpeed(float x, float y){
        velocityX = x;
        velocityY = y;
    }
}
