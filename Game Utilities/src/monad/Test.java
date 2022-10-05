package monad;

import java.util.Hashtable;

import gameutil.ConcurrentBidirectionalMap;
import gameutil.Sync;
import gameutil.text.Console;

public class Test {
	public static void main(String[] args) {/*
		WriteMap<Write<Integer>> add=new WriteMap<Write<Integer>>("Add 1") {

			@Override
			public Write<Integer> map(Write<Integer> input) {
				// TODO Auto-generated method stub
				return new Write<Integer>(input.v()+1,this.name);
			}
			
		};
		
		WriteMap<Write<Integer>> multiplyByThree=new Multiply(3);
		
		Write<Integer> num=new Write<>(0);
		num=num.r(multiplyByThree);
		Console.s.println(num.getLogs());
		num=num.r(add);
		Console.s.println(num.getLogs());
		num=num.r(multiplyByThree);
		Console.s.println(num.getLogs());
		num=num.r(add);
		Console.s.println(num.getLogs());
		num=num.r(add);
		Console.s.println(num.getLogs());
		num=num.r(add);
		Console.s.println(num.getLogs());
		num=num.r(add);
		Console.s.println(num.getLogs());
		num=num.r(add).r(add).r(new Multiply(7));
		Console.s.println(num.getLogs());
		Console.s.println(num.v());
		*/
		/*
		Sync<String> a1=new Sync<String>("2");
		Sync<String> a2=new Sync<String>("4");
		ConcurrentBidirectionalMap<Sync<String>,String> table=new ConcurrentBidirectionalMap<Sync<String>,String>();
		Console.s.println("a1 has value 2");
		Console.s.println("a2 has value 4");
		table.set(a1, "a1");
		table.set(a2, "a2");
		Console.s.println("Get a1:" + table.getSecond(a1));
		Console.s.println("Get a2:" + table.getSecond(a2));
		Console.s.println("Get new Sync with value 2:" +table.getSecond(new Sync<String>("2")));
		a2.set("2");
		//table.set(a2, "a1");
		Console.s.println("Get a1 after setting a2 to value 2:" + table.getSecond(a1));
		Console.s.println("Get a2 after setting a2 to value 2:" + table.getSecond(a2));
		Console.s.println("Get new Sync with value 2 after setting a2 to value 2:" + table.getSecond(new Sync<String>("2")));
		Console.s.println("a1.equals(a2): " + a1.equals(a2));*/
		Map<Integer,String> whatNumber = (i) -> {
			i=i+2;
			return "The number plus 2 is "+i;
		};
		Console.s.println(whatNumber.map(2));
	}
	
	private static class Multiply extends WriteMap<Write<Integer>>{

		int no;
		
		public Multiply(int no) {
			super("Multiply by "+no);
			this.no=no;
		}
		
		@Override
		public Write<Integer> map(Write<Integer> input) {
			// TODO Auto-generated method stub
			return new Write<Integer>(input.v()*no,this.name);
		}
		
	}
}