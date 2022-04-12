package gameutil.text.Iru;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Str implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4532375478074265750L;
	
	private Letter[] string=new Letter[0];
	
	public Str() {
		
	}
	
	public Str(Letter[] str) {
		string=str;
	}
	
	public Str(Letter l) {
		string= new Letter[] {l};
	}
	
	public Str(String s) {
		string=Letter.lettersFromString(s);
	}
	
	
	public Str cat(Str s) {
		Letter[] l=new Letter[string.length+s.string.length];
		int i;
		for (i=0;i<string.length;i++) {
			l[i]=string[i];
		}
		for (int j=0;j<s.string.length;j++) {
			l[i+j]=s.string[j];
		}
		return new Str(l);
	}
	
	public Str cat(String s) {
		return cat(new Str(s));
	}
	
	public Str cat(Letter l) {
		return cat(new Str(l));
	}
	
	public Str substring(int i) {
		return new Str(Letter.substring(string, i));
	}
	
	public Str substring(int i,int j) {
		return new Str(Letter.substring(string, i,j));
	}
	public int indexOf(Letter l) {
		return Letter.indexOf(string, l);
	}
	
	public int indexOf(Str str) {
		return Letter.indexOf(string, str.string);
	}
	
	public int indexOf(Str str,int start) {
		return Letter.indexOf(string, str.string,start);
	}
	public int indexOf(Letter l,int start) {
		return Letter.indexOf(string, l,start);
	}
	
	public int indexOf(String str,int start) {
		return indexOf(new Str(str),start);
	}
	
	public int indexOf(String s) {
		return Letter.indexOf(string, Letter.lettersFromString(s));
	}
	
	public Str getLine(int ln) {
		return new Str(Letter.getLine(string, ln));
	}
	
	public boolean equals(Str s) {
		return string.equals(s.string);
	}
	public boolean equals(Letter l) {
		if (string.length==1) {
			return string[0].equals(l);
		}
		return false;
	}
	public boolean equals(String s) {
		return string.equals(Letter.lettersFromString(s));
	}
	
	public int length() {
		return string.length;
	}
	
	public void setLine(int line,Str s) {
		int index=-1;
		for (long x = 0; x < line - 1; x++) {
			index=indexOf(Letter.NEWLINE,index+1);
		}
		int secondIndex=indexOf("/n",index+1);
		if (secondIndex>-1) {
			if (index>0) {
				string=substring(0, index).cat(s).cat(substring(secondIndex+1)).string;
			} else {
				string=s.cat(substring(secondIndex+1)).string;
			}
		} else {
			if (index>0) {
				string=s.substring(0, index).cat(s).string;
			} else {
				string=s.string;
			}
		}
	}
	
	public void insert(int line,int index,Str s) {
		Str orig=getLine(line);
		if (index>0) {
			setLine(line,orig.substring(0,index).cat(s).cat(orig.substring(index)));
		}else {
			setLine(line,s.cat(orig));
		}
	}
	
	public void insert(int line,int index,String s) {
		insert(line,index,new Str(s));
	}
	
	public void replace(int line,int index,Str s) {
		Str orig=getLine(line);
		if (index>0) {
			setLine(line,orig.substring(0,index).cat(s).cat(orig.substring(index+1)));
		}else {
			setLine(line,s.cat(orig.substring(1)));
		}
	}
	
	public void replace(int line,int index,String s) {
		replace(line,index,new Str(s));
	}
	
	public int getNoOfLines() {
		int line = 0;
		int index=-1;
		while (index<string.length) {
			index=indexOf(Letter.NEWLINE,index+1);
			if (index>0) {
				line++;
			} else {
				break;
			}
			
		}
		return line;
	}
	
	public Letter[] getLetters() {
		return string;
	}
	
	public Str clone() {
		return new Str(string);
	}
}
