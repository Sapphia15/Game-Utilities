package test.screen;

import java.awt.Dimension;
import java.awt.Frame;



public class SApp {
	static Frame f=new Frame();
	static Panel p;
	
	public static void main(String[] unicorns) {
		
		f.setPreferredSize(new Dimension(1000,800));
		
		//create panel and store it in a static variable for later reference
	         
	      
		p=new Panel(f);
		//set target frames per second (The panel runs a P loop on a separate thread to attempt to maintain the frame rate)
		p.setTargetFPS(40);
		
	      
      	p.setSize(400, 400);
		//add the panel to the frame so that it will be visible in the window
      	f.pack();
      	f.add(p);
		f.setSize(new Dimension(500,500));
		//this centers the window
		//f.setLocationRelativeTo(null);
		//this makes it so that the panel draws the next frame on a virtual canvas (a blank image the size of the window) and then displays it all at once in one step in which it draws the image. This causes much smoother display and also makes it so that the content in the window doesn't appear to constantly blink.
		
		//this makes it so that pressing X on the window will cause it to close.
		//p.closeOnExit();
		//this starts the panel thread which calls updates and manages repainting the screen.
		//p.start();
		//p.setDoubleBuffered(true);
		f.setVisible(true);
		
	}
}
