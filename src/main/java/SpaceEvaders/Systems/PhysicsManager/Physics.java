package SpaceEvaders.Systems.PhysicsManager;

import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.GameObjects.GameObject;
import java.util.List;
import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.GameObjects.ObjectType;

public class Physics {
    public static void UpdatePositions(double deltaTime, List<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            gameObject.setPosition(gameObject.getPosition().add(gameObject.getVelocity().multiply((float) deltaTime)));
            checkBoundaries((CollidableObject) gameObject);
            gameObject.updateToggler(deltaTime );
        }
    }

    private static void checkBoundaries(CollidableObject object) {
        ObjectType type = object.getType();
        Vector2 position = object.getPosition();
        Vector2 radius = object.getRadius();
        Vector2 velocity = object.getVelocity();
        Vector2 maxPos = object.getMaxPos();
        Vector2 minPos = object.getMinPos();
        if (maxPos.y > Constants.screenHeight - 4 * radius.y) {
            if (type == ObjectType.PLAYER) {
                object.setPosition(new Vector2(position.x, Constants.screenHeight - 5 * radius.y));
            }
        }
        if (maxPos.y > Constants.screenHeight + radius.y) {
            if (type != ObjectType.PLAYER) {
                object.setActive(false);
            }
        }
        if (minPos.y < 0) {
            if (type == ObjectType.PLAYER) {
                object.setPosition(new Vector2(position.x, radius.y));
            }
            else if (velocity.y < 0) {
                object.setActive(false);
            }
        }
        if (maxPos.x + radius.x > Constants.screenWidth) {
            object.setPosition(new Vector2(Constants.screenWidth - 2 * radius.x, position.y));
            object.setVelocity(new Vector2(-velocity.x, velocity.y));
        }
        if (minPos.x < 0) {
            object.setPosition(new Vector2(radius.x, position.y));
            object.setVelocity(new Vector2(-velocity.x, velocity.y));
        }

    }
}
