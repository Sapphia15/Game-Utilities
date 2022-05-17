package graphics.screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.ImageObserver;

/**
 * Created by kb9200 on 10/3/2019.
 */
public abstract class Screen {



    /**What to paint
     *
     */
    public abstract void paint(Graphics g);

    /**Called after every repaint of screen in panel
     *
     */
    public abstract void update();

    /**Called when mouse is pressed
     *
     * @param e
     */
    public void mousePressed(MouseEvent e){

    }

    /**Called when mouse is Released
     *
     * @param e
     */
    public void mouseReleased(MouseEvent e){

    }

    /**Called when mouse is moved
     *
     * @param e
     */
    public void mouseMoved(MouseEvent e){

    }
    
    /**Called when mouse is dragged (moved with mouse button down)
    *
    * @param e
    */
    public void mouseDragged(MouseEvent e) {
    	
    }

    /**Called when a key is pressed
     *
     * @param e
     */
    public void keyPressed(KeyEvent e){

    }
    
    public void mouseScrolled(MouseWheelEvent e) {
    	
    }

    /**Called when a key is released
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {

    }

    /**Called when screen is set
     *
     */
    public void setInit(){}

    /**Called when screen changes from this screen to another
     *
     */
    public void setDeinit(){}
    
    public void drawHorizontalRepeatingImage(Graphics g,Image im,int xOffset,double scale,int x,int y,int width,int height,ImageObserver observer) {
		int imageWidth=(int)(im.getWidth(null));
		int imageHeight=(int)(im.getHeight(null));
		
		int srcY2=(int)(imageHeight);
		/*if (srcY2>imageHeight) {
			srcY2=imageHeight;
		}*/
		int srcY1=(int) (0);
		/*if (srcY1<0) {
			srcY1=0;
		}*/
		//System.out.println("Src ys: ("+srcY1+", "+srcY2+")");
		
		for (int i=0;i<width;) {
			int drawWidth=(int)(imageWidth*scale);
			if (i+drawWidth>width) {
				drawWidth=width-i;
			}
			
			int srcX1=(int)((xOffset+i)/scale)%imageWidth;
			if (srcX1+drawWidth/scale>imageWidth) {
				drawWidth=(int)(imageWidth*scale-srcX1*scale);
			}
			g.drawImage(im, i+x,height-(int)(imageHeight*scale)+y , i+drawWidth+x,height+y , srcX1, srcY1, srcX1+(int)(drawWidth/scale), srcY2, observer);
			i+=drawWidth;
		}
	}

	protected abstract void keyDown(int key);
	
	protected void keyTyped(KeyEvent e) {
		
	}
}
