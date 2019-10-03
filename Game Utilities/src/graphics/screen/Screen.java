package graphics.screen;

/**
 * Created by kb9200 on 10/3/2019.
 */
public abstract class Screen {

    /**What to paint
     * 
     */
    public abstract void paint();

    /**Called after every repaint of screen in panel
     * 
     */
    public abstract void update();
    
    
}
