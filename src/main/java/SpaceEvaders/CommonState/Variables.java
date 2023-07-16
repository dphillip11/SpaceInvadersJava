package SpaceEvaders.CommonState;

import SpaceEvaders.Utilities.Vector2;

public class Variables {
    public static float playerSpeed = 400;
    public static Vector2 playerSize = new Vector2(15, 15);
    public static float playerBulletSpeed = 500;
    public static Vector2 playerBulletRadius = new Vector2(2, 20);

    public static int maxPlayerHealth = 10;

    public static float enemySpawnInterval = 4;

    public static float incidenceVariant0 = 0.02f;
    public static float incidenceVariant1 = 0.08f;
    public static float incidenceVariant2 = 0.01f;
    public static float incidenceVariant3 = 0.005f;
    public static float incidenceVariant4 = 0.001f;
    public static float incidenceVariant5 = 0.0005f;

    public static float powerup_size = 5;
    public static float healthPowerupChance = 0.0f;
    public static float healthPowerupSpeed = 50;
    public static float bulletPowerupChance = 1.0f;
    public static float bulletPowerupDuration = 2;
    
    public static float SFX_volume = 0.5f;
    public static int spawnPosY = -100;
    public static int evasionRange = 150;
    //this should be bigger than the largest enemy size
    public static int cellSize = 100;
}
