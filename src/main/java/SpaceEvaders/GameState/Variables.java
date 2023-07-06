package SpaceEvaders.GameState;

import SpaceEvaders.Utilities.Vector2;

public class Variables {

    public static int maxEnemyHealth = 5;
    public static float enemySpeed = 50;
    public static float enemyBulletSpeed = 200;

    public static int maxPlayerHealth = 10;

    public static float playerSpeed = 400;
    public static Vector2 playerSize = new Vector2(15, 15);

     public static Vector2 enemySize = new Vector2(15, 15);

    public static float playerBulletSpeed = 500;
    public static Vector2 playerBulletRadius = new Vector2(2, 20);
    
    public static float SFX_volume = 0.25f;
}
