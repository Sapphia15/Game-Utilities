package test;

import gameutil.Cloner;
import gameutil.math.geom.HyperVoxel;
import gameutil.math.geom.Point;
import gameutil.text.Argument;
import gameutil.text.Console;

public class instantiator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x=Console.s.readLineDouble();
		double y=Console.s.readLineDouble();
		double z=Console.s.readLineDouble();
		double w=Console.s.readLineDouble();
		HyperVoxel v=new HyperVoxel(new Point(new double[] {x,y,z,w}));
		HyperVoxel vClone=(HyperVoxel) Cloner.Clone(v);
		Console.s.println("("+vClone.getPos().tuple.i(0)+","+vClone.getPos().tuple.i(1)+","+vClone.getPos().tuple.i(2)+","+vClone.getPos().tuple.i(3)+")");
		/*
		Argument.constructFromArgs("test.blip long 8000234 String |/l\\| ha ha \"hello world!!\" |/eS\\|");
		Argument.constructFromArgs("test.blip |/o\\| test.blip |/o\\| test.blip long 3 String yum |/e2\\| |/p\\| String my |/e1\\|");
		Argument.constructFromArgs("test.blip char y float 12234.4 double 2034.34342321");*/
		//*can't be used to construct embedded classes
	}

}
