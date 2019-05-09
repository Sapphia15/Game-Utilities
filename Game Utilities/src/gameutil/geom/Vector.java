package gameutil.geom;

public class Vector {
	private double magnitude;
	private Tuple spds;
	
	public Vector(Tuple end){
		setSpds(spds);
	}
	
	public Vector(Point end) {
		setSpds(end.tuple);
	}
	
	public double magnitude(){
		return magnitude;
	}

	public Tuple end() {
		return spds;
	}
	
	public void setSpds(Tuple spds) {
		this.spds = spds;
		magnitude=new Point(spds).distanceO();
	}
	
	public Tuple getSpds(){
		return spds;
	}
	
	public Vector $A$(Vector v){
		return new Vector(v.getSpds() .$A$ (spds));
	}
	
	public Vector $A$(double s){
		return new Vector(spds .$X$ (s));
	}
	
	public Vector $S$(Vector v){
		return new Vector(v.getSpds() .$S$ (spds));
	}
	
	public Vector $S$(double s){
		return new Vector(spds .$S$ (s));
	}
	
	public Vector $X$(Vector v){
		return new Vector(v.getSpds() .$X$ (spds));
	}
	
	public Vector $X$(double s){
		return new Vector(spds .$X$ (s));
	}
	
	public Vector $D$(Vector v){
		return new Vector(v.getSpds() .$D$ (spds));
	}
	
	public Vector $D$(double s){
		return new Vector(spds .$D$ (s));
	}
	
	
}
