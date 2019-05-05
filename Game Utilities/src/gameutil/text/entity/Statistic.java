package gameutil.text.entity;

public class Statistic {
	private int val;
	private int max;
	private int min;
	private final int maxMax;
	String name;
	/**
	 * Creates a new statistic with a value of zero, infinite maximum, and minimum of zero
	 * @param name name of statistic
	 */
	public Statistic(String name){
		this.name=name;
		val=0;
		max=0;
		maxMax=0;
		min=0;
	}
	/**
	 * Creates a new statistic with a value of <code>val</code>, infinite maximum, and minimum of zero
	 * @param name name of statistic
	 * @param val value of statistic
	 */
	public Statistic(String name,int val){
		this.name=name;
		this.val=val;
		max=0;
		maxMax=0;
		min=0;
	}
	/**
	 * Creates a new statistic with a value of <code>val</code>, maximum value of <code>max</code>, and minimum of zero
	 * @param name name of statistic
	 * @param val value of statistic
	 * @param max maximum value of statistic *if zero is input then maximum value is infinity
	 */
	public Statistic(String name,int val,int max){
		this.name=name;
		this.val=val;
		this.max=max;
		maxMax=0;
		min=0;
	}
	public Statistic(String name,int val,int max,int maxMax){
		this.name=name;
		this.val=val;
		this.max=max;
		this.maxMax=maxMax;
		min=0;
	}
	public Statistic(String name,int val,int max,int maxMax,int min){
		this.name=name;
		this.val=val;
		this.max=max;
		this.maxMax=maxMax;
		this.min=min;
	}
	public Statistic(){
		name="untitled";
		val=0;
		max=0;
		maxMax=0;
		min=0;
	}
	public Statistic(int val){
		name="untitled";
		this.val=val;
		max=0;
		maxMax=0;
		min=0;
	}
	public Statistic(int val,int max){
		name="untitled";
		this.val=val;
		this.max=max;
		maxMax=0;
		min=0;
	}
	public Statistic(int val,int max,int maxMax){
		name="untitled";
		this.val=val;
		this.max=max;
		this.maxMax=maxMax;
		min=0;
	}
	public Statistic(int val, int max, int maxMax,int min){
		name="untitled";
		this.val=val;
		this.max=max;
		this.maxMax=maxMax;
		this.min=min;
	}
	/**
	 * Returns the name of the statistic
	 */
	public String toString(){
		return name;
	}
	/**
	 * Returns the name of the statistic
	 */
	public String getName(){
		return name;
	}
	public void set(int val){
		if (val>max&&!(max==0)){
			this.val=max;
		} else if (val<min){
			val=min;
		} else {
			this.val=val;
		}
	}
	public void decrease(int val){
		val=this.val-val;
		if (val>max&&!(max==0)){
			this.val=max;
		} else if (val<min){
			val=min;
		} else {
			this.val=val;
		}
	}
	public void increase(int val){
		val=this.val+val;
		if (val>max&&!(max==0)){
			this.val=max;
		} else if (val<min){
			val=min;
		} else {
			this.val=val;
		}
	}
	public void setMax(int max){
		if (max>maxMax&&!(maxMax==0)){
			this.max=maxMax; 
		} else {
			this.max=max;
		}
	}
	public void setMin(int min){
		this.min=min;
	}
	public int getVal(){
		return val;
	}
	public int getMax(){
		return max;
	}
	public int getMin(){
		return min;
	}
	public int getMaxMax(){
		return maxMax;
	}
}
