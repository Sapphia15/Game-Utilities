package test;

import gameutil.geom.Figure;
import gameutil.geom.Line;
import gameutil.geom.Point;
import gameutil.geom.Tuple;
import gameutil.geom.Vector;
import gameutil.geom.g2D.LineSegR2;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class ConsoleApp {
	public static void main(String[] argumentos) {
		Console.s.setTitle("Geometry Tester");
		Console.s.setVisible(true);
		//Console.s.readLineInt();
		//Console.s.read();
		//*/
		/*Console.s.println(Double.NaN<=0);*/
		Vector P1=new Vector(new double[]{1.0,4.0,14.5,10});
		Vector P2=new Vector(new double[]{0.0,4.0,14.5,10});
		Vector P3=new Vector(new double[]{2.0,8.0,14.5,10});
		Vector P4=new Vector(new double[]{-1.0,2.0,14.5,10});
		Line l1=new Line(P1,P2);
		Line l2=new Line(P3,P4);
		double x1=P1.end().i(0);
		double y1=P1.end().i(1);
		double x2=P2.end().i(0);
		double y2=P2.end().i(1);
		double x3=P3.end().i(0);
		double y3=P3.end().i(1);
		double x4=P4.end().i(0);
		double y4=P4.end().i(1);
		double t=( (y1-y3)*(x3-x4) + (x3-x1)*(y3-y4))/( (y3-y4)*(x1-x2)-(y1-y2)*(x3-x4) );
		double s=( t*(y1-y2)+y1-y3 )/( y3-y4 );
		Console.s.setTheme(Console.theme.sea);
		Console.s.setVisible(true);
		//Console.s.println("t: "+t);
		//Console.s.println("s:"+s);
		Vector result=l1.equation(0);
		Console.s.println(result.getSpds().i(0));
		Console.s.println(result.getSpds().i(1));
		Console.s.println(result.getSpds().i(2));
		Console.s.println(l1.intersects(l2));
		Figure intersection=l2.intersection(l1);
		if (intersection.getClass().equals(Line.class)) {
			Console.s.println("Lines are congruent");
		} else if (intersection.getClass().equals(Point.class)) {
			((Point)intersection).printVals("Intersection point:");
		} else {
			Console.s.println("Lines do not intersect");
		}
		Console.s.println("");
		/*Console.s.println(l1.contains(new Point(P1)));//t
		Console.s.println(l1.contains(new Point(P2)));//t
		Console.s.println(l1.contains(new Point(P3)));//t
		Console.s.println(l1.contains(new Point(P4)));//f
		Console.s.println(l2.contains(new Point(P1)));//f
		Console.s.println(l2.contains(new Point(P2)));//f
		Console.s.println(l2.contains(new Point(P3)));//t
		Console.s.println(l2.contains(new Point(P4)));//t*/
		new Point(P1).printVals("P1");
		new Point(P2).printVals("P2");
		String cmd="";
		while (true) {
			cmd=Console.s.readLine();
			switch (cmd) {
				case "l i p": 
					Console.s.print("X: ");
					double x=Console.s.readLineDouble();
					Console.s.print("Y: ");
					double y=Console.s.readLineDouble();
					Console.s.print("Z: ");
					double z=Console.s.readLineDouble();
					Console.s.println("Contains point ("+x+","+y+","+z+"): "+l1.contains(new Point(new Tuple(new double[]{x,y,z}))));
				break;
				case "l i l":
					Console.s.print("aX1: ");
					double mx1=Console.s.readLineDouble();
					Console.s.print("aY1: ");
					double my1=Console.s.readLineDouble();
					Console.s.print("aZ1: ");
					double mz1=Console.s.readLineDouble();
					Console.s.print("bX1: ");
					double bx1=Console.s.readLineDouble();
					Console.s.print("bY1: ");
					double by1=Console.s.readLineDouble();
					Console.s.print("bZ1: ");
					double bz1=Console.s.readLineDouble();
					
					Console.s.print("aX2: ");
					double mx2=Console.s.readLineDouble();
					Console.s.print("aY2: ");
					double my2=Console.s.readLineDouble();
					Console.s.print("aZ2: ");
					double mz2=Console.s.readLineDouble();
					Console.s.print("bX2: ");
					double bx2=Console.s.readLineDouble();
					Console.s.print("bY2: ");
					double by2=Console.s.readLineDouble();
					Console.s.print("bZ2: ");
					double bz2=Console.s.readLineDouble();
					
					
					l1=new Line(new Point(new Tuple(new double[] {mx1,my1,mz1})), new Point(new Tuple(new double[] {mx1,my1,mz1})));
					l2=new Line(new Point(new Tuple(new double[] {mx2,my2,mz2})), new Point(new Tuple(new double[] {mx2,my2,mz2})));
					Console.s.println("Lines intersect: "+l1.intersects(l2));
				break;
				case "?":
					Console.s.println("l i l - check if two lines intersect");
					Console.s.println("l i p - check if a line intersects/contains a point");
				break;
			}
		}
		//Console.s.println("Contains point (-1,-1): "+l1.containsPoint(new Point(new Tuple(new double[]{1,2,14}))));
		//Console.s.println("Contains point (0,2): "+l1.containsPoint(new Point(new Tuple(new double[]{20,,15}))));
	}
}
