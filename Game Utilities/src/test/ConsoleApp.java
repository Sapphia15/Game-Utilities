package test;

import gameutil.geom.Tuple;
import gameutil.geom.g2D.LineSeg;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class ConsoleApp {
	public static void main(String[] argumentos) {
		Tuple t1=new Tuple(new double[]{1.0,2.0,10});
		Tuple t2=new Tuple(new double[]{3.0,-1.0});
		Tuple t3=t1. $A$ (t2);
		Console.s.setTheme(Console.theme.sea);
		Console.s.setVisible(true);
		Console.s.println("t1: ["+t1.i(0)+","+t1.i(1)+","+t1.i(2)+"]");
		Console.s.println("t2: ["+t2.i(0)+","+t2.i(1)+"]");
		Console.s.println("t3: ["+t3.i(0)+","+t3.i(1)+","+t3.i(2)+"]");
	}
}
