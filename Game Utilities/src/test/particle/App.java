package test.particle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import graphics.screen.SPanel;

public class App {
	public static void main(String[] unicorns) {
		Frame f=new Frame();
		SPanel s=new SPanel(f);
		f.setPreferredSize(new Dimension(1500,800));
		f.add(s);
		f.pack();
		f.setTitle("Particle");
		s.closeOnExit();
		PScreen c=new PScreen(s);
		s.addScreen(c, "particle");
		s.setScreen("particle");
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		s.setDoubleBuffered(true);
		
		//s.setTargetFPS(30,1);
		s.start();
	}
}
