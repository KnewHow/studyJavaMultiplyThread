package showthreadPriority;

import java.util.ArrayList;
import java.util.List;

public class ShowthreadPriority {

	private static volatile boolean noStart=true;
	
	private static volatile boolean noEnd = true;
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Job> jobList = new ArrayList<>();
		for(int i=0;i<10;i++) {
			int priority = i<5?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobList.add(job);
			Thread thread = new Thread(job);
			thread.setPriority(priority);
			thread.start();
		}
		noStart=false;
		Thread.sleep(10000);
		noEnd =false;
		for(Job job:jobList) {
			System.out.println("priority="+job.priority+" counter="
					+ job.jobCounter);
		}
	}
	
	/* the result of run we find the distance is
	 * is too smaller between minimum priority thread
	 * and maximum priority in Java configuration
	 	priority=1 counter=6689474
		priority=1 counter=4572829
		priority=1 counter=6591028
		priority=1 counter=5853306
		priority=1 counter=5447664
		priority=10 counter=5603586
		priority=10 counter=6132408
		priority=10 counter=5752615
		priority=10 counter=6324212
		priority=10 counter=7673810 
	 */
	
	
	/**
	* The job will to act a action with multiply instance
	* @author Yuanguohao
	*
	* @date Oct 30, 2017 7:24:11 PM
	*
	*/
	static class Job implements Runnable{

		/**
		 * The priority of current thread
		 */
		@SuppressWarnings("unused")
		private Integer priority=0;
		
		private Long jobCounter=0L;
		
		public Job(Integer priority) {
			this.priority = priority;
		}


		@Override
		public void run() {
			while(noStart) {
				//暂停该线程，去执行其他线程
				Thread.yield();
			}
			while(noEnd) {
				Thread.yield();
				this.jobCounter++;
			}
		}
	}
	
}
