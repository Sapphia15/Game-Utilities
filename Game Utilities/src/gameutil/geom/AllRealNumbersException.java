package gameutil.geom;

public class AllRealNumbersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AllRealNumbersException() {
		super("Returned a set of values that consists of all real numbers");
		
	}
	
	public void printStackTrace() {
		System.out.println("*This is not an error, catch to do something when all real numbers are returned");
		super.printStackTrace(System.out);
	}
}
