package com.flappy.game;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CollisionManagerTests {
    @Test
    public void min_and_max_should_return_correct_values()
    {
        // Arrange
        GameObject gameObject = new TestObject(100, 300, 40);

        // Act
        int minX = gameObject.GetMinX();
        int minY = gameObject.GetMinY();
        int maxX = gameObject.GetMaxX();
        int maxY = gameObject.GetMaxY();

        // Assert
        Assertions.assertEquals(60, minX);
        Assertions.assertEquals(260, minY);
        Assertions.assertEquals(140, maxX);
        Assertions.assertEquals(340, maxY);
    }

    @Test
    public void allocateObjectsToGrid_ShouldAllocateObjectsToCorrectCells() {
        // Arrange
        int windowWidth = 800;
        int windowHeight = 600;
        int cellSize = 100;
        CollisionManager collisionManager = new CollisionManager(windowWidth, windowHeight, cellSize);
        List<GameObject> gameObjects = createGameObjects();

        // Act
        collisionManager.allocateObjectsToGrid(gameObjects);
        List<List<List<Integer>>> grid = collisionManager.getGrid();

        // Assert
        Assertions.assertEquals(6, grid.size()); // 6 rows
        Assertions.assertEquals(8, grid.get(0).size()); // 8 columns

        // Check allocation of objects
        Assertions.assertEquals(1, grid.get(0).get(0).size()); // One object in cell (0, 0)
        Assertions.assertEquals(1, grid.get(1).get(1).size()); // One object in cell (1, 1)
        Assertions.assertEquals(1, grid.get(2).get(2).size()); // One object in cell (2, 2)
        Assertions.assertEquals(0, grid.get(2).get(3).size()); // No object in cell (3, 2)
        Assertions.assertEquals(0, grid.get(3).get(2).size()); // No object in cell (2, 3)
        Assertions.assertEquals(0, grid.get(0).get(1).size()); // No object in cell (1, 0)
        Assertions.assertEquals(1, grid.get(0).get(5).size()); // Object can occupy two cells (5, 0) and (4, 0)
        Assertions.assertEquals(1, grid.get(0).get(4).size());
    }

    private List<GameObject> createGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new TestObject(50, 50, 10)); // (1, 1)
        gameObjects.add(new TestObject(150, 150, 10)); // (2, 2)
        gameObjects.add(new TestObject(500, 50, 10)); // (3, 2)
        gameObjects.add(new TestObject(250, 250, 10)); // (3, 3)
        return gameObjects;
    }

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
        gameObjects.add(new TestObject(150, 150, 50));
        gameObjects.add(new TestObject(200, 200, 50));
        gameObjects.add(new TestObject(250, 250, 50));
        gameObjects.add(new TestObject(300, 300, 50));
        return gameObjects;
    }
}
