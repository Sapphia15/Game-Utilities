package gameutil.math.geom;

import gameutil.math.geom.g2D.PointR2;
public class CartesianCoordinatePlane extends Plane{

	Line xAxis;
	Line yAxis;
	Point origin;
	//conversions of x and y to t
	double xpt;
	double ypt;
	
	/**Constructs a virtual Cartesian plane where the x axis parallel to <code>v1</code> and the origin lies at <code>p</code>
	 * 
	 * @param v1
	 * @param v2
	 * @param p
	 */
	public CartesianCoordinatePlane(Vector v1, Vector v2, Point p) {
		super(v1, v2, p);
		origin=p;
		xAxis=new Line(b,v1.$A$(b));
		yAxis=new Line(v2. $S$ (v2. project (v1)),p);
		xpt=new Point(xAxis.equation(0)).distance(new Point(xAxis.equation(1)));
		yAxis.translateAlongLine(xAxis, xToT(-v2. project (v1).magnitude()));//move y axis to origin
		ypt=new Point(yAxis.equation(0)).distance(new Point(yAxis.equation(1)));
	}
	
	public PointR2 getPointOnPlane(Point p) throws NoIntersectionException{
		if (contains(p)) {
			return null;//new PointR2()
		} else {
			throw new NoIntersectionException();
		}
	}
	
	private double xToT(double x) {
		return x/xpt;
	}
	
	private double tToX(double t) {
		return t*xpt;
	}
	
	private double yToT(double y) {
		return y/ypt;
	}
	
	private double tToY(double y) {
		return y*ypt;
	}

}
