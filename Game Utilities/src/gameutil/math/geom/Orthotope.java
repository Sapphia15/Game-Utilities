package gameutil.math.geom;

import gameutil.math.geom.g2D.PointR2;
import gameutil.text.Console;
import gameutil.math.geom.g1D.*;

public class Orthotope extends Figure{
	protected Point p1;
	protected Point p2;
	
	/**Creates an orthotope that goes from p1 to p2 (the p1 and p2 properties will be set to the points with the lowest x,y,z,w,... values and largest x,y,z,w,... values respectively)
	 * 
	 * @param p1
	 * @param p2
	 */
	public Orthotope(Point p1, Point p2) {
		int dims=p1.tuple.n();
		
		if (p2.tuple.n()>dims) {
			dims=p2.tuple.n();
		}
		Vector v1=new Vector(Tuple.origin(dims));
		Vector v2=new Vector(Tuple.origin(dims));
		for (int i=0;i<dims;i++) {
			Tuple t1=new Tuple(i+1);
			Tuple t2=new Tuple(i+1);
			double p1val=p1.tuple.i(i);
			double p2val=p2.tuple.i(i);
			
			if (p1val<p2val) {
				//if p1 is has the lowest value in the i dimension use it for the lowest value vector
				//(the tuples will be added to the appropriate vectors later)
				t1.set(i, p1val);
				//set the largest value to the value of the other point
				t2.set(i, p2val);
			} else {
				//if p2 is has the lowest value in the i dimension use it for the lowest value vector
				//(the tuples will be added to the appropriate vectors later)
				t1.set(i, p2val);
				//set the largest value to the value of the other point
				t2.set(i, p1val);				
			}
			//add tuples to vectors so that v1 eventually becomes a vector with minimum x,y,z,w,... values
			//and v2 eventually becomes a vector with the maximum x,y,z,w,... values of the hypervoxel
			
			v1=v1.$A$(new Vector(t1));
			v2=v2.$A$(new Vector(t2));
			
			
		}
		
		//set p1 to the most left, most low, most inner, most kata, etc. point on the hypercube (also a vertex)
		this.p1=new Point(v1);
		//set p2 to the most right, most high, most outer, most ana, etc. point on the hypercube (which is also a vertex)
		this.p2=new Point(v2);
		
	}
	
	/**creates a hypercube with the dimensionality of the significant basis magnitudes of <code>p1</code> with <code>p1</code> at it's most positive (upper left ana etc.) vertex and a length of <code>length</code>
	 * 
	 * (a hypervoxel is a nonoriented orthotope with sides of equal length in each dimension; in other words, a non oriented hypercube) 
	 * *hypercubes are a special type of orthotope
	 * @param p1
	 * @param length
	 */
	protected Orthotope(Point p1, double length) {
		this.p1=p1;
		this.p2=new Point(p1.tuple.$A$(length));
	}
	
	public boolean contains(Point p) {
		//p1 has the same dimensionality as p2 so we can use either to determine dims of hypercube
		int dims=p1.tuple.n();
		for (int i=0; i<dims;i++) {
			double p1val=p1.tuple.i(i);
			double p2val=p2.tuple.i(i);
			double pval=p.tuple.i(i);
			//kind of like rectangle or voxel checks for containing points but in however many dimensions the hypercube is in
			//this criteria must be met for the point to be contained in the hypercube so return false if it is not met.
			//also nice and easy because it's not oriented
			if (!(p1val<=pval&&pval<=p2val)) {
				return false;
			}
		}
		int extraDims=p.tuple.n()-dims;
		for(int i=0; i<extraDims; i++) {
			//0 is the default value for all unassigned tuple values so if p has more dimensions but is at 0 in all of those dimensions it will still be contained in the hypercube
			//in other words, if the converse is true then return false
			if (p1.tuple.i(dims+i)!=p.tuple.i(dims+i)) {
				return false;
			}
		}
		//all criteria were met so the point is contained within the hypercube
		return true;
	}
	
	public boolean intersects(Point p) {
		return contains(p);
	}
	
	@Deprecated
	/**NOT FUNCTIONAL!
	 * 
	 * @param l
	 * @return
	 */
	public Figure intersection(Line l) {
		//TODO
		
		//intersection could be a line segment or a point
		return Figure.SPACE;
	}
	
	public boolean intersects(Orthotope o) {
		int dims=p1.tuple.n();
		if (p2.tuple.n()>dims) {
			dims=p2.tuple.n();
		}
		if (o.p1.tuple.n()>dims) {
			dims=o.p1.tuple.n();
		}
		if (o.p2.tuple.n()>dims) {
			dims=o.p2.tuple.n();
		}
		
		for (int i=0;i<dims;i++) {
			if (!new LineSeg1D(p1.tuple.i(i),p2.tuple.i(i)).intersects(new LineSeg1D(o.p1.tuple.i(i),o.p2.tuple.i(i)))) {
				//if any of the one dimensional line segments don't intersect then the orthotopes don't intersect
				return false;
			}
		}
		
		//all of the one dimensional line segments intersect so the orthotopes intersect note that this algorithm only works for non oriented orthotopes (which is what this class is)
		return true;
	}
	
	public boolean contains(Orthotope o) {
		int dims=p1.tuple.n();
		if (p2.tuple.n()>dims) {
			dims=p2.tuple.n();
		}
		if (o.p1.tuple.n()>dims) {
			dims=o.p1.tuple.n();
		}
		if (o.p2.tuple.n()>dims) {
			dims=o.p2.tuple.n();
		}
		
		for (int i=0;i<dims;i++) {
			if (!new LineSeg1D(p1.tuple.i(i),p2.tuple.i(i)).contains(new LineSeg1D(o.p1.tuple.i(i),o.p2.tuple.i(i)))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**returns the corner with the lowest individual components (e.g. the lower left corner for a 2D orthotope and the lower left bottom corner for a 3D orthope etc.)
	 * 
	 * @return
	 */
	public Point getPos() {
		return p1.clone();
	}
	
	/**Moves the lower, leftmost, bottom, katamost, etc. vertex to the specified point
	 * 
	 * @param p
	 */
	public void setPos(Point p) {
		Tuple delta=p2.tuple.$S$(p1.tuple);//side lengths
		p1=p;
		p2=new Point(p.tuple.$A$(delta));
	}
	
	/**Sets the center to the specified point
	 * 
	 * @param p
	 */
	public void setCenter(Point p) {
		Tuple delta=p2.tuple.$S$(p1.tuple).$D$(2);//half the side lengths
		p1=new Point(p.tuple.$S$(delta));
		p2=new Point(p.tuple.$A$(delta));
	}
}
