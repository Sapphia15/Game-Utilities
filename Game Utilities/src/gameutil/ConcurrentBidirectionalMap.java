package gameutil;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

public class ConcurrentBidirectionalMap<T,O> {
	ConcurrentHashMap<T,O> to=new ConcurrentHashMap<>();
	ConcurrentHashMap<O,T> ot=new ConcurrentHashMap<>();
	
	public ConcurrentBidirectionalMap() {
		
	}
	
	
	
	public ConcurrentBidirectionalMap(int initialCapacity) {
		to=new ConcurrentHashMap<>(initialCapacity);
		ot=new ConcurrentHashMap<>(initialCapacity);
	}
	
	public ConcurrentBidirectionalMap(ConcurrentHashMap<T,O> mapping) {
		to=mapping;
		ot=new ConcurrentHashMap<>(to.keySet().size());
		for (T key:to.keySet()) {
			ot.put(to.get(key), key);
		}
	}
	
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
	
	public KeySetView<T, O> getFirstKeys(){
		return to.keySet();
	}
	
	public KeySetView<O, T> getSecondKeys(){
		return ot.keySet();
	}
	
	

}
