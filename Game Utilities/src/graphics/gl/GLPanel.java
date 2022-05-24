package graphics.gl;

import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class GLPanel {
	long window;
	int width=1920;
	int height=1080;
	String title="Unicorn Window";
	long lastFrame;
	
	
	public GLPanel(String title) {
		this.title=title;
	}
	
	public void run() {
		//initialize window
		init();
		
		//draw window
		loop();
		
		//free window memory
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		
		//terminate glfw and free error callback
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	

	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();
      if (!GLFW.glfwInit()) {
    	  System.err.println("Oh no.");
      }
      
      //window hints
      GLFW.glfwDefaultWindowHints();
      GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
      GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
      GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
      
      
      window=GLFW.glfwCreateWindow(width, height, "Test", NULL, NULL);
      if (window==NULL) {
    	  System.err.println("Girl, this is bad.");
      }
      
      GLFW.glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
      GLFW.glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
      GLFW.glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
      GLFW.glfwSetKeyCallback(window, KeyListener::keyCallback);
      
      GLFW.glfwMakeContextCurrent(window);
      //enable vsync?
      GLFW.glfwSwapInterval(1);
      
      GLFW.glfwShowWindow(window);
      GL.createCapabilities();
      lastFrame=System.currentTimeMillis();
	}

	private void loop() {
		float g=0f;
		float dg=.01f;
		float b=0f;
		// TODO Auto-generated method stub
		while (!GLFW.glfwWindowShouldClose(window)) {
			GLFW.glfwPollEvents();
			GL11.glClearColor(1f, g, b, 1f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GLFW.glfwSwapBuffers(window);
			long currentFrame=System.currentTimeMillis();
			System.out.println(60000/(currentFrame-lastFrame+1));
			lastFrame=currentFrame;
			if (KeyListener.keyPressed(GLFW.GLFW_KEY_SPACE)) {
				b=1f-b;    
			}
			g+=dg;
			if(g>1) {
				dg=-.01f;
				g+=dg;
			} else if (g<0) {
				dg=.01f;
				g+=dg;
			}
		}
	}
	
	public void paint() {
		
	}
}
