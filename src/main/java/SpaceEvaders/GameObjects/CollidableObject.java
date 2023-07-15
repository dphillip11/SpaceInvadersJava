package SpaceEvaders.GameObjects;

public abstract class CollidableObject extends GameObject {
    protected int health = 1;
    protected int maxHealth = 1;
    protected ObjectType type = ObjectType.UNDEFINED;
    protected Boolean hasCollided = false;

    public abstract void die();

    public void resetCollisions() {
        hasCollided = false;
    }

    public void setHasCollided(Boolean bool) {
        this.hasCollided = bool;
    }

    public Boolean getHasCollided() {
        return hasCollided;
    }
    
    public int getHealth() {
        return health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    
    public ObjectType getType() {
        return type;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health > maxHealth) {
            maxHealth = health;
        }
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public abstract void onCollide(ObjectType otherType);

    public void takeDamage(int damage) {
        assert damage >= 0;
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    public void replenishHealth(int health) {
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

}
