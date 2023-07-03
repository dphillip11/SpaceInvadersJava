package com.flappy.game;

import java.awt.*;
import java.util.List;


public class Spaceship implements GameObject{
    protected float x;
    protected float y;
    protected float velocityX = 0;
    protected float velocityY = 0;
    protected boolean isAlive = true;
    protected GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(100, Bullet.class);
    protected List<GameObject> gameObjects;
    protected int radius = 50;
    protected Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/spaceship.png"));
    
    public Spaceship(float x, float y, List<GameObject> gameObjects) {
        this.x = x;
        this.y = y;
        this.gameObjects = gameObjects;
    }
    
    @Override
     public void draw(Graphics g) {
        g.drawImage(image, (int) (x - radius), (int) (y - radius), 2 * radius, 2 * radius, null);   
    }
    
    @Override
    public void update(double deltaTime) {
        // Implement update logic for the Spaceship based on the elapsed time
        // For example:
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }

   
    @Override
    public void takeDamage(int damage) {
        isAlive = false;
    }

    @Override
    public boolean isActive() {
        return isAlive;
    }

    @Override
    public void setActive(boolean active) {
        isAlive = active;
    }

    public void shoot(boolean up) {
        float offset = up ? -radius : radius;
        offset *= 1.1f;
        Bullet bullet = bulletPool.getObject();
        bullet.Init((int) x, (int) (y + offset), up);
        gameObjects.add(bullet);
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

