package test;

import gameutil.geom.Line;
import gameutil.geom.Point;
import gameutil.geom.Tuple;
import gameutil.geom.Vector;
import gameutil.geom.g2D.LineR2;
import gameutil.geom.g2D.LineSegR2;
import gameutil.geom.g2D.PointR2;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class LineIntersect {
	public static void main(String[] argumentos) {
		Console.s.setVisible(true);
		
		//rN test
		Vector P1=new Vector(new double[]{7.0,0.0}); //current test does not pass
		Vector P2=new Vector(new double[]{7.0,-8.0});
		Vector P3=new Vector(new double[]{7.0,5.0});
		Vector P4=new Vector(new double[]{7.0,38.0});
		Line l1=new Line(P1,P2);
		Line l2=new Line(P3,P4);
		Console.s.println(l1.intersects(l2)); //
		
		
		//r2 Works!
		/*PointR2 p1=new PointR2(0,0);
		PointR2 p2=new PointR2(1,1);
		PointR2 p3=new PointR2(1,2);
		PointR2 p4=new PointR2(0,1);
		
		try {
			LineR2 l1=new LineR2(p1,p2);
			LineR2 l2=new LineR2(p3,p4);
			//LineSegR2 l1=new LineSegR2(p1,p2);
			//LineSegR2 l2=new LineSegR2(p3,p4);
			Console.s.println(l1.intersects(l2));
			if (l1.intersects(l2)) {
				Console.s.println(l1.intersection(l2).getX());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
