package SpaceEvaders.GameObjects;

import java.awt.Toolkit;

import SpaceEvaders.Utilities.Vector2;

public class Bullet extends CollidableObject{
    public Bullet() {
        setHealth(1);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Orb.png"));
    }

    public Bullet(Vector2 pos, Vector2 vel, Vector2 rad, boolean isFriendly) {
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
        hasCollided = false;
        isActive = true;
    }

}
