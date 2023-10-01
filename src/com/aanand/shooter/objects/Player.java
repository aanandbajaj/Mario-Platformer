package com.aanand.shooter.objects;

import java.awt.Color;
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
 * Represents the Player character in the game
 * The Player class handles the behavior, rendering, and interactions of the main character
 */

public class Player extends CharacterObject{
	
	//allows texture class to be able to be used
	Texture tex = Game.getInstance();
	
	// Animations for the player's movements
	private Animation playerWalk;
	private Animation playerWalkLeft;
	
	// Player score
	private int playerScore = 0;
		
	
	/**
	 * 
	 * @param x X-coordinate of player
	 * @param y y-coordinate of player
	 * @param width Width of player
	 * @param height Height of player
	 * @param handler Handler object to manage game objects
	 * @param id Unique identifier for player
	 */
	public Player(float x, float y, float width, float height, Handler handler, ObjectId id) {
		//super invokes constructor of superclass
		super(x, y, width, height, handler, id);
		gravity = 0.5f;
		MAX_SPEED = 10;
		
		playerWalk = new Animation(3, tex.player[1], tex.player[0]);
		playerWalkLeft = new Animation(3, tex.player[4], tex.player[3]);
	}
	
	/**
	 * Updates the player's state
	 * @param object list of all game objects
	 */
	public void tick(LinkedList<GameObject> object) {
		x = x + velX;
		
		//gravity starter
		//if want to turn-off gravity, comment this line out
		y = y + velY;
		
		//determine direction of player
		if(velX < 0){
			this.facing = -1;
		}
		else if(velX > 0){
			this.facing = 1;
		}
		
		//if falling or jumping
		if(falling || jumping){
			velY = velY + gravity;
			
			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}
		//check collision
		Collision(object);
		
		// Progress walking animation if player isn't jumping
		if(jumping == false){
			playerWalk.runAnimation();
			playerWalkLeft.runAnimation();
		}
	}
	
	/**
	 * this method is for collisions
	 * @param object list of all game objects
	 */
	public void Collision(LinkedList <GameObject> object){
		for(int i = 0; i < this.handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			//check if block
			if(tempObject.getId() == ObjectId.Block){
				
				//if top half collides with block
				if(getBoundsTop().intersects(tempObject.getBounds())){
					//snap to top block
					this.y = tempObject.getY() + tempObject.getHeight();
					this.velY = 0;
				}
				
				//if right side collides with block
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					//snap to right block
					this.x = tempObject.getX() + tempObject.getWidth();
					this.velX = 0;
				}
				
				//if left side collides with block
				if(getBoundsRight().intersects(tempObject.getBounds())){
					//snap to left block
					this.x = tempObject.getX() - this.width;
					this.velX = 0;
				}
				
				//if bottom half collides with block
				if(getBounds().intersects(tempObject.getBounds())){
					//snap to bottom block
					//fix
					//not moving down anymore and not jumping/falling
					this.velY = 0;
					this.falling = false;
					this.jumping = false;
					this.y = tempObject.getY() - this.height;					
						
				//anytime we are  not touching the block, we are falling
				//which starts the gravity
				}else{
					this.falling = true;
				}
				
			}
			else if(tempObject.getId() == ObjectId.Coin){
				if(getBoundsRight().intersects(tempObject.getBounds())){
					this.playerScore++;
					//snap to left block
					handler.removeObject(tempObject);
					Game.score.setText("Score: " + this.playerScore);
				}
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					this.playerScore++;
					//snap to left block
					handler.removeObject(tempObject);
					Game.score.setText("Score: " + this.playerScore);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		//render a blue rectangle
		//Make method for below operations
		g.setColor(Color.blue);
		if(jumping){
			if(facing == 1){
				g.drawImage(tex.player[2], (int)x, (int)y,(int)this.width, (int)this.height, null);
			}else if(facing == -1){
				g.drawImage(tex.player[5], (int)x, (int)y,(int)this.width, (int)this.height, null);
			}
		}
		//if moving, animate
		else if(velX != 0){
			if(facing == 1){
				playerWalk.drawAnimation(g, (int)x, (int)y, (int)this.width, (int)this.height);
			}else if(facing == -1){
				playerWalkLeft.drawAnimation(g, (int)x, (int)y, (int)this.width, (int)this.height);
			}
		}
		else{
			//if not moving, draw idle
			if(facing == 1){
				g.drawImage(tex.player[0], (int)x, (int)y,(int)this.width, (int)this.height, null);
			}else if(facing == -1){
				g.drawImage(tex.player[3], (int)x, (int)y,(int)this.width, (int)this.height, null);
			}
		}
		
		//comment out to take out bounding boxes
		//cast graphics variable to graphics 2d variable
		//to physically draw boxes 
		//Graphics2D g2d = (Graphics2D)g;
		

//		g.setColor(Color.yellow);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsTop());		
	}
	
	//used for collision
	//collision bounding
	//bottom collision
	//possibly put the bottom methods into GameObjects and you can overload them here
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + height/2), (int)width/2, (int)height/2 +5);
	}

	//top collision
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}

	//left collision
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width-5), (int)y+5, (int)5, (int)height-10);
	}

	//right collision
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

}
