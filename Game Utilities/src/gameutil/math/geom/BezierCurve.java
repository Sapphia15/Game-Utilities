package gameutil.math.geom;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class BezierCurve extends Figure{
	CopyOnWriteArrayList<Point> points;
	
	public BezierCurve(CopyOnWriteArrayList<Point> points) {
		this.points=points;
	}
	
	public BezierCurve(ArrayList<Point> points) {
		this.points=new CopyOnWriteArrayList<>();
		this.points.addAll(points);
	}
	
	public BezierCurve(Point[] points) {
		this.points=new CopyOnWriteArrayList<>();
		for (Point p:points) {
			this.points.add(p);
		}
	}
	
	public Point getPoint(double t) {
		ArrayList<Point> nextLerps=new ArrayList<>();
		ArrayList<Point> oldLerps=new ArrayList<>();
		oldLerps.addAll(points);
		for (int j=0;j<points.size()-1;j++ ) {
			for (int i=0;i<oldLerps.size()-1;i++ ) {
				
				nextLerps.add(oldLerps.get(i).lerp(oldLerps.get(i+1), t));
			}
			oldLerps.clear();
			oldLerps.addAll(nextLerps);
			nextLerps.clear();
		}
		return oldLerps.get(0);
	}
	
	public CopyOnWriteArrayList<Point> getPoints(){
		return (CopyOnWriteArrayList<Point>) points.clone();
	}
	
	public void setPoint(Point p,int index) {
		points.set(index, p);
	}
	
	public Orthotope getBoundingBox() {
		//this will not be a tight bounding box (will add that later) but it will be better than nothing
		Point min=points.get(0);
		Point max=points.get(0);
		for (Point p:points) {
			for (int i=0;i<p.tuple.n();i++) {
				if (p.tuple.i(i)<min.tuple.i(i)) {
					min.tuple.set(i,p.tuple.i(i));
				} else if (p.tuple.i(i)>max.tuple.i(i)) {
					max.tuple.set(i,p.tuple.i(i));
				}
			}
		}
		return new Orthotope(min,max);
	}
	
	public BezierCurve deriv() {
		CopyOnWriteArrayList<Point> dPoints=new CopyOnWriteArrayList<>();
		for (int i=0;i<points.size()-1;i++) {
			dPoints.add(new Point(points.get(i+1).tuple.$S$(points.get(i).tuple).$X$(points.size())));
		}
		return new BezierCurve(dPoints);
	}
}
