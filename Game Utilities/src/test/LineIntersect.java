package test;

import gameutil.geom.Figure;
import gameutil.geom.Line;
import gameutil.geom.Point;
import gameutil.geom.Tuple;
import gameutil.geom.Vector;
import gameutil.geom.g2D.LineOverlapException;
import gameutil.geom.g2D.LineR2;
import gameutil.geom.g2D.LineSegR2;
import gameutil.geom.g2D.NoIntersectionException;
import gameutil.geom.g2D.PointR2;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class LineIntersect {
	public static void main(String[] argumentos) {
		Console.s.setVisible(true);
		
		//rN tests
		
		//<ALL TESTS PASSED>
		
		//Parallel Diagonal Lines
		Vector P1=new Vector(new double[]{1.0,1.0});
		Vector P2=new Vector(new double[]{3.0,4.0});
		Vector P3=new Vector(new double[]{2.0,0.0});
		Vector P4=new Vector(new double[]{4.0,3.0});
		Line l1=new Line(P1,P2);
		Line l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Parallel Vertical Lines
		P1=new Vector(new double[]{1.0,1.0});
		P2=new Vector(new double[]{1.0,4.0});
		P3=new Vector(new double[]{2.0,0.0});
		P4=new Vector(new double[]{2.0,-78.0});
		l1=new Line(P1,P2);
		l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Parallel Horizontal Lines
		P1=new Vector(new double[]{5.0,3.0});
		P2=new Vector(new double[]{85.0,3.0});
		P3=new Vector(new double[]{-7.0,-5.0});
		P4=new Vector(new double[]{301.0,-5.0});
		l1=new Line(P1,P2);
		l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Same Line
		P1=new Vector(new double[]{0.0,2.0});
		P2=new Vector(new double[]{2.0,6.0});
		P3=new Vector(new double[]{1.0,4.0});
		P4=new Vector(new double[]{-1.0,0.0});
		l1=new Line(P1,P2);
		l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Vertical and Diagonal
		P1=new Vector(new double[]{3.0,5.0});
		P2=new Vector(new double[]{3.0,65.0});
		P3=new Vector(new double[]{55.0,55.0});
		P4=new Vector(new double[]{-3.0,-3.0});
		l1=new Line(P1,P2);
		l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Horizontal and Diagonal
		P1=new Vector(new double[]{5.0,4.0});
		P2=new Vector(new double[]{85.0,4.0});
		P3=new Vector(new double[]{67.0,67.0});
		P4=new Vector(new double[]{2.5,2.5});
		l1=new Line(P1,P2);
		l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
		
		//Horizontal and Vertical

		test(new double[]{5.0,3.0},new double[]{85,3.0},new double[]{-5.0,-7.0},new double[]{-5.0,307.0});
		
		//Not Parallel Diagonal Lines
		
		test(new double[]{5.0,3.0},new double[]{2.0,1.0},new double[]{-2.0,-2.0},new double[]{301.0,301.0});
		
		//Skew Lines
		
		test(new double[]{1.0,1.0,1.0},new double[]{85.0,85.0,85.0},new double[]{10.0,0.0,11.0},new double[]{0.0,10.0,1.0});
		
		//Intersecting Lines with 3 non-zero dimensional slopes
		
		test(new double[]{0.0,0.0,1.0},new double[]{2.5,7.5,6.0},new double[]{2.0,-4.0,6.0},new double[]{0.0,10.0,0.0});
		
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

	public static void test(double[] t1,double[] t2, double[] tl1, double[] tl2) {
		Vector P1=new Vector(t1);
		Vector P2=new Vector(t2);
		Vector P3=new Vector(tl1);
		Vector P4=new Vector(tl2);
		Line l1=new Line(P1,P2);
		Line l2=new Line(P3,P4);
		Console.s.println("Test line 1:\n"
						+ "	("+P1.getSpds().i(0)+", "+P1.getSpds().i(1)+", "+P1.getSpds().i(2)+")\n"
						+ "	("+P2.getSpds().i(0)+", "+P2.getSpds().i(1)+", "+P2.getSpds().i(2)+")");
		Console.s.println("Test line 2:\n"
				+ "	("+P3.getSpds().i(0)+", "+P3.getSpds().i(1)+", "+P3.getSpds().i(2)+")\n"
				+ "	("+P4.getSpds().i(0)+", "+P4.getSpds().i(1)+", "+P4.getSpds().i(2)+")");
		Console.s.println("Intersects: "+l1.intersects(l2)); //
		if (l1.intersects(l2)) {
			Figure intersection=l1.intersection(l2);
			if (intersection instanceof Point) {
				Console.s.println("Intersection: ("+((Point)intersection).tuple.i(0)+", "+((Point)intersection).tuple.i(1)+", "+((Point)intersection).tuple.i(2)+")\n");
			} else if (intersection instanceof Line){
				Console.s.println("Same line.\n");
			}
			
		}
	}
}
