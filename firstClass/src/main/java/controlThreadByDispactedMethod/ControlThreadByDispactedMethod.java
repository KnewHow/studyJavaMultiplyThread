package controlThreadByDispactedMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 *
 * @date Oct 31, 2017 7:31:10 PM
 *
 */
public class ControlThreadByDispactedMethod {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Thread thread = new Thread(new PrintThread(),"print Thread");
		thread.setDaemon(true);
		thread.start();
		
		ThreadUtils.sleepSeconds(3);
		thread.suspend();
		ThreadUtils.sleepSeconds(3);
		thread.resume();
		ThreadUtils.sleepSeconds(3);
		thread.stop();
	}
	
	
	/**
	 * 
	 * The thread will be used to sleep and print executing time
	 * @author Yuanguohao
	 *
	 * @date Oct 31, 2017 7:31:40 PM
	 *
	 */
	static class PrintThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				System.out.println(Thread.currentThread().getName()+"run at"
						+ dateFormat.format(new Date()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
