package makeWebServerModel;

import java.util.LinkedList;

public class Work implements Runnable {

	private volatile boolean isRunning = true;

	private LinkedList<Job> jobs;

	public Work(LinkedList<Job> jobs) {
		super();
		this.jobs = jobs;
	}

	@Override
	public void run() {
		while (isRunning) {
			Job job = null;
			synchronized (this.jobs) {
				while (this.jobs.isEmpty()) {
					try {
						jobs.wait();
					} catch (InterruptedException e) {
						Thread.interrupted();
						return;
					}
				}
				job = jobs.removeFirst();
			}
			if (job != null) {
				try {
					job.run();
					System.out.println(Thread.currentThread().getName()+"executed");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println(Thread.currentThread().getName()+"job is null");
			}
		}
	}

	/**
	 * shutdown current thread
	 */
	public void shutdown() {
		this.isRunning = false;
	}
}
