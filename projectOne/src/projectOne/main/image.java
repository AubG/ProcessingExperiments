package projectOne.main;

import processing.core.PApplet;
import processing.core.PImage;

public class image extends PImage{

	PApplet parent;
	PImage photo;
	float posX, posY, icon_Width, icon_Height, zoom_Width, zoom_Height, aspect_Ratio;
	
	image(PApplet p, PImage image){
		parent = p;
		photo = image;
		createIcon();
		createZoom();
	}

	// Sizes the icon of each image
	// keeps the aspect ratio 
	public void createIcon(){
		float tempWidth = photo.width, tempHeight = photo.height;
		
		if(photo.width > photo.height){
			icon_Width = 150;
			aspect_Ratio = tempHeight/tempWidth;
			icon_Height = icon_Width * aspect_Ratio;
		} else {
			icon_Height = 150;
			aspect_Ratio = tempWidth/tempHeight;
			icon_Width = icon_Height * aspect_Ratio;
		}	
	}
	
	public void createZoom(){
		if(photo.width > photo.height){
			zoom_Width = 700;
			zoom_Height = zoom_Width * aspect_Ratio;
		} else {
			zoom_Height = 500;
			zoom_Width = zoom_Height * aspect_Ratio;
		}
	}
	
	// sets position vars of image
	public void setPosition(float x, float y){
		posX = x;
		posY = y;
	}
	
	public float getX(){
		return posX;
	}
	
	public float getY(){
		return posY;
	}
	
	// Draws image at set position
	public void draw(){
		parent.image(photo, posX, posY, icon_Width, icon_Height);
	}
	
	//Zooms in on image, and centers on screen
	public void zoom(){
		parent.image(photo, 400-(zoom_Width/2), 300-(zoom_Height/2), zoom_Width, zoom_Height);
	}
}
