package com.flappy.game;

import java.awt.*;
import java.util.List;


public class Spaceship implements GameObject, InputListener {
    private float x;
    private float y;
    private float velocityX = 0;
    private float velocityY = 0;
    private boolean isAlive = true;
    private GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(100, Bullet.class);
    private List<GameObject> gameObjects;
    private int radius = 50;
    private Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/spaceship.png"));
    
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
        velocityX = 0;
        velocityY = 0;
    }

    @Override
    public void onKeyPressed(Input input) {
        if (input == Input.UP) {
            velocityY = -10;
        }
        if (input == Input.DOWN) {
            velocityY = 10;
        }
        if (input == Input.LEFT) {
            velocityX = -10;
        }
        if (input == Input.RIGHT) {
            velocityX = 10;
        }
        if (input == Input.SPACE) {
            shoot();
        }
    }

    @Override
    public void takeDamage(int damage) {
        // Implement Spaceship taking damage
    }

    @Override
    public boolean isActive() {
        return isAlive;
    }

    @Override
    public void setActive(boolean active) {
        isAlive = active;
    }

    public void shoot() {
        Bullet bullet = bulletPool.getObject();
        bullet.Init((int) x, (int) y);
        gameObjects.add(bullet);
    }


		
}

