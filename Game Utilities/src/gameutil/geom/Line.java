package gameutil.geom;

public class Line {
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
	
	public boolean intersects(Line l) {
		return false;
	}
	
	public boolean getIntersectionPoint() {
		
		return false;
	}
	
	public boolean containsPoint(Point p){
		
		int indexToFill=0;
		Tuple zeroIndexes=v.end().containsAtIndexes(0);
		
		if (zeroIndexes.n()==v.n()) {
			System.err.println("WARNING: a line has no change in direction in all dimensions");
			return true;
		}
		
		boolean indexInvalid=true;
		while(indexInvalid) {
			if (zeroIndexes.contains(indexToFill)) {
				indexToFill++;
				if (indexToFill>v.n()) {
					System.err.println("Dude. Theres definitely a problem. Somehow the program escaped the 'line has no change in direction in all dimensions'. Anyway, that's the way it is so... yeah. Good luck dude.");
					return true;
				}
			}
		}
		
		Vector T=new Vector(p). $S$ (P1). $D$ (v);
		
		if (T.end().equals(new Tuple(T.end().n(),T.end().i(1)))) {
			return true;
		}
		
		return false;
	}
	
	public Vector equation(double t){
		return (v. $X$ (t)). $A$ (P1);
	}
}
