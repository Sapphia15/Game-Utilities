package test;

import gameutil.math.geom.Figure;
import gameutil.math.geom.Line;
import gameutil.math.geom.LineSeg;
import gameutil.math.geom.Point;
import gameutil.math.geom.Ray;
import gameutil.math.geom.Tuple;
import gameutil.math.geom.Vector;
import gameutil.math.geom.g2D.LineSegR2;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class ConsoleApp {
	public static void main(String[] argumentos) {
		Console.s.setTitle("Geometry Tester");
		Console.s.setVisible(true);
		Console.s.rememberCommand(true);
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		//Console.s.readLineInt();
		//Console.s.read();
		//*/
		/*Console.s.println(Double.NaN<=0);*/
		Vector P1=new Vector(new double[]{4.0,4.0});
		Vector P2=new Vector(new double[]{2.0,2.0});
		Vector P3=new Vector(new double[]{3.0,3.0});
		Vector P4=new Vector(new double[]{2.0,2.0});
		Ray r1=new Ray(P3,P4);
		Ray r2=new Ray(P1,P2);
		Line l1=new Line(P3,P4);
		LineSeg seg=new LineSeg(P3,P4);
		Figure intersection=r1.intersection(r2);
		if (intersection instanceof Point) {
			Point intersectionPoint=((Point) intersection);
			Console.s.println("Intersection [POINT]: ("+intersectionPoint.tuple.i(0)+", "+intersectionPoint.tuple.i(1)+")");
			
		} else if (intersection.equals(Figure.SPACE)){
			Console.s.println("No intersection");
		} else if (intersection instanceof LineSeg) {
			LineSeg intersectionSeg=((LineSeg) intersection);
			Console.s.println("Intersection [LINE SEG]: ("+intersectionSeg.getEndPoints()[0].tuple.i(0)+", "+intersectionSeg.getEndPoints()[0].tuple.i(1)+") ("+intersectionSeg.getEndPoints()[1].tuple.i(0)+", "+intersectionSeg.getEndPoints()[1].tuple.i(1)+")");
		} else if (intersection instanceof Ray) {
			Ray intersectionRay=((Ray) intersection);
			Console.s.println("Intersection [RAY]: ("+intersectionRay.getEndPoints()[0].tuple.i(0)+", "+intersectionRay.getEndPoints()[0].tuple.i(1)+") ("+intersectionRay.getEndPoints()[1].tuple.i(0)+", "+intersectionRay.getEndPoints()[1].tuple.i(1)+")");
		}
		
		//Console.s.println("Contains point (-1,-1): "+l1.containsPoint(new Point(new Tuple(new double[]{1,2,14}))));
		//Console.s.println("Contains point (0,2): "+l1.containsPoint(new Point(new Tuple(new double[]{20,,15}))));
	}
}
