package gameutil.genetic;

public class BitManipulator {
	
	public int[] getCut(int[] b,int start,int end) {
		int[] array = new int[end-start+1];
		for (int i=start;i<=end;i++) {
			array[i-start]=b[i];
		}
		return array;
	}
	
	public static int getCut(int b,byte start,byte end ) {
		int and=0;
		for (byte i=start;i<=end;i++) {
			and<<=1;
			and+=1;
			
			
		}
		and<<=start;
		//System.out.println("and: "+and);
		return (b&and)>>>start;
	}
	
	public static boolean getBit(int b, byte index) {
		return getCut(b,index,index)==1;
	}
	
	public static int[] glue(int b1,int b2) {
		return new int[] {b1,b2};
	}
	
	public static int[] glue(int[] b1,int b2) {
		int[] array=new int[b1.length+1];
		for (int i=0; i<b1.length;i++) {
			array[i]=b1[i];
		}
		array[b1.length]=b2;
		return array;
	}
	
	public static int[] glue(int[] b1,int[] b2) {
		int[] array=new int[b1.length+b2.length];
		for (int i=0; i<b1.length;i++) {
			array[i]=b1[i];
		}
		for (int i=b1.length-1; i<b2.length;i++) {
			array[i]=b2[i];
		}
		return array;
	}
	
	public static int flip(int b,byte index) {
		int num=1;
		//System.out.println("index: "+index);
		for (byte i=0; i<index;i++) {
			num<<=1;
			//System.out.println("Num: "+num);
		}
		if (getBit(b,index)) {
			b-=num;
		} else {
			b+=num;
		}
		//System.out.println("flipped: "+b);
		return b;
	}
	
	public static int set(int b,byte index,boolean val) {
		boolean bit=getBit(b,index);
		if (bit&!val) {
			int num=1;
			for (byte i=0; i<index;i++) {
				num<<=num;
			}
			b-=num;
		} else if (!bit&val) {
			int num=1;
			for (byte i=0; i<index;i++) {
				num<<=num;
			}
			b+=num;
		}
		return b;
	}
	
	public static int[] and(int[] b1,int b2) {
		int[] array=b1.clone();
		array[0]&=b2;
		return array;
	}
	
	/**unfinished
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	/*
	public static int[] and(int[] b1,int[] b2) {
		int len=b1.length;
		int len2=b2.length;
		if (b1.length>b2.length) {
			
			len=b2.length;
			len2=b1.length;
			
		}
		int[] array=new int[len2];
		for (int i=0; i<len;i++) {
			array[i]=b1[i]&b2[i];
		}
		return array;
	}*/
	
	public static int[] or(int[] b1,int b2) {
		int[] array=b1.clone();
		array[0]|=b2;
		return array;
	}
	
	public static int[] xor(int[] b1,int b2) {
		int[] array=b1.clone();
		array[0]^=b2;
		return array;
	}
}
