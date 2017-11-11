package makeWebServerModel;

/**
 * A simple model of thread pool
 * @author Yuanguohao
 *
 * @date Nov 8, 2017 7:02:02 PM
 *
 * @param <StatisticJob>
 */
public interface ThreadPool {
	
	/**
	 * Execute a job,subclass may implement this by self
	 * ways,this job may be put in job queue,not executing
	 * at once
	 * @param job
	 */
	void execute(Job job);
	
	/**
	 * Get how many jobs waiting for executing
	 * @return The number of waiting jobs
	 */
	int getWaittingJobSize();
	
	/**
	 * Add some worker threads,but we make
	 * sure the total amounts of worker will don't
	 * more than the maximal worker threads which 
	 * the pool setting
	 * @param num The extra amounts the pool will 
	 * add worker threads
	 */
	void addWorkerThread(int num);
	
	/**
	 * Remove some worker threads from current thread pool.
	 * But we guarantee the threads amounts don't less
	 * than the minimal value which the pool setting.
	 * @param num The amounts the pool will remove worker
	 * thread from
	 */
	void removeWorkerThread(int num);
	
	/**
	 * Stop whole thread pool
	 */
	void shutdown();
}
