package test.recipe;

public class Item {
	
	String name;
	
	public Item(String name) {
		this.name=name;
	}
	
	public String getInfo() {
		return "	\r\n"
				+ "	\""+name+"\": {\r\n"
				+ "		\"type\": \"material\",\r\n"
				+ "		\"baseAttributes\": {\r\n"
				+ "		}\r\n"
				+ "	}";
	}
	
	public String getRecipeItem(int count) {
		return "{\"name\": \""+name+"\", \"count\": "+count+"}";
	}
	
	public String getRecipeItem() {
		return getRecipeItem(1);
	}
	
	public String getName() {
		return name;
	}
	
	public Stack toStack() {
		return new Stack(this);
	}
	
	public Stack toStack(int amount) {
		return new Stack(this,amount);
	}
}
