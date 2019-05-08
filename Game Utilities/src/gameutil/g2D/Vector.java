package gameutil.g2D;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Vector extends LineSeg{
	public Vector(Point base,Point end) throws Exception{
		super(base.getX(),base.getY(),end.getX(),end.getY());
	}
	
	public Point base(){
		return new Point(x1,y1);
	}
	
	public Point end(){
		return new Point(x2,y2);
	}
	
	public Point intersection(Rectangle r){
		if (intersects(r)){
			if (r.contains(base().getX(),base().getY()) && r.contains(end().getX(),end().getY())){
				return null;
			} else if (r.contains(base().getX(),base().getY())){
				LineSeg[] segments=LineSeg.rectToLineSegs(r);
				for (LineSeg l:segments){
					if (intersects(l)){
						try {
							return intersection(l);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				LineSeg[] segments=LineSeg.rectToLineSegs(r);
				ArrayList<Point> points=new ArrayList<>();
				for (LineSeg l:segments){
					if (intersects(l)){
						try {
							points.add(intersection(l));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Point closest=points.get(0);
				for (Point p:points){
					if (p.distance(base())>closest.distance(base())){
						closest=p;
					}
				}
				return closest;
			}
			
			
		}
		return null;
	}
}
