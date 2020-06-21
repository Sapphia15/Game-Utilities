package gameutil.geom.g2D;

public class RayR2 extends LineR2{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public static final int ID=7;
	
	public enum LIMIT_TYPE{min,max,exact};
	
	protected final LIMIT_TYPE xAxiom;
	protected final LIMIT_TYPE yAxiom;
	
	public RayR2(PointR2 tail,PointR2 head) throws Exception {
		super(tail,head);
		if (tail.getX()<head.getX()) {
			xAxiom=LIMIT_TYPE.min;
		} else if (tail.getX()>head.getX()) {
			xAxiom=LIMIT_TYPE.max;
		} else {
			xAxiom=LIMIT_TYPE.exact;
		}
		
		if (tail.getY()<head.getY()) {
			yAxiom=LIMIT_TYPE.min;
		} else if (tail.getY()>head.getY()) {
			yAxiom=LIMIT_TYPE.max;
		} else {
			yAxiom=LIMIT_TYPE.exact;
		}
	}
	
	
	public boolean intersects(LineR2 l) {
		return contains(super.intersection(l));
	}
	
	public boolean intersects(PointR2 p) {
		return contains(p);
	}
	
	public boolean contains(PointR2 p) {
		boolean intersects=super.contains(p);
		switch(xAxiom) {
			case min:
				if (p.getX()<p1.getX()){
					return false;
				}
			break;
			case max:
				if (p.getX()>p1.getX()) {
					return false;
				}
			break;
			case exact:
				if (p.getX()!=p1.getX()) {
					return false;
				}
			break;
		}
		
		switch(yAxiom) {
			case min:
				if (p.getY()<p1.getY()){
					return false;
				}
			break;
			case max:
				if (p.getY()>p1.getY()) {
					return false;
				}
			break;
			case exact:
				if (p.getY()!=p1.getY()) {
					return false;
				}
			break;
		}
	
		return intersects;
	}
	
	public FigureR2 intersection(LineR2 l) {
		FigureR2 intersection=super.intersection(l);
		if (intersection.ID()==PointR2.ID&&contains((PointR2)intersection)) {
			return intersection;
		} else if (intersection.ID()==LineR2.ID) {
			return clone();
		} else {
			return FigureR2.SPACE;
		}
	}
	
	public FigureR2 intersection(PointR2 p) {
		if (contains(p)) {
			return p.clone();
		} else {
			return FigureR2.SPACE;
		}
	}
	
	
	
	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public RayR2 clone() {
		try {
			return new RayR2(p1,p2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
