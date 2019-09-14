package gameutil.geom.g2D;

import java.awt.Rectangle;
import java.util.ArrayList;

/*TO DO:
 *  -Make functions for adding and subtracting other vectors
 *  
 */

public class VectorR2 extends LineSegR2 {
	double mX;
	double mY;
	
	public VectorR2(PointR2 base, PointR2 end) throws Exception {
		super(base.getX(), base.getY(), end.getX(), end.getY());
		mX=end.getX()-base.getX();
		mY=end.getY()-base.getY();
	}

	public PointR2 base() {
		return new PointR2(x1, y1);
	}

	public PointR2 end() {
		return new PointR2(x2, y2);
	}

	public PointR2 intersection(Rectangle r) {
		if (intersects(r)) {
			if (r.contains(base().getX(), base().getY()) && r.contains(end().getX(), end().getY())) {
				return null;
			} else if (r.contains(base().getX(), base().getY())) {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				for (LineSegR2 l : segments) {
					if (intersects(l)) {
						try {
							return intersection(l);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				ArrayList<PointR2> points = new ArrayList<>();
				for (LineSegR2 l : segments) {
					if (intersects(l)) {
						try {
							points.add(intersection(l));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				PointR2 closest = points.get(0);
				for (PointR2 p : points) {
					if (p.distance(base()) > closest.distance(base())) {
						closest = p;
					}
				}
				return closest;
			}

		}
		return null;
	}
	
	public double getMagnetudeX() {
		return mX;
	}
	
	public double getMagnetudeY() {
		return mY;
	}
}
