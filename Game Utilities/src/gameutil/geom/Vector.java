package gameutil.geom;

public class Vector extends Figure{
	private double magnitude;
	private Tuple spds;
	
	public Vector(double[] end){
		//super(end.length);
		setSpds(new Tuple(end));
	}
	
	public Vector(Tuple end){
		//super(end.n());
		setSpds(end);
	}
	
	public Vector(Point end) {
		//super(end.tuple.n());
		setSpds(end.tuple);
	}
	
	public Vector(Point stem,Point end) {
		setSpds(end.tuple. $S$ (stem.tuple));
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
		return new Vector(spds .$A$ (v.getSpds()));
	}
	
	public Vector $A$(double s){
		return new Vector(spds .$X$ (s));
	}
	
	public Vector $S$(Vector v){
		return new Vector(spds .$S$ (v.spds));
	}
	
	public Vector $S$(double s){
		return new Vector(spds .$S$ (s));
	}
	
	public Vector $X$(Vector v){
		return new Vector(spds .$X$ (v.getSpds()));
	}
	
	public Vector $X$(double s){
		return new Vector(spds .$X$ (s));
	}
	
	public Vector $D$(Vector v){
		return new Vector(spds .$D$ (v.spds));
	}
	
	public Vector $D$(double s){
		return new Vector(spds .$D$ (s));
	}
	
	public Vector $E$(double exp){
		return new Vector(spds .$E$(exp));
	}
	
	public double $DOT$(Vector v) {
		return spds.$DOT$(v.spds);
	}
	
	public Vector project(Vector v) {
		return v. $X$ (this. $DOT$ (v) / v. $DOT$ (v));
	}
	
	public Vector sq(){
		return $X$(this);
	}
	
	public int n(){
		return spds.n();
	}
	
	public Vector clone() {
		return new Vector(spds.clone());
	}
	
}
