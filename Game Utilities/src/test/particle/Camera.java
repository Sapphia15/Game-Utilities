package test.particle;

import gameutil.math.geom.Point;
import gameutil.math.geom.Vector;

public class Camera {
	Point pos;
	Vector facing;
	
	public Camera(double x,double y,double z,Vector facing) {
		pos=new Point(new double[] {x,y,z});
		this.facing=facing.normalize();
	}
	
	public Point rotate(int axis1,int axis2,Point p) {/*
		(a+bi)*(c+di)=ac-bd+(ad+bc)i
		*/
		//(facing X)+(facing Y)i is what we want to rotate by
		double px=p.tuple.i(0);
		double py=p.tuple.i(1);
		double fx=facing.getSpds().i(0);
		double fy=facing.getSpds().i(1);
		double x=px*fx-py*fy;
		double y=px*fy+py*fx;
		double[] xyz=new double[3];
		xyz[axis1]=x;
		xyz[axis2]=y;
		xyz[3-(axis1+axis2)]=p.tuple.i(3-(axis1+axis2));
		return new Point(xyz);
	}
	
	public Point rotateXY(Point p) {
		return rotate(0,1,p);
	}
	
	public Point rotateXZ(Point p) {
		return rotate(0,2,p);
	}
	
	public void move(Vector v) {
		pos.move(v);
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
	
	public void move(double x,double y, double z) {
		move(new Vector(new double[] {x,y,z}));
	}
	
	public Point translate(Point p) {
		return new Point(pos.tuple.$A$(p.tuple));
	}
	
	public Point transform(Point p) {
		return rotateXZ(translate(p));
	}
}
