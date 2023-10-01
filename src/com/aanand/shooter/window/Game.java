//TODO
//NPC
//Generalize LoadLevel
//Optimize Fire
//Create class "x" that extends game object and player&enemy that extends "x" 
//cutscenes
//change texture of player class
package com.aanand.shooter.window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.aanand.shooter.framework.GameObject;
import com.aanand.shooter.framework.KeyInput;
import com.aanand.shooter.framework.ObjectId;
import com.aanand.shooter.framework.Texture;
import com.aanand.shooter.objects.Block;
import com.aanand.shooter.objects.Coin;
import com.aanand.shooter.objects.Enemy;
import com.aanand.shooter.objects.Player;

public class Game extends Canvas implements Runnable{
  
/*
 * The serialVersionUID is a universal version identifier for a Serializable class. 
 * Deserialization uses this number to ensure that a loaded class corresponds exactly 
 * to a serialized object. If no match is found, then an InvalidClassException is thrown.
 */
  private static final long serialVersionUID = -2064978516857403497L;
  
  private boolean running = false;
  private Thread thread;
  
  public static int HEIGHT;
  public static int WIDTH;
  Camera cam;
  public static Text score;
  
  //Object
  Handler handler;
  private BufferedImage level = null;
  static Texture tex;
    
  //initalizing class
  //runs before the game loop
  private void init(){
	  WIDTH = getWidth();
	  HEIGHT = getHeight();
	  
	  tex = new Texture();
	  
	  BufferedImageLoader loader = new BufferedImageLoader();
	  //load the image
	  this.level = loader.loadImage("/level.png");
	  
	  handler = new Handler();
	  cam = new Camera(0,0);
	  score = new Text("Score: 0",50f, getWidth(), getHeight()/6);
	  
	  //load level
	  loadImageLevel(this.level);
	  	  
	  //need to add this line for keyinput class to work
	  this.addKeyListener(new KeyInput(handler));
  }
  
  //need synchronized when working with threads
  public synchronized void start(){
    //check if thread is running
    //do not want more than one thread running
    if(running){
    //exit method (does not run code below if)
      return;
    }
    //create and start thread
    running = true;
    thread = new Thread(this);
    thread.start();
  }
  
  public void run(){//need run() method because of Runnable interface
	init();  
	//what is requestFocus
    this.requestFocus();
    //updating the game at 60 frames per second
    //rendering as fast as computer can go
    long lastTime = System.nanoTime(); 
    double amountOfTicks = 60.0; 
    double ns = 1000000000 / amountOfTicks; 
    double delta = 0; 
    long timer = System.currentTimeMillis(); 
    int updates = 0; 
    int frames = 0; 
    while(running){ 
      long now = System.nanoTime(); 
      delta += (now - lastTime) / ns; 
      lastTime = now; 
      while(delta >= 1){ 
        tick(); 
        updates++; 
        delta--; 
      } 
      render(); 
      frames++; 
      
      if(System.currentTimeMillis() - timer > 1000){ 
        timer += 1000; 
        System.out.println("FPS: " + frames + " TICKS: " + updates); 
        frames = 0; 
        updates = 0; 
      } 
    }
  }
  
  //tick = updates
  private void tick(){
	//update the objects
    handler.tick();
    
    for(int i = 0; i < handler.object.size(); i++){
    	if(handler.object.get(i).getId() == ObjectId.Player){
    		//pass in player object
    	    GameObject tempObject = handler.object.get(i);
    		cam.tick(tempObject);
    		score.tick(cam);
    	}
    	if(handler.object.get(i).getId() == ObjectId.Enemy){
    		GameObject tempObject = handler.object.get(i);
    	}
    }
  }
  
  //graphical rendering
  private void render(){
    //this = referring to canvas class
    //bs is default set to null
    BufferStrategy bs = this.getBufferStrategy();
    
    if(bs == null){
      //triple buffering
      //load 2 images behind the monitor
      this.createBufferStrategy(3);
      //return exits the method
      return;
    }
    //get graphics context for buffering
    Graphics g = bs.getDrawGraphics();
    Graphics2D g2d = (Graphics2D)g;
    
    //DRAW HERE
    g.setColor(new Color(102, 179, 255));
    g.fillRect(0,0,this.getWidth(), this.getHeight());
    
    //begin of cam
    g2d.translate(cam.getX(), cam.getY());
    
    //render the objects
    //all at once
    handler.render(g); 
    score.render(g);
    //end of cam
    g2d.translate(cam.getX(), -cam.getY());
    
    //what is this
    g.dispose();
    bs.show();
    
  }
  
  private void loadImageLevel(BufferedImage level){
	  int w = level.getWidth();
	  int h = level.getHeight();
	  
	  //loop through first row
	  for(int i = 0; i < h; i++){
		  //loop through all columns
		  for(int j = 0; j < w; j++){
			  //pixel = location of current pixel
			  int pixel = level.getRGB(i, j);
			  //will hold the color of pixel
			  //can do this another way
			  int red = (pixel >> 16) & 0xff;
			  int green = (pixel >> 8) & 0xff;
			  int blue = (pixel) & 0xff;
			  			  
			  //generic
			  //if white, add dirt block
			  if(red == 255 && green == 255 && blue == 255){
				  handler.addObject(new Block(i*32,j*32, 0, ObjectId.Block));
			  }
			  //if grey, add grass block
			  if(red == 200 && green == 200 && blue == 200){
				  handler.addObject(new Block(i*32,j*32, 1, ObjectId.Block));
			  }
			  if(red == 255 && green == 0 && blue == 0){
				  handler.addObject(new Block(i*32,j*32, 2, ObjectId.Block));
			  }
			  //if blue, add player
			  if(red == 0 && green == 0 && blue == 255){
				  handler.addObject(new Player(i*32,j*32, 48, 64, handler, ObjectId.Player));
			  }
			  //coin
			  if(red == 255 && green == 255 && blue == 0){
				  handler.addObject(new Coin(i*32,j*32, ObjectId.Coin));
			  }
			  //enemy
			  if(red == 0 && green == 255 && blue == 0){
				  handler.addObject(new Enemy(i*32,j*32, 32, 32, handler, ObjectId.Enemy));
				  System.out.print("enemy block");
			  }
		  }
	  }
  }
  public static Texture getInstance(){
	  return tex;
  }
  
  public static void main(String args[]){
    new Window (800,600, "Shooter Platformer V0.1", new Game());//create Window
  }

  
}
