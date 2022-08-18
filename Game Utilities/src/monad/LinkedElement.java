package monad;

import java.util.ArrayList;
import java.util.Hashtable;

public class LinkedElement <T,V,S> extends Monad<T,S>{

	Option<Hashtable<T,V>> linkedTable;
	
	public LinkedElement(T value) {
		super(value);
	}
	
	public S r(S e,ClosedMap<S> map) {
		
		S out=map.map(e);
		
		linkedTable.r(new ClosedMap<Option<Hashtable<T,V>>>(){

			@Override
			public Option<Hashtable<T, V>> map(Option<Hashtable<T, V>> input) {
				Hashtable<T, V> table=input.value;
				if (!out.equals(e)) {
					if (table.contains(out)) {
						table.remove(out);
					}
				}
				return new Option<Hashtable<T,V>>(table);
			}
			
		});
		return e;
	}
	
	
	
	
	
	

}
