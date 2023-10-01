package com.aanand.shooter.framework;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.aanand.shooter.window.BufferedImageLoader;

/**
 * Manages and provides textures for the game.
 * This class loads and processes textures for different game objects.
 */
public class Texture {

    // Sprite sheets for different game objects
    SpriteSheet bs, ps, fs, cs, es;

    // BufferedImages for each sprite sheet
    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;
    private BufferedImage fireballSheet = null;
    private BufferedImage coinSheet = null;
    private BufferedImage enemySheet = null;
    
    // Arrays to hold individual sprites extracted from the sprite sheets
    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[6];
    public BufferedImage[] fireball = new BufferedImage[8];
    public BufferedImage[] coin = new BufferedImage[4];
    public BufferedImage[] enemy = new BufferedImage[4];
    
    /**
     * Constructor for Texture.
     * Loads the sprite sheets and extracts the individual sprites.
     */
    public Texture() {
        BufferedImageLoader loader = new BufferedImageLoader();
        
        // Load sprite sheets
        try {
            blockSheet = loader.loadImage("/block.png");
            playerSheet = loader.loadImage("/player.png");
            fireballSheet = loader.loadImage("/fireball.png");
            coinSheet = loader.loadImage("/coin.png");
            enemySheet = loader.loadImage("/enemy.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Initialize sprite sheets with the loaded images
        bs = new SpriteSheet(blockSheet);
        ps = new SpriteSheet(playerSheet);
        fs = new SpriteSheet(fireballSheet);
        cs = new SpriteSheet(coinSheet);
        es = new SpriteSheet(enemySheet);
        
        // Extract individual sprites
        getTextures();
    }
    
    /**
     * Extracts individual sprites from the sprite sheets.
     */
    private void getTextures() {
        // Extract block sprites
        block[0] = bs.grabImage(2, 1, 16, 16); // dirt block
        block[1] = bs.grabImage(5, 1, 16, 16); // grass block
        block[2] = bs.grabImage(7, 1, 16, 16); // lava block
        
        // Extract player sprites
        player[0] = ps.grabImage(1, 1, 15, 22); // idle pose
        player[1] = ps.grabImage(2, 1, 15, 22); // walking
        player[2] = ps.grabImage(3, 1, 15, 22); // jumping
        
        // Extract fireball sprites
        fireball[0] = fs.grabImage(1, 1, 17, 17); // start
        fireball[1] = fs.grabImage(2, 1, 17, 17);
        fireball[2] = fs.grabImage(3, 1, 17, 17);
        fireball[3] = fs.grabImage(4, 1, 17, 17);
        
        // Create reversed versions of the player sprites
        reverseTextures(player);
        
        // Extract coin sprites
        coin[0] = cs.grabImage(1, 1, 14, 16);
        coin[1] = cs.grabImage(2, 1, 14, 16);
        coin[2] = cs.grabImage(3, 1, 14, 16);
        coin[3] = cs.grabImage(4, 1, 14, 16);
        
        // Extract enemy sprites
        enemy[0] = es.grabImage(1, 1, 16, 16);
        enemy[1] = es.grabImage(2, 1, 16, 16); // For the walking state
        
        reverseTextures(enemy);
    }
    
    /**
     * Creates reversed versions of the sprites.
     * 
     * @param img Array of BufferedImages to reverse.
     */
    private void reverseTextures(BufferedImage[] img) {
        for (int i = 0; i < img.length / 2; i++) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-img[i].getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img[i + (img.length / 2)] = op.filter(img[i], null);
        }
    }

}
