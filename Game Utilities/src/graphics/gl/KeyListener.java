package graphics.gl;

import org.lwjgl.glfw.GLFW;

public class KeyListener {
	static boolean[] keyPressed=new boolean[350];
	
	public static void keyCallback(long window,int key,int scanCode,int action,int mods) {
		if (action==GLFW.GLFW_PRESS) {
			keyPressed[key]=true;
		} else if (action==GLFW.GLFW_RELEASE) {
			keyPressed[key]=false;
		}
	}
	
	public static boolean keyPressed(int key) {
		return keyPressed[key];
	}
}
