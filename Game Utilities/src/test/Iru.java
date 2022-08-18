package test;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JTextArea;

import gameutil.text.Iru.Int;
import gameutil.text.Iru.IruConsole;
import gameutil.text.Iru.IruScreen;
import gameutil.text.Iru.Letter;
import graphics.screen.SPanel;

public class Iru {
	
	
	
	public static void main(String[] unicorns) {
		//IruConsole c=new IruConsole();
		
		Frame f=new Frame();
		SPanel s=new SPanel(f);
		f.setPreferredSize(new Dimension(500,200));
		f.add(s);
		f.pack();
		
		s.closeOnExit();
		IruScreen c=new IruScreen(s);
		
		f.setTitle("Números en "+"ī".toUpperCase()+"rū");
		
		
		
		s.addScreen(c, "iru");
		s.setScreen("iru");
		f.setVisible(true);
		s.setDoubleBuffered(true);
		//s.setTargetFPS(30,1);
		s.start();
		
		c.setAutoScroll(true);
		/*
		c.printLine("0   1   2   3   4   5   6   7   8   9   /1   /2   /3   /4   /5   /6   /7");
		c.printLine("10  11  12  13  14  15  16  17  18  19  1/1  1/2  1/3  1/4  1/5  1/6  1/7");
		c.printLine("20  21  22  23  24  25  26  27  28  29  2/1  2/2  2/3  2/4  2/5  2/6  2/7");
		c.printLine("30  31  32  33  34  35  36  37  38  39  3/1  3/2  3/3  3/4  3/5  3/6  3/7");
		c.printLine("40  41  42  43  44  45  46  47  48  49  4/1  4/2  4/3  4/4  4/5  4/6  4/7");
		c.printLine("50  51  52  53  54  55  56  57  58  59  5/1  5/2  5/3  5/4  5/5  5/6  5/7");
		c.printLine("60  61  62  63  64  65  66  67  68  69  6/1  6/2  6/3  6/4  6/5  6/6  6/7");
		c.printLine("70  71  72  73  74  75  76  77  78  79  7/1  7/2  7/3  7/4  7/5  7/6  7/7");
		c.printLine("80  81  82  83  84  85  86  87  88  89  8/1  8/2  8/3  8/4  8/5  8/6  8/7");
		c.printLine("90  91  92  93  94  95  96  97  98  99  9/1  9/2  9/3  9/4  9/5  9/6  9/7");
		c.printLine("/10  /11  /12  /13  /14  /15  /16  /17  /18  /19  /1/1  /1/2  /1/3  /1/4  /1/5  /1/6  /1/7");
		c.printLine("/20  /21  /22  /23  /24  /25  /26  /27  /28  /29  /2/1  /2/2  /2/3  /2/4  /2/5  /2/6  /2/7");
		c.printLine("/30  /31  /32  /33  /34  /35  /36  /37  /38  /39  /3/1  /3/2  /3/3  /3/4  /3/5  /3/6  /3/7");
		c.printLine("/40  /41  /42  /43  /44  /45  /46  /47  /48  /49  /4/1  /4/2  /4/3  /4/4  /4/5  /4/6  /4/7");
		c.printLine("/50  /51  /52  /53  /54  /55  /56  /57  /58  /59  /5/1  /5/2  /5/3  /5/4  /5/5  /5/6  /5/7");
		c.printLine("/60  /61  /62  /63  /64  /65  /66  /67  /68  /69  /6/1  /6/2  /6/3  /6/4  /6/5  /6/6  /6/7");
		c.printLine("/70  /71  /72  /73  /74  /75  /76  /77  /78  /79  /7/1  /7/2  /7/3  /7/4  /7/5  /7/6  /7/7");
		c.printLine("/100 101 102 103 104 105 106 107 108 109 10/1 10/2 10/3 10/4 10/5 10/6 10/7");*/
		
		c.readLine();
		while (true) {
			c.print(">>");
			Int num1=c.readLineInt();
			Int num2=c.readLineInt();
			Int prod=num1.$X$(num2);
			c.printLine(num1.toStr().cat(" * ").cat(num2.toStr()).cat(" = ").cat(prod.toStr()));
			
		}
	}
	
	
}
