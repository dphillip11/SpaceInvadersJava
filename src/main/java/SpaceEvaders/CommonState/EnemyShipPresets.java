package SpaceEvaders.CommonState;

import SpaceEvaders.Builder.SpaceshipPreset;
import SpaceEvaders.GameObjects.ObjectType;
import SpaceEvaders.Utilities.Vector2;


public class EnemyShipPresets {



    public static float shootRateVariant0 = 0.05f;
    public static float shootRateVariant1 = 0.2f;


    public static SpaceshipPreset variant0() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 50;
        preset.bulletSize = new Vector2(1, 7);
        preset.shootRate = 0.05f;
        preset.maxHealth = 1;
        preset.type = ObjectType.ENEMY;
        preset.radius = new Vector2(5, 5);
        return preset;
    }

    public static SpaceshipPreset variant1() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(4, 4);
        preset.maxHealth = 2;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.06f;
        preset.velocity = new Vector2(0, 0);
        preset.radius = new Vector2(10, 10);
        return preset;
    }

    public static SpaceshipPreset variant2() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 20;
        preset.bulletSize = new Vector2(7, 7);
        preset.maxHealth = 3;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.02f;
        preset.velocity = new Vector2(0, 0);
        preset.radius = new Vector2(20, 20);
        return preset;
    }

    public static SpaceshipPreset variant3() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 50;
        preset.bulletSize = new Vector2(6, 2);
        preset.maxHealth = 4;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.03f;
        preset.velocity = new Vector2(0, 0);
        preset.radius = new Vector2(25, 25);
        return preset;
    }

    public static SpaceshipPreset variant4() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(2, 2);
        preset.maxHealth = 5;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.1f;
        preset.velocity = new Vector2(0, 0);
        preset.radius = new Vector2(30, 30);
        return preset;
    }

    public static SpaceshipPreset variant5() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(2, 2);
        preset.maxHealth = 10;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.1f;
        preset.velocity = new Vector2(0, 0);
        preset.radius = new Vector2(50, 50);
        return preset;
    }
}
