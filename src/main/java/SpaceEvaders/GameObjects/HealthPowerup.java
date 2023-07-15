package SpaceEvaders.GameObjects;

import SpaceEvaders.Utilities.BooleanToggler;
import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.EventsSystem.EventType;
import java.awt.Toolkit;

public class HealthPowerup extends CollidableObject {
    int amount = 1;

    public HealthPowerup(Vector2 _position, int _amount) {
        this.amount = _amount;
        health = 5;
        this.position = _position;
        this.velocity = new Vector2(Variables.healthPowerupSpeed - (float)Math.random() * 2 * Variables.healthPowerupSpeed, (float)Math.random() * 2 * Variables.healthPowerupSpeed);
        this.type = ObjectType.HEALTH_POWERUP;
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup1.png"));
        this.flashImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup2.png"));
        radius = new Vector2(amount * Variables.powerup_size, amount * Variables.powerup_size);
        this.flashToggler = new BooleanToggler(true, 0.3, 0.5, true);
        startFlashing();
    }

    public void die() {
        isActive = false;
    }

    @Override
    public void onCollide(ObjectType otherType) {
        if (otherType == ObjectType.PLAYER) {
            SL.eventHandler.notify(EventType.PLAYER_REPLENISH_HEALTH, amount);
            die();
        }
        else {
            takeDamage(1);
        }
    }
    
    
}
