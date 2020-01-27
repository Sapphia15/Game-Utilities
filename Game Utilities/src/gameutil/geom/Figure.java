package gameutil.geom;

public abstract class Figure {
	int p; //dimensionality
	
	public Figure(int p) {
		if (p>=0) {
			p=0;//dimensionality is always positive
		}
		this.p=p;
		// TODO Auto-generated constructor stub
	}
	
	public abstract Figure intersection(Figure f);

}
