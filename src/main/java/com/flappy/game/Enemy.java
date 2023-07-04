package com.flappy.game;

import java.util.List;
import java.awt.Toolkit;

public class Enemy extends Spaceship {
    Enemy(float x, float y, List<GameObject> gameObjects) {
        super(x, y, gameObjects);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/enemy1.png"));
        type = ObjectType.ENEMY;
    }

    @Override
    public void update(double deltaTime) {
        if(!isActive)
            return;
        // Implement update logic for the Spaceship based on the elapsed time
        // For example:
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
        respectScreenBoundary();
        if (hasCollided()) {
            isActive = false;
        }
        if (Math.random()>0.995) {
            shoot(false);
        }

    }
}


