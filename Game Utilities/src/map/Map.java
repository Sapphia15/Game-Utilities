package map;


import Sprite.Sprite;
import gameutil.geom.g2D.PointR2;
import gameutil.text.Console;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**Class to handle the mapping of the levels
 *
 */
public class Map {
	int lvNo=0;
    ArrayList<Level> levels;
    //need to make these lists into hashtables for layers.
    private ArrayList<Sprite> screenSprites;
    public static Level currentLevel;
    private static Camera camera;
    PointR2 lastCameraLocation;
    JPanel observer;

    public Map(JPanel observer){
        levels=new ArrayList<>();
        this.observer=observer;
        camera=new Camera(observer);
        lastCameraLocation=camera.location;
        setLevel(0);
        //screenSprites=getSpritesOnScreen();
    }

    public Map(JPanel observer,ArrayList<Level> levels){
        this.levels=levels;
        this.observer=observer;
        camera=new Camera(observer);
        lastCameraLocation=camera.location;
        //screenSprites=getSpritesOnScreen();
        setLevel(0);
    }
    

    public void update(){
        //update the current level
        currentLevel.update();
        camera.update();
        //screenSprites=getSpritesOnScreen();
    }

    public static Level getCurrentLevel(){
        return currentLevel;
    }

    public static Camera getCamera(){
        return camera;
    }
    
    /**Set the level by number
     *
     * @param levelNo
     */
    public void setLevel(int levelNo){
        currentLevel=levels.get(levelNo);
        lvNo=levelNo;
    }

    /**Returns an array of all the sprites visible on the screen
     *
     * @return
     */
    /*private ArrayList<Sprite> getSpritesOnScreen(){
        ArrayList<Sprite> screenSprites=new ArrayList<Sprite>();
        for (Sprite s:currentLevel.getSprites()){
            if (camera.getArea().contains(s.getPos())) {
                screenSprites.add(s);
            }
        }
        return screenSprites;
    }
    
    public ArrayList<Sprite> getScreenSprites(){
    	return screenSprites;
    }*/
    
    
    public int getLvNo() {
    	return lvNo;
    }
    
    public boolean isLastLevel() {
    	return lvNo==levels.size()-1;
    }
    
    public void saveLevels(String path) {
    	//save levels
    	for (Level l:levels) {
    		try {
				l.save(path+"/"+(levels.indexOf(l)+1)+".lv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Console.s.println("An error occured while trying to save levels. Press any key to continue.");
				Console.s.pause();
			}
    	}
    }
}
