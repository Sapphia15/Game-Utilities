package test;

import gameutil.geom.Line;
import gameutil.geom.Point;
import gameutil.geom.Tuple;
import gameutil.geom.Vector;
import gameutil.geom.g2D.LineSeg;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class ConsoleApp {
	public static void main(String[] argumentos) {
		Console.s.setVisible(true);
		//Console.s.readLineInt();
		//Console.s.read();
		Console.s.readLine();
		Console.s.readLine();
		Console.s.read();
		Console.s.read();
		Console.s.readLine();
		
		/*Vector P1=new Vector(new double[]{1.0,2.0,15});
		Vector P2=new Vector(new double[]{0.0,0.0,15});
		Vector P3=new Vector(new double[]{0.0,2.0});
		Vector P4=new Vector(new double[]{2.0,0.0});
		Line l1=new Line(P1,P2);
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
		Console.s.println("t: "+t);
		Console.s.println("s:"+s);
		Console.s.println(l1.equation(19).getSpds().i(2));
		Console.s.println("Contains point (-1,-1): "+l1.containsPoint(new Point(new Tuple(new double[]{20,40,14}))));
		Console.s.println("Contains point (0,2): "+l1.containsPoint(new Point(new Tuple(new double[]{20,40,15}))));*/
	}
}
