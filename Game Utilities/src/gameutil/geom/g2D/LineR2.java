package gameutil.geom.g2D;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class LineR2 {
	// ana ++ **cardinal directions of 4th dimension points
	// kata--
	protected double m;
	protected double b;// x coord for vertical lines
	protected double x1;
	protected double x2;
	protected double y1;
	protected double y2;
	protected PointR2 p1;
	protected PointR2 p2;
	protected boolean vertical;

	public LineR2(double x1, double y1, double x2, double y2) throws Exception {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		p1 = new PointR2(x1, y1);
		p2 = new PointR2(x2, y2);
		if (x1 == x2 && y1 == y2) {
			throw new Exception("Can't make a line out of one point.");
		}
		if (x1 - x2 == 0) {
			vertical = true;
			m = 0;
			b = x1;
		} else {
			vertical = false;
			m = (y1 - y2) / (x1 - x2);
			b = y1 - (m * x1);
		}
	}

	public LineR2(PointR2 p1, PointR2 p2) throws Exception {
		x1 = p1.getX();
		y1 = p1.getY();
		x2 = p2.getX();
		y2 = p2.getY();
		this.p1 = p1;
		this.p2 = p2;
		if (x1 == x2 && y1 == y2) {
			throw new Exception("Can't make a line out of one point.");
		}
		if (x1 - x2 == 0) {
			vertical = true;
			m = 0;
			b = x1;
		} else {
			vertical = false;
			m = (y1 - y2) / (x1 - x2);
			b = y1 - (m * x1);
		}
	}

	public PointR2 intersection(LineR2 l) throws Exception {
		if (intersects(l)) {
			if ((vertical && l.vertical) || (m == 0 && l.m == 0)) {
				return null;
			} else if (vertical) {
				return new PointR2(b, l.equation(b));
			} else if (l.vertical) {
				return new PointR2(l.b, equation(l.b));
			} else {
				double x = (l.b - b) / (m - l.m);
				return new PointR2(x, equation(x));
			}
		} else {
			throw new Exception("Can't find intersection point of lines that don't intersect.");
		}
	}

	public PointR2 intersection(LineSeg l) throws Exception {
		return l.intersection(this);
	}

	public boolean intersects(LineR2 l) {
		return Line2D.linesIntersect(x1, y1, x2, y2, l.x1, l.y1, l.x2, l.y2);
	}

	public boolean intersects(Rectangle r) {
		LineSeg[] segments = LineSeg.rectToLineSegs(r);
		return (intersects(segments[0]) || intersects(segments[1]) || intersects(segments[2])
				|| intersects(segments[3]));
	}

	public boolean intersects(LineSeg l) {
		return l.intersecs(this);
	}

	public double equation(double x) throws Exception {
		if (vertical) {
			throw new Exception("Cannot give one value for y on a vertical line.");
		}
		return m * x + b;
	}

	public boolean containsPoint(PointR2 p) {
		return (Line2D.ptLineDist(x1, y1, x2, y2, p.getX(), p.getY()) == 0);
	}

	public PointR2 endPoint1() {
		return new PointR2(x1, y1);
	}

	public PointR2 endPoint2() {
		return new PointR2(x2, y2);
	}
}
