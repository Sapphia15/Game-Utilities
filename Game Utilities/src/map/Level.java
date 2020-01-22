package map;

import ascii.sprites.Block;
import ascii.sprites.Sprite;
import gameutil.text.*;
import gameutil.text.Console;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by kb9200 on 10/8/2019.
 */
public class Level {
    private ArrayList<Sprite> sprites;
    private Image background;
    private String path;
    private int crystalsNeeded=0;
    
    public Level(){
        //make a blank level
        sprites=new ArrayList<>();
        path="dat/NULL.lv";
        /*sprites.add(new Block(new Point(0,4)));
        sprites.add(new Block(new Point(1,4)));
        sprites.add(new Block(new Point(2,4)));
        sprites.add(new Block(new Point(-1,4)));
        sprites.add(new Block(new Point(-2,4)));*/
    }

    public Level(String path){
        sprites=new ArrayList<>();
        this.path=path;
        //load the level (if it has a file)
        load(path,this);
    }

    public final void update(){
    	ArrayList<Sprite> spritesToRemove=new ArrayList<>();
        for (Sprite s:sprites){
            if (s.inWorld()) {
            	//Update sprites
            	s.update();
            } else {
            	//Sprite not in world, so remove it
            	spritesToRemove.add(s);//use other list to avoid concurrent modification exception
            }
        }
        sprites.removeAll(spritesToRemove);//remove necessary sprites
    }

    public void save() throws IOException {
        save(path);

    }
    
    public void save(String path) throws IOException {
    	//save to path
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(String.valueOf(crystalsNeeded));//save crystals needed
        writer.newLine();
        for (Sprite s : sprites){ //save sprites
            writer.write("S "+s.getClass().getName()+" "+s.getProps());
            writer.newLine();
        }
        writer.write("||");
        writer.close();

    }

    /**Load a level into a new level object
     *
     * @param path
     * @return
     */
    public static Level load(String path){
        return load(path,new Level());
    }

    /**Load a level into the specified level object
     *
     * @param path
     * @param level
     * @return
     */
    public static Level load(String path,Level level){
        String read="";
        BufferedReader reader=null;
        level.path=path;

        try {
            reader=new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            //level is not saved, so keep the level the way it is and return
            System.out.println("No information to load for level at path: "+level.path);
            return null;
        }
        int line=1;
        try {
        	level.crystalsNeeded=Integer.parseInt(reader.readLine());//set crystalls needed to win
            while (!read.equals("||")) {
                read = reader.readLine();
                Argument args=Argument.getArgs(read);
                if (args.cmd().equals("S")){
                    args=Argument.getArgs(read.substring(2));
                    level.sprites.add((Sprite) Argument.constructFromArgs(args));
                }
                line++;
            }
        } catch (IOException e) {
            Console.s.println("Error! Failed to load level because IO exception!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(1);
        }
        //read the next line
        try {
            read=reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            //stop reading
            read="||";
        }
        System.out.println("Loaded "+level.sprites.size()+" sprites for level "+level.path);
        return level;
    }

    Image getBackground(){
        return background;
    }

    public boolean add(Sprite s){
        return sprites.add(s);
    }

    public boolean remove(Sprite s) {
        return sprites.remove(s);
    }

    public ArrayList<Sprite> getSprites(){
        return sprites;
    }
    
    public int crystalsToWin() {
    	return crystalsNeeded;
    }
}
