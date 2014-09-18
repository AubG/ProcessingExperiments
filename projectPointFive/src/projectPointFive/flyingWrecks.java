package projectPointFive;

import processing.core.*;

public class flyingWrecks extends collidableObject {
	
	PApplet parent;
	int velocity;
	
	flyingWrecks(PApplet p, float x, float y, float w, float h, int s){
		parent = p;
		
		//setting variables
		this.setPosX(x);
		this.setPosY(y);
		this.setWidth(w);
		this.setHeight(h);
		velocity = s;
	}
	
	@Override
	boolean isColliding(int p_X, int p_Y){
		//Do something
		if(p_X > (posX-10) && p_X < (posX+width+10))
		{
			if(p_Y > (posY-10) && p_Y < (posY+height+10))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	void display(){
		//re-draw the rectangles
		parent.fill(255, 0, 0);
		parent.stroke(255);
		parent.rect(posX, posY, width, height);
	}
	
	@Override
	void update(int s_width, int s_height){
		//Do something
		if(posX < (0-width)){
			posX = s_width;
		} else {
			posX -= velocity;
		}
	}
}
