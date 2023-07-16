package SpaceEvaders.Builder;

import SpaceEvaders.GameObjects.ObjectType;
import SpaceEvaders.Utilities.Vector2;

public class SpaceshipPreset {
    public float bulletSpeed = 1;
    public Vector2 bulletSize = new Vector2(1, 1);
    public float shootRate = 0.05f;
    public int maxHealth = 1;
    public ObjectType type = ObjectType.ENEMY;
    public int variant = 0;
    public Vector2 velocity = new Vector2(10, 10);
    public Vector2 radius = new Vector2(1, 1);
    
}
