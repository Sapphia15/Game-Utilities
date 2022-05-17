package test.screen;

import java.awt.Dimension;
import java.awt.Frame;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;



public class SApp {
	static Frame f=new Frame();
	static Panel p;
	
	public static void main(String[] unicorns) {
		
		f.setPreferredSize(new Dimension(1000,800));
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
		//create panel and store it in a static variable for later reference
	      final GLCanvas glcanvas = new GLCanvas(capabilities);       
	      
		//p=new Panel(f,capabilities);
		//set target frames per second (The panel runs a P loop on a separate thread to attempt to maintain the frame rate)
		//p.setTargetFPS(40);
		GLEventListener b = new GLEventListener() {

			@Override
			public void display(GLAutoDrawable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose(GLAutoDrawable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void init(GLAutoDrawable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
				// TODO Auto-generated method stub
				
			}
			
		};
		glcanvas.addGLEventListener(b);
		glcanvas.setSize(400, 400);
		/*p.addGLEventListener(b);        
      	p.setSize(400, 400);*/
		//add the panel to the frame so that it will be visible in the window
      	//f.pack();
      	//f.add(p);
		f.add(glcanvas);
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
