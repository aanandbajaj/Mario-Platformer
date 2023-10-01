package com.aanand.shooter.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;//all images
	private BufferedImage currentImg;//current image to display
	
	//the reason why it is BufferedImage ...
	//is because it allows the constructor to have infinite paramters of BufferedImage
	//this makes the class generalized because in the future if we decide to have new sprites
	//with different amount of frames, this Animation Class will still work
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		//first argument is which image, then image[0] is first image
		//directly correlating with parameter to class
		for(int i = 0; i < args.length; i++){
			images[i] = args[i];
		}
		//number of frames
		this.frames = args.length;
	}
	
	public void runAnimation(){
		this.index++;
		//allows to reset animation
		if (this.index > this.speed){
			this.index = 0;
			nextFrame();
		}
	}
	
	public void nextFrame(){
		
		//purpose is to make currentImage the image from the BufferedImage array
		//count = current frame index
		//example: if i = 0 and current = 0, then change the current image to first image
		for(int i = 0; i< this.frames; i++){
			if(this.count == i){
				this.currentImg = this.images[i];
			}
		}
		count++;
		
		//repeat animation
		//if count is higher than the frames
		if(this.count > this.frames){
			this.count = 0;
		}
	}
	
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(this.currentImg, x, y, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
		g.drawImage(this.currentImg, x, y, scaleX, scaleY, null);
	}

	//Getters and Setters
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}

