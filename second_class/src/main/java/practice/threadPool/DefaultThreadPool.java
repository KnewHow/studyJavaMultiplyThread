package practice.threadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 8, 2017 7:24:24 PM
 *
 * @param <StatisticJob>
 */
public class DefaultThreadPool implements ThreadPool {

	/**
	 * The maximal thread worker number the thread pool setting
	 */
	private static final Integer MAX_WORKER_THREAD_NUMBER = 100;
	/**
	 * The default thread worker number the thread pool setting
	 */
	private static final Integer DEFAULT_WORKER_THREAD_NUMBER = 5;
	/**
	 * The minimal thread worker number the thread pool setting
	 */
	private static final Integer MIN_WORKER_THREAD_NUMBER = 1;

	/**
	 * The number of current pool worker threads,it can be added or reduced by call
	 * {@linkplain #addWorkerThread(int)} or {@linkplain #removeWorkerThread(int)}
	 */
	private Integer threadNumber = 0;

	/**
	 * A atomic variable to get thread id when creating a new worker thread
	 */
	private AtomicInteger threadsId = new AtomicInteger();

	/**
	 * The queue of job
	 */
	private volatile LinkedList<Job> jobs = new LinkedList<>();

	/**
	 * A list to worker threads
	 */
	private List<Work> workers = Collections.synchronizedList(new ArrayList<Work>());

	public DefaultThreadPool() {
		super();
		init(DEFAULT_WORKER_THREAD_NUMBER);
	}

	public DefaultThreadPool(int num) {
		int n = num > MAX_WORKER_THREAD_NUMBER ? MAX_WORKER_THREAD_NUMBER
				: num < MIN_WORKER_THREAD_NUMBER ? MIN_WORKER_THREAD_NUMBER : num;
		init(n);
	}

	@Override
	public void execute(Job job) {
		if (job != null) {
			synchronized (this.jobs) {
				this.jobs.addLast(job);
				this.jobs.notify();
			}
		}
	}

	@Override
	public int getWaittingJobSize() {
		return this.jobs.size();
	}

	@Override
	public void addWorkerThread(int num) {
		synchronized (this.jobs) {
			if (num + this.threadNumber > MAX_WORKER_THREAD_NUMBER) {
				num = MAX_WORKER_THREAD_NUMBER - this.threadNumber;
			}
			init(num);
		}
	}

	@Override
	public void removeWorkerThread(int num) {
		synchronized (this.jobs) {
			if (num > this.threadNumber) {
				num = this.threadNumber - MIN_WORKER_THREAD_NUMBER;
			}

			for (int i = 0; i < num; i++) {
				Work work = this.workers.get(i);
				if (this.workers.remove(work)) {
					work.shutdown();
				}
			}
			this.threadNumber -= num;
		}
	}

	private void init(int num) {
		for (int i = 0; i < num; i++) {
			Work work = new Work(this.jobs);
			this.workers.add(work);
			Thread thread = new Thread(work, "Thread-writer" + this.threadsId.incrementAndGet());
			thread.start();
		}
		this.threadNumber += num;
	}

	@Override
	public void shutdown() {
		for (Work worker : workers) {
			worker.shutdown();
		}
	}

}
