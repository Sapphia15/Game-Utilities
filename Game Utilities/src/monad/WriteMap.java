package monad;

public abstract class WriteMap<T> implements ClosedMap<T>{
	
	String name;
	
	public WriteMap(String name) {
		this.name=name;
	}
}
