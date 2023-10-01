package com.aanand.shooter.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aanand.shooter.framework.CharacterObject;
import com.aanand.shooter.framework.GameObject;
import com.aanand.shooter.framework.ObjectId;
import com.aanand.shooter.framework.Texture;
import com.aanand.shooter.window.Animation;
import com.aanand.shooter.window.Game;
import com.aanand.shooter.window.Handler;

/**
 * Represents an Enemy character in the game.
 * The Enemy class handles the behavior and rendering of enemy characters within the game world.
 */
public class Enemy extends CharacterObject {

    // Animations for enemy movement
    private Animation enemyWalk;
    private Animation enemyWalkLeft;

    // Access to game textures
    Texture tex = Game.getInstance();

    /**
     * Constructor for Enemy.
     * 
     * @param x X-coordinate of the enemy.
     * @param y Y-coordinate of the enemy.
     * @param width Width of the enemy.
     * @param height Height of the enemy.
     * @param handler Handler object to manage game objects.
     * @param id Unique identifier for the enemy.
     */
    public Enemy(float x, float y, float width, float height, Handler handler, ObjectId id) {
        super(x, y, width, height, handler, id);
        gravity = 0.5f;
        MAX_SPEED = 10;
        
        velX = -1;
        this.enemyWalk = new Animation(3, tex.enemy[1], tex.enemy[0]); // Assuming tex.enemy[0] is idle and tex.enemy[1] is walking, for facing left
        this.enemyWalkLeft = new Animation(3, tex.enemy[3], tex.enemy[2]); // For facing right


    }

    /**
     * Updates the enemy's state.
     * a
     * @param object List of all game objects.
     */
    public void tick(LinkedList<GameObject> object) {

        x += velX;
        y += velY;
        
        velY += gravity;
        
        // Determine direction the enemy is facing based on velocity
        if (velX > 0) {
            this.facing = -1;
        } else if (velX < 0) {
            this.facing = 1;
        }
        
		Collision(object);
		
		if (velX != 0) {
		    enemyWalk.runAnimation();
		    enemyWalkLeft.runAnimation();
		}
    }

    /**
     * Renders the enemy on the screen.
     * 
     * @param g Graphics context.
     */
    public void render(Graphics g) {
        if (facing == 1) {
        	if (velX != 0) {
                enemyWalk.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
        	} else {
                g.drawImage(tex.enemy[0], (int) x, (int) y, (int) this.width, (int) this.height, null); // drawing idle state
        	}
        } else if (facing == -1) {
        	if (velX!= 0) {
        		enemyWalkLeft.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
        	} else {
                g.drawImage(tex.enemy[2], (int) x, (int) y, (int) this.width, (int) this.height, null); // drawing walking state
        	}
        }
    }
    
    public void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            //If touches block
            if (tempObject.getId() == ObjectId.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    falling = false;
                    jumping = false;
                    velY = 0;
                    y = tempObject.getY() - height;
                } else {
                    falling = true;
                }
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                }
                
                //wall detection
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    velX = 1; // Change the enemy's direction when it hits a wall
                }
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    velX = -1; // Change the enemy's direction when it hits a wall
                }
            }else if (tempObject.getId() == ObjectId.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // Collision with fireball detected
                    System.out.print("hit fireball");
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }

            
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + height/2), (int)width/2, (int)height/2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
    }

    public Rectangle getBoundsLeft() {
    	return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
    }

    public Rectangle getBoundsRight() {
    	return new Rectangle((int) ((int)x + width-5), (int)y+5, (int)5, (int)height-10);
    }
}
