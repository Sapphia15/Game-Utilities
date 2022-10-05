package graphics;

import java.awt.Color;
import java.awt.image.ColorModel;
import java.util.Hashtable;

import gameutil.ByteUtil;
import gameutil.text.Console;

public class Raster3D implements ImageConsumer3D{
	Channel3D[] channels=new Channel3D[4];
	
	int width;
	int length;
	int height;
	
	int[] voxels;
	
	public static final int R_FILTER=0x000000ff;
	public static final int G_FILTER=0x0000ff00;
	public static final int B_FILTER=0x00ff0000;
	public static final int A_FILTER=0xff000000;
	
	public Raster3D(int width,int length,int height) {
		for (byte i=0;i<4;i++) {
			channels[i]=new Channel3D(width,length,height,i);
		}
		voxels=new int[width*length*height];
		this.width=width;
		this.length=length;
		this.height=height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getheight() {
		return height;
	}
	
	public int flatten(int x, int y, int z) {
		return x%width+(y%height)*width*length+(z%length)*width;
	}
	public int flatten(int[] p) {
		return flatten(p[0],p[1],p[2]);
	}
	
	public int[] volumize(int n) {
		int[] p=new int[3];
		p[0]=n%width;
		p[1]=(int)(Math.floor((double)n/(width*length)))%(length*width*height);
		p[2]=(int)(Math.floor((double)n/width))%(length);
		return p;
	}
	
	public Channel3D getRedChannel() {
		return channels[0];
	}
	
	public Channel3D getGreenChannel() {
		return channels[1];
	}
	
	public Channel3D getBlueChannel() {
		return channels[2];
	}
	
	public Channel3D getAlphaChannel() {
		return channels[3];
	}
	
	public void setVoxels(int x,int y,int z,int w,int l,int h,byte[] r,byte[] g,byte[] b,byte[] a) {
		channels[0].setVoxels(x, y, z, w, l, h, r);
		channels[1].setVoxels(x, y, z, w, l, h, g);
		channels[2].setVoxels(x, y, z, w, l, h, b);
		channels[3].setVoxels(x, y, z, w, l, h, a);
	}
	
	public void setVoxels(int x,int y,int z,int w,int l,int h,int[] voxels) {
		channels[0].setVoxels(x, y, z, w, l, h, voxels);
		channels[1].setVoxels(x, y, z, w, l, h, voxels);
		channels[2].setVoxels(x, y, z, w, l, h, voxels);
		channels[3].setVoxels(x, y, z, w, l, h, voxels);
	}
	
	public void setVoxel(int x,int y,int z,int voxel) {
		int[] voxels=new int[]{voxel};
		setVoxels(x,y,z,1,1,1,voxels);
	}
	
	public void blendVoxel(int x,int y,int z,int voxel) {
		int[] voxels=new int[]{voxel};
		blendVoxels(x,y,z,1,1,1,voxels);
	}
	public void blendVoxels(int x,int y,int z,int w,int l,int h,int[] voxels) {
		channels[0].blendVoxels(x, y, z, w, l, h, voxels);
		channels[1].blendVoxels(x, y, z, w, l, h, voxels);
		channels[2].blendVoxels(x, y, z, w, l, h, voxels);
		channels[3].blendVoxels(x, y, z, w, l, h, voxels);
	}
	
	public void setDimensions(int w,int l,int h) {
		width=w;
		length=l;
		height=h;
		voxels=new int[width*length*height];
		for (byte i=0;i<4;i++) {
			channels[i].setDimensions(w, l, h);
		}
	}
	
	public void computeVoxels() {
		for (byte i=0;i<4;i++) {
			for (int j=0;j<length*width*height;j++) {
				voxels[j]=ByteUtil.setByte(voxels[j],channels[i].voxels[j],i);
				
			}
		}
	}
	
	public int[] getVoxels() {
		return voxels;
	}
	
	public Raster3D sliceXZ(int y) {
		Raster3D raster=new Raster3D(width,length,1);
		for (byte i=0;i<4;i++) {
			raster.channels[i]=channels[i].sliceXZ(y);
		}
		raster.computeVoxels();
		return raster;
	}
	
	public void copyVolume(int x,int y,int z,int width,int length,int height,int dx,int dy,int dz) {
		for (byte i=0;i<4;i++) {
			channels[i].copyVolume(x, y, z, width, length, height, dx, dy, dz);
		}
	}

	@Deprecated
	@Override
	public void setDimensions(int width, int height) {
		setDimensions(width,length,height);
		
	}

	@Override
	public void setProperties(Hashtable<?, ?> props) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void setColorModel(ColorModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHints(int hintflags) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imageComplete(int status) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void setVoxels(int x, int y, int z, int w, int l, int h, ColorModel model, byte[] pixels, int off,
			int scansize) {
		// TODO Auto-generated method stub
		
	}
	
	@Deprecated
	@Override
	public void setVoxels(int x, int y, int z, int w, int l, int h, ColorModel model, int[] pixels, int off,
			int scansize) {
		// TODO Auto-generated method stub
		
	}

	
	@Deprecated
	@Override
	public void setVoxels(int x, int y, int z, int w, int l, int h, byte[] voxels) {
		
		
	}
	

	
	
}
