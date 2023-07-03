package com.flappy.game;

import java.util.List;
import java.awt.Toolkit;

public class Enemy extends Spaceship {
    Enemy(float x, float y, List<GameObject> gameObjects) {
        super(x, y, gameObjects);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/enemy1.png"));
    }

    @Override
    public void update(double deltaTime) {
        // Implement update logic for the Spaceship based on the elapsed time
        // For example:
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
        if (x > 1000) {
            x = 1000;
            velocityX = -velocityX;
        }
        if (x < 0) {
            x = 0;
            velocityX = -velocityX;
        }
        if (hasCollided()) {
            takeDamage(1);
        }

    }
}


