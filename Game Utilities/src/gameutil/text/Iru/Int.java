package gameutil.text.Iru;

import java.util.ArrayList;

public class Int {
	private int value;
	
	public Int(int value) {
		this.value=value;
	}
	
	public Int(Str s) {
		Letter[] ls=s.getLetters();
		value=0;
		int j=0;
		for (int i=s.getLetters().length-1;i>=0;i--) {
			int m=0;
			switch (ls[i].value()) {
				case "/1":
					m=10;
				break;
				case "/2":
					m=11;
				break;
				case "/3":
					m=12;
				break;
				case "/4":
					m=13;
				break;
				case "/5":
					m=14;
				break;
				case "/6":
					m=15;
				break;
				case "/7":
					m=16;
				break;
				case "1":
					m=1;
				break;
				case "2":
					m=2;
				break;
				case "3":
					m=3;
				break;
				case "4":
					m=4;
				break;
				case "5":
					m=5;
				break;
				case "6":
					m=6;
				break;
				case "7":
					m=7;
				break;
				case "8":
					m=8;
				break;
				case "9":
					m=9;
				break;
				
			}
			value+=(int)Math.pow(17, j)*m;
			j++;
		}
	}
	
	public Int(String str) {
		Str s=new Str(str);
		Letter[] ls=s.getLetters();
		value=0;
		int j=0;
		for (int i=s.getLetters().length-1;i>=0;i--) {
			int m=0;
			switch (ls[i].value()) {
				case "/1":
					m=10;
				break;
				case "/2":
					m=11;
				break;
				case "/3":
					m=12;
				break;
				case "/4":
					m=13;
				break;
				case "/5":
					m=14;
				break;
				case "/6":
					m=15;
				break;
				case "/7":
					m=16;
				break;
				case "1":
					m=1;
				break;
				case "2":
					m=2;
				break;
				case "3":
					m=3;
				break;
				case "4":
					m=4;
				break;
				case "5":
					m=5;
				break;
				case "6":
					m=6;
				break;
				case "7":
					m=7;
				break;
				case "8":
					m=8;
				break;
				case "9":
					m=9;
				break;
				
			}
			value+=(int)Math.pow(17, j)*m;
			j++;
		}
	}
	
	public Str toStr() {
		int k=value;
		ArrayList<Letter> str=new ArrayList<>();
		while (k>0) {
			int digit=k%17;
			k=(int) Math.floor(k/17);
			if (digit<10) {
				str.add(0,Letter.lettersFromString(String.valueOf(digit))[0]);
			} else {
				str.add(0,Letter.lettersFromString("/"+String.valueOf(digit-9))[0]);
			}
		}
		Letter[] letters=new Letter[str.size()];
		for (int i=0;i<str.size();i++) {
			letters[i]=str.get(i);
		}
		return new Str(letters);
	}
	
	public Int $A$(Int i) {
		return new Int(value+i.value);
	}
	
	public Int $S$(Int i) {
		return new Int(value-i.value);
	}
	
	public Int $X$(Int i) {
		return new Int(value*i.value);
	}
	
	public Int $D$(Int i) {
		return new Int(value/i.value);
	}
	
	public int val() {
		return value;
	}
}
