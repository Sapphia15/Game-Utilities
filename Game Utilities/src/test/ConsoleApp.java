package test;

import gameutil.geom.Figure;
import gameutil.geom.Line;
import gameutil.geom.LineSeg;
import gameutil.geom.Point;
import gameutil.geom.Ray;
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
		Vector P1=new Vector(new double[]{2.0,2.0});
		Vector P2=new Vector(new double[]{1.0,1.0});
		//Ray r1=new Ray(P1,P2);
		//Line l1=new Line(P1,P2);
		LineSeg seg=new LineSeg(P1,P2);
		Figure intersection=seg.intersection(new Point(new Vector(new double[] {1.78,1.77})));
		if (intersection instanceof Point) {
			Point intersectionPoint=((Point) intersection);
			Console.s.println("Intersection: ("+intersectionPoint.tuple.i(0)+", "+intersectionPoint.tuple.i(1)+")");
			
		} else if (intersection.equals(Figure.SPACE)){
			Console.s.println("No intersection");
		}
		
		//Console.s.println("Contains point (-1,-1): "+l1.containsPoint(new Point(new Tuple(new double[]{1,2,14}))));
		//Console.s.println("Contains point (0,2): "+l1.containsPoint(new Point(new Tuple(new double[]{20,,15}))));
	}
}
