package test.KPG;

import gameutil.text.entity.Entity;
import gameutil.text.entity.Statistic;

public class Player extends Entity {
	private Statistic hp;
	private Statistic dmg;
	private Statistic regen;
	private Statistic dex;
	private boolean alive;
	public Player(){
		hp=new Statistic("HP",10,10);
		dmg=new Statistic("DAMAGE",1,0,1);
		dex=new Statistic("DEX",1,20,100,1);
		alive=true;
	}
	public void takeDamage(int dmg){
		hp.decrease(dmg);
		if (hp.getVal()==0){
			
		}
	}
	 
	public void die(){
		alive=false;
	}
	public boolean isAlive(){
		return alive;
	}
}
