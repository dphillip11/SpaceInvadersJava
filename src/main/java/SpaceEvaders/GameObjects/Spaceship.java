package SpaceEvaders.GameObjects;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;



public class Spaceship extends CollidableObject {
    public void die() {
        isActive = false;
        SL.eventHandler.notify(EventType.ENEMY_DESTROYED);
    }

    public void shoot(Vector2 speed, Vector2 pos, Vector2 rad, boolean isFriendly) {
        if (isActive())
        {
            SL.eventHandler.notify(EventType.SHOOT, speed, pos, rad, isFriendly);
        }
    }
}
