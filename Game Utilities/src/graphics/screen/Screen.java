package graphics.screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

    /**Called when a key is pressed
     *
     * @param e
     */
    public void keyPressed(KeyEvent e){

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

}
