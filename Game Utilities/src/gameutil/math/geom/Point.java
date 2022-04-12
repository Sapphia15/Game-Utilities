package gameutil.math.geom;

import gameutil.math.geom.g2D.PointR2;

public class Point extends Brane{
	
	public Tuple tuple;
	
	
	public Point(Tuple t){
		super(0,0,t);
		tuple=t;
	}
	
	public Point(Vector v) {
		super(0,0,v.getSpds());
		tuple=v.getSpds();
	}
	
	public Point(PointR2 p) {
		super(0,0,new Tuple(new double[] {p.getX(),p.getY()}));
		tuple=getCenter().tuple;
	}
	
	private Point(Point p) {
		super(0,0,new Tuple(p.tuple.a()));
		tuple=getCenter().tuple;
	}
	
	public Point(double[] coords) {
		super(0,0,new Tuple(coords));
		tuple=getCenter().tuple;
	}

	/**Returns the distance between this point and a point <code>p</code>
	 * 
	 * @param p
	 * @return
	 */
	public double distance(Point p){
		return Math.sqrt((p.tuple .$S$ (tuple) .sq()).sum());
	}
	
	/**Returns the distance from this point to the origin.
	 * 
	 * @return
	 */
	public double distanceO(){
		return distance(new Point(Tuple.origin(tuple.n())));
	}
	
	public boolean equals(Point p){
		return tuple.equals(p.tuple);
	}
	
	public boolean intersects(Ray r) {
		return r.contains(this);
	}
	
	public boolean intersects(Line l) {
		return l.contains(this);
	}
	
	public boolean intersects(Point p) {
		return p.equals(this);
	}
	
	public boolean contains(Point p) {
		return p.equals(this);
	}
	
	public Figure intersection(Point p) {
		if (p.equals(this)) {
			return new Point(this);
		} else {
			return Figure.SPACE;
		}
	}
	
	public Figure intersection(Line l) {
		return l.intersection(this);
	}
	
	public Figure intersection(Ray r) {
		return r.intersection(this);
	}
	
	public void printVals() {
		tuple.printVals();
	}
	
	public void printVals(String lable) {
		tuple.printVals(lable);
	}
	
	public void move(Vector v) {
		tuple=tuple.$A$(v.getSpds());
	}
	
	@Override
	public Point clone() {
		return new Point(this);
	}
	
	public static Point origin(int n) {
		return new Point(Tuple.origin(n));
	}
	
	public static Point lerp(Point p1, Point p2,double t) {
		return new Point(p1.tuple.$X$((1-t)).$A$(p2.tuple.$X$(t)));
	}
	
	public Point lerp(Point p2,double t) {
		return new Point(tuple.$X$((1-t)).$A$(p2.tuple.$X$(t)));
	}

}


