package SpaceEvaders.Systems.RenderingSystem;

import SpaceEvaders.GameObjects.GameObject;

import java.awt.Graphics;
import java.util.List;

public class ObjectRenderer {

    // Render a list of game objects on the given Graphics object
    public static void render(Graphics g, List<GameObject> gameObjects) {
        if (gameObjects == null || g == null) {
            return;
        }
        List<GameObject> copy_gameObjects = List.copyOf(gameObjects);
        for (GameObject gameObject : copy_gameObjects) {
            if (gameObject.isActive()) {
                // Draw the game object
                gameObject.draw(g);
            }
        }
    }
}


