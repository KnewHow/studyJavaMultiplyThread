package learnLockAPI;

import threadUtil.ThreadUtils;

/**
 * When a thread get {@code synchronized} lock then wait
 * another thread status
 * 
 * @author Yuanguohao
 *
 * @date Nov 10, 2017 8:25:51 PM
 *
 */
public class SynchronizedDemo {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DemoThread(),"getLockedThread");
		t1.setDaemon(true);
		t1.start();
		ThreadUtils.sleepSeconds(2);
		Thread t2 = new Thread(new DemoThread(),"no_ogetLockedThread");
		t2.setDaemon(true);
		t2.start();
		try {
			ThreadUtils.sleepSeconds(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class DemoThread implements Runnable {

		@Override
		public void run() {
			synchronized (DemoThread.class) {
				try {
					DemoThread.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
