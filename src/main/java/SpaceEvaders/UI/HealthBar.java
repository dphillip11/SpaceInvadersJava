package SpaceEvaders.UI;

import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.CommonState.Constants;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {
    private float healthPercentage = 1.0f;
    private float displayedHealthPercentage = 1.0f;
    private float healthChangeSpeed = 0.01f;
    private Vector2 position = new Vector2(Constants.screenWidth * 0.02f, Constants.screenWidth * 0.05f) ;
    private Vector2 size = new Vector2(Constants.screenWidth * 0.02f, Constants.screenHeight * 0.8f);
    private Color bar_color = new Color(0,1,0,0.75f);
    private Color background_color = new Color(1,0,0,0.75f);

    private int padding = 5;
    
    public void setHealth(float current, float max)
    {
        healthPercentage = current / max;
    }

    public void setHealth(float percentage)
    {
        healthPercentage = percentage;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public void setPosition(float x, float y)
    {
        this.position = new Vector2(x, y);
    }

    public void setSize(Vector2 size)
    {
        this.size = size;
    }

    public void setSize(float x, float y)
    {
        this.size = new Vector2(x, y);
    }

    public void draw(Graphics g) {
        //draw rectangle size of health bar + padding
        g.setColor(background_color);
        //round edges on rectangle
        g.fillRoundRect((int) (position.x - padding), (int) (position.y - padding),
                (int) (size.x + 2 * padding), (int)(size.y + 2 * padding), 20, 20);
        //draw health bar
        g.setColor(bar_color);
        displayedHealthPercentage += (healthPercentage - displayedHealthPercentage) * healthChangeSpeed;
        float healthBarHeight = size.y * displayedHealthPercentage;
        g.fillRoundRect((int) (position.x), (int) (position.y + size.y - healthBarHeight),
                 (int) size.x, (int) healthBarHeight, 20 - padding, 20 - padding);

    }
}
