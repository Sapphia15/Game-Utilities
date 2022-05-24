package graphics.gl;

import org.lwjgl.glfw.GLFW;

public class MouseListener {
	static double 
		scrollX=0,
		scrollY=0;
	static double 
		xPos=0,
		yPos=0,
		lastX=0,
		lastY=0;
	static boolean[] mousePressed=new boolean[3];
	static boolean dragging;
	
	public static void mousePosCallback(long window,double nxPos,double nyPos) {
		lastX=nxPos;
		lastY=nyPos;
		xPos=nxPos;
		yPos=nyPos;
		dragging=mousePressed[0]||mousePressed[1]||mousePressed[2];
	}
	
	public static void mouseButtonCallback(long window,int button,int action,int mods) {
		if (action==GLFW.GLFW_PRESS) {
			if (button<mousePressed.length) {
				mousePressed[button]=true;
			}
		} else if (action==GLFW.GLFW_RELEASE){
			mousePressed[button]=false;
			dragging=false;
		}
	}
	
	public static void mouseScrollCallback(long window,double xOff,double yOff) {
		scrollX=xOff;
		scrollY=yOff;
	}
	
	public static void endFrame() {
		scrollX=0;
		scrollY=0;
		xPos=0;
		yPos=0;
		lastX=0;
		lastY=0;
	}
	
	public float getX() {
		return (float)xPos;
	}
	
	public float getY() {
		return (float)yPos;
	}
	public float getdX() {
		return (float)(xPos-lastX);
	}
	public float getdY() {
		return (float)(yPos-lastY);
	}
	
	public float getScrollX() {
		return (float)scrollX;
	}
	
	public float getScrollY() {
		return (float)scrollY;
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public static boolean mouseButtonDown(int button) {
		if (button<mousePressed.length) {
			return mousePressed[button];
		}
		return false;
	}
}
