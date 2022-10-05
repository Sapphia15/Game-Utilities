package test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import gameutil.ConcurrentBidirectionalMap;
import gameutil.math.ComplexDouble;
import gameutil.math.Hilbert;
import gameutil.math.geom.g2D.LineSegR2;
import gameutil.text.Console;
import graphics.BufferedSlicedImage3D;
import graphics.Graphics3D;
import graphics.Image3D;
import graphics.SlicedGraphics3D;
import graphics.g;
import graphics.screen.SPanel;
import graphics.screen.Screen;

public class Draw3D {
	
	
	public static void main(String[] unicorns) {
		/*int x=1234567890;
		for (int i=0;i<getDigits(x);i++) {
			Console.s.println(getDigit(x,i));
		}*/
		int a=0;
		Hilbert.init();
		//Console.s.println(new ComplexDouble(curve.getSecond(3)).R()*64);
		Frame f=new Frame();
		SPanel p=new Panel(f);
		f.add(p);
		f.setLocationRelativeTo(null);
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setVisible(true);
		p.closeOnExit();
		p.setDoubleBuffered(true);
		p.start();
		//p.setDoubleBuffered(true);
		//Console.s.println(a);
		
		//ComplexDouble d=new ComplexDouble(Math.E,0).$E$(new ComplexDouble(0,Math.PI));
		//Console.s.println(d.R()+" + "+d.I()+"i");
	}
	
	public static class Home extends Screen{
		int midX=0;
		int midY=0;
		int oldWidth=0;
		int oldHeight=0;
		Panel observer;
		long t=System.currentTimeMillis()+2000;
		Image3D himg=new Image3D(32,32,32);
		Image3D editing=new Image3D(32,32,32);
		BufferedImage line=new BufferedImage(1024,32,BufferedImage.TYPE_INT_ARGB);
		BufferedImage middle=new BufferedImage(36,36,BufferedImage.TYPE_INT_ARGB);
		int x=0;
		int y=0;
		int z=0;
		double angle=Math.PI/2;
		
		public Home(Panel observer) {
			this.observer=observer;
			paintImage();
			
			middle=new BufferedImage(36,36,BufferedImage.TYPE_INT_ARGB);
			Graphics gr=middle.getGraphics();
			g g3=new g(gr);
			g3.drawProjection(himg, 0, 0, 8, 8, new int[] {x,y,z}, new double[]{angle});
			gr.dispose();
			
		}
		
		
		@Override
		public void paint(Graphics gr) {
			g g3=new g(gr);
			gr.drawImage(line, midX-512, midY+5,1024,32, observer);
			gr.drawImage(middle, midX-64, midY-64-300,128,128, observer);
			
			//gr.drawImage(himg.getLayer(31-z), midX-64, midY-64-300,128,128, observer);
			//g3.drawImage3D(himg, midX-512, midY+100,1, 5);
			
		}

		public void paintImage() {
			himg.flush();
			himg=new Image3D(32,32,32);
			//SlicedGraphics3D g=new SlicedGraphics3D(himg.createGraphics());
			Graphics3D g=(Graphics3D)himg.getGraphics();
			g.setColor(Color.pink);
			/*g.fillCuboid(0, 0, 0, 32);
			g.setColor(Color.black);
			g.fillCuboid(8, 8, 8, 16);*/
			
			 //checkerboard
			g.setColor(Color.black);
			Color c=g.getColor();
			//Console.s.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" "+c.getAlpha());
			g.fillCuboid(0, 0, 0, 32,32,32);
			/*
			g.fillCuboid(0, 0, 0, 16,16,16);
			
			g.fillCuboid(0, 16, 16, 16,16,16);
			
			g.fillCuboid(16, 0, 16, 16,16,16);
			
			g.fillCuboid(16, 16, 0, 16,16,16);
			
			g.setColor(Color.red);
			
			g.fillCuboid(0, 16, 0, 16,16,16);
			
			g.fillCuboid(0, 0, 16, 16,16,16);
			
			g.fillCuboid(16, 0, 0, 16,16,16);
			
			g.fillCuboid(16, 16, 16, 16,16,16);
			
			g.setColor(Color.black);
			
			g.drawCuboid(0, 0, 0, 16,16,16);
			
			g.drawCuboid(0, 16, 16, 16,16,16);
			
			g.drawCuboid(16, 0, 16, 16,16,16);
			
			g.drawCuboid(16, 16, 0, 16,16,16);
			
			g.drawCuboid(0, 16, 0, 16,16,16);
		
			g.drawCuboid(0, 0, 16, 16,16,16);
			
			g.drawCuboid(16, 0, 0, 16,16,16);
			
			g.drawCuboid(16, 16, 16, 16,16,16);*/
			
			g.setColor(Color.green);
			
			g.drawLine(0,0,0,31,31,31);
			
			g.setColor(Color.blue);
			g.drawLine(0,0,0,31,0,31);
			
			g.setColor(Color.red);
			g.drawLine(0, 0, 0, 31,0,0);
			g.drawLine(0, 0, 0, 0,31,0);
			g.drawLine(0, 0, 0, 0,0,31);
			g.drawLine(31, 0, 0, 31,31,0);
			g.drawLine(31, 0, 0, 31,0,31);
			g.drawLine(0, 31, 0, 0,31,31);
			g.drawLine(0, 0, 31, 31,0,31);
			g.drawLine(31, 31, 0, 31,31,31);
			g.drawLine(0, 31, 31, 31,31,31);
			g.drawLine(31, 0, 31, 31,31,31);
			g.drawLine(0, 31, 0, 31,31,0);
			g.drawLine(0, 0, 31, 0,31,31);
			/*
			Graphics2D[] gs=himg.createGraphics();
			for (int l=0;l<himg.getLength();l++) {
				gs[l].setColor(Color.pink);
				gs[l].fillRect(0, 0, himg.getWidth(null), himg.getHeight(null));
				gs[l].setColor(Color.blue);
				if (l==0||l==himg.getLength()-1) {
					//gs[l].fillRect(0, 0, 32, 32);
					gs[l].setColor(Color.black);
					gs[l].fillRect(0, 0, 16,16);
					
					gs[l].setColor(Color.red);
					gs[l].fillRect(0, 16, 16,16);
					
					
					
					gs[l].setColor(Color.blue);
					gs[l].fillRect(16, 0, 16,16);
					
					gs[l].setColor(Color.green);
					gs[l].fillRect(16, 16, 16,16);
					/*
					gs[l].setColor(Color.black);
					gs[l].fillRect(0, 0, 16,16);
					gs[l].setColor(Color.green);
					gs[l].fillRect(16, 16, 16,16);
					
					gs[l].setColor(Color.blue);
					gs[l].fillRect(16, 0, 16,16);
					
					gs[l].setColor(Color.red);
					gs[l].fillRect(0, 16, 16,16);
					*//*
				} else {
					//gs[l].drawRect(0, 0, 31, 31);
					
					gs[l].setColor(Color.black);
					gs[l].drawRect(0, 0, 15,15);
					
					gs[l].setColor(Color.red);
					gs[l].drawRect(0, 16, 15,15);
					
					
					
					gs[l].setColor(Color.blue);
					gs[l].drawRect(16, 0, 15,15);
					
					gs[l].setColor(Color.green);
					gs[l].drawRect(16, 16, 15,15);
					
					
					gs[l].setColor(Color.black);
					gs[l].drawRect(1, 1, 13,13);
					
					gs[l].setColor(Color.green);
					gs[l].drawRect(17, 17, 13,13);
					
					gs[l].setColor(Color.blue);
					gs[l].drawRect(17, 1, 13,13);
					
					gs[l].setColor(Color.red);
					gs[l].drawRect(1, 17, 13,13);
					//gs[l].drawRect(4, 4, himg.getLength()-8, himg.getLength()-8);
					//gs[l].fillRect(4, 4, himg.getLength()-8, himg.getLength()-8);
					/*
					gs[l].setColor(Color.black);
					gs[l].drawRect(0, 0, 16,16);
					
					gs[l].setColor(Color.red);
					gs[l].drawRect(0, 16, 16,16);
					
					
					
					gs[l].setColor(Color.blue);
					gs[l].drawRect(16, 0, 16,16);
					
					gs[l].setColor(Color.green);
					gs[l].drawRect(16, 16, 16,16);
					
					gs[l].setColor(Color.black);
					gs[l].drawRect(1, 1, 14,14);
					
					gs[l].setColor(Color.green);
					gs[l].drawRect(17, 17, 14,14);
					
					gs[l].setColor(Color.blue);
					gs[l].drawRect(17, 1, 14,14);
					
					gs[l].setColor(Color.red);
					gs[l].drawRect(1, 17, 14,14);
					*//*
				}
				gs[l].dispose();
			}*/
			g.dispose();
		}
		
		public void drawLine() {
			line.flush();
			line=new BufferedImage(1024+31*4,32,BufferedImage.TYPE_INT_ARGB);
			Graphics gr=line.getGraphics();
			g g3=new g(gr);
			
			g3.drawImage3DStackStyleWithSeparation(himg, 0, 0, 1, 1,4);
			long time=System.currentTimeMillis();
			if (time%1100<1020) {
				int amount=(int)((time/2)%510);
				if (amount>255) {
					amount=255-(int)((time/2)%255);
				}
				gr.setColor(new Color(20,amount,20,amount));
				gr.drawLine(0, 31-z, 1024+31*4,31-z);
			}
			gr.dispose();
			/*middle.flush();
			middle=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
			gr=middle.getGraphics();
			g3=new g(gr);
			//g3.drawXZSlice(himg, 0, 0, 31-z);
			g3.drawProjection(himg, midX-512, midY-300, 32, 32, new int[] {x,y,z}, new double[]{Math.PI/2});
			gr.dispose();*/
		}
		
		@Override
		public void update() {
			// TODO Auto-generated method stub
			if (oldWidth!=observer.getWidth()||oldHeight!=observer.getHeight()) {
				oldWidth=observer.getWidth();
				oldHeight=observer.getHeight();
				midX=oldWidth/2;
				midY=oldHeight/2;
				
			}
			//paintImage();
			drawLine();
			/*
			if (System.currentTimeMillis()>t+800) {
				curve=nextIteration(curve);
				t=System.currentTimeMillis();
			}*/
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				if (z<31) {
					z++;
				}
			} else if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
				if (z>0) {
					z--;
				}
			} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				angle=(angle+.1)%(2*Math.PI);
			} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				angle=(angle-.1)%(2*Math.PI);
			}
			
			middle=new BufferedImage(36,36,BufferedImage.TYPE_INT_ARGB);
			Graphics gr=middle.getGraphics();
			g g3=new g(gr);
			g3.drawProjection(himg, 0, 0, 8, 8, new int[] {x,y,z}, new double[]{angle});
			gr.dispose();
		}
		
		@Override
		protected void keyDown(int key) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class Panel extends SPanel{

		
		
		public Panel(Frame observer) {
			super(observer);
			screens.put("home", new Home(this));
			this.currentScreen=screens.get("home");
			setScreen("home");
			// TODO Auto-generated constructor stub
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -8208919958454485896L;
		
	}
}
