package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import gameutil.ByteUtil;
import gameutil.math.geom.Orthotope;
import gameutil.math.geom.Point;
import gameutil.math.geom.Tuple;
import gameutil.math.geom.g2D.LineR2;
import gameutil.text.Console;

public class Graphics3D extends Graphics{
	
	int x=0;
	int y=0;
	int z=0;
	int color=0xff000000;
	Raster3D raster;
	
	protected Graphics3D(Raster3D raster) {
		this.raster=raster;
	}
	
	protected Graphics3D(int x,int y,int z,int c,Raster3D raster) {
		this(raster);
		this.x=x;
		this.y=y;
		this.z=z;
		color=c;
	}

	@Override
	public Graphics create() {
		
		// TODO Auto-generated method stub
		return new Graphics3D(x,y,z,color,raster);
	}

	@Override
	public void translate(int x, int y) {
		translate(x,y,0);
	}
	
	public void translate(int x, int y,int z) {
		this.x+=x;
		this.y+=y;
		this.z+=z;
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color((color&Raster3D.R_FILTER),(color&Raster3D.G_FILTER)>>>8,(color&Raster3D.B_FILTER)>>>16,(color&Raster3D.A_FILTER)>>>24);
		
	}

	@Override
	public void setColor(Color c) {
		color=c.getRed()|c.getGreen()<<8|c.getBlue()<<16|c.getAlpha()<<24;
		//Console.s.println(color);
	}
	
	public void copyVolume(int x,int y,int z,int width,int length,int height,int dx,int dy,int dz) {
		raster.copyVolume(x, y, z, width, length, height, dx, dy, dz);
	}
	
	public void drawLine(int x1, int y1,int z1, int x2, int y2,int z2) {
		double y=y1;
		double z=z1;
		int minY=y1;
		int minZ=z1;
		LineR2 XY=null;
		LineR2 XZ=null;
		int x=x1;
		if (x>x2) {
			x=x2;
			y=y2;
			z=z2;
		}
		if (minZ>z2) {
			minZ=z2;
		}
		if (minY>y2) {
			minY=y2;
		}
		
		try {
			//TODO these are broken sort of! need fix. need fix lines. need fix. need... need fix. fix. yesh. need fix.
			try {
				XY=new LineR2(x1,y1,x2,y2);
			} catch (Exception e) {
				XZ=new LineR2(0, y1);
			}
			try {
				XZ=new LineR2(x1,z1,x2,z2);
			} catch (Exception e) {
				XZ=new LineR2(0, z1);
			}
			Raster3D c=new Raster3D(x2-x1+1,z2-z1+1,y2-y1+1);
			int[] pt=new int[] {0,0,0};
			c.setVoxel(0,0,0,color);
			for (int i=0;i<c.width;i++) {
				y=XY.equation(i);
				z=XZ.equation(i);
				double dy=y-pt[1];
				double dz=z-pt[2];
				pt[0]=i;
				if (dy<.5&&dy>-.5) {
					if (dz<=.5&&dz>=-.5) {
						c.setVoxel(pt[0],pt[1],pt[2],color);
					} else {
						while (dz>.5||dz<-.5) {
							
							if (dz>.5) {
								pt[2]+=1;
								dz-=.5;
							} else if (dz<-.5) {
								pt[2]-=1;
								dz+=.5;
							}
							
							c.setVoxel(pt[0],pt[1],pt[2],color);
						}
					}
					
				} else {
					if (dy>dz) {
						while (dy>.5||dy<-.5) {
							if (dy>.5) {
								pt[1]+=1;
								dy-=.5;
							} else if (dy<-.5) {
								pt[1]-=1;
								dy+=.5;
							}
							
							if (dz>.5) {
								pt[2]+=1;
								dz-=.5;
							} else if (dz<-.5) {
								pt[2]-=1;
								dz+=.5;
							}
							
							c.setVoxel(pt[0],pt[1],pt[2],color);
						}
					} else {
						while (dz>.5||dz<-.5) {
						
							if (dy>.5) {
								pt[1]+=1;
								dy-=.5;
							} else if (dy<-.5) {
								pt[1]-=1;
								dy+=.5;
							}
							
							if (dz>.5) {
								pt[2]+=1;
								dz-=.5;
							} else if (dz<-.5) {
								pt[2]-=1;
								dz+=.5;
							}
						
							c.setVoxel(pt[0],pt[1],pt[2],color);
						}
					}
				}
			}
			c.computeVoxels();
			raster.blendVoxels(x, minY, minZ, c.width, c.length, c.height,c.getVoxels());
			raster.computeVoxels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void drawCuboid(int x,int y,int z,int width,int length,int height) {
		int[] colorTopBot=new int[width*length];
		int[] colorLeftRight=new int[height*length];
		int[] colorFrontBack=new int[height*width];
		
		for (int i=0;i<width*length;i++) {
			colorTopBot[i]=color;
		}
		for (int i=0;i<height*length;i++) {
			colorLeftRight[i]=color;
		}
		for (int i=0;i<width*height;i++) {
			colorFrontBack[i]=color;
		}
		raster.setVoxels(x, y, z, width, length, 1, colorTopBot);
		raster.setVoxels(x, y+height-1, z, width, length, 1, colorTopBot);
		
		raster.setVoxels(x, y, z, 1, length, height, colorLeftRight);
		raster.setVoxels(x+width-1, y, z, 1, length, height, colorLeftRight);
		
		raster.setVoxels(x, y, z, width, 1, height, colorTopBot);
		raster.setVoxels(x, y, z+length-1, width, 1, height, colorFrontBack);
		raster.computeVoxels();
	}
	
	public void fillCuboid(int x,int y,int z,int width,int length,int height) {
		int[] color=new int[width*length*height];
		for (int i=0;i<width*length*height;i++) {
			color[i]=this.color;
		}
		
		raster.setVoxels(x, y, z, width, length, height, color);
		raster.computeVoxels();
	}

	@Deprecated
	@Override
	public void setPaintMode() {
		// TODO Auto-generated method stub
		
	}
	
	@Deprecated
	@Override
	public void setXORMode(Color c1) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	
	@Deprecated
	@Override
	public FontMetrics getFontMetrics(Font f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public Rectangle getClipBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Orthotope getClipBound() {
		// TODO Auto-generated method stub
		return new Orthotope(new Point(new double[] {x,y,z}),new Point(new double[] {raster.width,raster.height,raster.length}));
	}
	
	@Deprecated
	@Override
	public void clipRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Deprecated
	@Override
	public void setClip(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public Shape getClip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public void setClip(Shape clip) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void fillRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void clearRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawOval(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void fillOval(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawString(String str, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Color bgcolor, ImageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		
		
	}

}
