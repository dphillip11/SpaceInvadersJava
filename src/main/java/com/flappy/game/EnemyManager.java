package com.flappy.game;

import java.util.List;

public class EnemyManager {
    static public void SpawnRow(List<GameObject> gameObjects, int screen_width, int cellSize, float speed) {
        //set random x speed
        float x_velocity = (float) (Math.random() * speed - speed * 0.5f);
        int max_possible = screen_width / cellSize;
        int num_enemies = Math.max(1,(int) (Math.random() * max_possible));
        //calculate spacing between enemies
        int spacing = screen_width / num_enemies;
        //calculate starting position
        int start = (int) (Math.random() * spacing);
        //create enemies
        for (int i = 0; i < num_enemies; i++) {
            gameObjects.add(new Enemy(start + (i * spacing), 0, gameObjects));
            //set enemy velocity
            gameObjects.get(gameObjects.size() - 1).SetSpeed(x_velocity, speed);
        }
    }

}
