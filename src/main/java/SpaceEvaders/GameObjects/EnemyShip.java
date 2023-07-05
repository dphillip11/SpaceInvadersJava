package SpaceEvaders.GameObjects;

import SpaceEvaders.GameState.Variables;

public class EnemyShip extends Spaceship {
    public EnemyShip() {
        radius = Variables.enemySize;
        type = ObjectType.ENEMY;
    }
}
