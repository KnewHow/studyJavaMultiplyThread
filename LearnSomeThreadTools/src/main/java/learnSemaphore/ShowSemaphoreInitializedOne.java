package learnSemaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 * 
 *         In this case,we will show initialize semaphore to one witch make it
 *         as a binary lock.
 *
 * @date Nov 5, 2017 7:39:39 PM
 *
 */
public class ShowSemaphoreInitializedOne {
	private static final Semaphore SEMAPHORE = new Semaphore(1, true);

	private static final Integer THREAD_NUMBER = 2;

	public static void main(String[] args) {
		List<Thread> threadList = new ArrayList<Thread>();
		for (int i = 0; i < THREAD_NUMBER; i++) {
			threadList.add(new Thread(new DemoThread(SEMAPHORE)));
		}
		for (Thread t : threadList) {
			t.start();
		}
		ThreadUtils.sleepSeconds(8);
	}

	static class DemoThread implements Runnable {

		private Semaphore semaphore;

		public DemoThread(Semaphore semaphore) {
			super();
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				this.semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " @ acquire semaphore");
			ThreadUtils.sleepSeconds(2);
			System.out.println(Thread.currentThread().getName() + " @ realse semaphore");
			this.semaphore.release();
		}

	}
}
