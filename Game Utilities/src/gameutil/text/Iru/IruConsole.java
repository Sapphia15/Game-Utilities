package gameutil.text.Iru;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class IruConsole extends JPanel implements ActionListener{
	private boolean userNextLineEnabled=false;
	private TAdapter keylistener;
	String text="";
	private int y=0;
	private int maxY=0;
	private Timer t;
	private boolean autoScroll=true;
	private boolean scrollToBottom=false;
	JFrame f;
	
	public IruConsole() {
		f=new JFrame();
		f.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2,(Toolkit.getDefaultToolkit().getScreenSize().height / 3)));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setFocusable(true);
		
		f.setVisible(true);
		f.add(this);
		keylistener=new TAdapter();
		//addKeyListener(keylistener);
		f.addKeyListener(keylistener);
		addMouseWheelListener(new MWAdapter());
		setFocusable(true);
		t=new Timer(10, this);
		t.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//System.out.println(text);
		g.setColor(Color.blue);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int maxX=(this.getWidth()-15)/16;
		int maxY=this.getHeight()+16;
		int x=0;
		int y=0;
		Letter[] letters=Letter.lettersFromString(text);
		for (int i=0;i<letters.length;i++) {
			Letter l=letters[i];
			if (l.value().equals("/n")) {
				x=0;
				y++;
			} else if (y*18+2-this.y>-18&&y*18+2-this.y<maxY) {
				g.drawImage(l.img16(), x*16, y*18+2-this.y, this);
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
	
	public void print(String s) {
		keylistener.print(s);
	}
	public void setTitle(String s) {
		f.setTitle(s);
	}
	
	public void print(Letter[] l) {
		String s="";
		for (int i=0;i<l.length;i++) {
			s=s+l[i].value();
		}
		keylistener.print(s);
	}
	
	public void printLine(String s) {
		keylistener.print(s+"/n");
	}
	
	public void printLine(Letter[] l) {
		String s="";
		for (int i=0;i<l.length;i++) {
			s=s+l[i].value();
		}
		keylistener.print(s+"/n");
	}
	
	public void printLine() {
		keylistener.print("/n");
	}
	
	/**
	 * Gets input from the user and goes to the next line
	 * 
	 * @return input
	 */
	public String readLine() {
		return keylistener.readLine();
	}
	
	/**
	 * Gets input from the user and goes to the next line
	 * 
	 * @return input
	 */
	public String read() {
		return keylistener.read();
	}
	
	public void pause() {
		//println("Press any key to continue...");// wasn't going to include this but got some feedback about people not knowing what to do
		keylistener.Pause();
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
	
	public class MWAdapter implements MouseWheelListener{

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			scroll(y+e.getWheelRotation()*3);
			
		}
	}

	public class TAdapter extends KeyAdapter {
		private boolean reading;
		private boolean readingNumber;
		private boolean paused;
		private boolean isInt;
		private boolean decimaled;
		private String readCache;
		private boolean skipLineAfterRead;
		private boolean shift=false;
		private boolean accent=false;
		
		public TAdapter() {
			reading = false;
			isInt = false;
			readingNumber = false;
			readCache = "";
			skipLineAfterRead = false;
			decimaled = false;
			/*ScheduledExecutorService s=Executors.newScheduledThreadPool(1);
			s.schedule(new Runnable() {public void run() {keySim(KeyEvent.VK_ENTER);}}, 500, TimeUnit.MILLISECONDS);
			readInt();*/
			
		}
		
		/*public void keySim(int keyCode) {
			if (readingNumber && !reading) {
				String print = "";
				if (keyCode == KeyEvent.VK_ENTER) {
					readingNumber = false;
					if (skipLineAfterRead) {
						println();
					}
					return;
				} else if (keyCode >= 48 && keyCode < 58) {
					int no = keyCode - 48;
					print = Integer.toString(no);
				} else if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE) {
					if (readCache.length() > 0) {
						if (readCache.endsWith(".")) {
							decimaled = false;
						}
						readCache = readCache.substring(0, readCache.length() - 1);
						backSpace();
					}
				} else if (!isInt && !decimaled && keyCode == KeyEvent.VK_PERIOD) {
					print = ".";
					decimaled = true;
				} else if (keyCode == KeyEvent.VK_SUBTRACT || keyCode == KeyEvent.VK_MINUS) {
					if (readCache.equals("")) {
						readCache = "-" + readCache;
						print("-");
					}
				}
				print(print);
				readCache = readCache + print;
			}
			if (reading && keyCode == KeyEvent.VK_ENTER) {
				if (skipLineAfterRead) {
					println();
				}
				reading = false;
			}
		}
		
		/*public void keySim(int keyCode,boolean shiftDown) {
			if (readingNumber && !reading) {
				String print = "";
				if (keyCode == KeyEvent.VK_ENTER) {
					readingNumber = false;
					if (skipLineAfterRead) {
						println();
					}
					return;
				} else if (keyCode >= 48 && keyCode < 58) {
					int no = keyCode - 48;
					print = Integer.toString(no);
				} else if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE) {
					if (readCache.length() > 0) {
						if (readCache.endsWith(".")) {
							decimaled = false;
						}
						readCache = readCache.substring(0, readCache.length() - 1);
						backSpace();
					}
				} else if (!isInt && !decimaled && keyCode == KeyEvent.VK_PERIOD) {
					print = ".";
					decimaled = true;
				} else if (keyCode == KeyEvent.VK_SUBTRACT || keyCode == KeyEvent.VK_MINUS) {
					if (readCache.equals("")) {
						readCache = "-" + readCache;
						print("-");
					}
				}
				print(print);
				readCache = readCache + print;
			}
			if (reading && keyCode == KeyEvent.VK_ENTER) {
				if (!userNextLineEnabled || !shiftDown) {
					if (skipLineAfterRead) {
						println();
					}
					reading = false;
				} else {
					println();
					readCache = readCache + "\n";
				}
			}
		}*/
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
				shift=true;
				return;
			} else if (e.getKeyCode()==KeyEvent.VK_BACK_QUOTE) {
				accent=!accent;
				return;
			} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				scroll(y+1);
				return;
			} else if (e.getKeyCode()==KeyEvent.VK_UP) {
				scroll(y-1);
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
				}
				System.out.println(print);			
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
					if (readCache.length() > 0) {
						if (readCache.length()>1&&readCache.substring(readCache.length()-2).contains("/")) {
							readCache=readCache.substring(0, readCache.length() - 2);
							text=text.substring(0, text.length() - 2);
						} else {
							readCache = readCache.substring(0, readCache.length() - 1);
							text=text.substring(0, text.length() - 1);
						}
						scrollToBottom=true;
					}
					return;
				}
			}
		}

		public void backSpace() {
			text=(text.substring(0, text.length() - 1));
		}

		public String getLine(int line) {
			Scanner s = new Scanner(text);
			for (int x = 0; x < line - 1; x++) {
				try {
					s.nextLine();
				} catch (NoSuchElementException e) {
					println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
					println("Line " + line + " not found!");
					println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
					return null;
				}
			}
			try {
				return s.nextLine();
			} catch (NoSuchElementException e) {
				println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
				println("Line " + line + " not found!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
				return null;
			}

		}

		public String getLine(long line) {
			Scanner s = new Scanner(text);
			for (long x = 0; x < line - 1; x++) {
				try {
					s.nextLine();
				} catch (NoSuchElementException e) {
					println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
					println("Line " + line + " not found!");
					println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
					return null;
				}
			}
			try {
				return s.nextLine();
			} catch (NoSuchElementException e) {
				println("\nâ—„â•�â•�â•�â•�ERRORâ•�â•�â•�â•�â–º");
				println("Line " + line + " not found!");
				println("â—„â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â–º");
				return null;
			}

		}

		public long getNoOfLines() {
			Scanner s = new Scanner(text);
			long line = 0;
			while (true) {
				try {
					s.nextLine();
				} catch (NoSuchElementException e) {
					return line;
				}
				line++;
			}
		}

		public void print(String s) {
			text=text+s;
		}

		public void println(String s) {
			text+=(s + "/n");
		}

		public void println() {
			text+=("/n");
		}

		public String readLine() {
			System.out.println("Awaiting user input");
			skipLineAfterRead = true;
			readCache = "";
			reading = true;
			while (reading) {
				doNothing();
			}
			if (autoScroll) {
				scrollToBottom();
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
		
		public String read() {
			reading = true;
			readCache = "";
			skipLineAfterRead = false;
			//System.out.println("Awaiting user input");
			while (reading) {
				doNothing();
			}
			if (autoScroll) {
				scrollToBottom();
			}
			//System.out.println("User input recieved");
			return readCache;
		}

		public int readInt() {
			readingNumber = true;
			readCache = "";
			skipLineAfterRead = false;
			isInt = true;
			//System.out.println("Awaiting user input of integer type");
			while (readingNumber) {
				doNothing();
			}
			if (autoScroll) {
				scrollToBottom();
			}
			//System.out.println("User input recieved");
			if (readCache.equals("")) {
				return 0;
			}
			int val;
			try {
				val = Integer.parseInt(readCache);
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
			return val;
		}

		public int readLineInt() {
			readingNumber = true;
			readCache = "";
			skipLineAfterRead = true;
			isInt = true;
			//System.out.println("Awaiting user input of integer type");
			while (readingNumber) {
				doNothing();
			}
			if (autoScroll) {
				scrollToBottom();
			}
			//System.out.println("User input recieved");
			if (readCache.equals("")) {
				return 0;
			}
			int val;
			try {
				val = Integer.parseInt(readCache);
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
			return val;
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
				scrollToBottom();
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
				scrollToBottom();
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void interrupt() {
			readingNumber=false;
			reading=false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		if (scrollToBottom) {
			scrollToBottom();
			scrollToBottom=false;
		}
	}
}
