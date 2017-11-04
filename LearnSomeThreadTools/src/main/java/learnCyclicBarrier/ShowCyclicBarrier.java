package learnCyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * In this case,we will show let some thread run then wait for all thread finish
 * first step. Then we will execute last thread second step
 * 
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 8:13:35 PM
 *
 */
public class ShowCyclicBarrier {

	private static final Integer THREAD_COUNT = 10;
	private static final List<Thread> THREAD_LIST = new ArrayList<Thread>();

	static CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT, new FirstThread());

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread thread = new Thread(new DemoThread(cyclicBarrier), "thread-" + i);
			THREAD_LIST.add(thread);
		}
		for (Thread t : THREAD_LIST) {
			t.start();
		}
	}

}

/**
 * When all thread block then wake up, first execute this thread
 * 
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 8:51:21 PM
 *
 */
class FirstThread implements Runnable {

	@Override
	public void run() {
		System.out.println("first runninh");
	}

}

/**
 * original thread class
 * 
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 8:50:22 PM
 *
 */
class DemoThread implements Runnable {

	private CyclicBarrier cyclicBarrier;

	public DemoThread(CyclicBarrier cyclicBarrier) {
		super();
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " @ first page");
		try {
			this.cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " @ second page");
	}
}
