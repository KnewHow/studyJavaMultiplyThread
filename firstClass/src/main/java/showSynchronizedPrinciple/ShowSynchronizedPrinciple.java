package showSynchronizedPrinciple;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 31, 2017 8:03:54 PM
 *
 */
public class ShowSynchronizedPrinciple {
	
	public static void main(String[] args) {
		synchronized (ShowSynchronizedPrinciple.class) {
			m();
		}
	}
	/**
	 * synchronized method
	 */
	public static synchronized void m() {
		
	}
}
