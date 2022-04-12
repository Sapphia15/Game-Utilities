package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import gameutil.math.geom.BezierCurve;
import gameutil.math.geom.Point;
import gameutil.math.geom.Vector;
import graphics.KPanel;

public class paneltest {
	static int x=10;
	static int y=10;
	static int width=10;
	static int height=10;
	static int mult=1;
	static int pIndex=-1;
	static BezierCurve b;
	static BezierCurve d;
	public static void main(String[] unicorns) {
		Frame f=new Frame();
		b=new BezierCurve(new Point[] {new Point(new double[] {1,200}),new Point(new double[] {200,100}),new Point(new double[] {10,500}),new Point(new double[] {50,500})});
		d=b.deriv();
		KPanel panel=new KPanel(f) {
			int brightness=0;
			double rate=.5;
			long lastTime=System.currentTimeMillis();
			public void paintComponent(Graphics g){
				/*int x=paneltest.x-width/2;
				int y=paneltest.y-height/2;
				g.setColor(Color.red);
				g.drawRect(x, y, width,height);
				g.drawRect(this.getWidth()-x, y, width, height);
				g.drawRect(this.getWidth()-x, this.getHeight()-y, width, height);
				g.drawRect(x, this.getHeight()-y, width, height);
				g.setColor(Color.green);
				g.drawRect(x, y, this.getWidth()-(2*x), this.getHeight()-(2*y));
				g.drawRect(this.getHeight()-x,y, -2*x,this.getHeight()-y-2*y);
				g.setColor(Color.blue);
				x=x+width;
				y=y+height;
				g.drawRect(x, y, width,height);
				g.drawRect(this.getWidth()-x, y, width, height);
				g.drawRect(this.getWidth()-x, this.getHeight()-y, width, height);
				g.drawRect(x, this.getHeight()-y, width, height);
				int width=paneltest.height/2;
				int height=paneltest.width/2;
				x=x+width;
				g.setColor(Color.red);
				g.drawRect(x, y, width,height);
				g.drawRect(this.getWidth()-x, y, width, height);
				g.drawRect(this.getWidth()-x, this.getHeight()-y, width, height);
				g.drawRect(x, this.getHeight()-y, width, height);
				g.setColor(Color.blue);
				y=y+height;
				g.drawRect(x, y, width,height);
				g.drawRect(this.getWidth()-x, y, width, height);
				g.drawRect(this.getWidth()-x, this.getHeight()-y, width, height);
				g.drawRect(x, this.getHeight()-y, width, height);*/
				int brightness=this.brightness;
				g.setColor(Color.black);
				g.fillRect(0,0,this.getWidth(),this.getHeight());
				g.setColor(Color.gray);
				CopyOnWriteArrayList<Point> points=b.getPoints();
				g.drawLine((int)points.get(0).tuple.i(0),(int)points.get(0).tuple.i(1),(int)points.get(1).tuple.i(0),(int)points.get(1).tuple.i(1));
				g.drawLine((int)points.get(2).tuple.i(0),(int)points.get(2).tuple.i(1),(int)points.get(3).tuple.i(0),(int)points.get(3).tuple.i(1));
				Point red=new Point(new double[] {255,brightness});
				Point green=new Point(new double[] {brightness,255});
				for (int val=0;val<1000;val++) {
					double t=val/1000d;
					g.setColor(new Color((int)red.lerp(green, t).tuple.i(0),(int)red.lerp(green, t).tuple.i(1),brightness,5));
					Point curvePoint=b.getPoint(t);
					Point derivPoint=d.getPoint(t);
					g.fillOval((int)Math.round(curvePoint.tuple.i(0))-2, (int)Math.round(curvePoint.tuple.i(1))-2,5, 5);
					g.setColor(new Color((int)red.lerp(green, t).tuple.i(0),(int)red.lerp(green, t).tuple.i(1),brightness));
					g.fillOval((int)Math.round(curvePoint.tuple.i(0))-1, (int)Math.round(curvePoint.tuple.i(1))-1,2, 2);
					
					g.fillOval((int)Math.round(derivPoint.tuple.i(0))-2, (int)Math.round(derivPoint.tuple.i(1))-2,5, 5);
					g.setColor(new Color((int)red.lerp(green, t).tuple.i(0),(int)red.lerp(green, t).tuple.i(1),brightness));
					g.fillOval((int)Math.round(derivPoint.tuple.i(0))-1, (int)Math.round(derivPoint.tuple.i(1))-1,2, 2);
					g.setColor(Color.pink);
					derivPoint=new Point(new Vector(derivPoint).normalize().$X$(50));
					//g.drawLine((int)Math.round(curvePoint.tuple.i(0)), (int)Math.round(curvePoint.tuple.i(1)),(int)Math.round(curvePoint.tuple.i(0)+derivPoint.tuple.i(0)), (int)Math.round(curvePoint.tuple.i(1)+derivPoint.tuple.i(1)));
				}
				/*
				double t=brightness/255d;
				Point curvePoint=b.getPoint(t);
				Point deriv=d.getPoint(t);
				*/
				CopyOnWriteArrayList<Point> bPoints=b.getPoints();
				g.setColor(Color.white);
				for (Point p : bPoints) {
					g.fillOval((int)p.tuple.i(0)-2, (int)p.tuple.i(1)-2, 5, 5);
				}
				
			}
			
			public void update() {
				long newTime=System.currentTimeMillis();
				brightness+=Math.floor(rate*(newTime-lastTime));
				lastTime=newTime;
				if (brightness>254) {
					brightness=255;
					rate=rate*-1;
				} else if (brightness<1) {
					brightness=0;
					rate=rate*-1;
				}
				/*paneltest.x+=1;
				if (paneltest.x>this.getWidth()) {
					paneltest.x=-1;
				}
				paneltest.y+=2;
				if (paneltest.y>this.getHeight()) {
					paneltest.y=-1;
				}
				paneltest.width+=mult;
				if (paneltest.width>20) {
					mult=mult*-1;
				} else if (paneltest.width<1){
					mult=mult*-1;
				}
				paneltest.height-=mult;*/
				java.awt.Point point=this.getMousePosition();
				if (point!=null) {
					if (pIndex>-1&&pIndex<4) {
						b.setPoint(new Point(new double[] {point.x,point.y}), pIndex);
					}
				}
				d=b.deriv();
			}
			
			
		};
		f.add(panel);
		panel.closeOnExit();
		f.setPreferredSize(new Dimension(500,500));
		f.pack();
		panel.addMouseListener(new MAdapter());
		f.setVisible(true);
		panel.setDoubleBuffered(true);
		panel.start();
		f.setLocationRelativeTo(null);
		
	}
	static class MAdapter extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			if (e.getButton()==1) {
				if (e.isShiftDown()) {
					pIndex=3;
				} else {
					pIndex=0;
				}
			} else if (e.getButton()==2) {
				pIndex=1;
			} else if (e.getButton()==3) {
				pIndex=2;
			}
		}
		public void mouseReleased(MouseEvent e) {
			pIndex=-1;
		}
		
		public void mouseDragged(MouseEvent e) {
			
		}
	}
}
