package makeThreadPoolModel.pool;

import makeThreadPoolModel.job.JobInterface;

public interface ThreadPool {

	/**
	 * Execute this job,subclass will implement this
	 * method my self ways,the job may be executed at once,
	 * it may in executing queue
	 * @param job
	 */
	void execute(JobInterface job);
	
	/**
	 * Close thread pool and let all worker thread
	 * stop before
	 */
	void shutdown();
	
	/**
	 * Add some worker threads into thread pool,
	 * but the total amounts of worker
	 * thread don't more than the max quantity
	 * witch the subclass setting
	 * @param num
	 */
	void addWorkers(int num);
	
	/**
	 * Remove workers from thread pool,but total amounts of
	 * threads in pool don't less than the minimal quantity 
	 * witch the subclass setting 
	 * @param num
	 */
	void removeWorkers(int num);
	
	/**
	 * Get current job queue size
	 */
	int getJobSize();
}
