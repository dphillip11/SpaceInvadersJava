package SpaceEvaders.CommonState;

import SpaceEvaders.Utilities.Vector2;

public class Variables {
    public static float playerSpeed = 400;
    public static Vector2 playerSize = new Vector2(15, 15);
    public static float playerBulletSpeed = 500;
    public static Vector2 playerBulletRadius = new Vector2(2, 20);
    public static int maxPlayerHealth = 10;

    public static float enemySpawnInterval = 4;
    public static float incidenceVariant1 = 0.08f;

    public static float enemySpeedVariant0 = 50;
    public static float enemySpeedVariant1 = 100;

    public static float enemyBulletSpeedVariant0 = 200;
    public static float enemyBulletSpeedVariant1 = 400;

    public static Vector2 enemyBulletRadiusVariant0 = new Vector2(4, 4);
    public static Vector2 enemyBulletRadiusVariant1 = new Vector2(1, 7);

    public static float shootRateVariant0 = 0.05f;
    public static float shootRateVariant1 = 0.2f;

    public static int enemyHealthVariant0 = 1;
    public static int enemyHealthVariant1 = 4;

    public static Vector2 enemySizeVariant0 = new Vector2(15, 15);
    public static Vector2 enemySizeVariant1 = new Vector2(30, 30);
    
    public static float SFX_volume = 0.5f;
    public static int spawnPosY = -100;
    public static int evasionRange = 150;
    //this should be bigger than the largest enemy size
    public static int cellSize = 100;
}
