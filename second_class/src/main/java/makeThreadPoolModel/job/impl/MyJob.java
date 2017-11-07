package makeThreadPoolModel.job.impl;

import makeThreadPoolModel.job.JobInterface;
import threadUtil.ThreadUtils;

/**
 * A simple model of job
 * @author Yuanguohao
 *
 * @date Nov 7, 2017 7:15:05 PM
 *
 */
public class MyJob implements JobInterface{
	
	private String jobName;
	

	public MyJob(String jobName) {
		super();
		this.jobName = jobName;
	}



	@Override
	public void run() {
		System.out.println(Thread.currentThread().
				getName()+" is excuting job "+jobName);
		ThreadUtils.sleepMillSeconds(100);
	}

}
