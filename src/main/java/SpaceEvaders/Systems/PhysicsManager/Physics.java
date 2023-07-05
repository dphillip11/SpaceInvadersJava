package SpaceEvaders.Systems.PhysicsManager;

import SpaceEvaders.GameObjects.GameObject;
import java.util.List;

public class Physics {
    public static void UpdatePositions(double deltaTime, List<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            gameObject.setPosition(gameObject.getPosition().add(gameObject.getVelocity().multiply((float)deltaTime)));
        }
    }
}
