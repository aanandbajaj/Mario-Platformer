/*
-Handler class updates objects, renders objects
-You can also add and remove objects from the linkedList
-LinkedList of GameObjects holds all the GameObjects, 
	rather than having separate GameObjects
*/
package com.aanand.shooter.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.aanand.shooter.framework.GameObject;

public class Handler {
	
	//list of GameObjects
	//objective = to render and update the list
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	//updates each object
	public void tick(){
		//if i = 0, then the loop will only update the first object in list
		for(int i =0; i< object.size(); i++){
			//set tempObject to object in list at index i
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	//render each object
	public void render(Graphics g){
		for(int i =0; i< object.size(); i++){
			//set tempObject to object in list at index i
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	//adding objects to the list
	public void addObject(GameObject object){
		//need to refer to LinkedList
		this.object.add(object);
	}
	
	//removing objects from the list
	public void removeObject(GameObject object){
		//need to refer to LinkedList
		this.object.remove(object);
	}
	
}
