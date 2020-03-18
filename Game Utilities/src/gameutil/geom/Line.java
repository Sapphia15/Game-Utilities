package gameutil.geom;

import java.awt.geom.Line2D;

import gameutil.geom.g2D.LineOverlapException;
import gameutil.geom.g2D.LineR2;
import gameutil.geom.g2D.NoIntersectionException;
import gameutil.geom.g2D.OutsideOfDomainOrRangeException;

public class Line extends Figure{
	private Vector P1;
	private Vector P2;
	private Vector v;//the vector parallel to the line
	
	/**initializes a line that intersects the tails of the input vectors
	 * 
	 * @param v1
	 * @param v2
	 */
	public Line(Vector v1,Vector v2){
		this.P1=v1;
		this.P2=v2;
		v=P1. $S$ (P2);
	}
	
	public boolean intersects(Point p) {
		return contains(p);
	}
	
	//Test this
	public boolean intersects(Line l) {
		
		int dims;
		//System.out.println("v.n: "+v.n());
		//System.out.println("l.v.n: "+l.v.n());
		if (l.v.n()>v.n()) {
			//if the other line has more dimensions set number of dimensions to the dimensions that the other line exists in.
			dims=l.v.n();
		} else {
			//other wise set the number of dimensions to the dimensions that the vector parallel to this line exists in (if v.n>p.n then v has higher dimensions and if v.n==p.n then they have equal dimensions)
			dims=v.n();
		}
		
		//create a list of all dimensional velocities for this line
		LineR2[] dimVs=new LineR2[dims];
				
		for (int i=0; i<dimVs.length;i++) {
			dimVs[i]=new LineR2(v.getSpds().i(i),P2.getSpds().i(i));
		}
		
		//create a list of all dimensional velocities for the other line
		LineR2[] dimVsl=new LineR2[dims];
		
		//System.out.println(dimVsl.length);
		for (int i=0; i<dimVsl.length;i++) {
			dimVsl[i]=new LineR2(l.v.getSpds().i(i),l.P2.getSpds().i(i));
		}
		
		for (int i=0; i<dimVs.length;i++) {
			if (!dimVs[i].intersects(dimVsl[i])) {
				//lines don't intersect
				return false;
			}
			//System.out.println(dimVs[i].intersects(dimVsl[i]));
		}
		
		//all tests passed successfully so return true
		return true;
	}
	
	public Point getIntersectionPoint(Line l) throws NoIntersectionException, LineOverlapException {
		int dims;
		//System.out.println("v.n: "+v.n());
		//System.out.println("l.v.n: "+l.v.n());
		if (l.v.n()>v.n()) {
			//if the other line has more dimensions set number of dimensions to the dimensions that the other line exists in.
			dims=l.v.n();
		} else {
			//other wise set the number of dimensions to the dimension that the vector parallel to this line exists in (if v.n>p.n then v has higher dimensions and if v.n==p.n then they have equal dimensions)
			dims=v.n();
		}
		
		//create a list of all dimensional velocities for this line
		LineR2[] dimVs=new LineR2[dims];
				
		for (int i=0; i<dimVs.length;i++) {
			dimVs[i]=new LineR2(v.getSpds().i(i),P2.getSpds().i(i));
		}
		
		//create a list of all dimensional velocities for the other line
		LineR2[] dimVsl=new LineR2[dims];
		
		//System.out.println(dimVsl.length);
		for (int i=0; i<dimVsl.length;i++) {
			dimVsl[i]=new LineR2(l.v.getSpds().i(i),l.P2.getSpds().i(i));
		}
		
		//Make a variable for the return coords
		double[] coords=new double[dims];
		
		for (int i=0; i<dimVs.length;i++) {
			coords[i]=dimVs[i].intersection(dimVsl[i]).getY()+dimVs[i].intersection(dimVsl[i]).getX();
			//System.out.println(dimVs[i].intersects(dimVsl[i]));
		}
		return new Point(new Tuple(coords));
	}
	
	//Functional!
	public boolean contains(Point p){
		
		
		int dims;
		if (p.tuple.n()>v.n()) {
			//if the point has more dimensions set number of dimensions to the dimensions that the point exists in.
			dims=p.tuple.n();
		} else {
			//other wise set the number of dimensions to the dimension that the vector parallel to this line exists in (if v.n>p.n then v has higher dimensions and if v.n==p.n then they have equal dimensions)
			dims=v.n();
		}
		
		//create a list of all dimensional velocities
		LineR2[] dimVs=new LineR2[dims];
		double tValue = 0;
		for (int i=0; i<dimVs.length;i++) {
			dimVs[i]=new LineR2(v.getSpds().i(i),P1.getSpds().i(i));
		}
		
		int state=0;
		//check if a line that contains the specified point intersects this line at that point
		boolean intersects=true;
		for (int i=0; i<dimVs.length;i++) {
			switch(state) {
				case 0://find tValue
					try {
						tValue=dimVs[i].xFromY(p.tuple.i(i));
						state++;
					} catch (OutsideOfDomainOrRangeException e) {
						return false; //value of p[i] not contained on line
					} catch (Exception e) {
						
					}
				break;
				case 1://check the rest of the dimensional velocities to see that they match the t value or contain the t value
					try {
						double tToCheck=dimVs[i].xFromY(p.tuple.i(i));
						if (tToCheck!=tValue) {
							return false; //dimensional velocity does not have the same t value as the rest so does not contain the point
						}
					} catch (OutsideOfDomainOrRangeException e) {
						return false; //value of p[i] not contained on line
					} catch (Exception e) {
						//dimensional velocity contains t value
					}
				break;
			}
		}
		
		return intersects;
	}
	
	
	
	public Vector equation(double t){
		return (v. $X$ (t)). $A$ (P1);
	}
	
	public Figure intersection(Line l) {
		int dims;
		//System.out.println("v.n: "+v.n());
		//System.out.println("l.v.n: "+l.v.n());
		
		if (l.v.equals(v)&&l.P1.equals(P1)) {
			return clone();//lines are the same so return
		}
		
		if (l.v.n()>v.n()) {
			//if the other line has more dimensions set number of dimensions to the dimensions that the other line exists in.
			dims=l.v.n();
		} else {
			//other wise set the number of dimensions to the dimension that the vector parallel to this line exists in (if v.n>p.n then v has higher dimensions and if v.n==p.n then they have equal dimensions)
			dims=v.n();
		}
		
		//create a list of all dimensional velocities for this line
		LineR2[] dimVs=new LineR2[dims];
				
		for (int i=0; i<dimVs.length;i++) {
			dimVs[i]=new LineR2(v.getSpds().i(i),P2.getSpds().i(i));
		}
		
		//create a list of all dimensional velocities for the other line
		LineR2[] dimVsl=new LineR2[dims];
		
		//System.out.println(dimVsl.length);
		for (int i=0; i<dimVsl.length;i++) {
			dimVsl[i]=new LineR2(l.v.getSpds().i(i),l.P2.getSpds().i(i));
		}
		
		//Make a variable for the return coords
		double[] coords=new double[dims];
		
		for (int i=0; i<dimVs.length;i++) {
			try {
				coords[i]=dimVs[i].intersection(dimVsl[i]).getY()+dimVs[i].intersection(dimVsl[i]).getX();
			} catch (NoIntersectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Figure.SPACE;
			} catch (LineOverlapException e) {
				//NEED TO FIGURE THIS PART OUT
				e.printStackTrace();
			}
			//System.out.println(dimVs[i].intersects(dimVsl[i]));
		}
		return new Point(new Tuple(coords));
	}
	
	public Line clone() {
		return new Line(P1.clone(),P2.clone());
	}
}
