package test.particle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameutil.math.geom.Vector;
import gameutil.text.Console;
import graphics.screen.SPanel;
import graphics.screen.Screen;

public class PScreen extends Screen{

	SPanel p;
	Camera c=new Camera(0,100,200,new Vector(new double[] {-1,0,-1}));
	ArrayList<Particle> particles=new ArrayList<>();
	
	public PScreen(SPanel p) {
		this.p=p;
		particles.add(new Particle(-200,-200,-100,new Color(200,200,200)));
		particles.add(new Particle(-200,-400,-100,new Color(200,200,200)));
		particles.add(new Particle(-400,-400,-100,new Color(200,200,200)));
		particles.add(new Particle(-400,-200,-100,new Color(200,200,200)));
		particles.add(new Particle(-200,-200,100,new Color(200,10,200)));
		particles.add(new Particle(-200,-400,100,new Color(200,10,200)));
		particles.add(new Particle(-400,-400,100,new Color(200,10,200)));
		particles.add(new Particle(-400,-200,100,new Color(200,10,200)));
		Console.s.setVisible(true);
		Console.s.setAutoScroll(true);
	}
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(0, 0, p.getWidth(), p.getHeight());
		for (Particle e:particles) {
			java.awt.Point proj=e.project(c);
			g.setColor(e.getColor());
			//Console.s.println("Drawing point at "+proj.x+", "+proj.y);
			g.fillOval(p.getWidth()/2+proj.x-5, p.getHeight()/2-proj.y-5, 11, 11);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyDown(int key) {
		// TODO Auto-generated method stub
		if (key==KeyEvent.VK_W) {
			c.move(0,2,0);
		} else if (key==KeyEvent.VK_A) {
			c.move(-2,0,0);
		} else if (key==KeyEvent.VK_S) {
			c.move(0,-2,0);
		} else if (key==KeyEvent.VK_D) {
			c.move(2,0,0);
		} else if (key==KeyEvent.VK_SPACE) {
			c.move(0,0,2);
		} else if (key==KeyEvent.VK_SHIFT) {
			c.move(0,0,-2);
		}
		Console.s.println("Pos: ("+c.getX()+", "+c.getY()+", "+c.getZ()+")");
	}
	
}
