package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import gameutil.ByteUtil;
import gameutil.ConcurrentBidirectionalMap;
import gameutil.math.Hilbert;
import gameutil.math.Quaternion;
import gameutil.text.Console;

public class g {
	Graphics gr;
	static Hashtable<Integer,ConcurrentBidirectionalMap<Integer,String>> hilbertCurves=null;
	static final double log2=Math.log(2);
	
	public g(Graphics gr) {
		this.gr=gr;
		if (hilbertCurves==null) {
			hilbertCurves=new Hashtable<>();
			hilbertCurves.put(0, Hilbert.curve);
			//getCurve(5);
		}

	}
	
	public void drawProjection(Image3D im,int x,int y,int width,int height,int[] pos,double[] look) {
		Raster3D raster=im.getRaster();
		int iwidth=im.getWidth(null);
		int iheight=im.getHeight(null);
		int ilength=im.getLength(null);
		int[] voxels=raster.getVoxels();
		Quaternion rotate=new Quaternion(0,0,1,0).$X$(Math.cos(look[0]/2)+Math.sin(look[0]/2));
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				int closest=0;
				double dist=Double.MAX_VALUE;
				for (int k=0;k<iwidth*ilength*iheight;k++) {
			
				
					int[] pt=raster.volumize(k);
					Quaternion rt=rotate.$X$(new Quaternion(0,pt[0],pt[1],pt[2])).$X$(rotate.complement());
					double distance=rt.$S$(new Quaternion(0,pos[0]+i-width/2,pos[1]+j-height/2,pos[2])).abs();
					if (distance<dist) {
						dist=distance;
						closest=k;
					}
					
				}
				
				//Console.s.println(pt[0]+", "+pt[1]+", "+pt[2]);
				int clr = voxels[closest];
				/*int alpha = (byte)(clr & Raster3D.A_FILTER);
		        int red =   (byte)(clr & Raster3D.R_FILTER);
		        int green = (byte)(clr & Raster3D.G_FILTER);
		        int blue =   (byte)(clr & Raster3D.B_FILTER);
		        */
		        
		        int red =   (clr & Raster3D.R_FILTER);
		        int green = (clr & Raster3D.G_FILTER)>>>8;
		        int blue = (clr & Raster3D.B_FILTER)>>>16;
		        int alpha = (clr & Raster3D.A_FILTER)>>>24;
				//Color c=new Color(data[0],data[1],data[2]);
		        Color c=new Color(red,green,blue,alpha);
				gr.setColor(c);
				gr.fillRect(x+i,y+j,1,1);
			}
		}
	}
	
	public void drawXZSlice(Image3D im,int x,int y,int sliceY) {
		Raster3D raster=im.getRaster().sliceXZ(sliceY);
		int width=im.getWidth(null);
		int length=im.getLength(null);
		int[] voxels=raster.getVoxels();
		for (int i=0;i<width*length;i++) {
			
				int[] pt=raster.volumize(i);
				//Console.s.println(pt[0]+", "+pt[1]+", "+pt[2]);
				int clr = voxels[i];
				/*int alpha = (byte)(clr & Raster3D.A_FILTER);
		        int red =   (byte)(clr & Raster3D.R_FILTER);
		        int green = (byte)(clr & Raster3D.G_FILTER);
		        int blue =   (byte)(clr & Raster3D.B_FILTER);
		        */
		        
		        int red =   (clr & Raster3D.R_FILTER);
		        int green = (clr & Raster3D.G_FILTER)>>>8;
		        int blue = (clr & Raster3D.B_FILTER)>>>16;
		        int alpha = (clr & Raster3D.A_FILTER)>>>24;
				//Color c=new Color(data[0],data[1],data[2]);
		        Color c=new Color(red,green,blue,alpha);
				gr.setColor(c);
				//Console.s.println(ByteUtil.toHex(red)+", "+ByteUtil.toHex(green)+", "+ByteUtil.toHex(blue)+", "+ByteUtil.toHex(alpha));
				gr.fillRect(x+pt[0], y+pt[2], 1, 1);
		}
	}
	
	public void drawImage3DStackStyleWithSeparation(Image3D im,int x,int y,int voxelWidthAndLength,int voxelHeight,int separation) {
		Raster3D raster=im.getRaster();
		int width=im.getWidth(null);
		int height=im.getHeight(null);
		int length=im.getLength(null);
		int[] voxels=raster.getVoxels();
		for (int i=0;i<width*length*height;i++) {
			
				int[] pt=raster.volumize(i);
				//Console.s.println(pt[0]+", "+pt[1]+", "+pt[2]);
				int clr = voxels[i];
				/*int alpha = (byte)(clr & Raster3D.A_FILTER);
		        int red =   (byte)(clr & Raster3D.R_FILTER);
		        int green = (byte)(clr & Raster3D.G_FILTER);
		        int blue =   (byte)(clr & Raster3D.B_FILTER);
		        */
		        
		        int red =   (clr & Raster3D.R_FILTER);
		        int green = (clr & Raster3D.G_FILTER)>>>8;
		        int blue = (clr & Raster3D.B_FILTER)>>>16;
		        int alpha = (clr & Raster3D.A_FILTER)>>>24;
				//Color c=new Color(data[0],data[1],data[2]);
		        Color c=new Color(red,green,blue,alpha);
				gr.setColor(c);
				int spot=pt[0]+pt[2]*(width+separation);
				gr.fillRect(x+spot*voxelWidthAndLength, y+voxelHeight*pt[1], voxelWidthAndLength, voxelHeight);
		}
	}
	
	public void drawImage3DStackStyle(Image3D im,int x,int y,int voxelWidthAndLength,int voxelHeight) {
		Raster3D raster=im.getRaster();
		int width=im.getWidth(null);
		int height=im.getHeight(null);
		int length=im.getLength(null);
		int[] voxels=raster.getVoxels();
		for (int i=0;i<width*length*height;i++) {
			
				int[] pt=raster.volumize(i);
				//Console.s.println(pt[0]+", "+pt[1]+", "+pt[2]);
				int clr = voxels[i];
				/*int alpha = (byte)(clr & Raster3D.A_FILTER);
		        int red =   (byte)(clr & Raster3D.R_FILTER);
		        int green = (byte)(clr & Raster3D.G_FILTER);
		        int blue =   (byte)(clr & Raster3D.B_FILTER);
		        */
		        
		        int red =   (clr & Raster3D.R_FILTER);
		        int green = (clr & Raster3D.G_FILTER)>>>8;
		        int blue = (clr & Raster3D.B_FILTER)>>>16;
		        int alpha = (clr & Raster3D.A_FILTER)>>>24;
				//Color c=new Color(data[0],data[1],data[2]);
		        Color c=new Color(red,green,blue,alpha);
				gr.setColor(c);
				int spot=pt[0]+pt[2]*width;
				gr.fillRect(x+spot*voxelWidthAndLength, y+voxelHeight*pt[1], voxelWidthAndLength, voxelHeight);
		}
	}
	
	public void drawImage3D(Image3D im,int x,int y,int voxelWidthAndLength,int voxelHeight) {
		Raster3D raster=im.getRaster();
		int width=im.getWidth(null);
		int height=im.getHeight(null);
		int length=im.getLength(null);
		int longestSide=width;
		if (height>longestSide) {
			longestSide=height;
		}
		if (length>longestSide) {
			longestSide=length;
		}
		int hilbertIteration=(int)Math.ceil(Math.log(longestSide)/log2);
		int hilbertLength=(int)Math.pow(2,hilbertIteration);
		ConcurrentBidirectionalMap<Integer,String> curve=getCurve(hilbertIteration);
		int[] voxels=raster.getVoxels();
		for (int i=0;i<width*length*height;i++) {
			
				int[] pt=raster.volumize(i);
				//Console.s.println(pt[0]+", "+pt[1]+", "+pt[2]);
				int clr = voxels[i];
				/*int alpha = (byte)(clr & Raster3D.A_FILTER);
		        int red =   (byte)(clr & Raster3D.R_FILTER);
		        int green = (byte)(clr & Raster3D.G_FILTER);
		        int blue =   (byte)(clr & Raster3D.B_FILTER);
		        */
		        
		        int red =   (clr & Raster3D.R_FILTER);
		        int green = (clr & Raster3D.G_FILTER)>>>8;
		        int blue = (clr & Raster3D.B_FILTER)>>>16;
		        int alpha = (clr & Raster3D.A_FILTER)>>>24;
				//Color c=new Color(data[0],data[1],data[2]);
		        Color c=new Color(red,green,blue,alpha);
				gr.setColor(c);
				int spot=Hilbert.squishify(pt[0],pt[2],curve);
				gr.fillRect(x+spot*voxelWidthAndLength, y+voxelHeight*pt[1], voxelWidthAndLength, voxelHeight);
		}
	}
	
	public void drawImage3D(SlicedImage3D im,int x,int y,int voxelWidthAndLength,int voxelHeight) {
		int width=im.getWidth(null);
		int height=im.getHeight(null);
		int length=im.getLength();
		int longestSide=width;
		if (height>longestSide) {
			longestSide=height;
		}
		if (length>longestSide) {
			longestSide=length;
		}
		int hilbertIteration=(int)Math.ceil(Math.log(longestSide)/log2);
		int hilbertLength=(int)Math.pow(2,hilbertIteration);
		//Console.s.println(hilbertIteration);
		ConcurrentBidirectionalMap<Integer,String> curve=getCurve(hilbertIteration);
		for (int l=0;l<length;l++) {
			Image layer=im.getLayer(l);
			BufferedImage buf;
			if (layer instanceof BufferedImage) {
				buf=(BufferedImage)layer;
			} else {
				buf=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
				Graphics bgraph=buf.getGraphics();
				bgraph.drawImage(layer, 0, 0, null);
				bgraph.dispose();
			}
			for (int i=0;i<width;i++) {
				for (int j=0;j<height;j++) {
					//int[] data=new int[3];
					int clr = buf.getRGB(i, j);
			        int red =   (clr & 0x00ff0000) >> 16;
			        int green = (clr & 0x0000ff00) >> 8;
			        int blue =   clr & 0x000000ff;
					//Color c=new Color(data[0],data[1],data[2]);
			        Color c=new Color(red,green,blue);
					gr.setColor(c);
					int spot=Hilbert.squishify(width-1-i,j,curve);
					gr.fillRect(x+spot*voxelWidthAndLength, y+voxelHeight*l, voxelWidthAndLength, voxelHeight);
				}
			}
		}
	}
	
	public void drawImage3D(SlicedImage3D im,int x,int y,int voxelHeight) {
		drawImage3D(im,x,y,1,voxelHeight);
	}
	
	public void drawImage3D(SlicedImage3D im,int x,int y) {
		drawImage3D(im,x,y,1,1);
	}
	
	public ConcurrentBidirectionalMap<Integer,String> getCurve(int iteration){
		ConcurrentBidirectionalMap<Integer,String> curve=hilbertCurves.get(iteration);
		if (curve==null&&iteration>0) {
			//Console.s.println("Calculating iteration "+iteration);
			curve=Hilbert.nextIteration(getCurve(iteration-1));
			hilbertCurves.put(iteration, curve);
			return curve;
		}
		
		return curve;
	}
}
