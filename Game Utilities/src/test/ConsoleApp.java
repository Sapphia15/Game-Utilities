package test;

import gameutil.math.geom.Figure;
import gameutil.math.geom.Line;
import gameutil.math.geom.LineSeg;
import gameutil.math.geom.Matrix;
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
import java.util.ArrayList;

class ConsoleApp {
	public static void main(String[] argumentos) {
		Console.s.setTitle("Geometry Tester");
		Console.s.setVisible(true);
		Console.s.rememberCommand(true);
		//not working for some reason....
		double radius=10000;
		//generate a population
		ArrayList<Matrix> ms=new ArrayList<Matrix>();
		//make random matrices
		for (int i=0;i<10;i++) {
			//for (int j=0;j<10)
		}
		Matrix m=new Matrix(new double[][] {{Math.random()*2-1,Math.random()*2-1},{Math.random()*2-1,Math.random()*2-1}});
		
		//generate examples
		ArrayList<Vector> inputs=new ArrayList<Vector>();
		ArrayList<Vector> outputs=new ArrayList<Vector>();
		for (int i=0;i<10;i++) {
			Point p1=new Point(new double[] {Math.random()*radius-radius/2,Math.random()*radius-radius/2,Math.random()*radius-radius/2});
			Point p2=new Point(new double[] {Math.random()*radius-radius/2,Math.random()*radius-radius/2,Math.random()*radius-radius/2});
			Point p3=new Point(new double[] {Math.random()*radius-radius/2,Math.random()*radius-radius/2,Math.random()*radius-radius/2});
			
			Point target=new Point(new double[] {Math.random()*radius-radius/2,Math.random()*radius-radius/2,Math.random()*radius-radius/2});
			double d1=target.distance(p1);
			double d2=target.distance(p2);
			double d3=target.distance(p3);
			
			inputs.add(new Vector(p1.tuple.$TENSOR$(p2.tuple.$TENSOR$(p3.tuple).$TENSOR$(new Tuple(new double[] {d1,d2,d3})))));
			outputs.add(new Vector(target.tuple.clone()));
		}
		for (int i=0;i<1000;i++) {
			
		}
		//Console.s.println(m.absdeterminant());
		/*Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.readLine();
		//Console.s.readLineInt();
		//Console.s.read();
		//*/
		/*Console.s.println(Double.NaN<=0);*/
		/*Vector P1=new Vector(new double[]{4.0,4.0});
		Console.s.println("Unnormallized magnitude: "+P1.magnitude());
		Console.s.println("Normallized magnitude: "+P1.normalize().magnitude());
		P1.normalize().getSpds().printVals("normalized");
		P1.getSpds().printVals("not normalized");
		Console.s.pause();
		System.exit(0);
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
		 * 
		 */
	}
}
