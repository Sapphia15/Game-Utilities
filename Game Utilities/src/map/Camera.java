package map;


import javax.swing.*;

import gameutil.geom.g2D.PointR2;

import java.awt.*;
import java.util.Observer;

/**
 * 
 * @author Keegan
 *
 */

public class Camera {
    private Rectangle CameraArea;
    private JPanel observer;
    public enum AREA_MODE{full,proportional,independent};
    public PointR2 location; //location is a point in the center of the camera
    PointR2 screenPos;
    private float proportionX=1;
    private float proportionY=1;
    private int width=0;
    private int height=0;

    /**Instantiate a camera with the window in which it resides as the observer
     * full is the default AreaMode
     * @param observer
     */
    public Camera(JPanel observer){
        this.observer=observer;
        CameraArea=observer.getBounds();
        location=new PointR2(0,0);
        screenPos=new PointR2(0,0);
    }
    
    /**Instantiate a camera with no observer, good for text games
     * independant is default areaMode;
     * @param observer
     */
    public Camera(PointR2 gameLocation,int width,int height){
        this.observer=null;
        location=new PointR2(gameLocation.getX(),gameLocation.getY());
        screenPos=new PointR2(0,0);
        setScreenArea(width,height);
    }


    /**Updates the camera area (call this within game loop if the game window is resizable)
     *
     */
    public void update(){
        AreaMode.updateArea(this);
    }

    /**Returns the area of the camera on the screen
     *
     * @return
     */
    public Rectangle getArea(){
        return  AreaMode.getArea(this);
    }

    /**Sets the mode to determine the position and area of the camera with
     *
     * @param mode
     */
    private void setAreaMode(AREA_MODE mode){
        AreaMode.setAreaMode(mode);
        AreaMode.updateArea(this);
    }

    /**Sets the proportions of the camera area in reference to the window size and sets the AreaMode to proportional
     *
     * @param x
     * @param y
     */
    public void setProportions(float x,float y){
        proportionX=x;
        proportionY=y;
        setAreaMode(AREA_MODE.proportional);
    }

    /**Sets the camera area to a specific width and height and sets AreaMode to independent
     *
     * @param width
     * @param height
     */
    public void setScreenArea(int width,int height){
        this.width=width;
        this.height=height;
        setAreaMode(AREA_MODE.independent);
    }

    /**Sets the camera area to a specific width and height at the specified screen position and sets AreaMode to independent
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setScreenArea(int x,int y,int width,int height){
        this.width=width;
        this.height=height;
        screenPos=new PointR2(x,y);
        setAreaMode(AREA_MODE.independent);
    }

    /**Sets the camera area to a specific width and height at the specified screen position and sets AreaMode to independent
     *
     * @param screenPos
     * @param width
     * @param height
     */
    public void setScreenArea(PointR2 screenPos,int width,int height){
        this.width=width;
        this.height=height;
        this.screenPos=screenPos;
        setAreaMode(AREA_MODE.independent);
    }

    /**Sets the camera area to the area of a specified rectangle and sets AreaMode to independent
     *
     * @param area
     */
    public void setScreenArea(Rectangle area){
        this.width=area.width;
        this.height=area.height;
        this.screenPos=new PointR2(area.x,area.y);
        setAreaMode(AREA_MODE.independent);
    }

    /**Sets the area mode to full
     *
     */
    public void setFullScreen(){
        setAreaMode(AREA_MODE.full);
    }







    /**Area mode class is designed to reduce conditional computation
     *
     */
    private static class AreaMode {

        private Full full;
        private Proportional proportional;
        private Independent independent;
        private AreaMode currentMode;
        private static AreaMode instance=new AreaMode(true);//singleton

        /**Used to set instance
         *
         */
        private AreaMode(boolean init){
            if (init) {
                //intantiate areamodes
                full = new Full();
                proportional = new Proportional();
                independent = new Independent();
                currentMode = full;
            }
        }

        /**for use in camera to get the area
         *
         * @return
         */
        public static Rectangle getArea(Camera observer){
            return instance.instanceGetArea(observer);
        }

        public static void setAreaMode(AREA_MODE mode){
            if (mode==AREA_MODE.full){
                instance.currentMode=instance.full;
            } else if (mode==AREA_MODE.proportional){
                instance.currentMode=instance.proportional;
            } else if (mode==AREA_MODE.independent){
                instance.currentMode=instance.independent;
            }
        }

        public static void updateArea(Camera observer){instance.instanceUpdateArea(observer);}

        /**Gets the area from current instance
         *
         * @return
         */
        public Rectangle instanceGetArea(Camera observer){return currentMode.instanceGetArea(observer);};

        /**Updates the area in current instance
         *
         * @return
         */
        public void instanceUpdateArea(Camera observer){currentMode.instanceUpdateArea(observer);};
    }

    /**The camera is the size of the observer
     *
     */
    private static class Full extends AreaMode {

        public Full(){
            super(false);
        }

        @Override
        public Rectangle instanceGetArea(Camera observer) {
            return new Rectangle((int) (observer.location.getX()-(((double) observer.CameraArea.width)/2d)),(int) (observer.location.getY()-(((double) observer.CameraArea.height)/2d)),observer.CameraArea.width,observer.CameraArea.height);
        }

        @Override
        public void instanceUpdateArea(Camera observer) {
            observer.CameraArea=observer.observer.getBounds();
        }
    }

    /**The camera is a size proportional to the observer
     *
     */
    private static class Proportional extends AreaMode {

        public Proportional(){
            super(false);
        }

        @Override
        public Rectangle instanceGetArea(Camera observer) {
            return new Rectangle((int) (observer.location.getX()-(((double) observer.CameraArea.width)/2d)),(int) (observer.location.getY()-(((double) observer.CameraArea.height)/2d)),observer.CameraArea.width,observer.CameraArea.height);
        }

        @Override
        public void instanceUpdateArea(Camera observer) {
            observer.CameraArea=new Rectangle((int) Math.round(observer.screenPos.getX()),(int) Math.round(observer.screenPos.getY()),Math.round(observer.observer.getWidth()*observer.proportionX),Math.round(observer.observer.getHeight()*observer.proportionY));
        }
    }

    /**The camera is a specific size independent of the observer
     *
     */
    private static class Independent extends AreaMode {

        public Independent(){
            super(false);
        }

        @Override
        public Rectangle instanceGetArea(Camera observer) {
        	return new Rectangle((int) (observer.location.getX()-observer.width/2),(int) (observer.location.getY()-observer.height/2),observer.CameraArea.width,observer.CameraArea.height);
        }

        @Override
        public void instanceUpdateArea(Camera observer) {
            observer.CameraArea=new Rectangle((int) Math.round(observer.screenPos.getX()),(int) Math.round(observer.screenPos.getY()),observer.width,observer.height);
        }
    }



}