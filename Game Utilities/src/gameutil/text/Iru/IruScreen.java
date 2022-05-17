package gameutil.text.Iru;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import gameutil.text.Iru.IruConsole.TAdapter;
import graphics.screen.SPanel;
import graphics.screen.Screen;

public class IruScreen extends Screen{

	private boolean userNextLineEnabled=false;
	Str text=new Str();
	private Str textToPaint=text;
	private int y=0;
	private int maxY=0;
	private int speed=1;
	private int cx=-1;
	private int cy=-1;
	private Timer t;
	private long lastBlink=System.currentTimeMillis();
	private boolean showCursor=true;
	private boolean showCursorOver=false;
	private boolean overrideRead=false;
	private final long cursorBlinkTime=500;
	private boolean autoScroll=true;
	private int scrollToBottom=0;
	SPanel f;
	private boolean reading;
	private boolean readingNumber;
	private boolean paused;
	private boolean isInt;
	private boolean decimaled;
	private String readCache;
	private boolean skipLineAfterRead;
	private boolean shift=false;
	private boolean accent=false;
	
	
	public IruScreen(SPanel observer) {
		this.f=observer;
		reading = false;
		isInt = false;
		readingNumber = false;
		readCache = "";
		skipLineAfterRead = false;
		decimaled = false;
		
	}
	
	@Override
	public void paint(Graphics g) {
		//System.out.println(text);
				g.setColor(Color.blue);
				g.fillRect(0, 0, f.getWidth(), f.getHeight());
				int maxX=(f.getWidth()-15)/16;
				int maxY=f.getHeight()+16;
				//System.out.println("height "+f.getHeight());
				int x=0;
				int y=0;
				Str text=textToPaint;//this.text.clone();
				try {
				if (((reading&&!overrideRead)||(overrideRead&&showCursorOver))&&showCursor) {
					if (cx<0&&cy<0) {
						text=text.cat("|");
					} else if (cx<0) {
						text.replace(cy, 0, "|");
					} else if (cy<0) {
						text.replace(text.getNoOfLines()-1, cx, "|");
					} else {
						text.replace(cy, cx, "|");
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Text: "+text);
				}
				Letter[] letters=text.getLetters();
				for (int i=0;i<letters.length;i++) {
					Letter l=letters[i];
					if (l.value().equals("/n")) {
						x=0;
						y++;
					} else if (y*18+2-this.y>-18&&y*18+2-this.y<maxY) {
						g.drawImage(l.img16(), x*16, y*18+2-this.y, f);
						x++;
						if (x>maxX) {
							x=0;
							y++;
						}
					} else {
						x++;
						if (x>maxX) {
							x=0;
							y++;
						}
					}
					
				}
				maxY-=16;
				this.maxY=(y+1)*18-maxY+3;
				if (this.maxY<0) {
					this.maxY=0;
				}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis()-lastBlink>cursorBlinkTime) {
			lastBlink=System.currentTimeMillis();
			showCursor=!showCursor;
		}
		if (scrollToBottom>0) {
			scrollToBottom();
			
			scrollToBottom--;
			if (scrollToBottom>10) {//this should prevent more than 110 milliseconds of forced scrolling to the bottom without an influx of scroll ops
				scrollToBottom=10;
			}
		}
		//do this so that if the window size changes it doesn't just show nothing
		if (y>maxY) {
			scrollToBottom++;
		}
		textToPaint=text.clone();
	}
	
	public void print(String s) {
		keylistenerprint(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	
	public void print(Str s) {
		keylistenerprint(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	public void print(Letter[] l) {
		String s="";
		for (int i=0;i<l.length;i++) {
			s=s+l[i].value();
		}
		keylistenerprint(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	public void printLine(String s) {
		keylistenerprint(s+"/n");
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	public void printLine(Str s) {
		println(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	public void printLine(Letter[] l) {
		String s="";
		for (int i=0;i<l.length;i++) {
			s=s+l[i].value();
		}
		keylistenerprint(s+"/n");
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	public void printLine() {
		keylistenerprint("/n");
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	/**
	 * Gets input from the user and goes to the next line
	 * 
	 * @return input
	 */
	public String readLine() {
		if (autoScroll) {
			scrollToBottom++;
		}
		return keylistenerreadLine();
	}
	
	public Int readInt() {
		if (autoScroll) {
			scrollToBottom++;
		}
		return keylistenerreadInt();
	}
	
	public Int readLineInt() {
		if (autoScroll) {
			scrollToBottom++;
		}
		return keylistenerreadLineInt();
	}
	
	/**
	 * Gets input from the user and goes to the next line
	 * 
	 * @return input
	 */
	public String read() {
		if (autoScroll) {
			scrollToBottom++;
		}
		return keylistenerread();
	}
	
	public void pause() {
		//println("Press any key to continue...");// wasn't going to include this but got some feedback about people not knowing what to do
		Pause();
	}
	
	public void scroll(int location) {
		y=location;
		if(y<0) {
			y=0;
		} else if (y>maxY) {
			y=maxY;
		}
	}
	
	public void scrollToBottom() {
		scroll(maxY);
	}
	
	public void setAutoScroll(boolean as) {
		autoScroll=as;
	}
	
	@Override
	public void mouseScrolled(MouseWheelEvent e) {
		scroll(y+e.getWheelRotation()*3);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
			shift=true;
			return;
		} else if (e.getKeyCode()==KeyEvent.VK_BACK_QUOTE) {
			accent=!accent;
			return;
		} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			scroll(y+18);
			return;
		} else if (e.getKeyCode()==KeyEvent.VK_UP) {
			scroll(y-18);
			return;
		}
		if (paused) {
			//unpause but don't interfere with reading actions
			paused=false;
			return;
		} else if (readingNumber && !reading) {
			if (autoScroll) {
				scrollToBottom();
			}
			String print = "";
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				readingNumber = false;
				if (skipLineAfterRead) {
					println();
				}
				return;
			} else if (e.getKeyCode() >= 48 && e.getKeyCode() < 58) {
				int no = e.getKeyCode() - 48;
				print = Integer.toString(no);
				if (no<8&&no>0&&accent) {
					print="/"+print;
					accent=false;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
				if (readCache.length() > 0) {
					if (readCache.endsWith(".")) {
						decimaled = false;
					}
					readCache = readCache.substring(0, readCache.length() - 1);
					backSpace();
				}
			} else if (!isInt && !decimaled && e.getKeyCode() == KeyEvent.VK_PERIOD) {
				print = ".";
				decimaled = true;
			} else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT || e.getKeyCode() == KeyEvent.VK_MINUS) {
				if (readCache.equals("")) {
					readCache = "-" + readCache;
					print("-");
				}
			}
			print(print);
			readCache = readCache + print;
		} else if (reading) {
			if (autoScroll) {
				scrollToBottom();
			}
			String print="";
			switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					if (!userNextLineEnabled || !e.isShiftDown()) {
						if (skipLineAfterRead) {
							println();
						}
						reading = false;
					} else {
						println();
						readCache = readCache + "/n";
					}
				break;
				case KeyEvent.VK_SPACE:
					print=" ";
				break;
				case KeyEvent.VK_A:
					if (accent) {
						if (shift) {
							print="/A";
						} else {
							print="/a";
						}
					} else {
						if (shift) {
							print="A";
						} else {
							print="a";
						}
					}
					
				break;
				case KeyEvent.VK_B:
					if (shift) {
						print="B";
					} else {
						print="b";
					}
				break;
				case KeyEvent.VK_C:
					if (shift) {
						print="C";
					} else {
						print="c";
					}
				break;
				case KeyEvent.VK_D:
					if (shift) {
						print="D";
					} else {
						print="d";
					}
				break;
				case KeyEvent.VK_E:
					if (accent) {
						if (shift) {
							print="/E";
						} else {
							print="/e";
						}
					} else {
						if (shift) {
							print="E";
						} else {
							print="e";
						}
					}
				break;
				case KeyEvent.VK_F:
					if (shift) {
						print="F";
					} else {
						print="f";
					}
				break;
				case KeyEvent.VK_G:
					if (shift) {
						print="G";
					} else {
						print="g";
					}
				break;
				case KeyEvent.VK_H:
					if (shift) {
						print="H";
					} else {
						print="h";
					}
				break;
				case KeyEvent.VK_I:
					if (accent) {
						if (shift) {
							print="/I";
						} else {
							print="/i";
						}
					} else {
						if (shift) {
							print="I";
						} else {
							print="i";
						}
					}
				break;
				case KeyEvent.VK_J:
					if (shift) {
						print="J";
					} else {
						print="j";
					}
				break;
				case KeyEvent.VK_K:
					if (shift) {
						print="K";
					} else {
						print="k";
					}
				break;
				case KeyEvent.VK_L:
					if (shift) {
						print="L";
					} else {
						print="l";
					}
				break;
				case KeyEvent.VK_M:
					if (shift) {
						print="M";
					} else {
						print="m";
					}
				break;
				case KeyEvent.VK_N:
					if (shift) {
						print="N";
					} else {
						print="n";
					}
				break;
				case KeyEvent.VK_O:
					if (accent) {
						if (shift) {
							print="/O";
						} else {
							print="/o";
						}
					} else {
						if (shift) {
							print="O";
						} else {
							print="o";
						}
					}
				break;
				case KeyEvent.VK_P:
					if (shift) {
						print="P";
					} else {
						print="p";
					}
				break;
				case KeyEvent.VK_Q:
					if (shift) {
						print="Q";
					} else {
						print="q";
					}
				break;
				case KeyEvent.VK_R:
					if (shift) {
						print="R";
					} else {
						print="r";
					}
				break;
				case KeyEvent.VK_S:
					if (shift) {
						print="S";
					} else {
						print="s";
					}
				break;
				case KeyEvent.VK_T:
					if (shift) {
						print="T";
					} else {
						print="t";
					}
				break;
				case KeyEvent.VK_U:
					if (accent) {
						if (shift) {
							print="/U";
						} else {
							print="/u";
						}
					} else {
						if (shift) {
							print="U";
						} else {
							print="u";
						}
					}
				break;
				case KeyEvent.VK_W:
					if (accent) {
						if (shift) {
							print="/W";
						} else {
							print="/w";
						}
					} else {
						if (shift) {
							print="W";
						} else {
							print="w";
						}
					}
				break;
				case KeyEvent.VK_V:
					if (shift) {
						print="V";
					} else {
						print="v";
					}
				break;
				case KeyEvent.VK_X:
					if (shift) {
						print="X";
					} else {
						print="x";
					}
				break;
				case KeyEvent.VK_Y:
					if (shift) {
						print="Y";
					} else {
						print="y";
					}
				break;
				case KeyEvent.VK_Z:
					if (shift) {
						print="Z";
					} else {
						print="z";
					}
				break;
				case KeyEvent.VK_PERIOD:
					if (shift) {
						print=">";
					} else {
						print=".";
					}
				break;
				case KeyEvent.VK_COMMA:
					if (shift) {
						print="<";
					} else {
						print=",";
					}
				break;
				case KeyEvent.VK_SEMICOLON:
					if (shift) {
						print=":";
					} else {
						print=";";
					}
				break;
				case KeyEvent.VK_COLON:
					print=":";
				break;
				case KeyEvent.VK_QUOTE:
					if (shift) {
						print="\"";
					} else {
						print="'";
					}
				break;
				case KeyEvent.VK_0:
					if (shift) {
						print=")";
					} else {
						print="0";
					}
				break;
				case KeyEvent.VK_1:
					if (accent) {
						print="!";
					} else if (shift) {
						print="/1";
					} else {
						print="1";
					}
				break;
				case KeyEvent.VK_2:
					if (accent) {
						print="@";
					} else if (shift) {
						print="/2";
					} else {
						print="2";
					}
				break;
				case KeyEvent.VK_3:
					if (accent) {
						print="#";
					} else if (shift) {
						print="/3";
					} else {
						print="3";
					}
				break;
				case KeyEvent.VK_4:
					if (accent) {
						print="$";
					} else if (shift) {
						print="/4";
					} else {
						print="4";
					}
				break;
				case KeyEvent.VK_5:
					if (accent) {
						print="%";
					} else if (shift) {
						print="/5";
					} else {
						print="5";
					}
				break;
				case KeyEvent.VK_6:
					if (accent) {
						print="^";
					} else if (shift) {
						print="/6";
					} else {
						print="6";
					}
				break;
				case KeyEvent.VK_7:
					if (accent) {
						print="&";
					} else if (shift) {
						print="/7";
					} else {
						print="7";
					}
				break;
				case KeyEvent.VK_8:
					if (shift||accent) {
						print="*";
					} else {
						print="8";
					}
				break;
				case KeyEvent.VK_9:
					if (shift||accent) {
						print="(";
					} else {
						print="9";
					}
				break;
				case KeyEvent.VK_MINUS:
					if (accent) {
						print="~";
					} else if (shift) {
						print="_";
					} else {
						print="-";
					}
				break;
				case KeyEvent.VK_EQUALS:
					if (accent) {
						print="+";//may change to approximately sign
					} else if (shift) {
						print="+";
					} else {
						print="=";
					}
				break;
				case KeyEvent.VK_SLASH:
					if (accent) {
						print="/?";
					} else if (shift) {
						print="?";
					} else {
						print="//";
					}
				break;
				case KeyEvent.VK_BACK_SLASH:
					if (shift) {
						print="|";
					} else {
						print="\\";
					}
				break;
				case KeyEvent.VK_OPEN_BRACKET:
					if (shift) {
						print="{";
					} else {
						print="[";
					}
				break;
				case KeyEvent.VK_CLOSE_BRACKET:
					if (shift) {
						print="}";
					} else {
						print="]";
					}
				break;
				case KeyEvent.VK_NUMPAD8:
					cy=cy-1;
					System.out.println("("+cx+", "+cy+")");
				break;
				case KeyEvent.VK_NUMPAD6:
					cx=cx+1;
					System.out.println("("+cx+", "+cy+")");
				break;
				case KeyEvent.VK_NUMPAD2:
					cy=cy+1;
					System.out.println("("+cx+", "+cy+")");
				break;
				case KeyEvent.VK_NUMPAD4:
					cx=cx-1;
					System.out.println("("+cx+", "+cy+")");
				break;
			}
			//System.out.println(print);			
			if (accent) {
				accent=false;
			}
			readCache=readCache+print;
			print(print);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
			shift=false;
		} else if (e.getKeyCode()==KeyEvent.VK_BACK_QUOTE) {
			//accent=false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		if (reading && !readingNumber && !paused) {
			if (Character.getName(e.getKeyChar()).equals("LINE FEED (LF)")) {
				// need this so that it doesn't print enter
				return;
			} else if (Character.getName(e.getKeyChar()).equals("BACKSPACE")
					|| Character.getName(e.getKeyChar()).equals("DELETE")) {
				Str rStr=new Str(readCache);
				if (rStr.length() > 0) {
					if (readCache.length()>1&&readCache.substring(readCache.length()-2).contains("/")) {
						readCache=readCache.substring(0, readCache.length() - 2);
						text=text.substring(0, text.length() - 1);
					} else {
						readCache = readCache.substring(0, readCache.length() - 1);
						text=text.substring(0, text.length()-1);
					}
					scrollToBottom++;
				}
				return;
			}
		}
	}
	
	public void backSpace() {
		text=(text.substring(0, text.length() - 1));
	}

	public Str getLine(long line) {
		return text.getLine((int)line);
	}
	
	public void setLine(long line,Str s) {
		text.setLine((int)line, s);
	}
	
	public void insert(int line,int index,Str s) {
		text.insert(line,index,s);
	}

	public int getNoOfLines() {
		return text.getNoOfLines();
	}

	private void keylistenerprint(String s) {
		text=text.cat(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	private void keylistenerprint(Str s) {
		text=text.cat(s);
		if (autoScroll) {
			scrollToBottom++;
		}
	}

	private void println(String s) {
		text=text.cat(s).cat(Letter.NEWLINE);
		if (autoScroll) {
			scrollToBottom++;
		}
	}

	private void println(Str s) {
		text=text.cat(s).cat(Letter.NEWLINE);
		if (autoScroll) {
			scrollToBottom++;
		}
	}
	
	private void println() {
		text=text.cat(Letter.NEWLINE);
		if (autoScroll) {
			scrollToBottom++;
		}
	}

	public String keylistenerreadLine() {
		//System.out.println("Awaiting user input");
		skipLineAfterRead = true;
		readCache = "";
		reading = true;
		while (reading) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		return readCache;
	}
	
	public void Pause() {
		paused=true;
		while (paused) {
			doNothing();
		}
	}
	
	public String keylistenerread() {
		reading = true;
		readCache = "";
		skipLineAfterRead = false;
		//System.out.println("Awaiting user input");
		while (reading) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		return readCache;
	}

	public Int keylistenerreadInt() {
		readingNumber = true;
		readCache = "";
		skipLineAfterRead = false;
		isInt = true;
		//System.out.println("Awaiting user input of integer type");
		while (readingNumber) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		if (readCache.equals("")) {
			return new Int(0);
		}
		int val;
		try {
			val = new Int(readCache).val();;
		} catch (NumberFormatException e) {
			if (Long.parseLong(readCache) > 2147483647) {
				val = 2147483647;
				println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
				println("Number entered is too large!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
			} else {
				val = -2147483648;
				println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
				println("Number entered is too small!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
			}
		}
		return new Int(val);
	}

	public Int keylistenerreadLineInt() {
		readingNumber = true;
		readCache = "";
		skipLineAfterRead = true;
		isInt = true;
		//System.out.println("Awaiting user input of integer type");
		while (readingNumber) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		if (readCache.equals("")) {
			return new Int(0);
		}
		int val;
		try {
			val = new Int(readCache).val();
		} catch (NumberFormatException e) {
			if (Long.parseLong(readCache) > 2147483647) {
				val = 2147483647;
				println("â—„â•�â•�â•�ERRORâ•�â•�â•�â–º");
				println("Number entered is too large!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
			} else {
				val = -2147483648;
				println("â—„â•�â•�â•�ERRORâ•�â•�â•�â–º");
				println("Number entered is too small!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
			}
		}
		return new Int(val);
	}

	public double readDouble() {
		readingNumber = true;
		readCache = "";
		skipLineAfterRead = false;
		isInt = false;
		decimaled = false;
		//System.out.println("Awaiting user input of double type");
		while (readingNumber) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		if (readCache.equals("")) {
			return 0;
		}
		return Double.parseDouble(readCache);
	}

	public double readLineDouble() {
		readingNumber = true;
		readCache = "";
		skipLineAfterRead = true;
		isInt = false;
		decimaled = false;
		//System.out.println("Awaiting user input of double type");
		while (readingNumber) {
			doNothing();
		}
		if (autoScroll) {
			scrollToBottom++;
		}
		//System.out.println("User input recieved");
		if (readCache.equals("")) {
			return 0;
		}
		
		return Double.parseDouble(readCache);
	}

	/**
	 * filler for infinite while loop
	 */
	private void doNothing() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void interrupt() {
		readingNumber=false;
		reading=false;
	}

	@Override
	protected void keyDown(int key) {
		// TODO Auto-generated method stub
		
	}

}
