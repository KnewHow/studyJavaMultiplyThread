package makeThreadPoolModel.worker.impl;

import java.util.LinkedList;

import makeThreadPoolModel.job.JobInterface;
import makeThreadPoolModel.worker.Worker;

/**
 * A simple worker model,it can stop by running flag and outer interrupt
 * 
 * @author Yuanguohao
 *
 * @date Nov 7, 2017 7:23:49 PM
 *
 */
public class MyWorker implements Worker {

	/**
	 * The end flag of this worker thread. When is true,the thread will run.You can
	 * update it to stop this worker stop
	 */
	private volatile boolean isRunning = true;

	/**
	 * The queue of job
	 */
	private LinkedList<JobInterface> jobs;

	public MyWorker(LinkedList<JobInterface> jobs) {
		super();
		this.jobs = jobs;
	}

	@Override
	public void run() {
		while (isRunning) {
			JobInterface job = null;
			synchronized (jobs) {
				while (jobs.isEmpty()) {
					try {
						jobs.wait();
					} catch (InterruptedException e) {
						/*
						 * If there outer interrupt,the worker will stop
						 */
						Thread.currentThread().interrupt();
						return;
					}
				}
				// get job from job queue,then run it.
				job = jobs.removeFirst();
			}
				if (job != null) {
					try {
						job.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
	}

	@Override
	public void shutdown() {
		this.isRunning = false;
	}
}
