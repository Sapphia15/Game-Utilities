package gameutil;

public class Sync<T> {
	private T syncedObject;
	
	public Sync(T initialValue){
		syncedObject=initialValue;
	}
	
	public T get() {
		return syncedObject;
		
	}
	
	public void set(T value) {
		syncedObject=value;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Sync<?>) {
			return equals((Sync<T>)o);
		}
		return super.equals(o);
	}
	
	public boolean equals(Sync<T> s) {
		return s.get().equals(get());
	}
}
