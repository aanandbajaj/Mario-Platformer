package com.aanand.shooter.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private  BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	//col and row is to indicate position in spritesheet that want to use
	//first parameter in getSubImage is the starting point for col
	//second parameter is starting point for row
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col*width) - width, (row*height) - height, width, height);
		return img;
	}
	
}
