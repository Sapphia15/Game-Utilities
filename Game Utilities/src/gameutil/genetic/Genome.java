package gameutil.genetic;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Genome {
	CopyOnWriteArrayList<Gene> genes;
	static Random rand=new Random();
	public Genome(int genes) {
		this.genes=new CopyOnWriteArrayList<>();
		for (int i=0;i<genes;i++) {
			this.genes.add(new Gene());
		}
	}
	
	private Genome() {
		this.genes=new CopyOnWriteArrayList<>();
	}
	
	public Genome asexualRepro(double mRate) {
		Genome child=new Genome();
		for (Gene g:genes) {
			child.genes.add(g.clone(mRate,16));
		}
		return child;
	}
	
	public Genome sexualRepro1P(double mRate,Genome mom) {
		Genome child=new Genome();
		byte slicePoint=0;
		slicePoint=(byte)rand.nextInt(32);
		boolean momBiggest=false;
		if (mom.genes.size()>genes.size()) {
			momBiggest=true;
		}
		if (momBiggest) {
			for (int i=0;i<mom.genes.size();i++) {
				int val=0;
				Gene mGene=mom.genes.get(i);
				if (i<genes.size()) {
					Gene myGene=genes.get(i);
					for (byte j=0;j<32;j++) {
						if (j<slicePoint) {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						} else {
							val=BitManipulator.set(val, j, BitManipulator.getBit(myGene.get(), j));
						}
					}
					Gene g=new Gene(val);
					child.genes.add(g.clone(mRate,16));
				} else {
					child.genes.add(mGene.clone(mRate,16));
				}
			}
		} else {
			for (int i=0;i<genes.size();i++) {
				int val=0;
				Gene myGene=genes.get(i);
				if (i<genes.size()) {
					Gene mGene=genes.get(i);
					for (byte j=0;j<32;j++) {
						if (j<slicePoint) {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						} else {
							val=BitManipulator.set(val, j, BitManipulator.getBit(myGene.get(), j));
						}
					}
					Gene g=new Gene(val);
					child.genes.add(g.clone(mRate,16));
				} else {
					child.genes.add(myGene.clone(mRate,16));
				}
			}
		}
		return child;
	}
	
	public Genome sexualRepro2P(double mRate,Genome mom) {
		Genome child=new Genome();
		byte[] slicePoint=new byte[2];
		slicePoint[0]=(byte)rand.nextInt(32);
		slicePoint[1]=(byte)rand.nextInt(32);
		if (slicePoint[0]>slicePoint[1]) {
			//swap so the 0 index is less than the 1 index
			byte slice0=slicePoint[1];
			slicePoint[1]=slicePoint[0];
			slicePoint[0]=slice0;
		} else if (slicePoint[0]==slicePoint[1]) {
			return sexualRepro1P(mRate,mom);
		}
		boolean momBiggest=false;
		if (mom.genes.size()>genes.size()) {
			momBiggest=true;
		}
		if (momBiggest) {
			for (int i=0;i<mom.genes.size();i++) {
				int val=0;
				Gene mGene=mom.genes.get(i);
				if (i<genes.size()) {
					Gene myGene=genes.get(i);
					for (byte j=0;j<32;j++) {
						if (j<slicePoint[0]) {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						} else if (j<slicePoint[1]) {
							val=BitManipulator.set(val, j, BitManipulator.getBit(myGene.get(), j));
						} else {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						}
					}
					Gene g=new Gene(val);
					child.genes.add(g.clone(mRate,16));
				} else {
					child.genes.add(mGene.clone(mRate,16));
				}
			}
		} else {
			for (int i=0;i<genes.size();i++) {
				int val=0;
				Gene myGene=genes.get(i);
				if (i<genes.size()) {
					Gene mGene=genes.get(i);
					for (byte j=0;j<32;j++) {
						if (j<slicePoint[0]) {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						} else if (j<slicePoint[1]){
							val=BitManipulator.set(val, j, BitManipulator.getBit(myGene.get(), j));
						} else {
							val=BitManipulator.set(val, j, BitManipulator.getBit(mGene.get(), j));
						}
					}
					Gene g=new Gene(val);
					child.genes.add(g.clone(mRate,16));
				} else {
					child.genes.add(myGene.clone(mRate,16));
				}
			}
		}
		return child;
	}
	
	public Gene getGene(int no) {
		return genes.get(no);
	}
}
