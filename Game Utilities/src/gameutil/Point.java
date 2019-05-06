package gameutil;

import java.awt.geom.Point2D;

public class Point {
	private double x;
	private double y;
	
	public Point(double x,double y){
		this.x=x;
		this.y=y;
	}
	
	public double distance(Point p){
		return Point2D.distance(x, y, p.x, p.y);
	}
	
	public double distanceSq(Point p){
		return Point2D.distanceSq(x, y, p.x, p.y);
	}
	
	public void move(double x,double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
}
