package test.screen;

import java.awt.Frame;


import graphics.screen.SPanel;
import graphics.screen.VideoScreen;

public class Panel extends SPanel{

	public Panel(Frame observer) {
		super(observer);
		//initialize all of the screens and store them in the hashtable so that they can be viewed later
		this.screens.put("vid", new VideoScreen("assets/video/Kalaygan Intro.mp4",this));
		//sets the current screen to the title screen
		this.currentScreen=screens.get("vid");//*this is necessary before the set screen function to prevent a null pointer exception as the setScreen function acts under the pretense that there was a previous screen and tries to run that screen's deInit funcion
		this.setScreen("vid");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
