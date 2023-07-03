package com.flappy.game;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CollisionManagerTests{

    @Test
    public void performCollisionDetection_WithNoCollisions_ShouldNotModifyGameObjects() {
        // Arrange
        CollisionManager collisionManager = new CollisionManager(1000,800, 20);
        List<GameObject> gameObjects = createGameObjectsWithoutCollisions();

        // Act
        collisionManager.performCollisionDetection(gameObjects);

        // Assert
        for (GameObject gameObject : gameObjects) {
            Assertions.assertTrue(!gameObject.hasCollided());
        }
        Assertions.assertEquals(3, gameObjects.size());
    }

    @Test
    public void performCollisionDetection_WithCollisions_ShouldModifyGameObjects() {
        // Arrange
         CollisionManager collisionManager = new CollisionManager(1000,800, 20);
        List<GameObject> gameObjects = createGameObjectsWithCollisions();

        // Act
        collisionManager.performCollisionDetection(gameObjects);

        // Assert
        Assertions.assertEquals(4, gameObjects.size());
        Assertions.assertTrue(gameObjects.get(0).hasCollided());
        Assertions.assertTrue(gameObjects.get(1).hasCollided());
    }

    private List<GameObject> createGameObjectsWithoutCollisions() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new TestObject(100, 100, 40));
        gameObjects.add(new TestObject(200, 200, 40));
        gameObjects.add(new TestObject(300, 300, 40));
        return gameObjects;
    }

    private List<GameObject> createGameObjectsWithCollisions() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new TestObject(100, 100, 50));
        gameObjects.add(new TestObject(200, 200, 50));
        gameObjects.add(new TestObject(250, 250, 50));
        gameObjects.add(new TestObject(300, 300, 50));
        return gameObjects;
    }
}
