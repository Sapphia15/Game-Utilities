package monad;

import java.lang.reflect.InvocationTargetException;


public class Monad<T,S>{
	
	protected T value;
	
	public Monad(T value){
		this.value=value;
		
	}
	
	public S r(S m,ClosedMap<S> map) {
		
		return map.map(m);
		
	}
	
	public S r(ClosedMap<S> map) {
		return map.map((S)this);
	}
	
	public T v() {
		return value;
	}
}
