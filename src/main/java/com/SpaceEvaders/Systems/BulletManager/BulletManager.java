package com.SpaceEvaders.Systems.BulletManager;

import com.SpaceEvaders.Utilities.Vector2;
import com.SpaceEvaders.GameState.GameState;
import com.SpaceEvaders.Systems.EventsSystem.EventListener;
import com.SpaceEvaders.Systems.EventsSystem.EventType;
import com.SpaceEvaders.GameObjects.Bullet;

public class BulletManager implements EventListener {
    public void onEvent(EventType event, Object... args) {
        if (event == EventType.SHOOT) {
            assert args.length == 3;
            Vector2 speed = (Vector2) args[0];
            Vector2 position = (Vector2) args[1];
            Vector2 radius = (Vector2) args[2];
            boolean isFriendly = (boolean) args[3];
            GameState.gameObjects.add(new Bullet(speed, position, radius, isFriendly));
        }
    }
}
