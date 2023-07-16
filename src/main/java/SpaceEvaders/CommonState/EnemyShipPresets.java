package SpaceEvaders.CommonState;

import SpaceEvaders.Builder.SpaceshipPreset;
import SpaceEvaders.GameObjects.ObjectType;
import SpaceEvaders.Utilities.Vector2;


public class EnemyShipPresets {



    public static float shootRateVariant0 = 0.05f;
    public static float shootRateVariant1 = 0.2f;


    public static SpaceshipPreset variant0() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 70;
        preset.bulletSize = new Vector2(5, 5);
        preset.shootRate = 0.05f;
        preset.maxHealth = 1;
        preset.type = ObjectType.ENEMY;
        preset.variant = 0;
        preset.velocity = new Vector2(30, 40);
        preset.radius = new Vector2(15, 15);
        return preset;
    }

    public static SpaceshipPreset variant1() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(4, 4);
        preset.maxHealth = 2;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.06f;
        preset.velocity = new Vector2(25, 80);
        preset.radius = new Vector2(28, 28);
        preset.variant = 1;
        return preset;
    }

    public static SpaceshipPreset variant2() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 70;
        preset.bulletSize = new Vector2(10, 10);
        preset.maxHealth = 3;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.02f;
        preset.velocity = new Vector2(20, 80);
        preset.radius = new Vector2(35, 35);
        preset.variant = 2;
        return preset;
    }

    public static SpaceshipPreset variant3() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(5, 5);
        preset.maxHealth = 4;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.03f;
        preset.velocity = new Vector2(15, 35);
        preset.radius = new Vector2(45, 45);
        preset.variant = 3;
        return preset;
    }

    public static SpaceshipPreset variant4() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 100;
        preset.bulletSize = new Vector2(3, 5);
        preset.maxHealth = 5;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.1f;
        preset.velocity = new Vector2(10, 120);
        preset.radius = new Vector2(50, 50);
        preset.variant = 4;
        return preset;
    }

    public static SpaceshipPreset variant5() {
        SpaceshipPreset preset = new SpaceshipPreset();
        preset.bulletSpeed = 150;
        preset.bulletSize = new Vector2(3, 6);
        preset.maxHealth = 10;
        preset.type = ObjectType.ENEMY;
        preset.shootRate = 0.1f;
        preset.velocity = new Vector2(5, 25);
        preset.radius = new Vector2(70, 70);
        preset.variant = 5;
        return preset;
    }
}
