package monad;

import java.lang.reflect.InvocationTargetException;

public class WriteE<T,S> extends Monad<T,S>{

	private String logs;
	
	protected WriteE(T value) {
		super(value);
		logs="";
	}
	
	protected WriteE(T value,String logs) {
		super(value);
		this.logs=logs;
	}
	
	@Override
	public S r(ClosedMap<S> map) {
		String logs=this.logs;
		S out=map.map((S)this);
		((WriteE)out).logs=logs.concat(" < "+((WriteE)out).logs+" > ");
		return out;
	}
	
	public String getLogs() {
		return logs;
	}

}
