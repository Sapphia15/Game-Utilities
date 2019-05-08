package gameutil.g3D;

import gameutil.g1D.Point1D;
import gameutil.g1D.Vector1D;

public class Line3D {
	private Point3D r0;
	private Point3D P;
	private Point3D r;
	private Vector1D v;
	
	/**Constructs a line which exists in 3-space
	 * 
	 * @param r0 the point of origen (t - a measure of distance from this point - is based off of a one dimensional vector with this point as it's origen and r as it's end point)
	 * @param P a point on the line used to find r
	 */
	public Line3D(Point3D r0,Point3D P) {
		this.r0=r0;
		this.P=P;
		double t0=P.getDistance(r0);
		double a=(P.getX()-r0.getX())/t0;
		double b=(P.getY()-r0.getY())/t0;
		double c=(P.getZ()-r0.getZ())/t0;
		r=new Point3D(a,b,c);
		v=new Vector1D(new Point1D(0),new Point1D(r.getDistance(r0)));
	}
	
	/**Returns the point that is at the end of a position vector which is the equivalent to the sum a position vector with an end of r0 and a vector parallel to the line
	 * 
	 * @return
	 */
	public Point3D getR() {
		return new Point3D(r.getX(),r.getY(),r.getZ());
	}
	
	/**Returns the point at the origen of the line (where t=0), the first point used to initialize the object
	 * 
	 * @return
	 */
	public Point3D getR0() {
		return new Point3D(r0.getX(),r0.getY(),r0.getZ());
	}
	
	/**Returns the one dimensional vector which begins at point r0 and ends at point r (points in the direction of r)
	 * 
	 * @return
	 */
	public Vector1D getV() {
		return v;
	}
	
	/**Returns the second point on the line input during initialization
	 * 
	 * @return
	 */
	public Point3D getP() {
		return new Point3D(P.getX(),P.getY(),P.getZ());
	}
	
	/**Returns the point on the line at a distance of <code>t</code>
	 * 
	 * @param t the distance from the r0 (polarity dependent on the one dimensional vector v)
	 * @return
	 */
	public Point3D equation(double t) {
		double x=r0.getX()+t*r.getX();
		double y=r0.getY()+t*r.getY();
		double z=r0.getZ()+t*r.getZ();
		return new Point3D(x,y,z);
	}
	
	public boolean containsPoint(Point3D p) {
		 double t=p.getDistance(r0);
		 if (equation(t).equals(p)||equation(-1*t).equals(p)) {
			 return true;
		 }
		 return false;
	}
	
	public double getTOfPoint(Point3D p) {
		if (containsPoint(p)) {
			if (equation(t).equals(p)) {
				return p.getDistance(r0);
			else {
				return -1*p.getDistance(r0);
			}
		}
	}
	
	
}
