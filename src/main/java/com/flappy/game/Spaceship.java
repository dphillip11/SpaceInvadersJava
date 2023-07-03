package com.flappy.game;

import java.awt.*;
import java.util.List;


public class Spaceship extends Collidable implements GameObject{
    protected float velocityX = 0;
    protected float velocityY = 0;
    protected GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(100, Bullet.class);
    protected List<GameObject> gameObjects;
    protected Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/spaceship.png"));
    
    public Spaceship(float x, float y, List<GameObject> gameObjects) {
        this.x = x;
        this.y = y;
        this.radius = 50;
        this.gameObjects = gameObjects;
    }
    
    @Override
     public void draw(Graphics g) {
        g.drawImage(image, (int) (x - radius), (int) (y - radius), 2 * (int)radius, 2 * (int)radius, null);   
    }
    
    @Override
    public void update(double deltaTime) {
        // Implement update logic for the Spaceship based on the elapsed time
        // For example:
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }
   
    public void shoot(boolean up) {
        float offset = up ? -radius : radius;
        offset *= 1.1f;
        Bullet bullet = bulletPool.getObject();
        bullet.Init((int) x, (int) (y + offset), up);
        gameObjects.add(bullet);
    }

}

