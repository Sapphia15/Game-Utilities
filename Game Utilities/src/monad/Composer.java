package monad;

public class Composer <T,S>{
	T value;
	
	public Composer(T value) {
		this.value=value;
	}
	
	/**Composes map with the specified composer
	 * 
	 * @param m
	 * @param map
	 * @return
	 */
	public S r(S m,Map<S, S> map) {
		
		return map.map(m);
		
	}
	
	
}
