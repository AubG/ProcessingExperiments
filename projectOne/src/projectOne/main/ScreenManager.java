package projectOne.main;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class ScreenManager {

	PApplet parent;
	ArrayList<image> listImages = new ArrayList<image>();	//List of PImages
	int listPosition = 0, 		//Position currently selected on the list
		reDrawIndex = 0,		//Holds the last index drawing started from
		positionOnScreen = 0;	//Defined as far left being 0 and far right being 4
	float s_W, s_H;		// screen width and screen height
	boolean isZoomed;	// Zoomed or Not
	
	ScreenManager(PApplet p, int screenWidth, int screenHeight){
		parent = p;
		s_W = screenWidth;
		s_H = screenHeight;
		setup();
	}
	
	// Puts images in the ArrayList
	public void setup(){	
		listImages.add(new image(parent, parent.loadImage("Test" + 0 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 1 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 2 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 3 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 4 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 5 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 6 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 7 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 8 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 9 + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'A' + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'B' + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'C' + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'D' + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'E' + ".png")));
		listImages.add(new image(parent, parent.loadImage("Test" + 'F' + ".png")));
	}
	
	// Draws image icons on the screen
	public void display(){
		
		int tempIndex = reDrawIndex, count = 0;

		do{
			
			//Setting index to 0 when list goes out of bounds
			if(tempIndex == listImages.size()){
				tempIndex = 0;
			}
			
			// Setting position of next image
			// Drawing next image
			listImages.get(tempIndex).setPosition(	(count*(s_W/5))+(((s_W/5)-listImages.get(tempIndex).icon_Width)/2),
													(s_H/2)-(listImages.get(tempIndex).icon_Height/2));
			listImages.get(tempIndex).draw();
			
			// Incrementing icon count and index
			count++;
			tempIndex++;
			
		} while (count != 5);
	}

	// Deals with keyboard input
	public void iconSelection(int key){
		
		//Essentially takes all user input
		//Continuously called from pO_main by keyReleased() func
		switch(key){
		case PConstants.UP:
			listImages.get(listPosition).zoom();
			isZoomed = true;
			break;
		case PConstants.DOWN:
			isZoomed = false;
			drawSelect();
			break;
		case PConstants.LEFT:
			adjustListPosition(-1);
			drawSelect();
			break;
		case PConstants.RIGHT:
			adjustListPosition(1);
			drawSelect();
			break;
		case '=':
			shiftRight(true);
			drawSelect();
			break;
		case '/':
			shiftLeft(true);
			drawSelect();
			break;
		}
	}
	
	// Shifts right the position on the list
	public void shiftRight(boolean isJump){
		reDrawIndex += 5;
		if(reDrawIndex > 15){
			reDrawIndex = reDrawIndex - 16;
		}
		
		if(isJump){
			listPosition += 5;
			if(listPosition > 15){
				listPosition = listPosition - 16;
			}
		}
	}
	
	// Shifts left the position on the list
	public void shiftLeft(boolean isJump){
		reDrawIndex = reDrawIndex - 5;
		if(reDrawIndex < 0){
			reDrawIndex = 16 + reDrawIndex;
		}
		
		if(isJump){
			listPosition = listPosition - 5;
			if(listPosition < 0){
				listPosition = 16 + listPosition;
			}
		}
	}
	
	// Draws the image selection
	public void drawSelect(){
		
		parent.background(0); // Refresh Background
		display();	// Refresh display
		
		parent.stroke(255);		// Outline (255) or white
		parent.noFill();		// Nothing drawn inside shape
		parent.rect(listImages.get(listPosition).getX() - 3, 
					listImages.get(listPosition).getY() - 3, 
					listImages.get(listPosition).icon_Width + 6,
					listImages.get(listPosition).icon_Height + 6);
		
		//Check if still zoomed, display zoomed image
		if(isZoomed)
		{
			listImages.get(listPosition).zoom();
		}
	}
	
	// Moves the listPosition up or down
	public void adjustListPosition(int val){
		
		//Checks for positive or negative value
		if(val == 1)
		{			
			//Checking if at end of list
			if(listPosition == listImages.size()-1){
				listPosition = 0;
			} else {
				listPosition += val;
			}
			
			// Checking if on far right of screen
			if(positionOnScreen == 4){
				shiftRight(false);
				positionOnScreen = 0;
			} else {
				positionOnScreen += val;
			}
		} 
		else 
		{
			//Checking if at beginning of list
			if(listPosition == 0){
				listPosition = 15;
			} else {
				listPosition += val;
			}
			
			//Checking if on far left of screen
			if(positionOnScreen == 0){
				shiftLeft(false);
				positionOnScreen = 4;
			} else {
				positionOnScreen += val;
			}
			
		}
		
		//Debug statements for all position indicators
		System.out.println("PositionOnScreen:: " + positionOnScreen 
							+ "  reDrawIndex:: " + reDrawIndex 
							+ "  listPosition:: " + listPosition);
		 
	}
}
