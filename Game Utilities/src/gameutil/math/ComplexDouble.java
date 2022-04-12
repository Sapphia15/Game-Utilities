package gameutil.math;

import gameutil.math.geom.g2D.PointR2;

public class ComplexDouble {
	
	static int expItr=100;
	private double a;
	private double b;
	private static double ln2=Math.log(2);
	
	public ComplexDouble(double real,double imaginary) {
		this.a=real;
		this.b=imaginary;
	}
	
	public ComplexDouble plus(ComplexDouble d) {
		return new ComplexDouble(d.a+this.a,d.b+this.b);
	}
	
	public ComplexDouble plus(double d) {
		return new ComplexDouble(d+this.a,this.b);
	}
	
	public ComplexDouble minus(ComplexDouble d) {
		return new ComplexDouble(this.a-d.a,this.b-d.b);
	}
	
	public ComplexDouble minus(double d) {
		return new ComplexDouble(this.a-d,this.b);
	}
	
	public ComplexDouble times(ComplexDouble d) {
		return new ComplexDouble(d.a*this.a-d.b*this.b,d.b*this.a+d.a*this.b);
	}
	
	public ComplexDouble times(double d) {
		return new ComplexDouble(d*this.a,d*this.b);
	}
	
	public ComplexDouble $D$(ComplexDouble d) {
		return this.times(new ComplexDouble(d.a,-1*d.b)). $D$ (Math.pow(d.a, 2)+Math.pow(d.b, 2));
	}
	
	public ComplexDouble $D$(double d) {
		return new ComplexDouble(this.a/d,this.b/d);
	}
	
	public ComplexDouble pow(int pow) {//double powers coming soon!
		ComplexDouble answer=this;
		boolean negativePow=false;
		if (pow==0) {
			return new ComplexDouble(1,0);
		} else if (pow<1) {
			negativePow=false;
			pow=-pow;
		}
		for (int i=1; i<pow;i++) {
			answer=answer.times(answer);
		}
		if (negativePow) {
			answer=new ComplexDouble(1,0). $D$ (answer);
		}
		return answer;
	}
	
	public ComplexDouble pow(ComplexDouble d) {
		
	}
	
	public ComplexDouble exp() {
		double mag=Math.exp(a);
		return new ComplexDouble(Math.cos(b)*mag,Math.sin(b)*mag);
		/*
		 * Too slow compared to built in trig methods
		//horner's method with boost
		double k=Math.floor(a/ln2);
		double a=this.a-k*ln2;
		ComplexDouble ans=new ComplexDouble(1,0);
		for (int i=expItr;i>0;i--) {
			ans=ans.times(new ComplexDouble(a/i,b/i)).plus(1);
		}
		return ans.times(Math.pow(2, k));
		
		//horner's method
		ComplexDouble ans=new ComplexDouble(1,0);
		for (int i=expItr;i>0;i--) {
			ans=ans.times(new ComplexDouble(a/i,b/i)).plus(1);
		}
		return ans;*/
	}
	
	
	public double getMagnitude() {
		return Math.sqrt(a*a+b*b);
	}
	
	public static void setExpIterations(int i) {
		if (i>0) {
			expItr=i;
		}
	}
	
	public double R() {
		return a;
	}
	
	public double I() {
		return b;
	}
	
	public PointR2 getPoint() {
		return new PointR2(a,b);
	}
}
