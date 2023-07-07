package SpaceEvaders.GameObjects;

import SpaceEvaders.CommonState.Variables;

public class EnemyShip extends Spaceship {

    public int variant = 0;

    public EnemyShip() {
        radius = Variables.enemySizeVariant0;
        type = ObjectType.ENEMY;
    }
}
