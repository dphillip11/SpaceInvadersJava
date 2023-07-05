package SpaceEvaders.GameObjects;

import SpaceEvaders.GameState.GameState;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;


public class Spaceship extends CollidableObject {
    public void die() {
        isActive = false;
    }

    public void shoot(Vector2 speed, Vector2 pos, Vector2 rad, boolean isFriendly) {
        GameState.eventHandler.notify(EventType.SHOOT, speed, pos, rad, isFriendly);
    }

}
