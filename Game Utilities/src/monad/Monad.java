package monad;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


public class Monad<T,S> implements Serializable{
	
	protected T value;
	
	public Monad(T value){
		this.value=value;
		
	}
	
	public S r(S m,ClosedMap<S> map) {
		
		return map.map(m);
		
	}
	
	public S r(ClosedMap<S> map) {
		return r((S)this,map);
	}
	
	public T v() {
		return value;
	}
}
