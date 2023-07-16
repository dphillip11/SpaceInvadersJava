package SpaceEvaders.GameObjects;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Utilities.Vector2;
import SpaceEvaders.Builder.SpaceshipPreset;



public class Spaceship extends CollidableObject {
    public int variant = 0;
    public Boolean hasPowerup = false;
    protected float powerupTimer = 0;
    protected float bulletSpeed = 5;
    protected float shootRate = 0.05f;
    protected Vector2 bulletSize = new Vector2(1, 1);

    public void applyPreset(SpaceshipPreset preset) {
        bulletSpeed = preset.bulletSpeed;
        bulletSize = preset.bulletSize;
        maxHealth = preset.maxHealth;
        type = preset.type;
        velocity = preset.velocity;
        radius = preset.radius;
        shootRate = preset.shootRate;
        variant = preset.variant;
    }

    public void setBulletSpeed(float speed) {
        bulletSpeed = speed;
    }

    public void setBulletSize(Vector2 size) {
        bulletSize = size;
    }

    public void updatePowerupStatus(float dt) {
        if (hasPowerup) {
            powerupTimer -= dt;
            if (powerupTimer <= 0) {
                hasPowerup = false;
            }
        }
    }

    public void resetPowerupTimer(float time) {
        powerupTimer = time;
        hasPowerup = true;
    }

    public void die() {
        isActive = false;
        if (type == ObjectType.PLAYER)
        {
            SL.eventHandler.notify(EventType.PLAYER_DESTROYED);
        }
        else if (type == ObjectType.ENEMY)
        {
            SL.eventHandler.notify(EventType.ENEMY_DESTROYED);
            if(Math.random() < Variables.healthPowerupChance)
            {
                SL.eventHandler.notify(EventType.SPAWN_HEALTH_POWERUP, position,
                        (int) (Math.ceil(Math.random() * maxHealth)));
            }
            else if (Math.random() < Variables.bulletPowerupChance)
            {
                SL.eventHandler.notify(EventType.SPAWN_BULLET_POWERUP, position,
                        (int) (Math.ceil(Math.random() * maxHealth)));
            }
        }
    }

    public void shoot() {
        if (isActive()) {
            Vector2 vel = new Vector2(0, bulletSpeed);
            Vector2 origin = new Vector2(position.x, position.y);
            float offset = radius.y + bulletSize.y;
            if (isFriendly()) {
                vel.y = -vel.y;
                origin.subtractFromThis(0, offset);
            } else {
                origin.addToThis(0, offset);
            }
            SL.eventHandler.notify(EventType.SHOOT, vel, origin, bulletSize, isFriendly());
        }
    }
    
    public void shootRandom()
    {
        if (Math.random() < shootRate) {
            shoot();
        }
    }
    
    @Override
    public void onCollide(ObjectType otherType, CollidableObject otherObject) {   
        if (type == ObjectType.ENEMY && otherType == ObjectType.BULLET_FRIENDLY || type == ObjectType.PLAYER && otherType == ObjectType.BULLET_ENEMY) {
            takeDamage(((Bullet)otherObject).firepower);
            startFlashing();
            setHasCollided(true);
        }
        if (otherType == ObjectType.BULLET_POWERUP) {
            resetPowerupTimer(((Powerup) otherObject).powerup_strength);
        }
    }
}
