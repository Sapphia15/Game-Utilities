package map;

import ascii.App;
import ascii.sprites.Crystal;
import ascii.sprites.Item;
import ascii.sprites.Sprite;
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
    Point lastCameraLocation;
    JPanel observer;

    public Map(JPanel observer){
        levels=new ArrayList<>();
        this.observer=observer;
        camera=new Camera(observer);
        lastCameraLocation=camera.location;
        setLevel(0);
        screenSprites=getSpritesOnScreen();
    }

    public Map(JPanel observer,ArrayList<Level> levels){
        this.levels=levels;
        this.observer=observer;
        camera=new Camera(observer);
        lastCameraLocation=camera.location;
        screenSprites=getSpritesOnScreen();
        setLevel(0);
    }
    
    public Map(String path,int lvNo){
        levels=new ArrayList<>();
        for (int i=1; i<3;i++) {
        	Level loadedLevel=Level.load(path+"/"+i+".lv");
        	if (!(loadedLevel==null)) {
        		levels.add(loadedLevel);
        	} else {
        		levels.add(Level.load("levels/"+i+".lv"));
        	}
        }
        this.observer=null;
        camera=new Camera();
        lastCameraLocation=camera.location;
        setLevel(lvNo);
        screenSprites=getSpritesOnScreen();
    }

    public void update(){
        //update the current level
        currentLevel.update();
        camera.update();
        screenSprites=getSpritesOnScreen();
    }

    public static Level getCurrentLevel(){
        return currentLevel;
    }

    public static Camera getCamera(){
        return camera;
    }
    
    public void drawMap(Console c) {
    	//x of point is column, y of point is row
    	Hashtable<Point,Sprite> spritesToDraw=new Hashtable<>();
    	
    	int cameraX=camera.getArea().width/2;
    	int cameraY=camera.getArea().height/2;
    	for (Sprite s:screenSprites) {
    		int x=s.getPos().x-camera.location.x+cameraX;
    		int y=s.getPos().y*-1-camera.location.y*-1+cameraY;//invert y so that y+ is up
    		spritesToDraw.put(new Point(x,y),s);
    	}
    	
    	spritesToDraw.put(new Point(cameraX,cameraY),App.p);
    	
    	for (int row=0;row<camera.getArea().height;row++) {
    		for (int column=0;column<camera.getArea().width;column++) {
    			if (spritesToDraw.containsKey(new Point(column,row))) {
    				Sprite s=spritesToDraw.get(new Point(column,row));
    				Console.s.print(s.getChar());
    				if (s.useSpace()) {
    					Console.s.print(" |");
    				}
    			}else {
    				Console.s.print("░");
    			}
    		}
    		Console.s.println("");
    		
    	}
    	Console.s.println("Crystals Used: "+Crystal.crystalsUsed()+"/"+currentLevel.crystalsToWin());
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
    private ArrayList<Sprite> getSpritesOnScreen(){
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
    }
    
    public Item itemAt(Point pos) {
    	if (camera.getArea().contains(pos)) {
    		for (Sprite s:screenSprites) {
    			if (s.getPos().distance(pos)==0&&s.getClass().getSuperclass().getName().equals("ascii.sprites.Item")) {
    				return (Item) s;
    			}
    		}
    	} else {
    		for (Sprite s:currentLevel.getSprites()) {
    			if (s.getPos().distance(pos)==0&&s.getClass().getSuperclass().getName().equals("ascii.sprites.Item")) {
    				return (Item) s;
    			}
    		}
    	}
    	return null;
    }
    
    public boolean isItemAt(Point pos) {
    	if (camera.getArea().contains(pos)) {
    		for (Sprite s:screenSprites) {
    			if (s.getPos().distance(pos)==0&&s.getClass().getSuperclass().getName().equals("ascii.sprites.Item")) {
    				return true;
    			}
    		}
    	} else {
    		for (Sprite s:currentLevel.getSprites()) {
    			if (s.getPos().distance(pos)==0&&s.getClass().getSuperclass().getName().equals("ascii.sprites.Item")) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean spriteAt(Point pos) {
    	if (camera.getArea().contains(pos)) {
    		for (Sprite s:screenSprites) {
    			if (s.getPos().distance(pos)==0) {
    				return true;
    			}
    		}
    	} else {
    		for (Sprite s:currentLevel.getSprites()) {
    			if (s.getPos().distance(pos)==0) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean solidAt(Point pos) {
    	if (camera.getArea().contains(pos)) {
    		for (Sprite s:screenSprites) {
    			if (s.getPos().distance(pos)==0&&s.isSolid()) {
    				return true;
    			}
    		}
    	} else {
    		for (Sprite s:currentLevel.getSprites()) {
    			if (s.getPos().distance(pos)==0&&s.isSolid()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
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
