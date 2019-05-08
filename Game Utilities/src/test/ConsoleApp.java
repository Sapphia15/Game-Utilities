package test;

import gameutil.g2D.LineSeg;
import gameutil.text.Console;
import static gameutil.text.Console.theme;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
class ConsoleApp {
	public static void main(String[] argumentos){
		Console console=new Console(theme.sea,true);
		while(true){
			console.readLine();
			console.setTheme(Console.theme.forest);
			console.setUserNextLineEnabled(false);
			console.readLine();
			Console.s.setTheme(Console.theme.sea);
			console.setUserNextLineEnabled(true);
		}
	}
}
