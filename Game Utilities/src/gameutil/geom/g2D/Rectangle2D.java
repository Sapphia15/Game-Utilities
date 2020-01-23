package gameutil.geom.g2D;

import java.awt.*;

public class Rectangle2D {
	private PointR2 center;
	private double width;
	private double height;
	
	
	/**Creates a rectangle centered around the point (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle2D(double x,double y,double width,double height) {
		center = new PointR2(x,y);
		this.width=width;
		this.height=height;
	}
	
	public Rectangle2D(Rectangle r) {
		
	}
	
	public PointR2 getCenter() {
		try {
			return (PointR2) center.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean setPos(double x,double y) {
		center.move(x, y);
		return true;
	}
	
	public boolean setPos(PointR2 p) {
		try {
			center=(PointR2) p.clone();
			return true;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean contains(PointR2 p) {
		return p.getX()>=getMinX()&&p.getX()<=getMaxX()&&p.getY()>=getMinY()&&p.getY()<=getMaxY();
	}
	
	public boolean intersects(Rectangle r) {
		if (getMaxX()<r.getMinX()) {
			return true;
		}
		//finish this
	}
	
	
	
	public double getMaxX() {
		return center.getX()+width/2;
	}
	
	public double getMinX() {
		return center.getX()-width/2;
	}
	
	public double getMaxY() {
		return center.getY()+height/2;
	}
	
	public double getMinY() {
		return center.getY()-height/2;
	}

}
