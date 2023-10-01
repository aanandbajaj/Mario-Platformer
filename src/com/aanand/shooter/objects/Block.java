package com.aanand.shooter.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aanand.shooter.framework.GameObject;
import com.aanand.shooter.framework.ObjectId;
import com.aanand.shooter.framework.Texture;
import com.aanand.shooter.window.Game;

/**
 * Represents a Block object in the game.
 * A Block is a basic building unit in the game world, and can be of different types like grass, dirt, or lava.
 */
public class Block extends GameObject {

    // Game texture instance to fetch the appropriate block image
    Texture tex = Game.getInstance();

    // Type of the block: 0 for grass top, 1 for dirt, and 2 for lava
    private int type;

    /**
     * Constructor for Block.
     * 
     * @param x X-coordinate of the block.
     * @param y Y-coordinate of the block.
     * @param type Type of the block (0, 1, or 2).
     * @param id Unique identifier for the block.
     */
    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, 32, 32, id);
        this.type = type;
    }

    /**
     * Updates the block's state.
     * 
     * @param object List of all game objects.
     */
    public void tick(LinkedList<GameObject> object) {
        // No update logic for blocks as they are static
    }

    /**
     * Renders the block on the screen.
     * 
     * @param g Graphics context.
     */
    public void render(Graphics g) {
        g.drawImage(tex.block[type], (int) x, (int) y, (int) this.width, (int) this.height, null);
    }

    /**
     * Gets the collision bounds of the block.
     * 
     * @return Rectangle representing the block's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) this.width, (int) this.height);
    }

    // The following methods return null as blocks don't have distinct bounds for top, left, or right in this implementation
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
