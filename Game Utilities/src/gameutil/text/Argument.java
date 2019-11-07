package gameutil.text;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Argument {
	
	private String[] args;
	
	public Argument(String[] args) {
		this.args=args;
	}
	
	
	/**Arg 0 is command
	 * 
	 * The rest are the other args
	 * 
	 * @param cmd
	 * @return args
	 */
	public static Argument getArgs(String cmd) {
		//initialize variables
		String[] args;
		ArrayList<String> argList=new ArrayList<>();
		//this stub represents the unscanned part of the string.
		String cmdStub=cmd;
		
		//Scan the cmd string for args
		while (cmdStub.contains(" ")) {
			//get index of next space
			int spaceIndex=cmdStub.indexOf(" ");
			//parse arg
			argList.add(cmdStub.substring(0, spaceIndex));
			//skip spaceIndex
			cmdStub=cmdStub.substring(spaceIndex+1);
		}
		
		//add last arg
		argList.add(cmdStub);
		
		//initialize args array to be size of list
		args=new String[argList.size()];
		
		//put args from list into array
		int i=0;
		for (String s:argList) {
			args[i]=s;
			i++;
		}
		
		return new Argument(args);
		
	}
	
	public String get(int index) {
		try {
			return args[index];
		} catch (NullPointerException e) {
			System.out.println("Nothing isn't where it's supposed to be...");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		return "";
	}
	
	public boolean hasArg(int index) {
		return !get(index).equals("");
	}
	
	public String cmd() {
		return get(0);
	}
	
	public boolean equals(Argument args) {
		int i=0;
		for (String s:this.args) {
			if (!s.equals(args.get(i))) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	public static Object constructFromArgs(Argument args) {
		
        try {
            Class[] classes = new Class[args.raw().length];
            //start after class name
            int i = 1;
            while (args.hasArg(i)) {
            	//get arg class
                classes[i] = Class.forName(args.get(i));
                //determine if arg is 
                i++;
            }
            Class.forName(args.cmd()).getConstructor(classes).newInstance((classes[0].cast(args.get(0))), 340, 4309);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
		
		return null;
	}
	
	public String[] raw() {
		return args;
	}
}
