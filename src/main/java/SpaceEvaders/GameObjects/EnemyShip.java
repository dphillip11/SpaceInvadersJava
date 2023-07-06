package SpaceEvaders.GameObjects;

import SpaceEvaders.CommonState.Variables;

public class EnemyShip extends Spaceship {
    public EnemyShip() {
        radius = Variables.enemySize;
        type = ObjectType.ENEMY;
    }
}
