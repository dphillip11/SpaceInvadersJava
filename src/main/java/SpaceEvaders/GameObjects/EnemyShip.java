package SpaceEvaders.GameObjects;

import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.ServiceLocator.SL;

public class EnemyShip extends Spaceship {

    public int variant = 0;

    public EnemyShip() {
        radius = Variables.enemySizeVariant0;
        type = ObjectType.ENEMY;
    }

    @Override
    public void onCollide(ObjectType otherType) {
        if (otherType == ObjectType.BULLET_FRIENDLY) {
            takeDamage(1);
            startFlashing();
            setHasCollided(true);
            SL.eventHandler.notify(EventType.ENEMY_HIT);
        }
    }
}
