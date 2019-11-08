package test;

public class blip {
	private String name;
	private long no;
	public blip(long no,String name) {
		this.name=name;
		this.no=no;
		System.out.println("[My name is " + name + " no."+no+"]");
	}
	
	public blip(blip b) {
		System.out.println("[other blip name is "+b.name+" no."+b.no);
		this.name=b.name;
		this.no=b.no+1;
		System.out.println("My name is " + name + " no."+no+"]");
	}
	
	public blip(blip b,String suffix) {
		System.out.println("[other blip name is "+b.name+" no."+b.no);
		this.name=b.name+suffix;
		this.no=b.no+1;
		System.out.println("My name is " + name + " no."+no+"]");
	}
	
	public blip(char c,float f, double d) {
		if (f>d) {
			System.out.println(c+"f");
			no=(int)f;
		} else {
			System.out.println(c+"d");
			no=(int)d;
		}
		name=String.valueOf(c);
	}
}
