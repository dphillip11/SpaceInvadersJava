package com.flappy.game;

import java.util.ArrayList;
import java.util.List;
import com.flappy.game.GameObject.ObjectType;

public class CollisionManager {
    private int numRows;
    private int numCols;
    private int cellSize;
    private List<List<List<Integer>>> grid;
    private List<List<List<ObjectType>>> objectTypeGrid;

    public List<List<List<Integer>>> getGrid() {
        return grid;
    }

    public CollisionManager(int windowWidth, int windowHeight, int cellSize) {
        this.cellSize = cellSize;
        numRows = windowHeight / cellSize;
        numCols = windowWidth / cellSize;

        // Initialize the grid
        grid = new ArrayList<>();
        objectTypeGrid = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<List<Integer>> rowList = new ArrayList<>();
            List<List<ObjectType>> rowObjectTypeList = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                rowList.add(new ArrayList<>()); // Initialize an empty list for each spot
                rowObjectTypeList.add(new ArrayList<>());
            }
            grid.add(rowList);
            objectTypeGrid.add(rowObjectTypeList);
        }
    }

    public void performCollisionDetection(List<GameObject> gameObjects) {
        allocateObjectsToGrid(gameObjects);
        applyCollisions(gameObjects);
        clearGrid(grid);
    }

    public void allocateObjectsToGrid(List<GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if (!object.isActive())
                continue;
            int minX = object.GetMinX();
            int minY = object.GetMinY();
            int maxX = object.GetMaxX();
            int maxY = object.GetMaxY();

            int startRow = Math.max(0, minY / cellSize);
            int endRow = Math.min(numRows - 1, maxY / cellSize);
            int startCol = Math.max(0, minX / cellSize);
            int endCol = Math.min(numCols - 1, maxX / cellSize);

            for (int row = startRow; row <= endRow; row++) {
                for (int col = startCol; col <= endCol; col++) {
                    List<Integer> cellObjects = grid.get(row).get(col);
                    List<ObjectType> cellObjectTypes = objectTypeGrid.get(row).get(col);
                    cellObjects.add(i);
                    cellObjectTypes.add(object.getType());
                }
            }
        }
    }

    private void applyCollisions(List<GameObject> gameObjects) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                List<Integer> cellObjects = grid.get(row).get(col);
                List<ObjectType> cellObjectTypes = objectTypeGrid.get(row).get(col);
                if (cellObjects.size() > 1) {
                    for (int objectIndex : cellObjects) {
                        GameObject object = gameObjects.get(objectIndex);
                        if (object.hasCollided())
                        {
                            continue;
                        }
                        for(ObjectType otherType : cellObjectTypes) {
                            object.Collide(otherType);
                        }
                    }
                }
            }
        }
    }


    private void clearGrid(List<List<List<Integer>>> grid) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                grid.get(row).get(col).clear();
                objectTypeGrid.get(row).get(col).clear();
            }
        }
    }
}
