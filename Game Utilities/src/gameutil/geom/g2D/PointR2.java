package gameutil.geom.g2D;

import java.awt.geom.Point2D;

public class PointR2 {
	private double x;
	private double y;

	public PointR2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(PointR2 p) {
		return Point2D.distance(x, y, p.x, p.y);
	}

	public double distanceSq(PointR2 p) {
		return Point2D.distanceSq(x, y, p.x, p.y);
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
