package showThreadInterrupted;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 30, 2017 8:30:59 PM
 *
 */
public class ShowThreadInterrupted {
	
	public static void main(String[] args) throws InterruptedException {
		Thread busyThread = new Thread(new RunningThread());
		busyThread.setDaemon(true);
		Thread sleepThread = new Thread(new SleepThread());
		sleepThread.setDaemon(true);
		
		busyThread.start();
		sleepThread.start();
		//Let thread execute enough
		Thread.sleep(10000);
		busyThread.interrupt();
		sleepThread.interrupt();
		System.out.println("Busy Thread is "+busyThread.isInterrupted());
		System.out.println("Sleep Thread is "+sleepThread.isInterrupted());
		//Avoid thread exiting suddenly
		Thread.sleep(2000);
	}

	/**
	 * Let the thread sleep all time
	 * @author Yuanguohao
	 *
	 * @date Oct 30, 2017 8:32:59 PM
	 *
	 */
	static class SleepThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Let the thread busy all time
	 * @author Yuanguohao
	 *
	 * @date Oct 30, 2017 8:35:00 PM
	 *
	 */
	static class RunningThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				
			}
		}
		
	}
}
