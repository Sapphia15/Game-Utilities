package test;

import gameutil.text.Argument;
import gameutil.text.Console;

public class instantiator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Argument.constructFromArgs("test.blip long 8000234 String |/l\\| ha ha \"hello world!!\" |/eS\\|");
		Argument.constructFromArgs("test.blip |/o\\| test.blip |/o\\| test.blip long 3 String yum |/e2\\| |/p\\| String my |/e1\\|");
		Argument.constructFromArgs("test.blip char y float 12234.4 double 2034.34342321");
		//*can't be used to construct embedded classes
	}

}
