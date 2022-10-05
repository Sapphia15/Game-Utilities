package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameutil.math.ComplexDouble;

public class ImageWrite {
	public static void main(String[] unicorns) {
		
		
		for (int k=1;k<=16;k++) {
			BufferedImage img=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
			File outputfile = new File("primes/"+(k-1)+".png");
			Graphics g=img.getGraphics();
			g.setColor(Color.MAGENTA);
			for (int i=-15;i<=15;i++) {
				for (int j=-15;j<15;j++) {
					ComplexDouble z=new ComplexDouble(i,j);
					if (z.getMagnitude()<=k&&z.isPrime()) {
						g.drawRect(i+16, j+16, 0, 0);
					}
				}
			}
			try {
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
