package com.SpaceEvaders.Systems.EnemyManager;

import com.SpaceEvaders.GameObjects.GameObject;
import com.SpaceEvaders.GameObjects.EnemyShip;
import com.SpaceEvaders.GameState.GameState;
import com.SpaceEvaders.Utilities.Vector2;

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
            GameState.gameObjects.add(new EnemyShip());
            //set enemy velocity
            gameObjects.get(gameObjects.size() - 1).setPosition(new Vector2(start + i * spacing, -20));
            gameObjects.get(gameObjects.size() - 1).setVelocity(new Vector2(x_velocity, speed));
        }
    }

}
