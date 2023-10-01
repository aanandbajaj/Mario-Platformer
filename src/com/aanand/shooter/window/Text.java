package com.aanand.shooter.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text extends Camera{

	private float size;
	private String text;
	private FontMetrics metrics;
	public Text(String text, float size, float x, float y) {
		super(x, y);
		this.text = text;
		this.size = size;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void render(Graphics g){
		Font f = new Font("TimesRoman", Font.PLAIN, (int)this.size);
	    this.metrics = g.getFontMetrics(f);
		g.setColor(Color.black);
		g.setFont(f);
		g.drawString(text, (int)this.x, (int)this.y);
	}

	public void tick(Camera cam){
		//camera snap to player
	    this.x = -cam.getX() + (Game.WIDTH - metrics.stringWidth(this.text)) / 2;
	    this.y = -cam.getY() + ((Game.HEIGHT - metrics.getHeight()) / 2) - metrics.getAscent();
		
	}
}
