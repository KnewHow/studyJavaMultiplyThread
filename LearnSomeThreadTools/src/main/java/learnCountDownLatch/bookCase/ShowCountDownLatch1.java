package learnCountDownLatch.bookCase;

import java.util.concurrent.CountDownLatch;

import threadUtil.ThreadUtils;

/**
 * In this case,we will call {@linkplain CountDownLatch#countDown()} method more
 * time when a thread executing
 * 
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 7:37:45 PM
 *
 */
public class ShowCountDownLatch1 {
	private static final CountDownLatch COUNT_DOWN_LATCH = 
			new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new DemoThread(COUNT_DOWN_LATCH), "demo Thread");
		thread.start();
		COUNT_DOWN_LATCH.await();
		System.out.println(Thread.currentThread().getName());
	}

}

/**
 * A simple thread class
 * 
 * @author Yuanguohao
 *
 * @date Nov 2, 2017 7:40:10 PM
 *
 */
class DemoThread implements Runnable {
	private CountDownLatch countDownLatch;

	public DemoThread(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		this.countDownLatch.countDown();
		System.out.println(Thread.currentThread().getName() + "first time countDown");
		this.countDownLatch.countDown();
		System.out.println(Thread.currentThread().getName() + "second time countDown");
		ThreadUtils.sleepSeconds(2);
		this.countDownLatch.countDown();
		System.out.println(Thread.currentThread().getName() + "third time countDown");
		
	}

}
