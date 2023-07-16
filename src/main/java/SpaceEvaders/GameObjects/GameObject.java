package SpaceEvaders.GameObjects;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.Utilities.BooleanToggler;



public abstract class GameObject {

    protected Vector2 position = new Vector2(0, 0);
    protected Vector2 velocity = new Vector2(0, 0);
    protected Vector2 radius = new Vector2(1, 1);
    protected boolean isActive = true;

    protected BooleanToggler flashToggler = new BooleanToggler(false, 0.1, 0.5);

    public void startFlashing() {
        flashToggler.start();
    }

    public boolean isFlashing() {
        return flashToggler.getValue();
    }

    public void updateToggler(double deltaTime) {
        flashToggler.update(deltaTime);
    }
    
    public Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy1.png"));
    public Image flashImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy1-red.png"));
  
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

    public void drawFlashImage(Graphics g) {
        int sizeX = (int) (radius.x * 2);
        int sizeY = (int) (radius.y * 2);
         
        g.drawImage(flashImage, (int) position.x - (int) radius.x, (int) position.y - (int) radius.y,
                sizeX, sizeY, null);
    }

    //default draw method
    public void draw(Graphics g) {
        int sizeX = (int) (radius.x * 2);
        int sizeY = (int) (radius.y * 2);
        g.drawImage(image, (int) position.x - (int) radius.x, (int) position.y - (int) radius.y,
                sizeX, sizeY, null);
        if (!isFlashing())
            return;
        drawFlashImage(g);
    }
}
