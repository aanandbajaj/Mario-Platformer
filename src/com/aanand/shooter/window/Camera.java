package com.aanand.shooter.window;

import com.aanand.shooter.framework.GameObject;

public class Camera {

	protected float x;
	protected float y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	//need player for camera to snap on to it
	public void tick(GameObject player){
		//camera snap to player
		this.x = -player.getX() + Game.WIDTH/2;
		this.y = -player.getY() + Game.HEIGHT/2;
	}
}
