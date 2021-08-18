package gameutil.math.geom;

public class Plane extends Figure{
	Vector v1;
	Vector v2;
	Vector b;
	
	public Plane(Vector v1,Vector v2, Vector b) {
		this.v1=v1;
		this.v2=v2;
		this.b=b;
	}
	
	public Plane(Vector v1,Vector v2, Point p) {
		this.v1=v1;
		this.v2=v2;
		this.b=new Vector(p);
	}
	
	public Plane(Point v1,Point v2, Point p) {
		this.v1=new Vector(v1);
		this.v2=new Vector(v2);
		this.b=new Vector(p);
	}
	
	public Point equation(double s,double t) {
		return new Point(v1. $X$ (s). $A$ (v2. $X$ (t)). $A$ (b));
	}
	
	public boolean equals(Plane p) {
		//check that all components of both normals are proportional (need to figure out how to get normal in n-dimensions)
		return false;
		
	}
	/*public boolean contains(Point p) {
		
	}*/
}