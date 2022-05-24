package graphics.screen;
import javax.swing.*;

import gameutil.math.geom.g2D.PointR2;
import graphics.KPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

public class SPanel extends KPanel{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected Timer t;
	    protected final int TIMER_DELAY=10;
	    protected Hashtable<String,Screen>screens;
	    protected Screen currentScreen;
	    protected TAdapter keyAdapter;
	    
	    public SPanel(Frame observer){
	    	super(observer);
	        setFocusable(true);
	        screens=new Hashtable<>();
	        setBackground(Color.BLACK);
	        MAdapter mAdapter=new MAdapter();
	        addMouseListener(mAdapter);
	        addMouseMotionListener(mAdapter);
	        addMouseWheelListener(new MWAdapter());
	        keyAdapter=new TAdapter();
	    	addKeyListener(keyAdapter);
	    }

	    public void paintComponent(Graphics g){
	        currentScreen.paint(g);
	        keyAdapter.update();
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
	    	CopyOnWriteArrayList<Integer> keysDown=new CopyOnWriteArrayList<>();
	    	
	        public void keyPressed(KeyEvent e){
	            currentScreen.keyPressed(e);
	            if (!keysDown.contains(e.getKeyCode())) {
	            	keysDown.add(e.getKeyCode());
	            }
	        }
	        
	        public void update() {
	        	for (int key:keysDown) {
	        		keyDown(key);
	        	}
	        }
	        
	        
	        public void keyDown(int key) {
	        	currentScreen.keyDown(key);
	        }

	        public boolean isKeyDown(int key) {
	        	return keysDown.contains(key);
	        }
	        
	        public void keyReleased(KeyEvent e) {
	            currentScreen.keyReleased(e);
	            if (keysDown.contains(e.getKeyCode())) {
	            	keysDown.remove((Integer)e.getKeyCode());
	            }
	        }
	        
	        public void keyTyped(KeyEvent e) {
	        	currentScreen.keyTyped(e);
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
	    
	    public class MWAdapter implements MouseWheelListener{

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				currentScreen.mouseScrolled(e);
				
			}
		}
	    
	    public boolean isKeyDown(int key) {
	    	return keyAdapter.isKeyDown(key);
	    }
	    
	    public int getCenterX() {
	    	return this.getWidth()/2;
	    }
	    
	    public int getCenterY() {
	    	return this.getHeight()/2;
	    }
	    
	    public PointR2 getCenter() {
	    	return new PointR2(getCenterX(),getCenterY());
	    }

}
