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
public class ShowCountDownLatch2 {
	private static final Integer COUNT = 10;
	private static final CountDownLatch COUNT_DOWN_LATCH = 
			new CountDownLatch(COUNT);

	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<COUNT;i++) {
			Thread thread =  new Thread(new DemoThread2(COUNT_DOWN_LATCH),"thread-"+i);
			thread.start();
		}
		COUNT_DOWN_LATCH.await();
		System.out.println(Thread.currentThread().getName()+"@ exected");
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
class DemoThread2 implements Runnable {
	private CountDownLatch countDownLatch;

	public DemoThread2(CountDownLatch countDownLatch) {
		super();
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"@ exected");
		ThreadUtils.sleepSeconds(1);
		this.countDownLatch.countDown();
	}

}
