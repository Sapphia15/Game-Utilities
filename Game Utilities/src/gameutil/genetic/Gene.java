package gameutil.genetic;

import java.util.Random;

public class Gene {
	static Random rand=new Random();
	
	private int val;
	
	public Gene(int val) {
		this.val=val;
	}
	
	public Gene() {
		this.val=rand.nextInt();
	}
	
	public static int mutate(int val) {
		byte index=(byte)rand.nextInt(32);
		return BitManipulator.flip(val, index);
	}
	
	public int get() {
		return val;
	}
	
	/**The probability of getting the maximum number of mutations will be (mRate/maxMutations)^mRate
	 * The probability of getting at least one mutation will be the mRate
	 * 
	 * @param mRate the probability of getting one mutation
	 * @param maxMutations the maximum number of mutations
	 * @return
	 */
	public Gene clone(double mRate,int maxMutations) {
		int val=this.val;
		double pMoney=mRate;
		while (pMoney>0) {
			double prob=mRate/maxMutations;
			
			if (rand.nextDouble()<mRate) {
				val=mutate(val);
			}
			
			pMoney-=prob;
		}
		return new Gene(val);
	}
	
	public Gene clone(double mRate) {
		return clone(mRate,1); 
	}
	
	public Gene clone() {
		return clone(0);
	}
}
