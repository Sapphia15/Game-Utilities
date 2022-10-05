package test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import gameutil.math.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gameutil.ConcurrentBidirectionalMap;
import gameutil.math.ComplexDouble;
import gameutil.math.geom.g2D.LineSegR2;
import gameutil.text.Console;
import graphics.screen.SPanel;
import graphics.screen.Screen;

public class HilbertTest {
	
	static ConcurrentBidirectionalMap<Integer,String> curve=new ConcurrentBidirectionalMap<>();
	static int length;
	static int side;
	
	
	public static void main(String[] unicorns) {
		/*int x=1234567890;
		for (int i=0;i<getDigits(x);i++) {
			Console.s.println(getDigit(x,i));
		}*/
		int a=0;
		//base curve
		curve.set(0,"-.5i-.5");
		curve.set(1,"-.5i.5");
		curve.set(2,".5i.5");
		curve.set(3,".5i-.5");//iteration 0
		/*
		curve=nextIteration(curve);//iteration 1
		curve=nextIteration(curve);//iteration 2
		curve=nextIteration(curve);//iteration 3
		curve=nextIteration(curve);//iteration 4
		*/
		
		curve=Hilbert.nextIterationNoFlip(curve);//iteration 1
		curve=Hilbert.nextIterationNoFlip(curve);//iteration 2
		curve=Hilbert.nextIterationNoFlip(curve);//iteration 3
		curve=Hilbert.nextIterationNoFlip(curve);//iteration 4
		
		//curve=nextIteration(curve);//iteration 5
		//curve=nextIteration(curve);//iteration 6
		
		length=curve.getFirstKeys().size();
		side=(int) Math.sqrt(length);
		
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
	
	public static ConcurrentBidirectionalMap<Integer,String> nextIteration(ConcurrentBidirectionalMap<Integer,String> curve) {
		ConcurrentBidirectionalMap<Integer,String> next=new ConcurrentBidirectionalMap<>();
		int length=curve.getFirstKeys().size();
		int off=(int)Math.sqrt(length)/2;
		for (int i=0;i<length;i++) {
			ComplexDouble z=new ComplexDouble(curve.getSecond(i));
			next.set(i, z.complement().$X$(ComplexDouble.i).$A$(new ComplexDouble(-off,-off)).stringify());
			
			next.set(i+length, z.$A$(new ComplexDouble(-off,off)).stringify());
			
			next.set(i+length*2, z.$A$(new ComplexDouble(off,off)).stringify());
			
			next.set(i+length*3, z.complement().$X$(new ComplexDouble(0,-1)).$A$(new ComplexDouble(off,-off)).stringify());
		}
		return next;
	}
	
	public static int squishify(int x,int y) {
		return curve.getFirst((x-side/2+.5)+"i"+(y-side/2+.5));
	}
	
	public static class Home extends Screen{
		int midX=0;
		int midY=0;
		int oldWidth=0;
		int oldHeight=0;
		Panel observer;
		long t=System.currentTimeMillis()+2000;
		BufferedImage himg=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
		//BufferedImage middle=new BufferedImage(36,36,BufferedImage.TYPE_INT_ARGB);
		int x=0;
		int y=0;
		
		int enX=31;
		int enY=31;
		boolean gameOver=false;
		
		public Home(Panel observer) {
			this.observer=observer;
		}
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			//ConcurrentBidirectionalMap<Integer,String> next=new ConcurrentBidirectionalMap<>();
			if (gameOver) {
				g.drawString("GAME OVER",midX-50, midY+300);
			}
			int length=curve.getFirstKeys().size();
			/*
			for (int i=0;i<length-1;i++) {
				ComplexDouble z=new ComplexDouble(curve.getSecond(i));
				ComplexDouble w=new ComplexDouble(curve.getSecond(i+1));
				g.drawLine(midX+(int)(z.R()*4)+200, midY-(int)(z.I()*4), midX+(int)(w.R()*4)+200, midY-(int)(w.I()*4));
			}*/
			paintImage();
			g.setColor(Color.black);
			g.fillRect(midX-74, midY-74, 148, 148);
			
			
			g.drawImage(himg, midX-64, midY-64,128,128, observer);
			
			int uspot=squishify(x,y);
			
			for (int i=0;i<32;i++) {
				for (int j=0;j<32;j++) {
					//int[] data=new int[3];
					int clr = himg.getRGB(i, j);
			        int red =   (clr & 0x00ff0000) >> 16;
			        int green = (clr & 0x0000ff00) >> 8;
			        int blue =   clr & 0x000000ff;
					//Color c=new Color(data[0],data[1],data[2]);
			        Color c=new Color(red,green,blue);
					g.setColor(c);
					int spot=Hilbert.squishify(i,31-j,curve);
					g.fillRect(midX-length/2+spot, midY+200, 1, 30);
					if (spot>uspot-20&&spot<uspot+20) {
						g.fillRect(midX-200+(spot+20-uspot)*10, midY+260, 10, 30);
						
					}
					
					
					ComplexDouble z=new ComplexDouble(curve.getSecond(spot));
					if (spot<length-1) {
						ComplexDouble w=new ComplexDouble(curve.getSecond(spot+1));
						g.drawLine(midX+(int)(z.R()*4)+200, midY-(int)(z.I()*4), midX+(int)(w.R()*4)+200, midY-(int)(w.I()*4));
						//g.drawLine(midX+(int)(z.R()*4)+201, midY-(int)(z.I()*4), midX+(int)(w.R()*4)+201, midY-(int)(w.I()*4));
						//g.drawLine(midX+(int)(z.R()*4)+199, midY-(int)(z.I()*4), midX+(int)(w.R()*4)+199, midY-(int)(w.I()*4));
						
						g.drawLine(midX+(int)(z.R()*4)+200, midY-(int)(z.I()*4)+1, midX+(int)(w.R()*4)+200, midY-(int)(w.I()*4)+1);
						//g.drawLine(midX+(int)(z.R()*4)+201, midY-(int)(z.I()*4)+1, midX+(int)(w.R()*4)+201, midY-(int)(w.I()*4)+1);
						//g.drawLine(midX+(int)(z.R()*4)+199, midY-(int)(z.I()*4)+1, midX+(int)(w.R()*4)+199, midY-(int)(w.I()*4)+1);

						g.drawLine(midX+(int)(z.R()*4)+200, midY-(int)(z.I()*4)-1, midX+(int)(w.R()*4)+200, midY-(int)(w.I()*4)-1);
						//g.drawLine(midX+(int)(z.R()*4)+201, midY-(int)(z.I()*4)-1, midX+(int)(w.R()*4)+201, midY-(int)(w.I()*4)-1);
						//g.drawLine(midX+(int)(z.R()*4)+199, midY-(int)(z.I()*4)-1, midX+(int)(w.R()*4)+199, midY-(int)(w.I()*4)-1);
					}
					if (spot==uspot) {
						g.fillRect(midX+(int)(z.R()*4)+200-2, midY-(int)(z.I()*4)-2, 4,4);
					}
					
				}
			}
			
		}

		public void paintImage() {
			himg.flush();
			himg=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g=himg.createGraphics();
			if (gameOver) {
				g.drawImage(new ImageIcon("assets/mage.png").getImage(), 0, 0, null);
			} else {
			
			g.setColor(Color.pink);
			g.fillRect(0, 0, 32,32);
			/*
			g.setColor(Color.MAGENTA);
			g.fillRect(16, 16, 16,16);
			
			g.setColor(Color.blue);
			g.fillRect(16, 0, 16,16);
			
			g.setColor(Color.white);
			g.fillRect(0, 16, 16,16);
			
			
			g.setColor(Color.black);
			g.fillRect(0, 16, 8,8);
			
			g.setColor(Color.CYAN);
			g.fillRect(8, 24, 8,8);
			
			g.setColor(Color.blue);
			g.fillRect(8, 16, 8,8);
			
			g.setColor(Color.pink);
			g.fillRect(0, 24, 4,4);
			
			g.setColor(Color.MAGENTA);
			g.fillRect(4, 28, 4,4);
			
			g.setColor(Color.blue);
			g.fillRect(4, 24, 4,4);
			*/
			}
			g.setColor(Color.MAGENTA);
			for (int i=-15;i<=15;i++) {
				for (int j=-15;j<15;j++) {
					ComplexDouble z=new ComplexDouble(i,j);
					if (z.isPrime()) {
						g.drawRect(i+16, j+16, 0, 0);
					}
				}
			}
			/*
			g.setColor(Color.green);
			
			g.fillRect(0, 0, 16,16);
			
			g.setColor(Color.red);
			g.fillRect(0, 16, 16,16);
			
			
			
			g.setColor(Color.blue);
			g.fillRect(16, 0, 16,16);
			
			g.setColor(Color.black);
			g.fillRect(16, 16, 16,16);
			*/
			/*
			g.setColor(Color.green);
			g.drawRect(0, 0, 15,15);
			
			g.setColor(Color.red);
			g.drawRect(0, 16, 15,15);
			
			
			
			g.setColor(Color.blue);
			g.drawRect(16, 0, 15,15);
			
			g.setColor(Color.black);
			g.drawRect(16, 16, 15,15);
			
			g.setColor(Color.green);
			g.drawRect(1, 1, 13,13);
			
			g.setColor(Color.black);
			g.drawRect(17, 17, 13,13);
			
			g.setColor(Color.blue);
			g.drawRect(17, 1, 13,13);
			
			g.setColor(Color.red);
			g.drawRect(1, 17, 13,13);
			*/
			
			//g.drawImage(new ImageIcon("assets/Logo Tile T.png").getImage(), 0, 0,32,32, null);
			//g.drawImage(new ImageIcon("assets/clark.png").getImage(), 0, 0,32,32, null);
			g.setColor(Color.green);
			//g.fillRect(x-1, 30-y, 3, 3);
			//g.fillRect(enX, 31-enY, 1, 1);
			g.setColor(Color.blue);
			//g.fillOval(8, 8, 16, 16);
			//g.fillRect(8, 8, 16, 16);
			//g.fillPolygon(new int[] {2,16,30}, new int[] {30,2,30}, 3);
			g.setColor(Color.red);
			//g.fillRect(x, 31-y, 1, 1);
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
			/*
			if (System.currentTimeMillis()>t+800) {
				curve=nextIteration(curve);
				t=System.currentTimeMillis();
			}*/
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!gameOver) {
				if (e.getKeyCode()==KeyEvent.VK_D) {
					if (x<31) {
						x=x+1;
					}
				} else if (e.getKeyCode()==KeyEvent.VK_A) {
					if (x>0) {
						x=x-1;
					}
				} else if (e.getKeyCode()==KeyEvent.VK_W) {
					if (y<31) {
						y=y+1;
					}
				} else if (e.getKeyCode()==KeyEvent.VK_S) {
					if (y>0) {
						y=y-1;
					}
					
				} else if (e.getKeyCode()==KeyEvent.VK_E) {
					int spot=Hilbert.squishify(x,y,curve);
					if (spot<length-1) {
						ComplexDouble z=new ComplexDouble(curve.getSecond(spot+1));
						x=(int)(z.R()-.5)+16;
						y=(int)(z.I()-.5)+16;
					}
				} else if (e.getKeyCode()==KeyEvent.VK_Q) {
					int spot=Hilbert.squishify(x,y,curve);
					if (spot>0) {
						ComplexDouble z=new ComplexDouble(curve.getSecond(spot-1));
						x=(int)(z.R()-.5)+16;
						y=(int)(z.I()-.5)+16;
					}
				}
				moveEnemy();
			} else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				x=0;
				y=0;
				enX=31;
				enY=31;
				gameOver=false;
			}
		}
		
		public void moveEnemy() {
			if (enX>x) {
				enX=enX-1;
			} else if (enX<x) {
				enX=enX+1;
			} else if (enY>y) {
				enY=enY-1;
			} else if (enY<y) {
				enY=enY+1;
			}
			if (enX==x&&enY==y) {
				gameOver=true;
			}
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
