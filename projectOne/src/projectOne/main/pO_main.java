package projectOne.main;

import processing.core.*;

public class pO_main extends PApplet{
	
	private static final long serialVersionUID = 1L;
	
	ScreenManager s_M;
	final int width = 800, height = 600;
	
	public void setup(){
		size(width, height);
		background(0);
		
		//Setting up screen
		s_M = new ScreenManager(this, width, height);
		s_M.display();
		s_M.drawSelect();
	}
	
	public void draw(){
		
	}
	
	public void mouseClicked(){
		s_M.iconSelection(mouseX, mouseY);
	}
	
	public void keyReleased(){
		s_M.iconSelection(keyCode);
	}
}
