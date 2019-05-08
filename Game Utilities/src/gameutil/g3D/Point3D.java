package gameutil.g3D;

import java.awt.Point;


public class Point3D {
	double x;
	double y;
	double z;
	
	public Point3D(double x,double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public static Point3D import2DPoint(Point p){
		return new Point3D(p.x,p.y,0);
	}
	
	public void setLocation(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void setLocation(Point3D p){
		x=p.x;
		y=p.y;
		z=p.z;
	}
	
	public double getDistance(int x,int y,int z){
		return Math.sqrt(Math.pow(this.x-x, 2)+Math.pow(this.y-y, 2)+Math.pow(this.z-z, 2));
	}
}
