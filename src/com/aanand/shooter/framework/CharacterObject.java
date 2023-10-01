package com.aanand.shooter.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aanand.shooter.window.Game;
import com.aanand.shooter.window.Handler;

public class CharacterObject extends GameObject{

	protected float gravity = 0f;
	protected float MAX_SPEED = 0;
	
	protected Handler handler;
	public CharacterObject(float x, float y, float width, float height, Handler handler, ObjectId id) {
		super(x, y, width, height, id);
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		
	}
	
	//this method is for collisions
		protected void Collision(LinkedList <GameObject> object){
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
			}
		}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + height/2), (int)width/2, (int)height/2 +5);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) ((int)x + width-5), (int)y+5, (int)5, (int)height-10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}

}
