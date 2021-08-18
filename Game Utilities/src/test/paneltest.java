package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

import graphics.KPanel;

public class paneltest {
	static int x=10;
	static int y=10;
	static int width=10;
	static int height=10;
	static int mult=1;
	public static void main(String[] unicorns) {
		Frame f=new Frame();
		KPanel panel=new KPanel(f) {
			public void paintComponent(Graphics g){
				int x=paneltest.x-width/2;
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
				g.drawRect(x, this.getHeight()-y, width, height);
				
			}
			
			public void update() {
				paneltest.x+=1;
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
				paneltest.height-=mult;
			}
		};
		f.add(panel);
		panel.closeOnExit();
		f.setPreferredSize(new Dimension(500,500));
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		panel.setDoubleBuffered(true);
		panel.start();
	}
}
