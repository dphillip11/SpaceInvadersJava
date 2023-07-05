package com.SpaceEvaders.GameObjects;
 
public abstract class CollidableObject extends GameObject {
    protected int health = 1;
    protected int maxHealth = 1;
    protected ObjectType type = ObjectType.UNDEFINED;
    protected boolean hasCollided = false;

    public abstract void die();

     public int getHealth() {
        return health;
    }

    public ObjectType getType() {
        return type;
    }

    public boolean hasCollided() {
        return hasCollided;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }
    
     public void Collide(ObjectType otherType) {
    if (type == otherType) {
        return;
    }
    if (type == ObjectType.BULLET_ENEMY && otherType == ObjectType.ENEMY) {
        return;
    }
    if (type == ObjectType.BULLET_FRIENDLY && otherType == ObjectType.PLAYER) {
        return;
    }
    if (type == ObjectType.PLAYER && otherType == ObjectType.BULLET_FRIENDLY) {
        return;
    }
    if (type == ObjectType.ENEMY && otherType == ObjectType.BULLET_ENEMY) {
        return;
    }
    hasCollided = true;
    }

    public void takeDamage(int damage) {
        hasCollided = false;
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
