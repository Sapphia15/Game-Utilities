package monad;

public class OptionE<T,S> extends Monad<T,S>{

	public OptionE(T value) {
		super(value);
		
	}
	
	public S r(S m,ClosedMap<S> map) {
		if (m == null) {
			return m;
		} else {
			return map.map(m);
		}
	}

}
