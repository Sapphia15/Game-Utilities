package gameutil.math.geom.g1D;

public class LineSeg1D extends Figure1D{
	private double minX;
	private double maxX;
	public static final int ID=1;
	
	public LineSeg1D(Point1D p1,Point1D p2) {
		this(p1.getX(),p2.getX());
	}
	
	public LineSeg1D(double p1,double p2) {
		if (p1>p2) {
			minX=p2;
			maxX=p1;
		} else {
			minX=p1;
			maxX=p2;
		}
	}
	
	public boolean contains(Point1D p) {
		return p.getX()>=minX && p.getX()<=maxX;
	}
	
	public boolean contains(LineSeg1D l) {
		return contains(new Point1D(l.minX))&&contains(new Point1D(l.maxX));
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMaxX() {
		return maxX;
	}
	
	public Figure1D intersection(Point1D p) {
		return p.intersection(this);
	}
	
	public Figure1D intersection(Ray1D r) {
		return r.intersection(this);
	}
	
	public boolean intersects(LineSeg1D ls) {
		return (contains(new Point1D(ls.minX))||contains(new Point1D(ls.maxX))||ls.contains(new Point1D(minX))||ls.contains(new Point1D(maxX)));
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
