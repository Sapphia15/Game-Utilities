package test;


import gameutil.text.Console;

import java.awt.geom.Point2D;

import gameutil.math.geom.*;
import gameutil.math.geom.g2D.PointR2;

public class PlaneTest {
	 public static void main(String[] args) {
		 Console.s.setTitle("Plane Test");
		 //the x,y plane
		 CartesianCoordinatePlane p=new CartesianCoordinatePlane(new Vector(new double[] {.5,.2}),new Vector(new double[] {0,1}),new Vector(new double[] {0,0}));
		 Console.s.print("t:");
		 double t=Console.s.readLineDouble();
		 Console.s.print("s:");
		 double s=Console.s.readLineDouble();
		 p.equation(t, s).printVals("eq("+t+","+s+")");
		 
		 PointR2 p2d;
		try {
			p2d = p.getPointOnPlane(p.equation(t, s));
			Console.s.println("Point on plane from eq("+t+","+s+")");
			Console.s.println("("+p2d.getX()+","+p2d.getY()+")");
			p.getPoint(0.5385164807134505, 0.2).printVals("Point from plane (0.5385164807134505, 0.2)");;
		} catch (NoIntersectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no intersection");
		}
		 
		 
	 }
}
