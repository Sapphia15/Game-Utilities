package gameutil.geom.g2D;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class LineSeg extends LineR2 {
	public LineSeg(double x1, double y1, double x2, double y2) throws Exception {
		super(x1, y1, x2, y2);
	}

	public LineSeg(PointR2 p1, PointR2 p2) throws Exception {
		super(p1, p2);
	}

	public boolean intersecs(LineR2 l) {
		if (super.intersects(l)) {
			PointR2 intsct = null;
			try {
				intsct = super.intersection(l);
			} catch (Exception e) {
				return false;
			}
			if (intsct == null) {
				return true;
			} else if (containsPoint(intsct)) {
				return true;
			}
		}
		return false;
	}

	public boolean intersects(LineSeg l) {
		if (super.intersects(l)) {
			PointR2 intsct = null;
			try {
				intsct = super.intersection(l);
			} catch (Exception e) {
				return false;
			}
			if (intsct == null
					&& (l.containsPoint(p1) || l.containsPoint(p2) || containsPoint(l.p1) || containsPoint(l.p2))) {
				return true;
			} else if (containsPoint(intsct) && l.containsPoint(intsct)) {
				return true;
			}
		}
		return false;
	}

	public boolean intersects(Rectangle r) {
		LineSeg[] segments = rectToLineSegs(r);
		return (r.contains(x1, y1) || r.contains(x2, y2) || intersects(segments[0]) || intersects(segments[1])
				|| intersects(segments[2]) || intersects(segments[3]));
	}

	/**
	 * Converts a Rectangle to an array of line segments
	 * 
	 * @param r
	 *            rectangle to be converted
	 * @return returns an array of line segments that bound the input rectangle
	 *         starting with the top and index 0 and going clockwise
	 */
	public static LineSeg[] rectToLineSegs(Rectangle r) {
		LineSeg l1;
		LineSeg l2;
		LineSeg l3;
		LineSeg l4;
		try {
			// top line
			l1 = new LineSeg(r.getMinX(), r.getMinY(), r.getMaxX(), r.getMinY());
			// right line
			l2 = new LineSeg(r.getMaxX(), r.getMaxY(), r.getMaxX(), r.getMinY());
			// bottom line
			l3 = new LineSeg(r.getMinX(), r.getMaxY(), r.getMaxX(), r.getMaxY());
			// left line
			l4 = new LineSeg(r.getMinX(), r.getMinY(), r.getMinX(), r.getMaxY());
		} catch (Exception e) {
			System.err.println(
					"rectToLineSegs(Rectangle r) could not produce segments from rectangle. (returning null for all indicies)");
			l1 = null;
			l2 = null;
			l3 = null;
			l4 = null;
		}
		LineSeg[] segments = { l1, l2, l3, l4 };
		return segments;
	}

	/*
	 * public Point intersection(Line l) throws Exception{ if (intersects(l)){
	 * if ((vertical&&l.vertical)){ return null; } else if (vertical) { return
	 * new Point(b,l.equation(b)); } else if (l.vertical){ return new
	 * Point(l.b,equation(l.b)); } else { double x=(l.b-b)/(m-l.m); return new
	 * Point(x,equation(x)); } } else { throw new Exception(
	 * "Can't find intersection point of lines that don't intersect."); } }
	 * 
	 * public Point intersection(LineSeg l) throws Exception{ if
	 * (intersects(l)){ if ((vertical&&l.vertical)){ return null; } else if
	 * (vertical) { return new Point(b,l.equation(b)); } else if (l.vertical){
	 * return new Point(l.b,equation(l.b)); } else { double x=(l.b-b)/(m-l.m);
	 * return new Point(x,equation(x)); } } else { throw new Exception(
	 * "Can't find intersection point of lines that don't intersect."); } }
	 */

	public boolean containsPoint(PointR2 p) {
		return (Line2D.ptSegDist(x1, y1, x2, y2, p.getX(), p.getY()) == 0);
	}

	public void draw(Graphics g, PointR2 view) {
		g.drawLine((int) Math.floor(x1 - view.getX()), (int) Math.floor(y1 - view.getY()),
				(int) Math.floor(x2 - view.getX()), (int) Math.floor(y2 - view.getY()));
	}

}
