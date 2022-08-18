package gameutil;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBidirectionalMap<T,O> {
	ConcurrentHashMap<T,O> to=new ConcurrentHashMap<>();
	ConcurrentHashMap<O,T> ot=new ConcurrentHashMap<>();
	
	public void set(T t,O o) {
		if(containsFirst(t)) {
			
			//remove the old o associated with t in the ot map
			ot.remove(getSecond(t),t);
			
		}
		if (containsSecond(o)) {
			
			//remove the old t associated with o in the to map
			to.remove(getFirst(o),o);
			
		} else {
			//set or overwrite the associations between o and t
			to.put(t, o);
			ot.put(o, t);
		}
	}
	
	public boolean containsFirst(T t) {
		return to.containsKey(t);
	}
	
	public boolean containsSecond(O o) {
		return to.contains(o);
	}
	
	public O getSecond(T key) {
		return to.get(key);
	}
	
	public T getFirst(O key) {
		return ot.get(key);
	}
	
	public Enumeration<T> getFirstKeys(){
		return to.keys();
	}
	
	public Enumeration<O> getSecondKeys(){
		return ot.keys();
	}
	
	

}
