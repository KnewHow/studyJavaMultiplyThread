package makeThreadPoolModel.test;

import makeThreadPoolModel.job.JobInterface;
import makeThreadPoolModel.job.impl.MyJob;
import makeThreadPoolModel.pool.DefaultThreadPool;
import threadUtil.ThreadUtils;

/**
 * 
 * @author Yuanguohao
 *
 * @date Nov 7, 2017 8:20:11 PM
 *
 */
public class TestDemo {
	private static final int JOB_AMOUTS = 500;
	private static DefaultThreadPool defaultThreadPool = new DefaultThreadPool(100);
	public static void main(String[] args) {
		ThreadUtils.sleepSeconds(6);
		for(int i=0;i<JOB_AMOUTS;i++) {
			JobInterface job = new MyJob(i+"");
			defaultThreadPool.execute(job);
		}
	}
}
