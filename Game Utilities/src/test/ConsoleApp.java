package test;

import gameutil.LineSeg;
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
			console.setUserNextLineEnabled(false);
			console.readLine();
			console.setUserNextLineEnabled(true);
		}
	}
}
