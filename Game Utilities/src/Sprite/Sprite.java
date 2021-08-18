package Sprite;

import gameutil.math.geom.Vector;

public class Sprite {
	private boolean inWorld=true;
	private Vector pos;
	
	public Sprite() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean inWorld() {
		return inWorld;
	}
	
	/**Override this method with what should happen every frame
	 * 
	 */
	public void update() {
		
	}
	
	public String getProps() {
		return "";
	}

	
}
