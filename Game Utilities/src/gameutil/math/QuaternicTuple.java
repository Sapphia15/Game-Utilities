package gameutil.math;

import java.io.Serializable;

import gameutil.math.geom.Tuple;

public class QuaternicTuple  implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2653121865707299910L;
	private Quaternion[] tuple;
	private int n;
	
	/**Instantiates a tuple with the values input.
	 * 
	 * @param values
	 */
	public QuaternicTuple(Quaternion[] values){
		tuple=values;
		n=tuple.length;
	}
	
	/**Instantiates a tuple with the values input.
	 * 
	 * @param values
	 */
	public QuaternicTuple(int[] values){
		tuple=new Quaternion[values.length];
		for (int i=0;i<tuple.length;i++) {
			tuple[i]=new Quaternion(values[i]);
		}
		n=tuple.length;
	}
	
	/**Instantiates a tuple with the values input.
	 * 
	 * @param values
	 */
	public QuaternicTuple(double[] values){
		tuple=new Quaternion[values.length];
		for (int i=0;i<tuple.length;i++) {
			tuple[i]=new Quaternion(values[i]);
		}
		n=tuple.length;
	}
	
	/**Instantiates a quaternic tuple with the real part of a real tuple.
	 * 
	 * @param values
	 */
	public QuaternicTuple(Tuple t){
		tuple=new Quaternion[t.n()];
		for (int i=0;i<tuple.length;i++) {
			tuple[i]=new Quaternion(t.i(i));
		}
		n=tuple.length;
	}
	
	/**Instantiates a quaternic tuple with the real part of a real tuple,and imaginary parts of other real tuples.
	 * 
	 * @param values
	 */
	public QuaternicTuple(Tuple r,Tuple i,Tuple j,Tuple k){
		n=r.n();
		if (i.n()>n) {
			n=i.n();
		}
		if (j.n()>n) {
			n=j.n();
		}
		if (k.n()>n) {
			n=k.n();
		}
		tuple=new Quaternion[n];
		for (int itr=0;itr<tuple.length;itr++) {
			tuple[itr]=new Quaternion(r.i(itr),i.i(itr),j.i(itr),k.i(itr));
		}
	}
	
	/**Instantiates a tuple with a size of <code>n</code> and 0 for all values.
	 * 
	 * @param n
	 */
	public QuaternicTuple(int n){
		tuple=new Quaternion[n];
		this.n=n;
		set(0);
	}
	
	/**Instantiates a tuple with a size of <code>n</code> and <code>val</code> for all values.
	 * 
	 * @param n
	 */
	public QuaternicTuple(int n, double val){
		tuple=new Quaternion[n];
		this.n=n;
		set(val);
	}
	
	/**Instantiates a tuple with a size of <code>n</code> and <code>val</code> for all values.
	 * 
	 * @param n
	 */
	public QuaternicTuple(int n, Quaternion val){
		tuple=new Quaternion[n];
		this.n=n;
		set(val);
	}
	
	/**Adds together two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $A$(Tuple t){
		int n;
		Quaternion[] sum;
		if (this.n>t.n()){
			n=this.n;
			sum=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					sum[i]=tuple[i].$A$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					sum[i]=tuple[i];
				}
			}
		} else {
			n=t.n();
			sum=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					sum[i]=tuple[i].$A$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					sum[i]=new Quaternion(t.i(i));
				}
			}
		}
		return new QuaternicTuple(sum);
	}
	
	/**Adds together two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $A$(QuaternicTuple t){
		int n;
		Quaternion[] sum;
		if (this.n>t.n()){
			n=this.n;
			sum=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					sum[i]=tuple[i].$A$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					sum[i]=tuple[i];
				}
			}
		} else {
			n=t.n();
			sum=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					sum[i]=tuple[i].$A$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					sum[i]=t.i(i);
				}
			}
		}
		return new QuaternicTuple(sum);
	}
	
	/**subtracts a tuple <code>(t)</code> from this tuple. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $S$(Tuple t){
		int n;
		Quaternion[] dif;
		if (this.n>t.n()){
			n=this.n;
			dif=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					dif[i]=tuple[i].$S$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					dif[i]=tuple[i];
				}
			}
		} else {
			n=t.n();
			dif=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					dif[i]=tuple[i].$S$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					dif[i]=new Quaternion(-t.i(i));
				}
			}
		}
		return new QuaternicTuple(dif);
	}
	
	/**subtracts a tuple <code>(t)</code> from this tuple. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $S$(QuaternicTuple t){
		int n;
		Quaternion[] dif;
		if (this.n>t.n()){
			n=this.n;
			dif=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					dif[i]=tuple[i].$S$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					dif[i]=tuple[i];
				}
			}
		} else {
			n=t.n();
			dif=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					dif[i]=tuple[i].$S$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					dif[i]=t.i(i).negate();
				}
			}
		}
		return new QuaternicTuple(dif);
	}
	
	/**Multiplies two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $X$(Tuple t){
		int n;
		Quaternion[] pro;
		if (this.n>t.n()){
			n=this.n;
			pro=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					pro[i]=tuple[i].$X$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					pro[i]=new Quaternion(0);
				}
			}
		} else {
			n=t.n();
			pro=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					pro[i]=tuple[i].$X$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					pro[i]=new Quaternion(0);
				}
			}
		}
		return new QuaternicTuple(pro);
	}
	
	/**Multiplies two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $X$(QuaternicTuple t){
		int n;
		Quaternion[] pro;
		if (this.n>t.n()){
			n=this.n;
			pro=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					pro[i]=tuple[i].$X$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					pro[i]=new Quaternion(0);
				}
			}
		} else {
			n=t.n();
			pro=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					pro[i]=tuple[i].$X$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					pro[i]=new Quaternion(0);
				}
			}
		}
		return new QuaternicTuple(pro);
	}
	
	/**Divides two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $D$(Tuple t){
		int n;
		Quaternion[] quo;
		if (this.n>t.n()){
			n=this.n;
			quo=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					quo[i]=tuple[i].$D$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					System.err.println("WARNING: Dividing by zero!");
					System.err.println("  Time: during $D$");
					System.err.println("  Tuple index: "+i);
					e.printStackTrace();
					quo[i]=tuple[i].$D$(0);
				}
			}
		} else {
			n=t.n();
			quo=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					quo[i]=tuple[i].$D$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					quo[i]=new Quaternion(0);
				}
			}
		}
		return new QuaternicTuple(quo);
	}
	
	/**Divides two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $D$(QuaternicTuple t){
		int n;
		Quaternion[] quo;
		if (this.n>t.n){
			n=this.n;
			quo=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					quo[i]=tuple[i].$D$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					System.err.println("WARNING: Dividing by zero!");
					System.err.println("  Time: during $D$");
					System.err.println("  Tuple index: "+i);
					e.printStackTrace();
					quo[i]=tuple[i].$D$(0);
				}
			}
		} else {
			n=t.n();
			quo=new Quaternion[n];
			for (int i=0; i<n; i++){
				try {
					quo[i]=tuple[i].$D$(t.i(i));
				} catch (IndexOutOfBoundsException e) {
					quo[i]=new Quaternion(0);
				}
			}
		}
		return new QuaternicTuple(quo);
	}
	
	/**Adds <code>d</code> to all significant basis magnitudes (e.g [1 2 7] + 6 = [7 9 14] = [7 9 14 0 0 0 0 0 ...] and not [7 9 14 7 7 7 7 7 7 ...] 
	 * 
	 * @param d
	 * @return
	 */
	public QuaternicTuple $A$(double d){
		QuaternicTuple t=new QuaternicTuple(n,d);
		Quaternion[] dif;
		dif=new Quaternion[n];
		for (int i=0; i<n; i++){
			dif[i]=tuple[i].$A$(t.tuple[i]);
		}
		return new QuaternicTuple(dif);
	}
	
	/**subtracts a tuple <code>(t)</code> from this tuple. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $S$(double d){
		QuaternicTuple t=new QuaternicTuple(n,d);
		Quaternion[] dif;
		dif=new Quaternion[n];
		for (int i=0; i<n; i++){
			dif[i]=tuple[i].$S$(t.tuple[i]);
		}
		return new QuaternicTuple(dif);
	}
	
	/**Multiplies two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $X$(double d){
		QuaternicTuple t=new QuaternicTuple(n,d);
		Quaternion[] pro=new Quaternion[n];
		for (int i=0; i<n; i++){
			pro[i]=tuple[i].$X$(t.tuple[i]);
		}
		return new QuaternicTuple(pro);
	}
	
	/**Divides two tuples. If the tuples are different sizes a tuple the size of the larger will be returned.
	 * 
	 * @param t
	 * @return
	 */
	public QuaternicTuple $D$(double d){
		QuaternicTuple t=new QuaternicTuple(n,d);
		Quaternion[] quo=new Quaternion[n];
		for (int i=0; i<n; i++){
				quo[i]=tuple[i].$D$(t.tuple[i]);
		}
		return new QuaternicTuple(quo);
	}
	
	/**Returns the square of this tuple.
	 * 
	 * @return
	 */
	public QuaternicTuple sq(){
		return $X$(this);
	}
	
	public Quaternion $DOT$(Tuple t) {
		Quaternion val=new Quaternion(0);
		int n=this.n;
		if (t.n()>n) {
			n=t.n();
		}
		for (int i=0;i<n;i++) {
			val=val.$A$(i(i).$X$(t.i(i)));
		}
		return val;
	}
	
	public Quaternion $DOT$(QuaternicTuple t) {
		Quaternion val=new Quaternion(0);
		int n=this.n;
		if (t.n()>n) {
			n=t.n();
		}
		for (int i=0;i<n;i++) {
			val=val.$A$(i(i).$X$(t.i(i)));
		}
		return val;
	}
	
	//TODO create cross product function
	
	/**Returns this tuple to the power of <code>(exp)</code>.
	 * 
	 * @param exp
	 * @return
	 */
	public QuaternicTuple $E$(double exp){
		Quaternion[] pow=new Quaternion[n];
		for (int i=0; i<n;i++){
			pow[i]=tuple[i];
			pow[i]=pow[i].$E$(exp);
		}
		return new QuaternicTuple(pow);
	}
	
	/**Returns this tuple to the left power of <code>(exp)</code>.
	 * 
	 * @param exp
	 * @return
	 */
	public QuaternicTuple $EL$(Quaternion exp){
		Quaternion[] pow=new Quaternion[n];
		for (int i=0; i<n;i++){
			pow[i]=tuple[i];
			pow[i]=pow[i].$EL$(exp);
		}
		return new QuaternicTuple(pow);
	}
	
	/**Returns this tuple to the right power of <code>(exp)</code>.
	 * 
	 * @param exp
	 * @return
	 */
	public QuaternicTuple $ER$(Quaternion exp){
		Quaternion[] pow=new Quaternion[n];
		for (int i=0; i<n;i++){
			pow[i]=tuple[i];
			pow[i]=pow[i].$ER$(exp);
		}
		return new QuaternicTuple(pow);
	}
	
	/**Returns the square root of this tuple.
	 * 
	 * @return
	 */
	public QuaternicTuple sqrt(){
		Quaternion[] pow=new Quaternion[n];
		for (int i=0; i<n;i++){
			pow[i]=tuple[i];
			pow[i]=pow[i].$E$(.5);
		}
		return new QuaternicTuple(pow);
	}
	
	/**Returns the value at the specified index of this tuple
	 * 
	 * @param index
	 * @return
	 */
	public Quaternion i(int index){
		try {
			return tuple[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Quaternion(0);
		}
	}
	
	/**Returns and array form of this tuple
	 * 
	 * @return
	 */
	public Quaternion[] a(){
		return tuple;
	}
	
	public Quaternion sum(){
		Quaternion sum=new Quaternion(0);
		for (int i=0; i<n; i++){
			sum=sum.$A$(tuple[i]);
		}
		return sum;
	}
	
	/**Returns the size of this tuple
	 * 
	 * @return
	 */
	public int n(){
		return n;
	}
	
	/**Sets the value of a specific index of this tuple
	 * 
	 * @param index
	 * @param val
	 * @return weather this tuple grew to compensate for an index that was out of bounds
	 */
	public boolean set(int index,double val){
		if (index>tuple.length-1){
			double[] d=new double[index+1];
			d[index]=val;
			tuple=this .$A$ (new Tuple(d)).a();
			n=tuple.length;
			return true;
		} else {
			tuple[index]=new Quaternion(val);
			return false;
		}
	}
	
	/**Sets the value of a specific index of this tuple
	 * 
	 * @param index
	 * @param val
	 * @return weather this tuple grew to compensate for an index that was out of bounds
	 */
	public boolean set(int index,Quaternion val){
		if (index>tuple.length-1){
			Quaternion[] d=new Quaternion[index+1];
			d[index]=val;
			tuple=this .$A$ (new QuaternicTuple(d)).a();
			n=tuple.length;
			return true;
		} else {
			tuple[index]=val;
			return false;
		}
	}
	
	/**Sets the values of this tuple to the specified array of values
	 * 
	 * @param values
	 */
	public void set(double[] values){
		Quaternion[] array=new Quaternion[values.length];
		for (int i=0;i<values.length;i++) {
			array[i]=new Quaternion(values[i]);
		}
		tuple=array;
		n=tuple.length;
	}
	
	/**Sets the values of this tuple to the specified array of values
	 * 
	 * @param values
	 */
	public void set(Quaternion[] values){
		tuple=values;
		n=tuple.length;
	}
	
	/**Sets this tuple to imitate the properties of another tuple
	 * 
	 * @param t
	 */
	public void set(Tuple t){
		set(t.a());
	}
	
	/**Sets this tuple to imitate the properties of another tuple
	 * 
	 * @param t
	 */
	public void set(QuaternicTuple t){
		set(t.a());
	}
	
	/**Sets all values of this touple to the double <code>d</code>
	 * 
	 * @param d
	 */
	public void set(double d){
		for (int i=0; i<n; i++){
			tuple[i]=new Quaternion(d);
		}
	}
	
	/**Sets all values of this touple to the double <code>d</code>
	 * 
	 * @param d
	 */
	public void set(Quaternion q){
		for (int i=0; i<n; i++){
			tuple[i]=q;
		}
	}
	
	/**Returns a tuple with all zero values
	 * 
	 * @param n size
	 * @return tuple that represents origin
	 */
	public static QuaternicTuple origin(int n){
		return new QuaternicTuple(new Quaternion[n]);
	}
	
	public boolean equals(QuaternicTuple t){
		int n;
		boolean tuplesEqual=true;
		if (t.n>this.n){
			n=t.n;
			for (int i=0; i<n; i++){
				try{
					if (!(tuple[i].equals(t.tuple[i]))){
						tuplesEqual=false;
						break;
					}
				} catch (IndexOutOfBoundsException e){
					//undefined index of this tuple defaults to zero
					if (!(t.tuple[i].equals(new Quaternion(0)))){
						tuplesEqual=false;
						break;
					}
				}
			}
		} else {
			n=this.n;
			for (int i=0; i<n; i++){
				try{
					if (!(tuple[i].equals(t.tuple[i]))){
						tuplesEqual=false;
						break;
					}
				} catch (IndexOutOfBoundsException e){
					//undefined index of tuple t defaults to zero
					if (!(tuple[i].equals(new Quaternion(0)))){
						tuplesEqual=false;
						break;
					}
				}
			}
		}
		
		return tuplesEqual;
	}
	
	public boolean equals(Tuple t) {
		return equals(new QuaternicTuple(t));
	}
	
	public boolean contains(double d){
		for (Quaternion q:tuple) {
			if (q.equals(d)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(Quaternion dq) {
		for (Quaternion q:tuple) {
			if (q.equals(dq)) {
				return true;
			}
		}
		return false;
	}
	
	public Tuple containsAtIndexes(double d) {
		Tuple indexes=new Tuple(0);
		boolean tupleIsNew=true;
		int i=0;
		for (Quaternion q:tuple) {
			if (q.equals(d)) {
				if (!tupleIsNew) {
					indexes.set(1,i);
				} else {
					indexes.set(1,i);
					tupleIsNew=false;
				}
			}
			i++;
			
		}
		return indexes;
	}
	
	public void printVals() {
		for (int i=0;i<n;i++) {
			System.out.println(tuple[i].toString());
		}
	}
	
	public void printVals(String lable) {
		System.out.println(lable+": ");
		for (int i=0;i<n;i++) {
			System.out.println(tuple[i].toString());
		}
	}
	
	@Override
	public QuaternicTuple clone() {
		try {
			return (QuaternicTuple) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
