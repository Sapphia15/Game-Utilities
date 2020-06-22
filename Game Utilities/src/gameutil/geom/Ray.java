package gameutil.geom;

public class Ray extends Line{
	
	enum AXIOM {negative,zero,positive};
	
	/**initializes a ray that extends from the position vector <code>v1</code> and has a unit direction vector
	 * 
	 * @param v1
	 * @param v2
	 */
	public Ray(Point v1, Point v2) {
		super(v1, v2);
		// TODO Auto-generated constructor stub
	}
	
	/**initializes a ray that extends from point <code> p </code> and has a unit direction vector <code> v </code>
	 * 
	 * @param v
	 * @param p
	 */
	public Ray(Vector v, Point p) {
		super(p,new Point(v));
	}
	
	//need to test
	public boolean contains(Point p) {
		if (super.contains(p)) {
			AXIOM[] axioms=getAxioms();
			for (int i=0;i<axioms.length;i++) {
				double point=p.tuple.i(i);
				double start=P1.getSpds().i(i);
				switch (axioms[i]) {
					case negative:
						if (point>start) {
							return false;
						}
					break;
					case positive:
						if (point<start) {
							return false;
						}
					break;
					case zero:
						if (point!=start) {
							return false;
						}
					break;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public AXIOM[] getAxioms() {
		int dims;
		//System.out.println("v.n: "+v.n());
		//System.out.println("l.v.n: "+l.v.n());
		if (v.n()>P1.n()) {
			//if the other line has more dimensions set number of dimensions to the dimensions that the other line exists in.
			dims=v.n();
		} else {
			//other wise set the number of dimensions to the dimensions that the vector parallel to this line exists in (if v.n>p.n then v has higher dimensions and if v.n==p.n then they have equal dimensions)
			dims=P1.n();
		}
		
		AXIOM[] axioms=new AXIOM[dims];
		
		for (int i=0; i<dims; i++) {
			double start=P2.getSpds().i(i);
			double slope=v.getSpds().i(i);
			if (slope<start) {
				axioms[i]=AXIOM.negative;
			} else if (slope>start) {
				axioms[i]=AXIOM.positive;
			} else {
				axioms[i]=AXIOM.zero;
			}
		}
		
		return axioms;
	}

}
