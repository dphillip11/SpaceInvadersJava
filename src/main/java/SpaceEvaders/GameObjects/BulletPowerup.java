package SpaceEvaders.GameObjects;

import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;
import java.awt.Toolkit;

public class BulletPowerup extends Powerup {
    public BulletPowerup(Vector2 _position, int _powerup_strength)
    {
        super(_position, _powerup_strength);
        this.type = ObjectType.BULLET_POWERUP;
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup4.png"));
        this.flashImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/powerup3.png"));
        collideEvent = EventType.BULLET_POWERUP_COLLECTED;
    }
}
