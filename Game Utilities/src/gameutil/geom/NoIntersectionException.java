package gameutil.geom;

public class NoIntersectionException extends Exception {
	protected NoIntersectionException() {
		super("Failed to find intersection.");
		
	}
}
