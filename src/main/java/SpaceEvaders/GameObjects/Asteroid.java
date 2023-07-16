package SpaceEvaders.GameObjects;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Utilities.Vector2;


public class Asteroid extends CollidableObject {
    private static List<GameObject> gameobjects;
    private Image asteroidLarge = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/large/a10000.png"));
    private Image asteroidMedium = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/medium/a10000.png"));
    public Image asteroidSmall = Toolkit.getDefaultToolkit()
            .getImage(getClass().getResource("/images/small/a10000.png"));

    private int size = 2;
    
    public static void setGameObjects(List<GameObject> gameobjects) {
        Asteroid.gameobjects = gameobjects;
    }

    public void die() {
        if (size > 0)
        {
            Asteroid asteroid1 = newAsteroid(size - 1);
            asteroid1.setPosition(new Vector2(position.x + 20, position.y));
            Asteroid asteroid2 = newAsteroid(size - 1);
            asteroid2.setPosition(new Vector2(position.x - 20, position.y - 20));
            Asteroid asteroid3 = newAsteroid(size - 1);
            asteroid3.setPosition(new Vector2(position.x + 20, position.y - 20));
        }
        isActive = false;
    }
    
    private void setImage() {
        if (size > 1) {
            image = asteroidLarge;
            setRadius(new Vector2(90, 90));
        } else if (size > 0) {
            image = asteroidMedium;
            setRadius(new Vector2(30, 30));
        } else {
            image = asteroidSmall;
            setRadius(new Vector2(10, 10));
        }
    }
    
    public static Asteroid newAsteroid(int _size){
        Asteroid asteroid = new Asteroid(new Vector2((float)(Math.random() * Constants.screenWidth), -100), _size);
        gameobjects.add(asteroid);
        return asteroid;
    }

    Asteroid(Vector2 position, int _size) {
        setPosition(position);
        setMaxHealth(4);
        size = _size;
        setImage();
        setVelocity(new Vector2(0, (float)(Math.random() * 70 + 30)));
    }
    
    public void onCollide(ObjectType otherType, CollidableObject otherObject) {
        if (otherType == ObjectType.BULLET_ENEMY || otherType == ObjectType.BULLET_FRIENDLY) {
            takeDamage(1);
            setHasCollided(true);
        }
    }


}
