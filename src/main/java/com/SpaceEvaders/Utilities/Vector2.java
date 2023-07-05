package com.SpaceEvaders.Utilities;

public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Addition
    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    // Subtraction
    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    // Multiplication by scalar
    public Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    // Optional: In-place addition
    public void addToThis(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Optional: In-place subtraction
    public void subtractFromThis(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
    }

        // Optional: In-place addition
    public void addToThis(float x, float y) {
        this.x += x;
        this.y += y;
    }

    // Optional: In-place subtraction
    public void subtractFromThis(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    // Optional: In-place multiplication by scalar
    public void multiplyThisBy(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }
}
