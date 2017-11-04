package showWaitCondition;

import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 1, 2017 8:07:49 PM
 *
 */
public class ShowThreadLocal {
	
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		};
	};
	
	/**
	 * Record current mill seconds at current thread
	 */
	public static final void begin() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}
	
	/**
	 * calculate executing time.The end time is
	 * current mill seconds,the start time will 
	 * get from {@linkplain ThreadLocal} witch
	 * set when call {@linkplain ShowThreadLocal#begin()}
	 * method
	 * 
	 * @return The mill second from call 
	 * {@linkplain ShowThreadLocal#begin()} to 
	 * call {@linkplain ShowThreadLocal#end()}
	 */
	public static final Long end() {
		return System.currentTimeMillis()-TIME_THREADLOCAL.get();
	}
	
	public static void main(String[] args) {
		begin();
		ThreadUtils.sleepSeconds(1);
		System.out.println("Cost:"+end()+" mills");
	}
}
