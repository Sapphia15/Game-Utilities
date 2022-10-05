package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Image3D extends Image{
	
	int width=0;
	int length=0;
	int height=0;
	
	Raster3D raster;
	Graphics3D graphics;
	
	public Image3D(int width,int length,int height) {
		raster=new Raster3D(width,length,height);
		graphics=new Graphics3D(raster);
		this.width=width;
		this.length=length;
		this.height=height;
	}
	
	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return width;
	}
	
	public int getLength(ImageObserver observer) {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public Raster3D getRaster() {
		return raster;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return graphics.create();
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

}
