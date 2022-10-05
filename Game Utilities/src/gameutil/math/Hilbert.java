package gameutil.math;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import gameutil.ConcurrentBidirectionalMap;
import gameutil.math.ComplexDouble;
import gameutil.math.geom.g2D.LineSegR2;
import gameutil.text.Console;
import graphics.screen.SPanel;
import graphics.screen.Screen;

public class Hilbert {
	
	public static ConcurrentBidirectionalMap<Integer,String> curve=new ConcurrentBidirectionalMap<>();
	static int length;
	static int side;
	
	
	public static void init() {
		curve.set(0,"-.5i-.5");
		curve.set(1,"-.5i.5");
		curve.set(2,".5i.5");
		curve.set(3,".5i-.5");//iteration 0
		length=curve.getFirstKeys().size();
		side=(int) Math.sqrt(length);
	}
	
	public static ConcurrentBidirectionalMap<Integer,String> nextIteration(ConcurrentBidirectionalMap<Integer,String> curve) {
		ConcurrentBidirectionalMap<Integer,String> next=new ConcurrentBidirectionalMap<>();
		int length=curve.getFirstKeys().size();
		int off=(int)Math.sqrt(length)/2;
		for (int i=0;i<length;i++) {
			ComplexDouble z=new ComplexDouble(curve.getSecond(i));
			next.set(i, z.complement().$X$(ComplexDouble.i).$A$(new ComplexDouble(-off,-off)).stringify());
			
			next.set(i+length, z.$A$(new ComplexDouble(-off,off)).stringify());
			
			next.set(i+length*2, z.$A$(new ComplexDouble(off,off)).stringify());
			
			next.set(i+length*3, z.complement().$X$(new ComplexDouble(0,-1)).$A$(new ComplexDouble(off,-off)).stringify());
		}
		return next;
	}
	
	public static ConcurrentBidirectionalMap<Integer,String> nextIterationNoFlip(ConcurrentBidirectionalMap<Integer,String> curve) {
		ConcurrentBidirectionalMap<Integer,String> next=new ConcurrentBidirectionalMap<>();
		int length=curve.getFirstKeys().size();
		int off=(int)Math.sqrt(length)/2;
		for (int i=0;i<length;i++) {
			ComplexDouble z=new ComplexDouble(curve.getSecond(i));
			next.set(i, z.$A$(new ComplexDouble(-off,-off)).stringify());
			
			next.set(i+length, z.$A$(new ComplexDouble(-off,off)).stringify());
			
			next.set(i+length*2, z.$A$(new ComplexDouble(off,off)).stringify());
			
			next.set(i+length*3, z.$A$(new ComplexDouble(off,-off)).stringify());
		}
		return next;
	}
	
	public static int squishify(int x,int y) {
		return curve.getFirst((x-side/2+.5)+"i"+(y-side/2+.5));
	}
	
	public static int squishify(int x,int y,ConcurrentBidirectionalMap<Integer,String> curve) {
		int length=curve.getFirstKeys().size();
		int side=(int) Math.sqrt(length);
		return curve.getFirst((x-side/2+.5)+"i"+(y-side/2+.5));
	}
	
}
