package gameutil;

import java.awt.geom.Line2D;

public class LineSeg extends Line {
	public LineSeg(double x1,double y1,double x2,double y2) throws Exception{
		super(x1,y1,x2,y2);
	}
	
	public LineSeg(Point p1,Point p2) throws Exception{
		super(p1,p2);
	}
	
	public boolean intersecs(Line l){
		if (super.intersects(l)){
			Point intsct=null;
			try {
				intsct=super.intersection(l);
			} catch (Exception e) {return false;}
			if (intsct==null){
				return true;
			} else if (containsPoint(intsct)){
				return true;
			}
		}
		return false;
	}
	
	public boolean intersecs(LineSeg l){
		if (super.intersecs(l)){
			Point intsct=null;
			try {
				intsct=super.intersection(l);
			} catch (Exception e) {return false;}
			if (intsct==null&&(l.containsPoint(p1)||l.containsPoint(p2)||containsPoint(l.p1)||containsPoint(l.p2))){
				return true;
			} else if (containsPoint(intsct)&&l.containsPoint(intsct)){
				return true;
			}
		}
		return false;
	}
	
	/*public Point intersection(Line l) throws Exception{
		if (intersects(l)){
			if ((vertical&&l.vertical)){
				return null;
			} else if (vertical) {
				return new Point(b,l.equation(b));
			} else if (l.vertical){
				return new Point(l.b,equation(l.b));
			} else {
				double x=(l.b-b)/(m-l.m);
				return new Point(x,equation(x));
			}
		} else {
			throw new Exception("Can't find intersection point of lines that don't intersect.");
		}
	}
	
	public Point intersection(LineSeg l) throws Exception{
		if (intersects(l)){
			if ((vertical&&l.vertical)){
				return null;
			} else if (vertical) {
				return new Point(b,l.equation(b));
			} else if (l.vertical){
				return new Point(l.b,equation(l.b));
			} else {
				double x=(l.b-b)/(m-l.m);
				return new Point(x,equation(x));
			}
		} else {
			throw new Exception("Can't find intersection point of lines that don't intersect.");
		}
	}*/
	
	public boolean containsPoint(Point p){
		return (Line2D.ptSegDist(x1, y1, x2, y2, p.getX(), p.getY())==0);
	}
	
}
