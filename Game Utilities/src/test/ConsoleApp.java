package test;

import gameutil.text.Console;
import static gameutil.text.Console.theme;
public class ConsoleApp {
	public static void main(String[] argumentos){
		Console console=new Console(theme.sea,true);
		while(true){
			console.readLineInt();
			console.readLineDouble();
			console.readLine();
			console.setUserNextLineEnabled(false);
			console.readLine();
			console.setUserNextLineEnabled(true);
		}
	}
}
