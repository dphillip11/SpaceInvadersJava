package SpaceEvaders.Systems.BulletManager;

import SpaceEvaders.GameObjects.Bullet;
import SpaceEvaders.GameState.GameState;
import SpaceEvaders.Systems.EventsSystem.EventListener;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;

public class BulletManager implements EventListener {
    public void onEvent(EventType event, Object... args) {
        if (event == EventType.SHOOT) {
            assert args.length == 3;
            Vector2 speed = (Vector2) args[0];
            Vector2 position = (Vector2) args[1];
            Vector2 radius = (Vector2) args[2];
            boolean isFriendly = (boolean) args[3];
            GameState.gameObjects.add(new Bullet(position, speed, radius, isFriendly));
        }
    }
}
