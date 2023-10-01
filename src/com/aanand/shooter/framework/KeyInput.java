package com.aanand.shooter.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.aanand.shooter.objects.Bullet;
import com.aanand.shooter.window.Handler;

/**
 * Handles keyboard input for the game.
 * This class extends the KeyAdapter and provides methods to react to key presses, releases, and types.
 */
public class KeyInput extends KeyAdapter {
    
    // Handler to manage game objects
    Handler handler;
    
    /**
     * Constructor for KeyInput.
     * 
     * @param handler Handler object to manage game objects.
     */
    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    /**
     * Called when a key is pressed.
     * 
     * @param e KeyEvent object containing details about the key event.
     */
    public void keyPressed(KeyEvent e) {
        // Get the key code
        int key = e.getKeyCode();
        
        // Loop through all GameObjects in the LinkedList
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            // Check if the current object is a Player
            if(tempObject.getId() == ObjectId.Player) {
                // React to the D key press
                if(key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);  // Move right
                }
                // React to the A key press
                if(key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);  // Move left
                }
                // React to the W key press (only if not jumping)
                if(key == KeyEvent.VK_W && !tempObject.getJumping()) {
                    tempObject.setVelY(-15);  // Jump
                    tempObject.setJumping(true);
                }
                // React to the SPACE key press
                if(key == KeyEvent.VK_SPACE) {
                    // Create and add a bullet object
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + tempObject.getHeight()/2, tempObject.getFacing()*10, ObjectId.Bullet));
                }
            }
        }
        
        // Quit the game if ESCAPE key is pressed
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }
    
    /**
     * Called when a key is released.
     * 
     * @param e KeyEvent object containing details about the key event.
     */
    public void keyReleased(KeyEvent e) {
        // Get the key code
        int key = e.getKeyCode();
        
        // Loop through all GameObjects in the LinkedList
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            // If the current object is a Player and either D or A key is released, stop the movement
            if(tempObject.getId() == ObjectId.Player) {
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
    
    /**
     * Called when a key is typed (pressed and then released).
     * Currently, this method is empty and does not handle any key typed events.
     * 
     * @param e KeyEvent object containing details about the key event.
     */
    public void keyTyped(KeyEvent e) {
        // No implementation for keyTyped events in the current version
    }
}
