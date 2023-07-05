package com.SpaceEvaders.GameObjects;

import com.SpaceEvaders.GameState.GameState;
import com.SpaceEvaders.Systems.EventsSystem.EventType;
import com.SpaceEvaders.Utilities.Vector2;


public class Spaceship extends CollidableObject {
    public void die() {
        isActive = false;
    }

    public void shoot(Vector2 speed, Vector2 position, float radius, boolean isFriendly) {
        GameState.eventHandler.notify(EventType.SHOOT, speed, position, radius, isFriendly);
    }

}
