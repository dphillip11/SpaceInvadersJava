package SpaceEvaders.Systems.RenderingSystem;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Utilities.Vector2;

import java.awt.Graphics;
import java.util.List;

public class ObjectRenderer {

    // Render a list of game objects on the given Graphics object
    public static void render(Graphics g, List<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive()) {
                Vector2 position = gameObject.getPosition();
                Vector2 size = gameObject.getRadius().multiply(2);
                g.drawImage(gameObject.image, (int) position.x, (int) position.y, (int)size.x, (int)size.y, null);
            }
        }
    }
}
