package gameutil.geom;

public class Scalar {
	public static final Scalar R=new Scalar(Constant.R);
	public static final Scalar i=new Scalar(Constant.i);
	public static final Scalar NaS=new Scalar(Constant.NaS);
	public static final Scalar Infinity=new Scalar(Constant.inf);
	public static final Scalar NegInfinity=new Scalar(Constant.negInf);
	private enum Constant{R,i,NaS,inf,negInf};
	private Constant c;
	
	private double valD;
	private long valL;
	
	public Scalar(double val) {
		c=null;
	}
	
	private Scalar(Constant c) {
		this.c=c;
	}
}
