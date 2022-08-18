package monad;

public class Write<T> extends WriteE<T,Write<T>> {

	protected Write(T value) {
		super(value);
	}
	
	protected Write(T value,String s) {
		super(value,s);
	}

}
