package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheet {
	BufferedImage image;
	
	public SpriteSheet(String path) {
		try {
			image=ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			image=null;
		}
	}
	
	public void get() {
		
	}
}
