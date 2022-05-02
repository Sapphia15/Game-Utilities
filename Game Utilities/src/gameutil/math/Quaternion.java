package gameutil.math;



import java.io.Serializable;

import gameutil.math.geom.Point;
import gameutil.math.geom.Tuple;
import gameutil.math.geom.Vector;

public class Quaternion  implements Cloneable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5022511477617034740L;
	Vector v;
	
	public Quaternion(double r,double i,double j,double k) {
		v=new Vector(new double[] {r,i,j,k});
	}
	
	public Quaternion(double r) {
		v=new Vector(new double[] {r,0,0,0});
	}
	
	public Quaternion(Vector v) {
		this.v=new Vector(new double[] {v.getSpds().i(0),v.getSpds().i(1),v.getSpds().i(2),v.getSpds().i(3)});
	}
	
	public Quaternion(double r,Vector v) {
		this.v=new Vector(new double[] {r,v.getSpds().i(0),v.getSpds().i(1),v.getSpds().i(2)});
	}
	
	public Vector Im() {
		return new Vector(new double[] {v.getSpds().i(1),v.getSpds().i(2),v.getSpds().i(3)});
	}
	
	public double Re() {
		return v.getSpds().i(0);
	}
	
	public Quaternion complement() {
		return new Quaternion(Re(),Im().$X$(-1));
	}
	
	public Quaternion $X$(Quaternion q) {
		return new Quaternion(
			Re()*q.Re()-i()*q.i()-j()*q.j()-k()*q.k(),
			Re()*q.i()+i()*q.Re()+j()*q.k()-k()*q.j(),
			Re()*q.j()-i()*q.k()+j()*q.Re()-k()*q.i(),
			Re()*q.k()-i()*q.j()-j()*q.i()+k()*q.Re());
	}
	
	public Quaternion $X$(double d) {
		return new Quaternion(v.$X$(d));
	}
	
	public Quaternion $A$(Quaternion q) {
		return new Quaternion (v.$A$(q.v));
	}
	
	public Quaternion $A$(double d) {
		return new Quaternion (this.Re()+d,this.Im());
	}
	
	public Quaternion $S$(Quaternion q) {
		return new Quaternion (v.$S$(q.v));
	}
	
	public Quaternion $S$(double d) {
		return new Quaternion (this.Re()-d,this.Im());
	}
	
	public Quaternion $D$(Quaternion q) {
		return this.$X$(q.complement()).$X$(q.$X$(q.complement()).Re());
	}
	
	public Quaternion $D$(double d) {
		return new Quaternion(v.$D$(d));
	}
	
	public Quaternion $E$(double d) {
		return ln().$X$(d).exp();
	}
	
	public Quaternion $EL$(Quaternion q) {
		return q.$X$(ln()).exp();
	}
	
	public Quaternion $ER$(Quaternion q) {
		return ln().$X$(q).exp();
	}
	
	public Quaternion ln() {
		Vector im=Im();
		return new Quaternion(Math.log(v.magnitude()),im.$X$(Math.acos(Re()/v.magnitude())/(im.magnitude())));
	}
	
	public Quaternion exp() {
		double a=Math.exp(Re());
		Vector im=Im();
		double theta=im.magnitude();
		return new Quaternion(a*Math.cos(theta),v.$X$(a*Math.sin(theta)/theta));
	}
	
	
	
	public double i() {
		return v.getSpds().i(1);
	}
	
	public double j() {
		return v.getSpds().i(2);
	}
	
	public double k() {
		return v.getSpds().i(3);
	}
	
	public Quaternion negate() {
		return new Quaternion(v.$X$(-1));
	}
	
	public static Quaternion rotate(Quaternion p,Quaternion axis,double angle) {
		Quaternion q=new Quaternion(Math.cos(angle/2),axis.Im().$X$(Math.sin(angle/2)));
		return q.$X$(p).$X$(q.complement());
	}
	
	public Quaternion rotate(Quaternion axis,double angle) {
		Quaternion q=new Quaternion(Math.cos(angle/2),axis.Im().$X$(Math.sin(angle/2)));
		return q.$X$(this).$X$(q.complement());
	}
	
	public boolean equals(double d) {
		return (Re()==d)&&(i()+j()+k()==0);
	}
	
	public boolean equals(Quaternion q) {
		return v.equals(q.v);
	}
	
	public String toString() {
		return Re()+" + "+i()+"i + "+j()+"j + "+"k";
	}
	
}
