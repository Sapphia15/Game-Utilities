package test.recipe;

public class Stack {
	Item i;
	int amount;
	
	public Stack(Item i) {
		this.i=i;
		amount=1;
	}
	
	public Stack(Item i,int amount) {
		this.i=i;
		this.amount=amount;
	}
	
	public Stack(String s) {
		this.i=new Item(s);
		amount=1;
	}
	
	public Stack(String s,int amount) {
		this.i=new Item(s);
		this.amount=amount;
	}
	
	public String getInfo() {
		return i.getInfo();
	}
	
	public String getRecipeItem() {
		return i.getRecipeItem(amount);
	}
	
	public String getName() {
		return i.name;
	}
	
	public void add(int amount) {
		this.amount+=amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Item getItem(){
		return i;
	}
}
