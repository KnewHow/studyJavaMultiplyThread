package showWaitAndNotify;

import java.text.SimpleDateFormat;
import java.util.Date;

import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 31, 2017 8:36:15 PM
 *
 */
public class ShowWaitAndNotify {

	static boolean isWait = true;

	private static final Object LOCK = new Object();
	
	public static void main(String[] args) {
		Thread notifyThread = new Thread(new notifyThread());
		Thread waitThread = new Thread(new WaitThread());
		waitThread.start();
		ThreadUtils.sleepSeconds(1);
		notifyThread.start();
	}

	/**
	 * Waitting class
	 * 
	 * @author Yuanguohao
	 *
	 * @date Oct 31, 2017 8:38:08 PM
	 *
	 */
	static class WaitThread implements Runnable {

		@Override
		public void run() {
			//get lock then wait or executing by condition
			synchronized (LOCK) {
				while (isWait) {
					try {
						System.out.println(Thread.currentThread().getName() + "flag is true.wait@"
								+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
						LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + "flag is false.running @"
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}

	}

	/**
	 * Notify class
	 * 
	 * @author Yuanguohao
	 *
	 * @date Oct 31, 2017 8:44:39 PM
	 *
	 */
	static class notifyThread implements Runnable {

		@Override
		public void run() {
			//get lock then notify all thread
			synchronized (LOCK) {
				System.out.println(Thread.currentThread().getName() + "hold lock notify. @"
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				LOCK.notifyAll();
				isWait=false;
				ThreadUtils.sleepSeconds(5);
			}
			
			//get lock again then sleep
			synchronized (LOCK) {
				System.out.println(Thread.currentThread().getName() + "hold lock agin.sleep @"
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				ThreadUtils.sleepSeconds(5);
			}
			
		}

	}
}
