package finishThreadByRightWays;

import threadUtil.ThreadUtils;

/**
 * @author Yuanguohao
 *
 * @date Oct 31, 2017 7:44:34 PM
 *
 */
public class FinishThreadByRightWays {

	public static void main(String[] args) {
		CounterThread counterThread = new CounterThread();
		Thread thread = new Thread(counterThread, "CounterThread");
		thread.start();
		ThreadUtils.sleepSeconds(1);
		//use interrupt to make the thread stop
		thread.interrupt();
		CounterThread counterThread2 = new CounterThread();
		thread = new Thread(counterThread2, "CounterThread");
		thread.start();
		ThreadUtils.sleepSeconds(1);
		//use volatile to make thread stop
		counterThread2.stop();
	}

	/**
	 * The thread will be stoped will interrupt or boolean flag witch will make it
	 * has changes to release resource
	 * 
	 * @author Yuanguohao
	 *
	 * @date Oct 31, 2017 7:45:12 PM
	 *
	 */
	private static class CounterThread implements Runnable {
		long counter = 0L;
		volatile boolean isStop = false;

		@Override
		public void run() {
			while (!isStop && !Thread.currentThread().isInterrupted()) {
				counter++;
			}
			System.out.println("counter=" + counter);
		}

		/**
		 * 
		 */
		public void stop() {
			this.isStop = true;
		}
	}

}
