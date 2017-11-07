package makeThreadPoolModel.worker;

import makeThreadPoolModel.job.JobInterface;

/**
 * This simple model of worker witch will
 * to do {@linkplain JobInterface}
 * @author Yuanguohao
 *
 * @date Nov 7, 2017 7:17:11 PM
 *
 */
public interface Worker extends Runnable{
	
	/**
	 * If you want this woker thread stop,please
	 * call this method. subclass will implement it
	 * by self ways
	 */
	public void shutdown();
}
