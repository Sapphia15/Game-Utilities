package test.recipe;

import java.util.ArrayList;
import java.util.Hashtable;

public class Recipe {
	ArrayList<Stack> materials=new ArrayList<>();
	Stack output;
	
	
	
	public Recipe(Item[] inputs,Stack output) {
		addItems(inputs);
		this.output=output;
	}
	
	public Recipe(Stack[] inputs,Stack output) {
		addItems(inputs);
		this.output=output;
	}
	
	public Recipe(String[] inputs,Stack output) {
		addItems(inputs);
		this.output=output;
	}
	
	public Recipe(Stack input,Stack output) {
		addItem(input);
		this.output=output;
	}
	
	public Recipe(Item input,Stack output) {
		addItem(input);
		this.output=output;
	}
	
	public Recipe(String input,Stack output) {
		addItem(input);
		this.output=output;
	}
	
	public Recipe(String[] inputs,Item output) {
		this(inputs,output.toStack());
	}
	
	public Recipe(Item[] inputs,Item output) {
		this(inputs,output.toStack());
	}
	
	public Recipe(Stack[] inputs,Item output) {
		this(inputs,output.toStack());
	}
	
	public Recipe(Stack input,Item output) {
		this(input,output.toStack());
	}
	
	public Recipe(Item input,Item output) {
		this(input,output.toStack());
	}
	
	public Recipe(String[] input,String output) {
		this(input,new Stack(output));
	}
	
	public Recipe(Item[] inputs,String output) {
		this(inputs,new Stack(output));
	}
	
	public Recipe(Stack[] inputs,String output) {
		this(inputs,new Stack(output));
	}
	
	public Recipe(Stack input,String output) {
		this(input,new Stack(output));
	}
	
	public Recipe(Item input,String output) {
		this(input,new Stack(output));
	}
	
	public Recipe(String input,String output) {
		this(input,new Stack(output));
	}
	
	public void addItems(Item[] items) {
		for (Item i:items) {
			addItem(i);
		}
	}
	
	public void addItems(Stack[] items) {
		for (Stack s:items) {
			addItem(s);
		}
	}
	
	public void addItems(String[] items) {
		for (String s:items) {
			addItem(s);
		}
	}
	
	public void addItem(Item i,int amount) {
		boolean itemInMaterials=false;
		for (Stack s:materials) {
			if (s.getName().equals(i.getName())) {
				s.add(amount);
				itemInMaterials=true;
				break;
			}
		} 
		if (!itemInMaterials) {
			materials.add(i.toStack(amount));
		}
	}
	
	public void addItem(Item i) {
		addItem(i,1);
	}
	
	public void addItem(Stack s) {
		addItem(s.getItem(),s.getAmount());
	}
	
	public void addItem(String s,int amount) {
		addItem(new Item(s),amount);
	}
	
	public void addItem(String s) {
		addItem(new Item(s));
	}
	
	public void removeItem(Item i,int amount) {
		boolean removeStack=false;
		Stack stackToRemove=null;
		for (Stack s:materials) {
			if (s.getName().equals(i.getName())) {
				s.add(-amount);
				if (s.getAmount()<=0) {
					removeStack=true;
					stackToRemove=s;
				}
				break;
			}
		} 
		if (removeStack) {
			materials.remove(stackToRemove);
		}
	}
	
	public String getRecipe() {
		String recipe="	{\r\n"
				+ "		\"recipe\": [";
		for (int i=0;i<materials.size();i++) {
			recipe+=materials.get(i).getRecipeItem();
			if (i<materials.size()-1) {
				recipe+=", ";
			}
		}
		recipe+="],\r\n"
				+ "		\"result\": "+output.getRecipeItem()+"\r\n"
				+ "	}";
		return recipe;
	}
	
	public ArrayList<Stack> getMaterials(){
		return materials;
	}
	
	public Stack getOutput(){
		return output;
	}
}
