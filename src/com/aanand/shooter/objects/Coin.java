package com.aanand.shooter.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aanand.shooter.framework.GameObject;
import com.aanand.shooter.framework.ObjectId;
import com.aanand.shooter.framework.Texture;
import com.aanand.shooter.window.Animation;
import com.aanand.shooter.window.Game;

/**
 * Represents a Coin object in the game.
 * The Coin class handles the behavior and rendering of coins, which might be collected by the player.
 */
public class Coin extends GameObject {

    // Access to game textures
    Texture tex = Game.getInstance();

    // Animation for the coin
    private Animation coin;

    /**
     * Constructor for Coin.
     * 
     * @param x X-coordinate of the coin.
     * @param y Y-coordinate of the coin.
     * @param id Unique identifier for the coin.
     */
    public Coin(float x, float y, ObjectId id) {
        super(x, y, 28, 32, id);

        // Initialize coin animation with appropriate frames and delay
        this.coin = new Animation(3, tex.coin[0], tex.coin[1], tex.coin[2], tex.coin[3]);
    }

    /**
     * Updates the coin's state.
     * 
     * @param object List of all game objects.
     */
    public void tick(LinkedList<GameObject> object) {
        // Progress the animation
        coin.runAnimation();
    }

    /**
     * Renders the coin on the screen.
     * 
     * @param g Graphics context.
     */
    public void render(Graphics g) {
        // Draw the coin using the animation
        coin.drawAnimation(g, (int)x, (int)y, 28, 32);
    }

    /**
     * Gets the collision bounds of the coin.
     * 
     * @return Rectangle representing the coin's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)this.width, (int)this.height);
    }

    // The following methods return null as coins don't have distinct bounds for top, left, or right in this implementation
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
