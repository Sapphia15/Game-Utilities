package graphics;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class SlicedImage3D {
	Image[] layers;
	
	/**Images should all have same dimensions
	 * 
	 * @param y
	 * @return
	 */
	public SlicedImage3D(Image[] layers) {
		this.layers=layers;
	}
	
	public SlicedImage3D(int length) {
		this.layers=new Image[length];
	}
	
	public Image getLayer(int z) {
		return layers[z];
	}
	
	public int getWidth(ImageObserver o) {
		if (layers.length>0) {
			return layers[0].getWidth(o);
		}
		 return 0;
	}
	
	public int getHeight(ImageObserver o) {
		if (layers.length>0) {
			return layers[0].getHeight(o);
		}
		return 0;
	}
	
	public int getLength() {
		return layers.length;
	}
	
	
}
