package com.flappy.game;


import java.util.List;

public class PlayerShip extends Spaceship implements InputListener{
    PlayerShip(float x, float y, List<GameObject> gameObjects) {
        super(x, y, gameObjects);
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
            shoot(true);
        }
    }



		
}
