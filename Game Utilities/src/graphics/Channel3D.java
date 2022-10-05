package graphics;

import java.awt.image.ColorModel;
import java.util.Hashtable;
import gameutil.ByteUtil;
import gameutil.text.Console;

public class Channel3D implements ImageConsumer3D{
	byte[] voxels;
	int width=0;
	int length=0;
	int height=0;
	byte channelNumber=0;
	
	public Channel3D(int w,int l,int h) {
		width=w;
		length=l;
		height=h;
		voxels=new byte[width*length*height];
	}
	
	public Channel3D(int w,int l,int h,byte channelNumber) {
		width=w;
		length=l;
		height=h;
		voxels=new byte[width*length*height];
		this.channelNumber=channelNumber;
	}
	
	public byte getVoxel(int x,int y,int z) {
		return voxels[flatten(x,y,z)];
	}
	
	//public void setVoxels()
	
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
		p[2]=(int)(Math.floor((double)n/width))%(length*width);
		return p;
	}

	@Override
	public void setDimensions(int width, int height) {
		setDimensions(width,length,height);
		
	}

	public void copyVolume(int x,int y,int z,int w,int l,int h,int dx,int dy,int dz) {
		for (int k=0;k<h;k++) {
			if (y+k>=height||y+dy+k>=height){
				break;
			}
			for (int j=0;j<l;j++) {
				if (z+j>=length||z+dz+j>=length){
					break;
				}
				for (int i=0;i<w;i++) {
					if (x+i>=width||x+dx+i>=width){
						break;
					}
					if (x+i>=0&&y+k>=0&&z+j>=0&&x+i+dx>=0&&y+k+dy>=0&&z+j+dz>=0) {
						int n=flatten(x+i,y+k,z+j);
						int index=flatten(x+i+dx,y+k+dy,z+j+dz);
						this.voxels[index]=voxels[n];
					}
				}
			}
		}
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
		
		
	}
	@Deprecated
	@Override
	public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
		
	}

	@Override
	public void imageComplete(int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDimensions(int width, int length, int height) {
		byte[] oldVoxels=voxels;
		int oldWidth=this.width;
		int oldHeight=this.height;
		int oldLength=this.length;
		this.width=width;
		this.height=height;
		this.length=length;
		voxels=new byte[width*length*height];
		setVoxels(0, 0, 0, oldWidth, oldLength, oldHeight, voxels);
	
	}

	@Deprecated
	@Override
	public void setVoxels(int x, int y, int z, int w, int l, int h, ColorModel model, byte[] voxels, int off,
			int scansize) {
		
		setVoxels(x,y,z,w,l,h,voxels);
	}
	
	public void blit(Channel3D channel) {
		if (channel.width==this.width&&channel.height==this.height&&channel.length==this.length) {
			byte[] oldVoxels=voxels;
			voxels=channel.voxels;
			channel.voxels=oldVoxels;
		}
	}
	
	public void setVoxels(int x, int y, int z, int w, int l, int h, byte[] voxels) {
		int index=0;
		for (int k=0;k<h;k++) {
			if (y+k>=height){
				break;
			}
			for (int j=0;j<l;j++) {
				if (z+j>=length){
					break;
				}
				index=j*w+k*l*w;
				for (int i=0;i<w;i++) {
					if (x+i>=width){
						break;
					}
					if (x+i>=0&&y+k>=0&&z+j>=0) {
						int n=flatten(x+i,y+k,z+j);
						this.voxels[n]=voxels[index];
					}
					index++;
				}
			}
		}
		
	}
	
	public void setVoxels(int x, int y, int z, int w, int l, int h, int[] voxels) {
		int index=0;
		for (int k=0;k<h;k++) {
			if (y+k>=height){
				break;
			}
			for (int j=0;j<l;j++) {
				if (z+j>=length){
					break;
				}
				index=j*w+k*l*w;
				for (int i=0;i<w;i++) {
					if (x+i>=width){
						break;
					}
					if (x+i>=0&&y+k>=0&&z+j>=0) {
						int n=flatten(x+i,y+k,z+j);
						this.voxels[n]=ByteUtil.getByte(voxels[index],channelNumber);
					}
					index++;
				}
			}
		}
		
	}
	
	public void blendVoxels(int x, int y, int z, int w, int l, int h, int[] voxels) {
		int index=0;
		for (int k=0;k<h;k++) {
			if (y+k>=height){
				break;
			}
			for (int j=0;j<l;j++) {
				if (z+j>=length){
					break;
				}
				index=j*w+k*l*w;
				for (int i=0;i<w;i++) {
					if (x+i>=width){
						break;
					}
					if (x+i>=0&&y+k>=0&&z+j>=0) {
						int n=flatten(x+i,y+k,z+j);
						if (channelNumber<3) {
							int value=(byte)Math.floor((1.0-(ByteUtil.getByte(voxels[index],(byte)3)/(double)0xff))*this.voxels[n]/(double)0xff+ByteUtil.getByte(voxels[index],channelNumber));
							if (value>0xff) {
								value=0xff;
							}
							this.voxels[n]=(byte)value;
						} else {
							int alpha=this.voxels[n]+ByteUtil.getByte(voxels[index],channelNumber);
							if (alpha>0xff) {
								alpha=0xff;
							}
							this.voxels[n]=(byte)alpha;
						}
					}
					index++;
				}
			}
		}
		
	}

	public Channel3D sliceXZ(int y) {
		Channel3D c=new Channel3D(width,length,1,channelNumber);
		byte[] voxels=new byte[width*length];
		int off=flatten(0,y,0);
		for (int i=0;i<width*length;i++) {
			//Console.s.println(off);
			voxels[i]=this.voxels[off+i];
		}
		c.voxels=voxels;
		return c;
	}
	
	@Deprecated
	@Override
	public void setVoxels(int x, int y, int z, int w, int l, int h, ColorModel model, int[] pixels, int off,
			int scansize) {
		// TODO Auto-generated method stub
		
	}
}
