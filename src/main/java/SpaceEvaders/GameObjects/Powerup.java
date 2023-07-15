package SpaceEvaders.GameObjects;

import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.Utilities.BooleanToggler;
import SpaceEvaders.CommonState.Variables;
import java.awt.Toolkit;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.ServiceLocator.SL;


public abstract class Powerup extends CollidableObject {
    protected int powerup_strength = 1;
    protected EventType collideEvent = EventType.PLAYER_REPLENISH_HEALTH;

    public Powerup(Vector2 _position, int _powerup_strength) {
        health = 5;
        powerup_strength = _powerup_strength;
        this.position = _position;
        this.velocity = new Vector2(Variables.healthPowerupSpeed - (float)Math.random() * 2 * Variables.healthPowerupSpeed, (float)Math.random() * 2 * Variables.healthPowerupSpeed);
        this.type = ObjectType.HEALTH_POWERUP;
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup1.png"));
        this.flashImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup2.png"));
        radius = new Vector2(Variables.powerup_size, Variables.powerup_size);
        this.flashToggler = new BooleanToggler(true, 0.3, 0.5, true);
        startFlashing();
    }

    public void die() {
        isActive = false;
    }

     @Override
    public void onCollide(ObjectType otherType) {
        if (otherType == ObjectType.PLAYER) {
            SL.eventHandler.notify(collideEvent, powerup_strength);
            die();
        }
        else {
            takeDamage(1);
        }
    } 
    
}
