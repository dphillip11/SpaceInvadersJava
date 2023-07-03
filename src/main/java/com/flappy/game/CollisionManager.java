package com.flappy.game;
import java.util.ArrayList;
import java.util.List;


public class CollisionManager {
    private int numRows;
    private int numCols;
    private int cellSize;
    private List<List<List<Integer>>> grid;

    public CollisionManager(int window_width, int window_height, int cellSize) {
        this.cellSize = cellSize;
        numRows = window_height / cellSize;
        numCols = window_width / cellSize;

        // Initialize the grid
        grid = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<List<Integer>> rowList = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                rowList.add(new ArrayList<>()); // Initialize an empty list for each spot
            }
            grid.add(rowList);
        }
    }

    public void performCollisionDetection(List<GameObject> gameObjects) {
        allocateObjectsToGrid(gameObjects);
        applyCollisions(grid, gameObjects);
        clearGrid(grid);
    }

    private void allocateObjectsToGrid(List<GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
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
                    cellObjects.add(i);
                }
            }
        }
    }

    private void applyCollisions(List<List<List<Integer>>> grid, List<GameObject> gameObjects) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                List<Integer> cellObjects = grid.get(row).get(col);
                if (cellObjects.size() > 1) {
                    for (int objectIndex : cellObjects) {
                        GameObject object = gameObjects.get(objectIndex);
                        object.applyHit(); // Apply collision logic specific to your game
                    }
                }
            }
        }
    }

    private void clearGrid(List<List<List<Integer>>> grid) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                grid.get(row).get(col).clear();
            }
        }
    }
}
