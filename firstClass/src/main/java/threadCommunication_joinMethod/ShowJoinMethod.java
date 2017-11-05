package threadCommunication_joinMethod;

import threadUtil.ThreadUtils;

/**
 * In this case,we will use
 * join method letting two thread communication
 * 
 * Node:Thread who execute {@linkplain Thread.join} method
 * will wait until target thread executing finished. 
 * @author Yuanguohao
 *
 * @date Nov 1, 2017 7:46:06 PM
 *
 */
public class ShowJoinMethod {
	
	private static Thread previousThread=Thread.currentThread();
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			Thread thread = new Thread(new DemoThread(previousThread),
					String.valueOf(i));
			thread.start();
			previousThread = thread;
		}
		ThreadUtils.sleepSeconds(5);
		System.out.println(Thread.currentThread().getName()+" terminate.");
	}
	
	
	static class DemoThread implements Runnable{

		private Thread anteriorThread;

		public DemoThread(Thread anteriorThread) {
			super();
			this.anteriorThread = anteriorThread;
		}

		@Override
		public void run() {
			try {
				this.anteriorThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" terminate.");
		}
		
	}
}
