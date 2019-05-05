package test;

import gameutil.text.Console;
import static gameutil.text.Console.theme;
public class ConsoleApp {
	public static void main(String[] argumentos){
		Console console=new Console(theme.sea);
		while(true){
			//console.readLineInt();
			//console.readLineDouble();
			console.readLine();
		}
		
	}
}
