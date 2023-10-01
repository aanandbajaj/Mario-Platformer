package com.aanand.shooter.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aanand.shooter.framework.GameObject;
import com.aanand.shooter.framework.ObjectId;
import com.aanand.shooter.framework.Texture;
import com.aanand.shooter.window.Animation;
import com.aanand.shooter.window.Game;

/**
 * Represents a Bullet object in the game.
 * The Bullet class handles the behavior and rendering of bullets, typically fired by the player or enemies.
 */
public class Bullet extends GameObject {

    // Access to game textures
    Texture tex = Game.getInstance();

    // Animation for the bullet (fireball)
    private Animation fireball;

    /**
     * Constructor for Bullet.
     * 
     * @param x X-coordinate of the bullet.
     * @param y Y-coordinate of the bullet.
     * @param velX Velocity in the X direction.
     * @param id Unique identifier for the bullet.
     */
    public Bullet(float x, float y, int velX, ObjectId id) {
        super(x, y, 34, 34, id);
        this.velX = velX;

        // Initialize fireball animation with appropriate frames and delay
        this.fireball = new Animation(3, tex.fireball[0], tex.fireball[1], tex.fireball[2], tex.fireball[3], tex.fireball[0]);
    }

    /**
     * Updates the bullet's state.
     * 
     * @param object List of all game objects.
     */
    public void tick(LinkedList<GameObject> object) {
        // Update bullet's position
        x += velX;

        // Progress the animation
        fireball.runAnimation();
    }

    /**
     * Renders the bullet on the screen.
     * 
     * @param g Graphics context.
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        
        // Draw the bullet using the animation
        fireball.drawAnimation(g, (int)x, (int)y, 34, 34);
    }

    /**
     * Gets the collision bounds of the bullet.
     * 
     * @return Rectangle representing the bullet's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)this.width, (int)this.height);
    }

    // The following methods return null as bullets don't have distinct bounds for top, left, or right in this implementation
    public Rectangle getBoundsTop() {
        return null;
    }

    public Rectangle getBoundsLeft() {
        return null;
    }

    public Rectangle getBoundsRight() {
        return null;
    }
}
