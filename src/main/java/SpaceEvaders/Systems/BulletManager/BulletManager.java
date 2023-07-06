package SpaceEvaders.Systems.BulletManager;

import java.util.List;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.Bullet;
import SpaceEvaders.Systems.EventsSystem.ShootListener;
import SpaceEvaders.Utilities.Vector2;

public class BulletManager extends ShootListener {

    private List<GameObject> gameObjects;

    public BulletManager(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void onShoot(Object... args) {
            assert args.length == 3;
            Vector2 speed = (Vector2) args[0];
            Vector2 position = (Vector2) args[1];
            Vector2 radius = (Vector2) args[2];
            boolean isFriendly = (boolean) args[3];
            gameObjects.add(new Bullet(position, speed, radius, isFriendly));
    }
}
