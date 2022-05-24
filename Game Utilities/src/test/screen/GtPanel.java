package test.screen;

import graphics.gl.GLPanel;
import graphics.gl.Shader;

public class GtPanel extends GLPanel{

	private Shader defaultShader;
	
	public GtPanel() {
		super("Unicorn Test");
		// TODO Auto-generated constructor stub
	}

	public void init() {
		defaultShader=new Shader("assets/shader/default.glsl");
		defaultShader.compile();
		super.init();
	}
	
	public void paint() {
		//defaultShader.use();
		//defaultShader.detach();
	}
	
}
