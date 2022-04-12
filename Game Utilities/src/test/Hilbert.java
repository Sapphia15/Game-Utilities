package test;

import java.util.ArrayList;
import java.util.Random;

import gameutil.math.ComplexDouble;
import gameutil.math.geom.g2D.LineSegR2;
import gameutil.text.Console;

public class Hilbert {
	public static void main(String[] unicorns) {
		ComplexDouble d=new ComplexDouble(Math.E,0).$E$(new ComplexDouble(0,Math.PI));
		Console.s.println(d.R()+" + "+d.I()+"i");
	}
}
