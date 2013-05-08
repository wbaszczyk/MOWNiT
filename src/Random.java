
public class Random {
	
	private static java.util.Random rand = null;
	
	private Random() { }
	
	public static synchronized int nextInt(int n) {
		
		if(rand == null)
			rand = new java.util.Random();
		
		return rand.nextInt(n);
	}
}
