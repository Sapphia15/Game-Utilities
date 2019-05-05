package test.KPG;

import gameutil.text.entity.Entity;
import gameutil.text.entity.Statistic;

public class Enemy extends Entity {
	private Statistic hp;
	private Statistic dmg;
	private Statistic regen;
	private Statistic dex;
	private String name;
	private boolean alive;
	public Enemy(){
		name="untitled";
		hp=new Statistic("HP",10,10);
		dmg=new Statistic("DAMAGE",1,0,1);
		dex=new Statistic("DEX",1,20,100,1);
		alive=true;
	}
	public Enemy(String name){
		this.name=name;
		hp=new Statistic("HP",10,10);
		dmg=new Statistic("DAMAGE",1,0,1);
		dex=new Statistic("DEX",1,20,100,1);
		alive=true;
	}
	public Enemy(String name,int hp,int dmg){
		this.name=name;
		this.hp=new Statistic("HP",hp,hp);
		this.dmg=new Statistic("DAMAGE",dmg,0,1);
		dex=new Statistic("DEX",1,20,100,1);
		alive=true;
	}
	public Enemy(String name,int hp,int dmg,int dex){
		this.name=name;
		this.hp=new Statistic("HP",hp,hp);
		this.dmg=new Statistic("DAMAGE",dmg,0,1);
		this.dex=new Statistic("DEX",1,dex,0,1);
		alive=true;
	}
	public void attack(Player p){
		p.takeDamage(dmg.getVal());
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
