package learnSemaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 4, 2017 9:34:23 AM
 *
 */
public class ShowSemaphore {

	/**
	 * The max amounts of thread we starting
	 */
	private static final  Integer MAX_THREAD_AMOUT = 30;
	
	/**
	 * The max concurrency we allow in same time
	 */
	private static final Integer MAX_CONCURRENCY_COUNT_AMOUT = 10;
	
	
	
	/**
	 * Make ten thread concurrency at same time
	 */
	private static final Semaphore SEMAPHORE = new Semaphore(MAX_CONCURRENCY_COUNT_AMOUT,true);
	
	public static void main(String[] args) {
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i=0;i<MAX_THREAD_AMOUT;i++) {
			Thread thread = new Thread(new showThread(SEMAPHORE));
			threadList.add(thread);
		}
		
		for(Thread t:threadList) {
			t.start();
		}
	}
	static class showThread implements Runnable{
		
		
		
		private Semaphore semaphore;
		public showThread(Semaphore semaphore) {
			super();
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				this.semaphore.acquire();
				System.out.println(Thread.currentThread().getName()+" @ acquire semaphore");
				ThreadUtils.sleepSeconds(2);
				System.out.println(Thread.currentThread().getName()+" @ release semaphore");
				this.semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
