package SpaceEvaders.GameObjects;

import java.awt.Toolkit;

import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Utilities.Vector2;

public class Bullet extends CollidableObject {
    
    public int firepower = 1;

    public Bullet() {
        setHealth(1);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Orb.png"));
        type = ObjectType.BULLET_FRIENDLY;
    }

    public Bullet(Vector2 pos, Vector2 vel, Vector2 rad, boolean isFriendly) {
        setHealth(1);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Orb.png"));
        init(pos, vel, rad, isFriendly);
    }

    public void die() {
        isActive = false;
    }

    public void init(Vector2 pos, Vector2 vel, Vector2 rad, boolean isFriendly) {
        position = pos;
        velocity = vel;
        radius = rad;
        if (isFriendly) {
            type = ObjectType.BULLET_FRIENDLY;
        } else {
            type = ObjectType.BULLET_ENEMY;
        }
        isActive = true;
    }

    @Override
    public void onCollide(ObjectType otherType, CollidableObject otherObject) {
        if (type == ObjectType.BULLET_FRIENDLY && otherType == ObjectType.ENEMY) {
            takeDamage(1);
            setHasCollided(true);
            SL.eventHandler.notify(EventType.ENEMY_HIT);
        }
        else if (type == ObjectType.BULLET_ENEMY && otherType == ObjectType.PLAYER) {
            takeDamage(1);
            setHasCollided(true);
            SL.eventHandler.notify(EventType.PLAYER_HIT);
        }
        else if(type == ObjectType.BULLET_FRIENDLY && otherType == ObjectType.BULLET_ENEMY)
        {
            takeDamage(1);
            setHasCollided(true);
        }
        else if(type == ObjectType.BULLET_ENEMY && otherType == ObjectType.BULLET_FRIENDLY)
        {
            takeDamage(1);
            setHasCollided(true);
        }

    }

}
