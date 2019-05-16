package gameutil.geom;

public class Scalar {
	public static final Scalar R=new Scalar(Constant.R);
	public static final Scalar i=new Scalar(Constant.i);
	public static final Scalar NaS=new Scalar(Constant.NaS);
	public static final Scalar Infinity=new Scalar(Constant.inf);
	public static final Scalar NegInfinity=new Scalar(Constant.negInf);
	public static final Scalar Zero=new Scalar(0,0);
	private enum Constant{R,i,NaS,inf,negInf};
	private Constant c;
	
	private double valD;
	private long valL;
	
	public Scalar(double val) {
		c=null;
		valD=val;
		valL=0;
		if (valD>=1) {
			valL+=Math.floor(valD);
			valD-=Math.floor(valD);
		} else if (valD<=-1) {
			valL+=Math.ceil(valD);
			valD-=Math.ceil(valD);
		}
	}
	
	public Scalar(long val) {
		c=null;
		valD=0;
		valL=val;
	}
	
	public Scalar(long valL,double valD) {
		c=null;
		this.valD=valD;
		this.valL=valL;
		if ()
		if (this.valD>=1) {
			this.valL+=Math.floor(this.valD);
			this.valD-=Math.floor(this.valD);
		} else if (this.valD<=-1) {
			valL+=Math.ceil(this.valD);
			valD-=Math.ceil(this.valD);
		}
	}
	
	public Scalar $A$(Scalar s) {
		return new Scalar(valL+s.valL,valD+s.valD);
	}
	
	public Scalar $S$(Scalar s) {
		return new Scalar(valL-s.valL,valD-s.valD);
	}
	
	public Scalar $X$(Scalar s) {
		if (this.equals(Infinity)){
			if (s. _L_ (Zero)){
				return Infinity;
			} else if (s. _S_(Zero)){
				return NegInfinity;
			} else if (s.equals(Zero)){
				
			}
		}
		return new Scalar(valL*s.valL,valD*s.valD);
	}
	
	public Scalar $D$(Scalar s) {
		if (this .$X$ (s) .equals (Zero)) {
			return R;
		} else if (this.equals(Infinity))
	}
	
	public boolean _L_(Scalar s) {
		if (s.equals(Infinity)){
			return false;
		} else if (equals(Infinity)){
			return true;
		} else if (s.equals(R)){
			return false;
		} else if (equals(R)){
			return true;
		}
		if (valL>s.valL) {
			return true;
		} else if (valL<s.valL) {
			return false;
		} else if (valD>s.valD) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean _S_(Scalar s) {
		if (valL<valL) {
			return true;
		} else if (valL<s.valL) {
			return false;
		} else if (valD>s.valD) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Scalar s) {
		if (c==null&&s.c==null) {
			return (valL==s.valL&&valD==s.valD);
		}
		return (c==s.c);
	}
	
	private Scalar(Constant c) {
		this.c=c;
	}
	
	public String toString() {
		return String.valueOf(valL)+String.valueOf(valD);
	}
}
