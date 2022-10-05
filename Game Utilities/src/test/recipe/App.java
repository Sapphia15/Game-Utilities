package test.recipe;

import java.util.ArrayList;

import gameutil.text.Console;

public class App {
	static ArrayList<Recipe> recipes=new ArrayList<>();
	static ArrayList<Item> items=new ArrayList<>();
	static ArrayList<Item> baseItems=new ArrayList<>();
	
	public static void main(String[] unicorns) {
		Console.s.setTheme(Console.theme.sea);
		Console.s.setAutoScroll(true);
		addBaseItems();
		/*
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				for (int k=0;k<10;k++) {
					for (int l=0;l<10;l++) {
						Console.s.println("Adding ("+i+","+j+","+k+","+l+")");
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+(i+1)+","+j+","+k+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+(i-1)+","+j+","+k+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+(j+1)+","+k+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+(j-1)+","+k+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+j+","+(k+1)+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+j+","+(k-1)+","+l+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+j+","+k+","+(l+1)+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+","+l+")","("+i+","+j+","+k+","+(l-1)+")"));
					}
				}
			}
			
		}*/
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				for (int k=0;k<10;k++) {
						Console.s.println("Adding ("+i+","+j+","+k+")");
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+(i+1)+","+j+","+k+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+(i-1)+","+j+","+k+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+i+","+(j+1)+","+k+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+i+","+(j-1)+","+k+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+i+","+j+","+(k+1)+")"));
						recipes.add(new Recipe("("+i+","+j+","+k+")","("+i+","+j+","+(k-1)+")"));
				}
			}
			
		}
		generateItems();
		
		//add base recipes
		addBaseRecipes();
		Console.s.clr();
		Console.s.println(getAppendableItemInfo());
	}
	
	public static void addBaseRecipes() {
		recipes.add(new Recipe("Wood",new Stack("Stick",20)));
		recipes.add(new Recipe("Stone",new Stack("Rock",5)));
		recipes.add(new Recipe(new String[] {"Rock","Hypersilk","Stick"},"Hammer"));
		recipes.add(new Recipe(new String[] {"Sand","Wood"},"Glass"));
		
		recipes.add(new Recipe(new String[] {"Iron Ore","Wood"},"Iron Bars"));
		recipes.add(new Recipe(new String[] {"Deadly Ore","Wood"},"Deadly Bars"));
		recipes.add(new Recipe(new String[] {"Solenoid Ore","Wood"},"Solenoid Bars"));
		recipes.add(new Recipe(new Stack[] {new Stack("Stick"),new Stack("Iron Bars",2)},"Iron Pick"));
		recipes.add(new Recipe(new Stack[] {new Stack("Stick"),new Stack("Deadly Bars",2)},"Deadly Pick"));
		
		recipes.add(new Recipe(new Stack[] {new Stack("Hypersilk"),new Stack("Deadly Bars",3),new Stack("Iron Bars")},"Compass"));
		recipes.add(new Recipe(new Stack[] {new Stack("Iron Bars",3),new Stack("Wood",3)},"Chest"));
		
		recipes.add(new Recipe(new Stack[] {new Stack("Red Lens"),new Stack("Green Lens"),new Stack("Blue Lens"),new Stack("Midnight Wood",2)},"4D Glasses"));
		recipes.add(new Recipe(new Stack[] {new Stack("Deadly Bars",3),new Stack("Hypersilk"),new Stack("Stick")},"Ultrahammer"));
		recipes.add(new Recipe(new Stack[] {new Stack("Solenoid Bars",3),new Stack("Iron Bars")},"Solenoid Collector"));
	}
	
	public static void addBaseItems() {
		baseItems.add(new Item("Air"));
		baseItems.add(new Item("Grass"));
		baseItems.add(new Item("Dirt"));
		baseItems.add(new Item("Stone"));
		baseItems.add(new Item("Wood"));
		baseItems.add(new Item("Leaf"));
		baseItems.add(new Item("Lava"));
		baseItems.add(new Item("Iron Ore"));
		baseItems.add(new Item("Deadly Ore"));
		baseItems.add(new Item("Chest"));
		baseItems.add(new Item("Midnight Grass"));
		baseItems.add(new Item("Midnight Soil"));
		baseItems.add(new Item("Midnight Stone"));
		baseItems.add(new Item("Midnight Wood"));
		baseItems.add(new Item("Midnight Leaf"));
		baseItems.add(new Item("Bush"));
		baseItems.add(new Item("Midnight Bush"));
		baseItems.add(new Item("Red Flower"));
		baseItems.add(new Item("White Flower"));
		baseItems.add(new Item("Blue Flower"));
		baseItems.add(new Item("Tall Grass"));
		baseItems.add(new Item("Sand"));
		baseItems.add(new Item("Sandstone"));
		baseItems.add(new Item("Cactus"));
		baseItems.add(new Item("Snow"));
		baseItems.add(new Item("Ice"));
		baseItems.add(new Item("Snowy Bush"));
		baseItems.add(new Item("Glass"));
		baseItems.add(new Item("Solenoid Ore"));
		baseItems.add(new Item("Stick"));
		baseItems.add(new Item("Hammer"));
		baseItems.add(new Item("Iron Pick"));
		baseItems.add(new Item("Deadly Pick"));
		baseItems.add(new Item("Iron Axe"));
		baseItems.add(new Item("Deadly Axe"));
		baseItems.add(new Item("Ulrahammer"));
		baseItems.add(new Item("Solenoid Collector"));
		baseItems.add(new Item("Rock"));
		baseItems.add(new Item("Hypersilk"));
		baseItems.add(new Item("Iron Bars"));
		baseItems.add(new Item("Deadly Bars"));
		baseItems.add(new Item("Solenoid Bars"));
		baseItems.add(new Item("Compass"));
		baseItems.add(new Item("4D Glasses"));
		baseItems.add(new Item("Klein Bottle"));
		baseItems.add(new Item("Health Potion"));
		baseItems.add(new Item("Red Lens"));
		baseItems.add(new Item("Green Lens"));
		baseItems.add(new Item("Blue Lens"));
	}
	
	public static String getRecipeChunk(){
		String recipe="";
		
		for (int i=0;i<recipes.size();i++) {
			recipe+=recipes.get(i).getRecipe();
			if (i<recipes.size()-1) {
				recipe+=", \r\n";
			}
		}
		
		return recipe;
	}
	
	public static String getAppendableRecipeChunk() {
		return ", "+getRecipeChunk();
	}
	
	public static String getRecipeList() {
		return "[\r\n"+getRecipeChunk()+"\r\n]";
	}
	
	public static void generateItems() {
		for (Recipe r:recipes) {
			for (Stack s:r.materials) {
				generateItem(s.getItem());
			}
			generateItem(r.getOutput().getItem());
		}
	}
	
	public static void generateItem(Item s) {
		Console.s.println("Generating "+s.name);
		for (Item i:items) {
			if (i.getName().equals(s.getName())) {
				return;
			}
		}
		for (Item i:baseItems) {
			if (i.getName().equals(s.getName())) {
				return;
			}
		}
		items.add(s);
	}
	
	public static String getAppendableItemInfo() {
		String info="";
		for (Item i:items) {
			info+=",\r\n"+i.getInfo();
		}
		return info;
	}
}
