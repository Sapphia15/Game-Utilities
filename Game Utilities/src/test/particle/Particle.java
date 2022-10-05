package test.particle;

import java.awt.Color;

import gameutil.math.geom.Point;

public class Particle {
	Point pos;
	Color c;
	
	public Particle(double x,double y, double z,Color c) {
		this.pos=new Point(new double[] {x,y,z});
		this.c=c;
	}
	
	public java.awt.Point project(){
		return new java.awt.Point((int) (getX()/getZ()),(int) (getY()/getZ()));
	}
	
	public java.awt.Point project(Camera c){
		Point p=c.transform(pos);
		return new java.awt.Point((int) (p.tuple.i(0)/*p.tuple.i(2)*/),(int) (p.tuple.i(1)/*p.tuple.i(2)*/));
	}
	
	public double getX() {
		return pos.tuple.i(0);
	}
	
	public double getY() {
		return pos.tuple.i(1);
	}
	
	public double getZ() {
		return pos.tuple.i(2);
	}
	
	public Color getColor() {
		return c;
	}
}
