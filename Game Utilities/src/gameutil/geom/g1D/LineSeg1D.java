package gameutil.geom.g1D;

public class LineSeg1D extends Figure1D{
	private double minX;
	private double maxX;
	public static final int ID=1;
	
	public LineSeg1D(Point1D p1,Point1D p2) {
		if (p1.getX()>p2.getX()) {
			minX=p2.getX();
			maxX=p1.getX();
		} else {
			minX=p1.getX();
			maxX=p2.getX();
		}
	}
	
	public boolean contains(Point1D p) {
		return p.getX()>=minX && p.getX()<=maxX;
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

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
