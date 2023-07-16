package SpaceEvaders.GameObjects;

import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.Utilities.BooleanToggler;
import SpaceEvaders.CommonState.Variables;
import java.awt.Toolkit;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.ServiceLocator.SL;


public abstract class Powerup extends CollidableObject {
    public int powerup_strength = 1;
    protected EventType collideEvent = EventType.PLAYER_REPLENISH_HEALTH;

    public Powerup(Vector2 _position, int _powerup_strength) {
        setMaxHealth(_powerup_strength);
        setHealth(_powerup_strength * 2);
        powerup_strength = _powerup_strength;
        this.position = _position;
        this.velocity = new Vector2(Variables.healthPowerupSpeed - (float)Math.random() * 2 * Variables.healthPowerupSpeed, (float)Math.random() * 2 * Variables.healthPowerupSpeed);
        this.type = ObjectType.HEALTH_POWERUP;
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup1.png"));
        this.flashImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup2.png"));
        radius = new Vector2(Variables.powerup_size, Variables.powerup_size);
        this.flashToggler = new BooleanToggler(true, 0.3, 0.5, true);
        radius = new Vector2(Variables.powerup_size * powerup_strength, Variables.powerup_size * powerup_strength);
        startFlashing();
    }

    public void die() {
        isActive = false;
    }

     @Override
    public void onCollide(ObjectType otherType, CollidableObject otherObject) {
        if (otherType == ObjectType.PLAYER) {
            SL.eventHandler.notify(collideEvent, powerup_strength);
            die();
        }
        else {
            takeDamage(1);
            powerup_strength = (int)( health / 2);
            radius = new Vector2(Variables.powerup_size * powerup_strength, Variables.powerup_size * powerup_strength);
            hasCollided = true;
        }
    } 
    
}
