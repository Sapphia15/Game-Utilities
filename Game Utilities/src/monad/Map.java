package monad;

import java.io.Serializable;

public interface Map <I,O> extends Serializable{
	abstract O map(I input);
}
