package makeThreadPoolModel.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import makeThreadPoolModel.job.JobInterface;
import makeThreadPoolModel.worker.Worker;
import makeThreadPoolModel.worker.impl.MyWorker;

/**
 * A simple model of thread pool
 * 
 * @author Yuanguohao
 *
 * @date Nov 7, 2017 7:42:40 PM
 *
 */
public class DefaultThreadPool implements ThreadPool {

	/**
	 * The maximal number of worker threads
	 */
	private static final Integer MAX_WORKER_NUMBERS = 10000;

	/**
	 * The default number of worker threads
	 */
	private static final Integer DEFAULT_WORKER_NUMBERS = 5;

	/**
	 * The minimal number of worker threads
	 */
	private static final Integer MIN_WORKER_NUMBERS = 1;

	/**
	 * The queue of jobs
	 */
	private final LinkedList<JobInterface> jobs = new LinkedList<>();

	/**
	 * The worker threads list
	 */
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

	private Integer workerNum = DEFAULT_WORKER_NUMBERS;

	/**
	 * The worker thread number
	 */
	private AtomicLong threadNum = new AtomicLong();

	public DefaultThreadPool() {
		super();
		initializeWorkers(DEFAULT_WORKER_NUMBERS);
	}

	public DefaultThreadPool(int num) {
		super();
		workerNum = num > MAX_WORKER_NUMBERS ? 
				MAX_WORKER_NUMBERS : num
				< MIN_WORKER_NUMBERS ?
				MIN_WORKER_NUMBERS : num;
		initializeWorkers(workerNum);
	}

	@Override
	public void execute(JobInterface job) {
		if (job != null) {
			synchronized (jobs) {
				jobs.addLast(job);
				/**
				 * notify worker to executed
				 */
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		for (Worker worker : workers) {
			worker.shutdown();
		}
	}

	@Override
	public void addWorkers(int num) {
		if(num+workerNum>MAX_WORKER_NUMBERS) {
			num = MAX_WORKER_NUMBERS-workerNum;
		}
		initializeWorkers(num);
		this.workerNum +=num;
	}

	@Override
	public void removeWorkers(int num) {
		if(num>this.workerNum) {
			throw new IllegalArgumentException("The current worker number is"
					+ workerNum+"the parameter is "+num+" more than"
							+ "!!");
		}else {
			int count = 0;
			while(count<num) {
				Worker worker = workers.get(count);
				if(workers.remove(worker)) {
					worker.shutdown();
					count++;
				}
			}
		}
		
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}

	/**
	 * initialize the thread pool
	 * 
	 * @param number
	 *            The amouts of workers
	 */
	private void initializeWorkers(int num) {
		for (int i = 0; i < num; i++) {
			Worker worker = new MyWorker(jobs);
			workers.add(worker);
			Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
			thread.start();
		}
	}

}
