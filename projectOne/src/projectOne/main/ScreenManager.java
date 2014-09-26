package projectOne.main;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class ScreenManager {

	PApplet parent;
	final File folder = new File("/Users/Aubrey/Desktop/School/Fall2014/ComputerScience/690/Processing/ProjectOne/src/data");
	ArrayList<String> files = new ArrayList<String>();
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
	
	public ArrayList<String> checkFileFolder() {
		for (final File fileEntry : folder.listFiles()) {
	        if(fileEntry.getName().contains(".jpg") || fileEntry.getName().contains(".png")){
				files.add(fileEntry.getName());
	        }
	    }
		return files;
	}
	
	// Puts images in the ArrayList
	public void setup(){	
		checkFileFolder();
		
		for(String fileName : files){
			listImages.add(new image(parent, parent.loadImage(fileName)));
		}
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
		case '.':
			shiftRight(true);
			drawSelect();
			break;
		case ',':
			shiftLeft(true);
			drawSelect();
			break;
		}
	}
	
	//Deals with mouse clicks
	public void iconSelection(int mouse_X, int mouse_Y){
		int tempIndex = reDrawIndex, count = 0;

		do{
			
			//Setting index to 0 when list goes out of bounds
			if(tempIndex == listImages.size()){
				tempIndex = 0;
			}
			
			if(mouse_X > listImages.get(tempIndex).posX  
					&& mouse_X < listImages.get(tempIndex).icon_Width + listImages.get(tempIndex).posX){
				
				if(mouse_Y > listImages.get(tempIndex).posY 
						&& mouse_Y < listImages.get(tempIndex).icon_Height + listImages.get(tempIndex).posY){
					
					listPosition = tempIndex;
					positionOnScreen = count;
					listImages.get(listPosition).zoom();
					isZoomed = true;
					drawSelect();
					break;
					
				}
				
			}
			
			// Incrementing icon count and index
			count++;
			tempIndex++;
			
		} while (count != 5);
	}
	
	// Shifts right the position on the list
	public void shiftRight(boolean isJump){
		reDrawIndex += 5;
		if(reDrawIndex > files.size()-1){
			reDrawIndex = reDrawIndex - files.size();
		}
		
		if(isJump){
			listPosition += 5;
			if(listPosition > files.size()-1){
				listPosition = listPosition - files.size();
			}
		}
	}
	
	// Shifts left the position on the list
	public void shiftLeft(boolean isJump){
		reDrawIndex = reDrawIndex - 5;
		if(reDrawIndex < 0){
			reDrawIndex = files.size() + reDrawIndex;
		}
		
		if(isJump){
			listPosition = listPosition - 5;
			if(listPosition < 0){
				listPosition = files.size() + listPosition;
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
				listPosition = files.size()-1;
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
