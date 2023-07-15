package SpaceEvaders.GameObjects;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;



public class Spaceship extends CollidableObject {
    public void die() {
        isActive = false;
        SL.eventHandler.notify(EventType.ENEMY_DESTROYED);
        if(Math.random() < Variables.healthPowerupChance)
        {
            SL.eventHandler.notify(EventType.SPAWN_HEALTH_POWERUP, position,
                    (int) (Math.ceil(Math.random() * maxHealth)));
        }
        else if (Math.random() < Variables.bulletPowerupChance)
        {
            SL.eventHandler.notify(EventType.SPAWN_BULLET_POWERUP, position,
                    (int) (Math.ceil(Math.random() * maxHealth)));
        }
    }

    public void shoot(float speed, Vector2 bullet_size, boolean isFriendly) {
        if (isActive()) {
            Vector2 vel = new Vector2(0, speed);
            Vector2 origin = new Vector2(position.x, position.y);
            float offset = radius.y + bullet_size.y;
            if (isFriendly) {
                vel.y = -vel.y;
                origin.subtractFromThis(0, offset);
            } else {
                origin.addToThis(0, offset);
            }
            SL.eventHandler.notify(EventType.SHOOT, vel, origin, bullet_size, isFriendly);
        }
    }
    
    public void onCollide(ObjectType otherType) {
        startFlashing();
    }
}
