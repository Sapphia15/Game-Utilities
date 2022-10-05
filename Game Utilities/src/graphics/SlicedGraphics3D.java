package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class SlicedGraphics3D {
	Graphics[] g;
	Color c=Color.black;
	
	public SlicedGraphics3D(Graphics[] g) {
		this.g=g;
	}
	
	public void drawCube(int x,int y,int z,int sideLength) {
		for (int i=0;i<sideLength;i++) {
			int layer=z+i;
			if (layer>g.length) {
				break;
			}
			if (i==0||i==sideLength-1) {
				g[layer].fillRect(x, y, sideLength, sideLength);
			} else {
				g[layer].drawRect(x, y, sideLength-1, sideLength-1);
			}
		}
	}
	
	public void fillCube(int x,int y,int z,int sideLength) {
		for (int i=0;i<sideLength;i++) {
			int layer=z+i;
			if (layer>g.length) {
				break;
			}
			
			g[layer].fillRect(x, y, sideLength, sideLength);
		}
	}
	
	public void setColor(Color c) {
		this.c=c;
		for (Graphics gr:g) {
			gr.setColor(c);
		}
	}
	
	public void dispose() {
		for (Graphics gr:g) {
			gr.dispose();
		}
	}
}
