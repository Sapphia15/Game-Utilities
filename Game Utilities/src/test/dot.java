package test;

import gameutil.math.geom.Tuple;
import gameutil.text.Console;

public class dot {
	public static void main(String[] unicorns) {
		Console.s.println(new Tuple(new double[] {0d,1d,1d}).$DOT$(new Tuple(new double[] {2d,-1d,1d})));
	}
}
