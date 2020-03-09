package gameutil.geom;

public abstract class Figure {
	//protected int p; //dimensionality
	//empty space
	public static final Figure SPACE=new Figure() {};
	
	public Figure(/*int p*/) {
		/*if (p<0) {
			p=0;//dimensionality is always positive
		}
		this.p=p;*/
		// TODO Auto-generated constructor stub
	}
	
	public final Figure intersection(Figure f) {return SPACE;}
	
	public final boolean instersects(Figure f) {return false;}

}
