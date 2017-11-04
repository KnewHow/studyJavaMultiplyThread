package learnSemaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	 * Constructor 30 thread
	 */
	private static ExecutorService threadPool = 
			Executors.newFixedThreadPool(MAX_THREAD_AMOUT);
	
	/**
	 * Make ten thread concurrency at same time
	 */
	private static Semaphore semaphore = new Semaphore(MAX_CONCURRENCY_COUNT_AMOUT,true);
	
	public static void main(String[] args) {
		for(int i=0;i<MAX_CONCURRENCY_COUNT_AMOUT;i++) {
			threadPool.execute(new Thread(new showThread(semaphore)));
		}
		threadPool.shutdown();
//		ThreadUtils.sleepSeconds(20);
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
				System.out.println(Thread.currentThread().getName()+" @ executed");
				ThreadUtils.sleepSeconds(2);
				this.semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
