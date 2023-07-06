package SpaceEvaders.GameObjects;

import java.awt.Image;
import java.awt.Toolkit;

import SpaceEvaders.Utilities.Vector2;


public abstract class GameObject {

    protected Vector2 position = new Vector2(0, 0);
    protected Vector2 velocity = new Vector2(0, 0);
    protected Vector2 radius = new Vector2(1, 1);
    protected boolean isActive = true;
    
    public Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy1.png"));
  
    public void move(Vector2 distance)
    {
        position = position.add(distance);
    }

    public Vector2 getMaxPos() {
        return position.add(radius);
    }

    public Vector2 getMinPos() {
        return position.subtract(radius);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getRadius() {
        return radius;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setRadius(Vector2 radius) {
        this.radius = radius;
    }

    public void setActive(boolean active) {
        isActive = active;
    }







}
