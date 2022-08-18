package monad;

public class Option<T> extends OptionE<T,Option<T>> {

	public Option(T value) {
		super(value);
	}

}
