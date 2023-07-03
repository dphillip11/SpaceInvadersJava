package com.flappy.game;

import java.util.List;
import java.awt.Toolkit;

public class Enemy extends Spaceship{
    Enemy(float x, float y, List<GameObject> gameObjects) {
        super(x, y, gameObjects);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/enemy1.png"));
    }
}
