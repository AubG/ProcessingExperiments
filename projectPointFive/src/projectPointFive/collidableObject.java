package projectPointFive;

//import processing.core.*;

abstract class collidableObject{
	public float posX, posY, width, height;
	
	// Get-ers
	float getPosX(){ return posX; }
	float getPosY(){ return posY; }
	float getWidth(){ return width; }
	float getHeight(){return height; }
	
	// Set-ers
	void setPosX(float x){ posX = x; }
	void setPosY(float y){ posY = y; }
	void setWidth(float w){ width = w; }
	void setHeight(float h){ height = h; }
	
	void display(){}
	void update(int width, int height){}
	boolean isColliding(int p_X, int p_Y){ return false; }
}
