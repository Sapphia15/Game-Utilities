package gameutil.text;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Console{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextArea field;
	private TAdapter keylistener;
	private static boolean init=true;
	public enum theme{shell1,shell2,white,sea,forest,pink};
	public static Console s=new Console();
	public Console(){
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2,(Toolkit.getDefaultToolkit().getScreenSize().height/3)));
		frame.pack();
		field=new JTextArea();
		field.setEditable(false);
		keylistener=new TAdapter(field);
		field.addKeyListener(keylistener);
		field.setBackground(Color.BLACK);
		field.setForeground(Color.WHITE);
		JScrollPane scrollPane=new JScrollPane(field);
		frame.add(scrollPane);
		frame.setLocationRelativeTo(null);
		if (init){
			setVisible(false);
			init=false;
		}
	}
	public Console(theme t){
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2,(Toolkit.getDefaultToolkit().getScreenSize().height/3)));
		frame.pack();
		field=new JTextArea();
		field.setEditable(false);
		keylistener=new TAdapter(field);
		field.addKeyListener(keylistener);
		if (t==theme.shell1){
			field.setBackground(Color.BLACK);
			field.setForeground(Color.WHITE);
		} else if (t==theme.shell2){
			field.setBackground(Color.BLACK);
			field.setForeground(Color.GREEN);
		} else if (t==theme.white){
			field.setBackground(Color.WHITE);
			field.setForeground(Color.BLACK);
		} else if (t==theme.sea){
			field.setBackground(Color.CYAN);
			field.setForeground(Color.BLUE);
		} else if (t==theme.forest){
			field.setBackground(Color.getHSBColor(50, 334, 60));
			field.setForeground(Color.GREEN);
		} else if (t==theme.pink){
			field.setBackground(Color.PINK);
			field.setForeground(Color.MAGENTA);
		}
		JScrollPane scrollPane=new JScrollPane(field);
		frame.add(scrollPane);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	/**
	 * Prints a string to the console
	 * @param s
	 */
	public void print(String s){
		field.append(s);
	}
	/**
	 * Prints a string to the console and goes to the next line
	 * @param s
	 */
	public void println(String s){
		field.append(s+"\n");
	}
	/**
	 * Gets input from the user and goes to the next line
	 * @return input
	 */
	public String readLine(){
		return keylistener.readLine();
	}
	/**
	 * Gets input from the user and goes to the next line
	 * @return input
	 */
	public String read(){
		return keylistener.read();
	}
	/**
	 * Gets integer input from the user
	 * @return input
	 */
	public int readInt(){
		return keylistener.readInt();
	}
	/**
	 * Gets integer input from the user and goes to the next line
	 * @return input
	 */
	public int readLineInt(){
		return keylistener.readLineInt();
	}
	/**
	 * Gets double input from the user
	 * @return input
	 */
	public double readDouble(){
		return keylistener.readDouble();
	}
	/**
	 * Gets double input from the user and goes to the next line
	 * @return input
	 */
	public double readLineDouble(){
		return keylistener.readLineDouble();
	}
	/**
	 * Clears all text from the console window
	 */
	public void clr(){
		field.setText("");
	}
	/**
	 * Replaces any current text in the console with the <code>String s</code>. 
	 * @param s
	 */
	public void setText(String s){
		field.setText(s);
	}
	public void backSpace(){
		keylistener.backSpace();
	}
	public void backSpace(int i){
		if (i<1){
			if (i<0){
				i=-1*i;
			} else {
				return;
			}
		}
		for (int x=0;x<i;x++){
			backSpace();
		}
	}
	public void backSpace(long i){
		if (i<1){
			if (i<0){
				i=-1*i;
			} else {
				return;
			}
		}
		for (long x=0;x<i;x++){
			backSpace();
		}
	}
	/**
	 * Returns all of the text in the console in the form of a string
	 * @return
	 */
	public String getText(){
		return field.getText();
	}
	/**
	 * Sets whether the viewer can see the console
	 * @param b
	 */
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	public String getLine(int line){
		return keylistener.getLine(line);
	}
	public class TAdapter extends KeyAdapter{
		private JTextArea field;
		private boolean reading;
		private boolean readingNumber;
		private boolean isInt;
		private boolean decimaled;
		private String readCache;
		private boolean skipLineAfterRead;
		public TAdapter(JTextArea field){
			this.field=field;
			reading=false;
			isInt=false;
			readingNumber=false;
			readCache="";
			skipLineAfterRead=false;
			decimaled=false;
		}
		public void keyPressed(KeyEvent e){
			if (readingNumber&&!reading){
				String print="";
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					readingNumber=false;
					if (skipLineAfterRead){
						println();
					}
					return;
				} else if (e.getKeyCode()>=48&&e.getKeyCode()<58){
					int no=e.getKeyCode()-48;
					print=Integer.toString(no);
				} else if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_DELETE){
					if (readCache.length()>0){
						if (readCache.endsWith(".")){
							decimaled=false;
						}
						readCache=readCache.substring(0, readCache.length()-1);
						backSpace();
					}
				} else if (!isInt&&!decimaled&&e.getKeyCode()==KeyEvent.VK_PERIOD){
					print=".";
					decimaled=true;
				} else if (e.getKeyCode()==KeyEvent.VK_SUBTRACT||e.getKeyCode()==KeyEvent.VK_MINUS){
					if (readCache.equals("")){
						readCache="-"+readCache;
						print("-");
					}
				}
				print(print);
				readCache=readCache+print;
			}
			if (reading&&e.getKeyCode()==KeyEvent.VK_ENTER){
				if (skipLineAfterRead){
					println();
				}
				reading=false;
			}
		}
		public void keyTyped(KeyEvent e){
			if (reading&&!readingNumber){
				String print="";
				if (Character.getName(e.getKeyChar()).equals("LINE FEED (LF)")){
					
					return;
				} else if (Character.getName(e.getKeyChar()).equals("BACKSPACE")||Character.getName(e.getKeyChar()).equals("DELETE")){
					if (readCache.length()>0){
						readCache=readCache.substring(0, readCache.length()-1);
						field.setText(field.getText().substring(0, field.getText().length()-1));
					}
					return;
				} else if (!(e.getKeyCode()==KeyEvent.VK_CONTROL||e.getKeyCode()==KeyEvent.VK_SHIFT||e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_ENTER)){
					print=String.valueOf(e.getKeyChar());
					if (!e.isShiftDown()){
						print=print.toLowerCase();
					}
				}
				print(print);
				readCache=readCache+print;
			}
		}
		public void backSpace(){
			field.setText(field.getText().substring(0, field.getText().length()-1));
		}
		public String getLine(int line){
			Scanner s=new Scanner(field.getText());
			for (int x=0;x<line-1;x++){
				try{
					s.nextLine();
				} catch (NoSuchElementException e) {
					println("\n◄════ERROR════►");
					println("Line "+line+" not found!");
					println("◄════════════►");
					return null;
				}
			}try{
				return s.nextLine();
			} catch (NoSuchElementException e) {
				println("\n◄════ERROR════►");
				println("Line "+line+" not found!");
				println("◄════════════►");
				return null;
			}
			
		}
		public String getLine(long line){
			Scanner s=new Scanner(field.getText());
			for (long x=0;x<line-1;x++){
				try{
					s.nextLine();
				} catch (NoSuchElementException e) {
					println("\n◄════ERROR════►");
					println("Line "+line+" not found!");
					println("◄════════════►");
					return null;
				}
			}try{
				return s.nextLine();
			} catch (NoSuchElementException e) {
				println("\n◄════ERROR════►");
				println("Line "+line+" not found!");
				println("◄════════════►");
				return null;
			}
			
		}
		public long getNoOfLines(){
			Scanner s=new Scanner(field.getText());
			long line=0;
			while(true){
				try{
					s.nextLine();
				} catch (NoSuchElementException e) {
					return line;
				}
				line++;
			}
		}
		public void print(String s){
			field.append(s);
		}
		public void println(String s){
			field.append(s+"\n");
		}
		public void println(){
			field.append("\n");
		}
		public String readLine(){
			System.out.println("Awaiting user input");
			skipLineAfterRead=true;
			readCache="";
			reading=true;
			while (reading){
				doNothing();
			}
			System.out.println("User input recieved");
			return readCache;
		}
		public String read(){
			reading=true;
			readCache="";
			skipLineAfterRead=false;
			System.out.println("Awaiting user input");
			while (reading){
				if (!reading){
					doNothing();
				}
			}
			System.out.println("User input recieved");
			return readCache;
		}
		public int readInt(){
			readingNumber=true;
			readCache="";
			skipLineAfterRead=false;
			isInt=true;
			System.out.println("Awaiting user input of integer type");
			while (readingNumber){
				doNothing();
			}
			System.out.println("User input recieved");
			if (readCache.equals("")){
				return 0;
			}
			int val;
			try {
				val=Integer.parseInt(readCache);
			}catch (NumberFormatException e){
				if (Long.parseLong(readCache)>2147483647){
					val=2147483647;
					println("\n◄════ERROR════►");
					println("Number entered is too large!");
					println("◄════════════►");
				} else {
					val=-2147483648;
					println("\n◄════ERROR════►");
					println("Number entered is too small!");
					println("◄════════════►");
				}
			}
			return val;
		}
		public int readLineInt(){
			readingNumber=true;
			readCache="";
			skipLineAfterRead=true;
			isInt=true;
			System.out.println("Awaiting user input of integer type");
			while (readingNumber){
				doNothing();
			}
			System.out.println("User input recieved");
			if (readCache.equals("")){
				return 0;
			}
			int val;
			try {
				val=Integer.parseInt(readCache);
			}catch (NumberFormatException e){
				if (Long.parseLong(readCache)>2147483647){
					val=2147483647;
					println("◄═══ERROR═══►");
					println("Number entered is too large!");
					println("◄═══════════►");
				} else {
					val=-2147483648;
					println("◄═══ERROR═══►");
					println("Number entered is too small!");
					println("◄═══════════►");
				}
			}
			return val;
		}
		public double readDouble(){
			readingNumber=true;
			readCache="";
			skipLineAfterRead=false;
			isInt=false;
			decimaled=false;
			System.out.println("Awaiting user input of double type");
			while (readingNumber){
				doNothing();
			}
			System.out.println("User input recieved");
			if (readCache.equals("")){
				return 0;
			}
			return Double.parseDouble(readCache);
		}
		public double readLineDouble(){
			readingNumber=true;
			readCache="";
			skipLineAfterRead=true;
			isInt=false;
			decimaled=false;
			System.out.println("Awaiting user input of double type");
			while (readingNumber){
				doNothing();
			}
			System.out.println("User input recieved");
			if (readCache.equals("")){
				return 0;
			}
			return Double.parseDouble(readCache);
		}
		/**
		 * filler for infinite while loop
		 */
		private void doNothing(){
			if (!reading){
				System.out.print("");
			}
		}
	}
}
