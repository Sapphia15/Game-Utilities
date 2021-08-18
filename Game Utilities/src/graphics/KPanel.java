package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferStrategy;
import java.util.concurrent.atomic.AtomicBoolean;

public class KPanel extends Canvas{
	Frame observer;
	Color background=Color.white;
	int targetFPS=45;
	double P=.8;
	boolean buffered=false;
	AtomicBoolean paintThreadActive=new AtomicBoolean();
	PaintThread paintThread=new PaintThread(this);
	
	public KPanel(Frame observer) {
		this.observer=observer;
		 setFocusable(true);
	}
	
	public void closeOnExit() {
		observer.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
	}
	
	public void paint(Graphics g) {
			drawFrame(g);
	}
	
	public void setDoubleBuffered(boolean b) {
		if (b) {
			this.createBufferStrategy(2);
		} else {
			this.createBufferStrategy(1);
		}
		buffered=b;
	}
	
	public void setTargetFPS(int target) {
		targetFPS=target;
	}
	
	/**
	 * 
	 * @param target
	 * @param P the P value for the proportional control loop that regulates the frame rate
	 */
	public void setTargetFPS(int target,double P) {
		targetFPS=target;
	}
	
	public Frame getObserver() {
		return observer;
	}
	
	private void drawFrame(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		paintComponent(g);
	}
	
	public void paintComponent(Graphics g) {
		
	}
	
	public void setBackgroundColor(Color c) {
		background=c;
	}
	
	public void update() {
		
	}
	
	public void start() {
		paintThread.start();
	}
	
	public void stop() {
		paintThreadActive.set(false);
		paintThread=new PaintThread(this);
	}
	
	
	
	private class PaintThread extends Thread {
		
		KPanel panel;
		long sleepTime;
		
		public PaintThread(KPanel panel) {
			this.panel=panel;
			sleepTime=getTargetTime();
		}
		
		public void run() {
			long lastTime=System.currentTimeMillis()-getTargetTime();
			long currentTime;
			panel.paintThreadActive.set(true);
			while (panel.paintThreadActive.get()) {
				update();
				currentTime=System.currentTimeMillis();
				repaint();
				//p loop to control sleep time in order to achieve target fps
				double error =(double)(getTargetTime()-(currentTime-lastTime));
				sleepTime=sleepTime+Math.round(error*panel.P);
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
				
				}
				lastTime=currentTime;
			}
		}
		
		private long getTargetTime() {
			return 1000/panel.targetFPS;
		}
	}
	
	
}
