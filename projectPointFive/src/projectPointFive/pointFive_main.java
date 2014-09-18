package projectPointFive;

import java.util.ArrayList;

import processing.core.PApplet;

public class pointFive_main extends PApplet {
	
	int posX,
		posY, 
		screenWidth = 800, 
		screenHeight = 400;
	final int START_X = 400, 
			START_Y = 380;
	ArrayList<collidableObject> listWrecks = new ArrayList<collidableObject>();

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "pointFive" });
	}

	public void setup() {
		size(screenWidth, screenHeight);
		frameRate(60);
		
		//First Creation
		reset();
		
		//Initial CollidableObjects Created
		collidableShapes();
		drawCollidableObjects();
	}
	
	// Creates and Resets the game
	void reset(){
		//Empty ArrayList and Re-populate
//		listWrecks.clear();

		//CreatePlayer and reset position to start
		fill(255);
		noStroke();
		ellipse(START_X, 
				START_Y, 
				30, 
				30);
		posX = START_X;
		posY = START_Y;
	}
	
	//creates and adds collidableShapes to the listWrecks ArrayList
	void collidableShapes(){
		flyingWrecks f1 = new flyingWrecks(this, screenWidth+150, 300, 150, 20, 6);
		flyingWrecks f2 = new flyingWrecks(this, screenWidth-150, 250, 150, 35, 4);
		flyingWrecks f3 = new flyingWrecks(this, screenWidth-450, 150, 160, 55, 10);
		flyingWrecks f4 = new flyingWrecks(this, screenWidth+150, 90, 150, 40, 6);
		flyingWrecks f5 = new flyingWrecks(this, screenWidth-150, 50, 150, 25, 5);
		listWrecks.add(f1);
		listWrecks.add(f2);
		listWrecks.add(f3);
		listWrecks.add(f4);
		listWrecks.add(f5);
	}
	
	//Move Ellipse with keys
	void playerMovement(){

		if (keyPressed)
		{
			switch(keyCode)
			{
			
			case UP:
				posY -= 2;
				break;
				
			case DOWN:
				posY += 2;
				break;
				
			case LEFT:
				posX -= 2;
				break;
				
			case RIGHT:
				posX += 2;
				break;
				
			}
		}
		fill(255);
		noStroke();
		ellipse(posX, posY, 30, 30);
	}

	//Calls each collidableObjects Update method
	void moveCollidableObjects(){
		for(collidableObject tempObj : listWrecks){
			tempObj.update(screenWidth, screenHeight);
		}
	}
	
	//Checks if ellipse is on screen
	//(if not) resets to starting location
	void inBounds(){
		if(posX < 0 || posX > screenWidth){ reset(); }
		if(posY < 0 || posY > screenHeight){ reset(); }
	}
	
	//Checks screen bounds and CollidableObject Bounds
	void collisionCheck(){
		inBounds();
		for(collidableObject tempObj : listWrecks){
			if(tempObj.isColliding(posX, posY)){ reset(); break; }
		}
	}
	
	public void drawCollidableObjects(){
		for(collidableObject tempObj : listWrecks){
			tempObj.display();
		}
	}
	
	public void draw() {
		
		moveCollidableObjects();
		background(0);
		drawCollidableObjects();
		collisionCheck();
		playerMovement();
		
		 
	}
}
