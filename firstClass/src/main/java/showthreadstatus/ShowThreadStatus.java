package showthreadstatus;

/**
 * Show different status with different status in JVM
 * 
 * @author Yuanguohao
 *
 * @date Oct 30, 2017 7:56:15 PM
 *
 */
public class ShowThreadStatus {
	
	public static void main(String[] args) {
		new Thread(new RunningThread(),"runningThread").start();
		new Thread(new WaitingThread(),"waitingThread").start();
		new Thread(new BlockThread(),"blockedThread-1").start();
		new Thread(new BlockThread(),"blockedThread-2").start();
	}

	/**
	 * Let the thread running all time
	 * 
	 * @author Yuanguohao
	 *
	 * @date Oct 30, 2017 7:58:35 PM
	 *
	 */
	static class RunningThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Let the thread is waiting
	 * 
	 * @author Yuanguohao
	 *
	 * @date Oct 30, 2017 8:01:41 PM
	 *
	 */
	static class WaitingThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				synchronized (WaitingThread.class) {
					try {
						WaitingThread.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}
	
	/**
	 * First thread joining will sleep(conditional waited)
	 * other thread will be blocked out
	 * @author Yuanguohao
	 *
	 * @date Oct 30, 2017 8:04:05 PM
	 *
	 */
	static class BlockThread implements Runnable {

		@Override
		public void run() {
			synchronized (BlockThread.class) {
				while (true) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
