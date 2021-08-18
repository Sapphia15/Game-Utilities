package graphics.screen;
import javax.swing.*;

import graphics.KPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

public class SPanel extends KPanel{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected Timer t;
	    protected final int TIMER_DELAY=10;
	    protected Hashtable<String,Screen>screens;
	    protected Screen currentScreen;
	     

	    public SPanel(Frame observer){
	    	super(observer);
	        setFocusable(true);
	        screens=new Hashtable<>();
	        setBackground(Color.BLACK);
	        MAdapter mAdapter=new MAdapter();
	        addMouseListener(mAdapter);
	        addMouseMotionListener(mAdapter);
	    	addKeyListener(new TAdapter());
	    }

	    public void paintComponent(Graphics g){
	        currentScreen.paint(g);
	    }



	    @Override
	    public void update() {
	        currentScreen.update();
	    }

	    public void setScreen(String name){
	    	try {
	    		currentScreen.setDeinit();
	    	} catch (NullPointerException e) {
	    		//this must be the first screen being set.
	    	}
	        currentScreen=screens.get(name);
	        currentScreen.setInit();
	    }

	    public void addScreen(Screen s,String name){
	        screens.put(name,s);
	    }

	    protected class TAdapter extends KeyAdapter{
	        public void keyPressed(KeyEvent e){
	            currentScreen.keyPressed(e);
	        }

	        public void keyReleased(KeyEvent e) {
	            currentScreen.keyReleased(e);
	        }
	    }

	    protected class MAdapter extends MouseAdapter{

	        public void mousePressed(MouseEvent e){
	            currentScreen.mousePressed(e);
	        }

	        public void mouseReleased(MouseEvent e){
	            currentScreen.mouseReleased(e);
	        }

	        public void mouseMoved(MouseEvent e){
	            currentScreen.mouseMoved(e);
	        }
	        public void mouseDragged(MouseEvent e) {
	        	currentScreen.mouseDragged(e);
	        }

	    }

}
