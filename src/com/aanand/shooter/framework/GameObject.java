package com.aanand.shooter.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Abstract class representing a game object.
 * This class provides a framework for all game objects, including their position, movement, and collision.
 */
public abstract class GameObject {

    // Protected variables can be accessed by subclasses

    // X and Y coordinates of the object
    protected float x, y;

    // X and Y velocities of the object
    protected float velX = 0;
    protected float velY = 0;

    // Unique identifier for the type of the game object
    protected ObjectId id;

    // Flags indicating if the object is currently falling or jumping
    protected boolean falling;
    protected boolean jumping;

    // Direction the object is facing: 1 for left, 0 for right
    protected int facing = 1;

    // Dimensions of the game object
    protected float width;
    protected float height;

    /**
     * Constructor for GameObject.
     * 
     * @param x X-coordinate of the object.
     * @param y Y-coordinate of the object.
     * @param width Width of the object.
     * @param height Height of the object.
     * @param id Unique identifier for the type of the game object.
     */
    public GameObject(float x, float y, float width, float height, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.falling = true;
        this.jumping = false;
        this.width = width;
        this.height = height;
    }

    // Getter and setter for the direction the object is facing
    public int getFacing() {
        return facing;
    }
    public void setFacing(int facing) {
        this.facing = facing;
    }

    // Getters and setters for the object's dimensions
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    // Abstract methods for game logic and rendering

    /**
     * Update the object's state.
     * 
     * @param object List of all game objects.
     */
    public abstract void tick(LinkedList<GameObject> object);

    /**
     * Render the object on the screen.
     * 
     * @param g Graphics context.
     */
    public abstract void render(Graphics g);

    // Abstract methods for collision detection

    /**
     * Get the collision bounds of the object.
     * 
     * @return Rectangle representing the object's bounds.
     */
    public abstract Rectangle getBounds();
    public abstract Rectangle getBoundsTop();
    public abstract Rectangle getBoundsLeft();
    public abstract Rectangle getBoundsRight();

    // Getters and setters for position and velocity

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }

    // Getters and setters for the object's identifier and state flags

    public ObjectId getId(){
        return id;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    public boolean getFalling(){
        return this.falling;
    }
    public boolean getJumping(){
        return this.jumping;
    }
    public void setFalling(boolean falling){
        this.falling = falling;
    }
    public void setJumping(boolean jumping){
        this.jumping = jumping;
    }
}
