package gameutil.geom;

public class Line {
	private Vector v1;
	private Vector v2;
	
	/**initializes a line that intersects the tails of the input vectors
	 * 
	 * @param v1
	 * @param v2
	 */
	public Line(Vector v1,Vector v2){
		this.v1=v1;
		this.v2=v2;
	}
}
