package test;

import java.util.ArrayList;
import java.util.Random;

import gameutil.genetic.BitManipulator;
import gameutil.genetic.Genome;
import gameutil.math.geom.LineSeg;
import gameutil.math.geom.Point;
import gameutil.math.geom.Vector;
import gameutil.math.geom.g3D.PointR3;
import gameutil.text.Console;

public class Triangulate {
	static double d1;
	static double d2;
	static double d3;
	static double d4;
	static Point p1;
	static Point p2;
	static Point p3;
	static Point p4;
	Point tp1;
	Point tp2;
	Point tp3;
	Point tp4;
	static Random rand=new Random();
	static double P=.7;
	static double I=.0000002;
	static double D=-.06;
	
	public Triangulate(Point p1,Point p2,Point p3,Point p4) {
		tp1=p1;
		tp2=p2;
		tp3=p3;
		tp4=p4;
	}
	
	public Point locate(double d1,double d2,double d3,double d4,int itr1,int itr2) {
		
		Point p1=tp1.clone();
		Point p2=tp2.clone();
		Point p3=tp3.clone();
		Point p4=tp4.clone();
		
		Vector v1=new Vector(p1.tuple.$S$(p2.tuple)).normalize();
		Vector v2=new Vector(p2.tuple.$S$(p3.tuple)).normalize();
		Vector v3=new Vector(p3.tuple.$S$(p1.tuple)).normalize();
		Vector v4=new Vector(p4.tuple.$S$(p1.tuple)).normalize();
		Vector v5=new Vector(p4.tuple.$S$(p3.tuple)).normalize();
		Vector v6=new Vector(p4.tuple.$S$(p2.tuple)).normalize();
		Point i1=new LineSeg(new Point(new Vector(p1.tuple).$S$(v1.$X$(d1))),new Point(new Vector(p2.tuple).$A$(v1.$X$(d2)))).midPoint();
		Point i2=new LineSeg(new Point(new Vector(p2.tuple).$S$(v2.$X$(d2))),new Point(new Vector(p3.tuple).$A$(v2.$X$(d3)))).midPoint();
		Point i3=new LineSeg(new Point(new Vector(p3.tuple).$S$(v3.$X$(d3))),new Point(new Vector(p1.tuple).$A$(v3.$X$(d1)))).midPoint();
		Point i4=new LineSeg(new Point(new Vector(p4.tuple).$S$(v4.$X$(d4))),new Point(new Vector(p1.tuple).$A$(v4.$X$(d1)))).midPoint();
		Point i5=new LineSeg(new Point(new Vector(p4.tuple).$S$(v5.$X$(d4))),new Point(new Vector(p3.tuple).$A$(v5.$X$(d3)))).midPoint();
		Point i6=new LineSeg(new Point(new Vector(p4.tuple).$S$(v6.$X$(d4))),new Point(new Vector(p2.tuple).$A$(v6.$X$(d2)))).midPoint();
		Point aprox2=i1;
		Point a1;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		a1=aprox2.clone();
		aprox2=i2;
		Point a2;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		
		a2=aprox2.clone();
		aprox2=i3;
		Point a3;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		a3=aprox2.clone();
		aprox2=i4;
		Point a4;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		
		a4=aprox2.clone();
		Point a5;
		aprox2=i5;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		a5=aprox2.clone();
		
		Point a6;
		aprox2=i6;
		for (int i=0;i<itr1;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
		}
		a6=aprox2.clone();
		
		/*double err1=(d1-a3.distance(p1))/2;
		double err2=(d2-a3.distance(p2))/2;
		double err3=(d3-a3.distance(p3))/2;
		double err4=(d4-a3.distance(p4))/2;
		*/
		aprox2=a1.lerp(a2, .5).lerp(a3, .5).lerp(a4, .5).lerp(a5,.5);
		
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a1)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a1;
		}
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a2)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a2;
		}
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a3)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a3;
		}
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a4)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a4;
		}
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a5)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a5;
		}
		if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a6)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
			aprox2=a6;
		}
		
		for (int i=0;i<itr2;i++) {
			aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/8)),aprox2);
		}
		return aprox2;
	}
	
	/**Good for locating points up to 10000 units away. Takes about 1 second to calculate.
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @return
	 */
	public Point locate(double d1,double d2,double d3,double d4) {
		return locate(d1,d2,d3,d4,10,40);
	}
	
	public static void main(String[] unicorns) {
		Console.s.setTheme(Console.theme.shell2);
		Console.s.println("Triangulate");
		int points1=0;
		int points2=0;
		double dist=0;
		
		p1=new Point(new double[] {2,3,4});
		p2=new Point(new double[] {209,-30,77});
		p3=new Point(new double[] {-199,3301,71});
		p4=new Point(new double[] {200,700,301});
		double radius=10000;
		double largestDist=0;
		int itr=10;
		for (int j=0;j<itr;j++) {
			Point target=new Point(new double[] {Math.random()*radius,Math.random()*radius,Math.random()*radius});
			//target.printVals("Target");
			d1=target.distance(p1);
			d2=target.distance(p2);
			d3=target.distance(p3);
			d4=target.distance(p4);
			Vector v1=new Vector(p1.tuple.$S$(p2.tuple)).normalize();
			Vector v2=new Vector(p2.tuple.$S$(p3.tuple)).normalize();
			Vector v3=new Vector(p3.tuple.$S$(p1.tuple)).normalize();
			Vector v4=new Vector(p4.tuple.$S$(p1.tuple)).normalize();
			Vector v5=new Vector(p4.tuple.$S$(p3.tuple)).normalize();
			Vector v6=new Vector(p4.tuple.$S$(p2.tuple)).normalize();
			//v1.$X$(d1).getSpds().$A$(p1.tuple).printVals();
			//Console.s.println(new Point(new Vector(p1.tuple).$S$(v1.$X$(d1))).distance(p1)+" = "+d1+" = "+new Point(new Vector(p1.tuple).$A$(v3.$X$(d1))).distance(p1));
			//Console.s.println(new Point(new Vector(p2.tuple).$A$(v1.$X$(d2))).distance(p2)+" = "+d2+ " = "+new Point(new Vector(p2.tuple).$S$(v2.$X$(d2))).distance(p2));
			//Console.s.println(new Point(new Vector(p3.tuple).$A$(v2.$X$(d3))).distance(p3)+" = "+d3+" = "+new Point(new Vector(p3.tuple).$S$(v3.$X$(d3))).distance(p3));
			//centers of the circular intersections of the spheres
			Point i1=new LineSeg(new Point(new Vector(p1.tuple).$S$(v1.$X$(d1))),new Point(new Vector(p2.tuple).$A$(v1.$X$(d2)))).midPoint();
			Point i2=new LineSeg(new Point(new Vector(p2.tuple).$S$(v2.$X$(d2))),new Point(new Vector(p3.tuple).$A$(v2.$X$(d3)))).midPoint();
			Point i3=new LineSeg(new Point(new Vector(p3.tuple).$S$(v3.$X$(d3))),new Point(new Vector(p1.tuple).$A$(v3.$X$(d1)))).midPoint();
			Point i4=new LineSeg(new Point(new Vector(p4.tuple).$S$(v4.$X$(d4))),new Point(new Vector(p1.tuple).$A$(v4.$X$(d1)))).midPoint();
			Point i5=new LineSeg(new Point(new Vector(p4.tuple).$S$(v5.$X$(d4))),new Point(new Vector(p3.tuple).$A$(v5.$X$(d3)))).midPoint();
			Point i6=new LineSeg(new Point(new Vector(p4.tuple).$S$(v6.$X$(d4))),new Point(new Vector(p2.tuple).$A$(v6.$X$(d2)))).midPoint();
			//Point avg=new Point(i1.tuple.$A$(i2.tuple).$A$(i3.tuple).$D$(3));
			
			//i1.printVals("i1");
			//i2.printVals("i2");
			//i3.printVals("i3");
			//avg.printVals("avg");
			Console.s.setAutoScroll(true);
			long start=System.currentTimeMillis();
			//Point aprox1=intersect(p1,p2,p3,p4,d1,d2,d3,d4,100000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,1000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,100,intersect(p1,p2,p3,p4,d1,d2,d3,d4,10,intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,1000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,100,intersect(p1,p2,p3,p4,d1,d2,d3,d4,10,i1)))))))));
			//aprox1.printVals("Aprox1");
			//intersect(p1,p2,p3,p4,d1,d2,d3,d4,100000,intersect(p1,p2,p3,p4,d1,d2,d3,d4,10,i1)).printVals("Aprox1");
			//long time1=System.currentTimeMillis()-start;
			
			//start=System.currentTimeMillis();
			Point aprox2=i1;
			Point a1;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			a1=aprox2.clone();
			aprox2=i2;
			Point a2;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			
			a2=aprox2.clone();
			aprox2=i3;
			Point a3;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			a3=aprox2.clone();
			aprox2=i4;
			Point a4;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			
			a4=aprox2.clone();
			Point a5;
			aprox2=i5;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			a5=aprox2.clone();
			
			Point a6;
			aprox2=i6;
			for (int i=0;i<10;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/2)),aprox2);
			}
			a6=aprox2.clone();
			
			/*double err1=(d1-a3.distance(p1))/2;
			double err2=(d2-a3.distance(p2))/2;
			double err3=(d3-a3.distance(p3))/2;
			double err4=(d4-a3.distance(p4))/2;
			*/
			aprox2=a1.lerp(a2, .5).lerp(a3, .5).lerp(a4, .5).lerp(a5,.5);
			
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a1)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a1;
			}
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a2)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a2;
			}
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a3)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a3;
			}
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a4)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a4;
			}
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a5)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a5;
			}
			if (getErr(p1,p2,p3,p4,d1,d2,d3,d4,a6)<getErr(p1,p2,p3,p4,d1,d2,d3,d4,aprox2)) {
				aprox2=a6;
			}
			
			for (int i=0;i<40;i++) {
				aprox2=intersect(p1,p2,p3,p4,d1,d2,d3,d4,10000+(int)Math.ceil(Math.pow(10, i/8)),aprox2);
			}
			
			
			//aprox2.printVals("Aprox2");
			//Console.s.println(time1);
			Console.s.println(System.currentTimeMillis()-start);
			
			/*if (new Vector(aprox1.tuple.$S$(target.tuple)).magnitude()<Math.sqrt(3)) {
				//Console.s.println("Aprox1  good enough");
				points1++;
			} else {
				//Console.s.println("Aprox1 failed");
			}*/
			double thisDist=aprox2.distance(target);
			if (new Vector(aprox2.tuple.$S$(target.tuple)).magnitude()<Math.sqrt(3)) {
				//Console.s.println("Aprox2  good enough");
				points2++;
			} else {
				Console.s.println("Aprox failed");
				Console.s.println("Distance: "+thisDist);
			}
			
			if (thisDist>largestDist) {
				largestDist=thisDist;
			}
			dist+=aprox2.distance(target);
		}
		//Console.s.println("Accuracy 1: "+points1+"%");
		Console.s.println("Accuracy: "+(points2*100/itr)+"%");
		Console.s.println("Avg Dist: "+dist/itr);
		Console.s.println("Largest Dist: "+largestDist);
		/*ArrayList<Genome> genomes=new ArrayList<>();
		
		//Console.s.println("4: "+BitManipulator.flip(BitManipulator.flip(2, (byte)1),(byte)2));
		//Console.s.println(BitManipulator.getBit(4, (byte)2));
		
		Console.s.print("Population size: ");
		Console.s.setAutoScroll(true);
		int population=Console.s.readLineInt();
		for (int i=0;i<population;i++) {
			genomes.add(new Genome(10));
		}
		Console.s.print("Maximum Generations: ");
		int maxGen=Console.s.readLineInt();
		//idea: try starting with a very high mutation rate and then slowly decreasing it after each generation
		//like how a human is very playful and experimental as a child and then grows to be more set in their ways
		//or how annealing works
		//another idea: similar to the last but make the mutation acceleration be a wave (note: probably will only be effective with elitism)
		Console.s.print("Mutation Rate: ");
		double mRate=Console.s.readLineDouble();
		Console.s.print("Mutation accelleration (0 for constant mutation rate): ");
		double mAccel=Console.s.readLineDouble();
		double minMutationRate=.2;
		if (minMutationRate>mRate) {
			minMutationRate=mRate/5;
		}
		Console.s.print("Fitness threshhold (1 is perfect, smaller numbers are less fit): ");
		double thresh=Console.s.readLineDouble();
		double best=Double.MAX_VALUE*-1;
		int gens=0;
		while (best<thresh&&gens<maxGen) {
			gens=gens+1;
			
			Genome bestG=null;
			double worst=2;
			Genome worstG=null;
			
			ArrayList<Genome> sorted=new ArrayList<>();
			
			ArrayList<Double> vals=new ArrayList<>();
			
			vals.add(Double.MAX_VALUE*-1);
			for (Genome g: genomes) {
				double fit=fitness(g);
				//check if is best, then if is second best, etc
				//Console.s.println("fit: "+fit);
				for (int i=0;i<vals.size();i++) {
					//Console.s.println("Val: "+vals.get(i));
					if (fit>vals.get(i)) {
						if (fit>best) {
							best=fit;
						} else if (fit<worst) {
							worst=fit;
						}
						vals.add(i+1, fit);
						sorted.add(i,g);
						break;
					}
				}
			}
			
			bestG=sorted.get(sorted.size()-1);
			worstG=sorted.get(0);
			
			
			Console.s.println("Gen "+gens);
			Console.s.println("     Best: "+best);
			Console.s.println("     Worst: "+worst);
			Console.s.println("     Solution Found: "+(best>=thresh));
			PointR3 a=getPoint(bestG);
			Console.s.println("     Appoximation: ("+a.getX()+", "+a.getY()+", "+a.getZ()+")");
			Console.s.println("     Mutation Rate: "+mRate);
			Console.s.println("     Population: "+genomes.size());
			
			ArrayList<Genome> nextGen=new ArrayList<>();
			for (int i=0;i<10;i++) {
				nextGen.add(sorted.get(sorted.size()-1-i));
				genomes.remove(sorted.get(i));
			}
			
			for (Genome g:genomes) {
				boolean sexual=BitManipulator.getBit(g.getGene(9).get(), (byte) 0);
				boolean mom=BitManipulator.getBit(g.getGene(9).get(), (byte) 1);
				boolean attractedToTheBest=BitManipulator.getBit(g.getGene(9).get(), (byte) 2);
				boolean attractedToMoms=BitManipulator.getBit(g.getGene(9).get(), (byte) 3);
				boolean twoPoint=BitManipulator.getBit(g.getGene(9).get(), (byte)4);
				if (sexual) {
					if (attractedToTheBest) {
						if (mom) {
							if (twoPoint) {
								nextGen.add(bestG.sexualRepro2P(mRate, g));
							} else {
								nextGen.add(bestG.sexualRepro1P(mRate, g));
							}
						} else {
							if (twoPoint) {
								nextGen.add(g.sexualRepro2P(mRate, bestG));
							} else {
								nextGen.add(g.sexualRepro1P(mRate, bestG));
							}
						}	
					} else if (attractedToMoms) {
						boolean mated=false;
						for (Genome mate:genomes) {
							if (!mate.equals(g)) {
								if (BitManipulator.getBit(mate.getGene(9).get(), (byte) 1)) {
									if (twoPoint) {
										nextGen.add(mate.sexualRepro2P(mRate, g));
									} else {
										nextGen.add(mate.sexualRepro1P(mRate, g));
									}
									mated=true;
									break;
								}
							}
						}
						if (!mated) {
							nextGen.add(g.asexualRepro(mRate));
						}
					} else {
						boolean mated=false;
						for (Genome mate:genomes) {
							if (!mate.equals(g)&&!mate.equals(bestG)) {
								if (twoPoint) {
									nextGen.add(mate.sexualRepro2P(mRate, g));
								} else {
									nextGen.add(mate.sexualRepro1P(mRate, g));
								}
								mated=true;
								break;
							}
						}
						if (!mated) {
							nextGen.add(g.asexualRepro(mRate));
						}
					}
				} else {
					nextGen.add(g.asexualRepro(mRate));
				}
			}
			genomes=nextGen;
			
			mRate=mRate+mAccel;
			if (mRate<minMutationRate) {
				mRate=minMutationRate;
				//No longer breaks//no more mutation means no more changes (unless you do crossovers but these creatures are asexual rn)
			}
		}
	}
	
	public static double fitness(Genome g) {
		PointR3 a=getPoint(g);
		double d1=a.getDistance(p1);
		double d2=a.getDistance(p2);
		double d3=a.getDistance(p3);
		return 1-(Math.pow(d1-Triangulate.d1, 2)+Math.pow(d2-Triangulate.d2, 2)+Math.pow(d3-Triangulate.d3, 2));
	}
	
	public static PointR3 getPoint(Genome g) {
		return new PointR3(Math.log(Math.pow((g.getGene(0).get()+((double)Math.pow(10, -32))*g.getGene(1).get())-Math.sqrt(g.getGene(6).get()),2)),Math.log(Math.pow((g.getGene(2).get()+((double)Math.pow(10, -32))*g.getGene(3).get())/Math.sqrt(g.getGene(7).get()),2)),Math.log(Math.pow((g.getGene(4).get()+((double)Math.pow(10, -32))*g.getGene(5).get())-Math.sqrt(g.getGene(8).get()),2)));
	}*/
	}
	
	public static Point intersect(Point p1,Point p2,Point p3,Point p4,double d1,double d2,double d3,double d4,int iterations,Point aprox) {
		/*Vector v1=new Vector(p1.tuple.$S$(p2.tuple)).normalize();
		Vector v2=new Vector(p2.tuple.$S$(p3.tuple)).normalize();
		Vector v3=new Vector(p3.tuple.$S$(p1.tuple)).normalize();
		
		//centers of the circular intersections of the spheres
		Point i1=new LineSeg(new Point(new Vector(p1.tuple).$S$(v1.$X$(d1))),new Point(new Vector(p2.tuple).$A$(v1.$X$(d2)))).midPoint();
		Point i2=new LineSeg(new Point(new Vector(p2.tuple).$S$(v2.$X$(d2))),new Point(new Vector(p3.tuple).$A$(v2.$X$(d3)))).midPoint();
		Point i3=new LineSeg(new Point(new Vector(p3.tuple).$S$(v3.$X$(d3))),new Point(new Vector(p1.tuple).$A$(v3.$X$(d1)))).midPoint();
	*/double err1=(d1-aprox.distance(p1))/2;
		double err2=(d2-aprox.distance(p2))/2;
		double err3=(d3-aprox.distance(p3))/2;
		double err4=(d4-aprox.distance(p4))/2;
		double lastErr1=err1;
		double lastErr2=err2;
		double lastErr3=err3;
		double lastErr4=err4;
		double integral1=0;
		double integral2=0;
		double integral3=0;
		double integral4=0;
		double lastSpeed1=0;
		double lastSpeed2=0;
		double lastSpeed3=0;
		double lastSpeed4=0;
		Point best=aprox.clone();
		double minErr=(Math.sqrt(Math.pow(err1,2)+ Math.pow(err2,2)+Math.pow(err3,2)+Math.pow(err4,2)));
		//f=m*a
		//s=s0+a*t
		//d=d0+s0*t+(a/2)*t^2
		double dt=.1;
		for (int i=0;i<iterations;i++) {
			Vector v1=new Vector(aprox.tuple.$S$(p1.tuple)).normalize();
			Vector v2=new Vector(aprox.tuple.$S$(p2.tuple)).normalize();
			Vector v3=new Vector(aprox.tuple.$S$(p3.tuple)).normalize();
			Vector v4=new Vector(aprox.tuple.$S$(p4.tuple)).normalize();
			err1=d1-aprox.distance(p1);
			err2=d2-aprox.distance(p2);
			err3=d3-aprox.distance(p3);
			err4=d4-aprox.distance(p4);
			Vector weight=(new Vector(new double[] {rand.nextDouble(),rand.nextDouble(),rand.nextDouble(),rand.nextDouble()}));
			weight.normalize();
			/*Console.s.println("err1: "+err1);
			Console.s.println("err2: "+err2);
			Console.s.println("err3: "+err3);*/
			//Console.s.println("Err: "+(Math.sqrt(Math.pow(err1,2)+ Math.pow(err2,2)+Math.pow(err3,2))));
			double err=(Math.sqrt(Math.pow(err1,2)+ Math.pow(err2,2)+Math.pow(err3,2)+Math.pow(err4,2)));
			integral1+=err1;
			integral2+=err2;
			integral3+=err3;
			integral4+=err4;
			double speed1=lastSpeed1+(err1*(P+(weight.getSpds().i(0)-.5))+integral1*I+(err1-lastErr1)*D)*Math.pow(dt,2);
			double speed2=lastSpeed2+(err2*(P+(weight.getSpds().i(1)-.5))+integral2*I+(err2-lastErr2)*D)*Math.pow(dt,2);
			double speed3=lastSpeed3+(err3*(P+(weight.getSpds().i(2)-.5))+integral3*I+(err3-lastErr3)*D)*Math.pow(dt,2);
			double speed4=lastSpeed4+(err4*(P+(weight.getSpds().i(3)-.5))+integral4*I+(err4-lastErr4)*D)*Math.pow(dt,2);
			aprox.move(v1.$X$(speed1/*weight.getSpds().i(0)*/));
			aprox.move(v2.$X$(speed2/*weight.getSpds().i(1)*/));
			aprox.move(v3.$X$(speed3/*weight.getSpds().i(2)*/));
			aprox.move(v4.$X$(speed4/*weight.getSpds().i(2)*/));
			lastSpeed1=speed1;
			lastSpeed2=speed2;
			lastSpeed3=speed3;
			lastSpeed4=speed4;
			
			if (err<minErr) {
				best=aprox.clone();
				minErr=err;
			}
			lastErr1=err1;
			lastErr2=err2;
			lastErr3=err3;
			lastErr4=err4;
		}
		aprox=best;
		err1=d1-aprox.distance(p1);
		err2=d2-aprox.distance(p2);
		err3=d3-aprox.distance(p3);
		//Console.s.println("err1: "+err1);
		//Console.s.println("err2: "+err2);
		//Console.s.println("err3: "+err3);
		//Console.s.println("err4: "+err3);
		//Console.s.println("err: "+minErr);
		return best;
	}
	
	public static double getErr(Point p1,Point p2,Point p3,Point p4,double d1,double d2,double d3,double d4,Point aprox) {
		double err1=(d1-aprox.distance(p1))/2;
		double err2=(d2-aprox.distance(p2))/2;
		double err3=(d3-aprox.distance(p3))/2;
		double err4=(d4-aprox.distance(p4))/2;
		return (Math.sqrt(Math.pow(err1,2)+ Math.pow(err2,2)+Math.pow(err3,2)+Math.pow(err4,2)));
	}
}
