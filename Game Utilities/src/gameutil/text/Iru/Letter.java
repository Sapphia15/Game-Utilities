package gameutil.text.Iru;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Letter {
	
	private final String value;
	private final Image image16;
	private final Image image32;
	private final Image image64;
	
	public static final Letter a=new Letter("a","a");//
	public static final Letter A=new Letter("A","Ac");//
	public static final Letter al=new Letter("/a","al");//
	public static final Letter Al=new Letter("/A","alc");//
	public static final Letter b=new Letter("b","b");//
	public static final Letter B=new Letter("B","Bc");//
	public static final Letter c=new Letter("c","c");//
	public static final Letter C=new Letter("C","Cc");//
	public static final Letter d=new Letter("d","d");//
	public static final Letter D=new Letter("D","Dc");//
	public static final Letter e=new Letter("e","e");//
	public static final Letter E=new Letter("E","Ec");//
	public static final Letter el=new Letter("/e","el");//
	public static final Letter El=new Letter("/E","Elc");//
	public static final Letter f=new Letter("f","f");//
	public static final Letter F=new Letter("F","Fc");//
	public static final Letter g=new Letter("g","g");//
	public static final Letter G=new Letter("G","Gc");//
	public static final Letter h=new Letter("h","h");//
	public static final Letter H=new Letter("H","Hc");//
	public static final Letter i=new Letter("i","i");//
	public static final Letter I=new Letter("I","Ic");//
	public static final Letter il=new Letter("/i","il");//
	public static final Letter Il=new Letter("/I","Ilc");//
	public static final Letter j=new Letter("j","j");
	public static final Letter J=new Letter("J","jc");
	public static final Letter k=new Letter("k","k");//
	public static final Letter K=new Letter("K","Kc");//
	public static final Letter l=new Letter("l","l");//
	public static final Letter L=new Letter("L","Lc");//
	public static final Letter m=new Letter("m","m");//
	public static final Letter M=new Letter("M","Mc");//
	public static final Letter n=new Letter("n","n");//
	public static final Letter N=new Letter("N","Nc");//
	public static final Letter o=new Letter("o","o");//
	public static final Letter O=new Letter("O","Oc");//
	public static final Letter ol=new Letter("/o","ol");//
	public static final Letter Ol=new Letter("/O","Olc");//
	public static final Letter p=new Letter("p","p");//
	public static final Letter P=new Letter("P","Pc");//
	public static final Letter q=new Letter("q","q");//
	public static final Letter Q=new Letter("Q","Qc");//
	public static final Letter r=new Letter("r","r");//
	public static final Letter R=new Letter("R","Rc");//
	public static final Letter s=new Letter("s","s");//
	public static final Letter S=new Letter("S","Sc");//
	public static final Letter t=new Letter("t","t");//
	public static final Letter T=new Letter("T","Tc");//
	public static final Letter u=new Letter("u","u");//
	public static final Letter U=new Letter("U","Uc");//
	public static final Letter ul=new Letter("/u","ul");//
	public static final Letter Ul=new Letter("/U","Ulc");
	public static final Letter ud=new Letter("w","ud");//
	public static final Letter Ud=new Letter("W","Udc");//
	public static final Letter w=new Letter("/w","w");//
	public static final Letter W=new Letter("/W","wc");//
	public static final Letter v=new Letter("v","v");//
	public static final Letter V=new Letter("V","Vc");//
	public static final Letter x=new Letter("x","x");//
	public static final Letter X=new Letter("X","Xc");//
	public static final Letter y=new Letter("y","y");//
	public static final Letter Y=new Letter("Y","Yc");//
	public static final Letter z=new Letter("z","z");//
	public static final Letter Z=new Letter("Z","Zc");//
	public static final Letter ZERO=new Letter("0","zero");//
	public static final Letter ONE=new Letter("1","one");//
	public static final Letter TWO=new Letter("2","two");//
	public static final Letter THREE=new Letter("3","three");//
	public static final Letter FOUR=new Letter("4","four");//
	public static final Letter FIVE=new Letter("5","five");//
	public static final Letter SIX=new Letter("6","six");//
	public static final Letter SEVEN=new Letter("7","seven");//
	public static final Letter EIGHT=new Letter("8","eight");//
	public static final Letter NINE=new Letter("9","nine");//
	public static final Letter TEN=new Letter("/1","ten");//
	public static final Letter ELEVEN=new Letter("/2","eleven");//
	public static final Letter TWELVE=new Letter("/3","twelve");
	public static final Letter THIRTEEN=new Letter("/4","thirteen");//
	public static final Letter FOURTEEN=new Letter("/5","fourteen");//
	public static final Letter FIFTEEN=new Letter("/6","fifteen");//
	public static final Letter SIXTEEN=new Letter("/7","sixteen");//
	public static final Letter SLASH=new Letter("//","slash");//
	public static final Letter BACK_SLASH=new Letter("\\","backslash");//
	public static final Letter PERIOD=new Letter(".","period");//
	public static final Letter DOUBLE_QUOTES=new Letter("\"","doublequotes");
	public static final Letter SINGLE_QUOTE=new Letter("'","singlequote");
	public static final Letter COMMA=new Letter(",","comma");//
	public static final Letter EXCLAMATION=new Letter("!","exclamation");//
	public static final Letter IBANG=new Letter("/?","ibang");//
	public static final Letter SEMICOLON=new Letter(";","semicolon");//
	public static final Letter COLON=new Letter(":","colon");//
	public static final Letter QUESTION=new Letter("?","question");//
	public static final Letter SQUIGGLE=new Letter("~","squiggle");
	public static final Letter MINUS=new Letter("-","minus");//
	public static final Letter PLUS=new Letter("+","plus");//
	public static final Letter EQUALS=new Letter("=","equals");//
	public static final Letter UNDERSCORE=new Letter("_","underscore");//
	public static final Letter AT=new Letter("@","at");//
	public static final Letter POUND=new Letter("#","pound");//
	public static final Letter AND=new Letter("&","and");
	public static final Letter CARROT=new Letter("^","carrot");//
	public static final Letter LESS=new Letter("<","less");
	public static final Letter MORE=new Letter(">","more");
	public static final Letter NEWLINE=new Letter("/n","space");//
	public static final Letter SPACE=new Letter(" ","space");//
	public static final Letter ASTRISK=new Letter("*","astrisk");//
	public static final Letter MONEY=new Letter("$","money");
	public static final Letter L_PARENT=new Letter("(","lparent");//
	public static final Letter R_PARENT=new Letter(")","rparent");//
	public static final Letter L_BRACK=new Letter("[","lbrack");//
	public static final Letter R_BRACK=new Letter("]","rbrack");//
	public static final Letter L_BRACE=new Letter("{","lbrace");//
	public static final Letter R_BRACE=new Letter("}","rbrace");//
	public static final Letter LINE=new Letter("|","line");//
	public static final Letter PERCENT=new Letter("%","percent");//
	
	private Letter(String value,String img) {
		image16=loadImage(img);
		image32=loadImage(img+"32");
		image64=loadImage(img+"64");
		this.value=value;
	}
	
	private Image loadImage(String img) {
		return new ImageIcon("assets/letters/"+img+".png").getImage();
	}
	
	public String value() {
		return value;
	}
	
	public Image img16() {
		return image16;
	}
	
	public Image img32() {
		return image32;
	}
	
	public Image img64() {
		return image64;
	}
	
	public static Letter[] lettersFromString(String s) {
		ArrayList<Letter> letters=new ArrayList<>();
		for (int i=0; i<s.length();i++) {
			char c=s.charAt(i);
			switch (c){
				case '/':
					i++;
					try {
					c=s.charAt(i);
					} catch (Exception e) {
						
					}
					if (c=='/') {
						letters.add(SLASH);
					} else if (c=='a') {
						letters.add(al);
					} else if (c=='A') {
						letters.add(Al); 
					} else if (c=='i') {
						letters.add(il);
					} else if (c=='I') {
						letters.add(Il);
					} else if (c=='o') {
						letters.add(ol);
					} else if (c=='O') {
						letters.add(Ol);
					} else if (c=='e') {
						letters.add(el);
					} else if (c=='E') {
						letters.add(El);
					} else if (c=='u') {
						letters.add(ul);
					} else if (c=='U') {
						letters.add(Ul);
					} else if (c=='n') {
						letters.add(NEWLINE);
					} else if (c=='1') {
						letters.add(TEN);
					} else if (c=='2') {
						letters.add(ELEVEN);
					}  else if (c=='3') {
						letters.add(TWELVE);
					}  else if (c=='4') {
						letters.add(THIRTEEN);
					}  else if (c=='5') {
						letters.add(FOURTEEN);
					}  else if (c=='6') {
						letters.add(FIFTEEN);
					}  else if (c=='7') {
						letters.add(SIXTEEN);
					} else if (c=='w') {
						letters.add(w);
					} else if (c=='W') {
						letters.add(W);
					} else if (c=='?') {
						letters.add(IBANG);
					}
				break;
				case 'a':
					letters.add(a);
				break;
				case 'A':
					letters.add(A);
				break;
				case 'b':
					letters.add(b);
				break;
				case 'B':
					letters.add(B);
				break;
				case 'c':
					letters.add(Letter.c);
				break;
				case 'C':
					letters.add(C);
				break;
				case 'd':
					letters.add(d);
				break;
				case 'D':
					letters.add(D);
				break;
				case 'e':
					letters.add(e);
				break;
				case 'E':
					letters.add(E);
				break;
				case 'f':
					letters.add(f);
				break;
				case 'F':
					letters.add(F);
				break;
				case 'g':
					letters.add(g);
				break;
				case 'G':
					letters.add(G);
				break;
				case 'h':
					letters.add(h);
				break;
				case 'H':
					letters.add(H);
				break;
				case 'i':
					letters.add(Letter.i);
				break;
				case 'I':
					letters.add(I);
				break;
				case 'j':
					letters.add(Letter.j);
				break;
				case 'J':
					letters.add(J);
				break;
				case 'k':
					letters.add(k);
				break;
				case 'K':
					letters.add(K);
				break;
				case 'l':
					letters.add(l);
				break;
				case 'L':
					letters.add(L);
				break;
				case 'm':
					letters.add(m);
				break;
				case 'M':
					letters.add(M);
				break;
				case 'n':
					letters.add(n);
				break;
				case 'N':
					letters.add(N);
				break;
				case 'o':
					letters.add(o);
				break;
				case 'O':
					letters.add(O);
				break;
				case 'p':
					letters.add(p);
				break;
				case 'P':
					letters.add(P);
				break;
				case 'q':
					letters.add(q);
				break;
				case 'Q':
					letters.add(Q);
				break;
				case 'r':
					letters.add(r);
				break;
				case 'R':
					letters.add(R);
				break;
				case 's':
					letters.add(Letter.s);
				break;
				case 'S':
					letters.add(S);
				break;
				case 't':
					letters.add(t);
				break;
				case 'T':
					letters.add(T);
				break;
				case 'u':
					letters.add(u);
				break;
				case 'U':
					letters.add(U);
				break;
				case 'w':
					letters.add(ud);
				break;
				case 'W':
					letters.add(Ud);
				break;
				case 'v':
					letters.add(v);
				break;
				case 'V':
					letters.add(V);
				break;
				case 'x':
					letters.add(x);
				break;
				case 'X':
					letters.add(X);
				break;
				case 'y':
					letters.add(y);
				break;
				case 'Y':
					letters.add(Y);
				break;
				case 'z':
					letters.add(z);
				break;
				case 'Z':
					letters.add(Z);
				break;
				case '.':
					letters.add(PERIOD);
				break;
				case ',':
					letters.add(COMMA);
				break;
				case '\"':
					letters.add(DOUBLE_QUOTES);
				break;
				case '\'':
					letters.add(SINGLE_QUOTE);
				break;
				case '!':
					letters.add(EXCLAMATION);
				break;
				case '?':
					letters.add(QUESTION);
				break;
				case '+':
					letters.add(PLUS);
				break;
				case '-':
					letters.add(MINUS);
				break;
				case '=':
					letters.add(EQUALS);
				break;
				case '_':
					letters.add(UNDERSCORE);
				break;
				case '~':
					letters.add(SQUIGGLE);
				break;
				case ':':
					letters.add(COLON);
				break;
				case ';':
					letters.add(SEMICOLON);
				break;
				case '1':
					letters.add(ONE);
				break;
				case '2':
					letters.add(TWO);
				break;
				case '3':
					letters.add(THREE);
				break;
				case '4':
					letters.add(FOUR);
				break;
				case '5':
					letters.add(FIVE);
				break;
				case '6':
					letters.add(SIX);
				break;
				case '7':
					letters.add(SEVEN);
				break;
				case '8':
					letters.add(EIGHT);
				break;
				case '9':
					letters.add(NINE);
				break;
				case '0':
					letters.add(ZERO);
				break;
				case '@':
					letters.add(AT);
				break;
				case '#':
					letters.add(POUND);
				break;
				case '&':
					letters.add(AND);
				break;
				case '^':
					letters.add(CARROT);
				break;
				case '<':
					letters.add(LESS);
				break;
				case '>':
					letters.add(MORE);
				break;
				case '(':
					letters.add(L_PARENT);
				break;
				case ')':
					letters.add(R_PARENT);
				break;
				case '[':
					letters.add(L_BRACK);
				break;
				case ']':
					letters.add(R_BRACK);
				break;
				case '{':
					letters.add(L_BRACE);
				break;
				case '}':
					letters.add(R_BRACE);
				break;
				case '|':
					letters.add(LINE);
				break;
				case ' ':
					letters.add(SPACE);
				break;
				case '*':
					letters.add(ASTRISK);
				break;
				case '$':
					letters.add(MONEY);
				break;
				case '\\':
					letters.add(BACK_SLASH);
				break;
				case '%':
					letters.add(PERCENT);
				break;
				/*case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;
				case '':
					
				break;*/
				
			}
		}
		return letters.toArray(new Letter[letters.size()]);
	}
	
	public static int indexOf(Letter[] string,Letter l) {
		for (int i=0;i>string.length;i++) {
			if (string[i].value().equals(l.value)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(Letter[] string,Letter[] l) {
		if (l.length==0) {
			return 0;
		}
		for (int i=0;i<string.length+1-l.length;i++) {
			boolean matches=true;
			for (int j=0;j<l.length;j++) {
				if (!string[i+j].value().equals(l[j].value)) {
					matches=false;
					break;
				}
			}
			if (matches) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(Letter[] string,Letter[] l,int start) {
		return indexOf(substring(string,start),l);
	}
	
	public static int indexOf(Letter[] string,Letter l,int start) {
		for (int i=start;i>string.length;i++) {
			if (string[i].value().equals(l.value)) {
				return i;
			}
		}
		return -1;
	}
	
	public static Letter[]  substring(Letter[] string,int start) {
		if (start>string.length) {
			return new Letter[0];
		}
		Letter[] sub=new Letter[string.length-start];
		for (int i=start;i<string.length;i++) {
			sub[i-start]=string[i];
		}
		return sub;
	}
	
	public static Letter[] substring(Letter[] string,int start,int end) {
		if (start>end) {
			return new Letter[0];
		}
		Letter[] sub=new Letter[end-start];
		for (int i=start;i<end;i++) {
			sub[i-start]=string[i];
		}
		return sub;
	}
	
	public static Letter[] getLine(Letter[] string, int line) {
		int index=-1;
		for (int i=0;i<line;i++) {
			index+=indexOf(string,NEWLINE,index+1);
		}
		int secondIndex=indexOf(string,NEWLINE,index+1);
		if (index<0) {
			if (secondIndex>-1) {
				return substring(string,0,secondIndex);
			}
			return new Letter[0];
		}
		if (secondIndex<0) {
			secondIndex=string.length;
		}
		return substring(string,index,secondIndex);
		
	}
	
}
