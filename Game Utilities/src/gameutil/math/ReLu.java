package gameutil.math;

import gameutil.math.geom.Matrix;
import gameutil.math.geom.Tuple;
import gameutil.math.geom.Vector;

public class ReLu{
	
	
	public static double apply(double d) {
		if (d>0) {
			return d;
		} else {
			return 0;
		}
	}
	
	public static Vector apply(Vector v) {
		Tuple ret=Tuple.origin(v.n());
		for (int i=0;i<v.n();i++) {
			ret.set(i, apply(v.getSpds().i(i)));
		}
		return new Vector(ret);
	}
}
