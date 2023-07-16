package SpaceEvaders.Systems.CollisionSystem;

import java.util.ArrayList;
import java.util.List;

import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.ObjectType;
import SpaceEvaders.Utilities.Vector2;

public class CollisionManager {
    private int numRows;
    private int numCols;
    private int cellSize;
    private List<List<List<Integer>>> grid;

    public List<List<List<Integer>>> getGrid() {
        return grid;
    }

    public CollisionManager(int windowWidth, int windowHeight, int cellSize) {
        this.cellSize = cellSize;
        numRows = windowHeight / cellSize;
        numCols = windowWidth / cellSize;

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

    public void performCollisionDetection(List<GameObject> objects) {
        allocateObjectsToGrid(objects);
        applyCollisions(objects);
        clearGrid(grid);
    }

    public void allocateObjectsToGrid(List<GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            CollidableObject object = (CollidableObject)gameObjects.get(i);
            if (!object.isActive())
                continue;
            Vector2 min = object.getMinPos();
            Vector2 max = object.getMaxPos();

            int startRow = Math.max(0, (int)min.y / cellSize);
            int endRow = Math.min(numRows - 1, (int)max.y / cellSize);
            int startCol = Math.max(0, (int)min.x / cellSize);
            int endCol = Math.min(numCols - 1, (int)max.x / cellSize);

            for (int row = startRow; row <= endRow; row++) {
                for (int col = startCol; col <= endCol; col++) {
                    List<Integer> cellObjects = grid.get(row).get(col);
                    cellObjects.add(i);
                }
            }
        }
    }

    private void applyCollisions(List<GameObject> gameObjects) {
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            List<Integer> cellObjects = grid.get(row).get(col);
            if (cellObjects.size() < 2) {
                continue;}

            for (int cellPositionIndex = 0; cellPositionIndex < cellObjects.size(); cellPositionIndex++) {
                int objectIndex = cellObjects.get(cellPositionIndex);
                if (objectIndex < 0 || objectIndex >= gameObjects.size()) {
                    continue;
                }
                for(int otherCellPositionIndex = cellPositionIndex + 1; otherCellPositionIndex < cellObjects.size(); otherCellPositionIndex++) {
                    int otherObjectIndex = cellObjects.get(otherCellPositionIndex);
                    if (otherObjectIndex < 0 || otherObjectIndex >= gameObjects.size()) {
                        continue;
                    }
                    CollidableObject object = (CollidableObject) gameObjects.get(objectIndex);
                    CollidableObject otherObject = (CollidableObject) gameObjects.get(otherObjectIndex);
                    if (!object.getHasCollided() && !otherObject.getHasCollided()) {
                        object.onCollide(otherObject.getType(), otherObject);
                        otherObject.onCollide(object.getType(), object);
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
            }
        }
    }
}
