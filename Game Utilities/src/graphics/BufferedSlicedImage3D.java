package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BufferedSlicedImage3D extends SlicedImage3D{
	
	public BufferedSlicedImage3D(int width,int height,int length,int imageType) {
		super(length);
		for (int i=0;i<length;i++) {
			layers[i]=new BufferedImage(width,height,imageType);
		}
	}
	
	public BufferedImage getLayer(int z) {
		return (BufferedImage)layers[z];
	}
	
	public void flush() {
		for (Image im:layers) {
			((BufferedImage)im).flush();
		}
	}
	
	public Graphics[] getGraphics() {
		Graphics[] gs=new Graphics[layers.length];
		for (int i=0;i<layers.length;i++) {
			gs[i]=layers[i].getGraphics();
		}
		return gs;
	}
	
	public Graphics2D[] createGraphics() {
		Graphics2D[] gs=new Graphics2D[layers.length];
		for (int i=0;i<layers.length;i++) {
			gs[i]=((BufferedImage)layers[i]).createGraphics();
		}
		return gs;
	}
}
